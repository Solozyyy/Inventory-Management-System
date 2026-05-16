package learnSB.project.Repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learnSB.project.Domain.Enum.RefType;
import learnSB.project.Domain.StockLedger;

@Repository
public interface StockLedgerRepository extends JpaRepository<StockLedger, Integer> {

    // Lấy lịch sử ledger theo materialId (phân trang, sắp xếp mới nhất)
    Page<StockLedger> findByMaterialIdOrderByCreatedAtDesc(Integer materialId, Pageable pageable);

    // Lọc theo khoảng thời gian
    Page<StockLedger> findByMaterialIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            Integer materialId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable);

    // Check idempotency theo refType + refId
    boolean existsByRefTypeAndRefId(RefType refType, Integer refId);
}
