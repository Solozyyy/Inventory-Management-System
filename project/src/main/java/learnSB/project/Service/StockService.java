package learnSB.project.Service;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import learnSB.project.Domain.Material;
import learnSB.project.Domain.StockLedger;
import learnSB.project.Domain.Enum.RefType;
import learnSB.project.Domain.Enum.StockReason;
import learnSB.project.DTO.Request.AdjustStockRequest;
import learnSB.project.Exception.DuplicateLedgerEntryException;
import learnSB.project.Repository.MaterialRepository;
import learnSB.project.Repository.StockLedgerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {

    private final MaterialRepository materialRepository;
    private final StockLedgerRepository stockLedgerRepository;

    /**
     * Điều chỉnh tồn kho thủ công (chỉ dành cho admin).
     * Toàn bộ thao tác trong một transaction.
     * Thứ tự: lock Material → idempotency → ghi ledger → cập nhật quantity.
     */
    @Transactional
    public StockLedger adjustStock(AdjustStockRequest request, String createdBy) {
        // Validate qtyDelta != 0
        if (request.getQtyDelta() == 0) {
            throw new IllegalArgumentException("qtyDelta phải khác 0");
        }

        // Kiểm tra idempotency nếu client cung cấp refId
        if (request.getRefId() != null) {
            if (stockLedgerRepository.existsByRefTypeAndRefId(RefType.ADJUSTMENT, request.getRefId())) {
                throw new DuplicateLedgerEntryException(
                        "Yêu cầu điều chỉnh đã được xử lý (refId=" + request.getRefId() + ")");
            }
        }

        // Lock Material (optimistic lock tự động qua @Version khi save)
        Material material = materialRepository.findById(request.getMaterialId().intValue());
        if (material == null) {
            throw new IllegalArgumentException("Không tìm thấy vật tư với id=" + request.getMaterialId());
        }

        // Ghi dòng ledger
        StockLedger ledger = new StockLedger();
        ledger.setMaterial(material);
        ledger.setQtyDelta(request.getQtyDelta());
        ledger.setReason(StockReason.ADJUSTMENT);
        ledger.setRefType(RefType.ADJUSTMENT);
        ledger.setRefId(request.getRefId() != null ? request.getRefId() : 0);
        ledger.setCreatedBy(createdBy);
        stockLedgerRepository.save(ledger);

        // Cập nhật quantity
        material.setQuantity(material.getQuantity() + request.getQtyDelta());
        try {
            materialRepository.save(material);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new IllegalStateException("Bản ghi vật tư đã thay đổi bởi người dùng khác, vui lòng thử lại.");
        }

        return ledger;
    }
}
