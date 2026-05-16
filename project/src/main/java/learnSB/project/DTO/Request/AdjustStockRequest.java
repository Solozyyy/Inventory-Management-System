package learnSB.project.DTO.Request;

import jakarta.validation.constraints.NotNull;
import learnSB.project.Domain.Enum.StockReason;

public class AdjustStockRequest {

    @NotNull(message = "materialId không được để trống")
    private Integer materialId;

    @NotNull(message = "qtyDelta không được để trống")
    private Integer qtyDelta;

    @NotNull(message = "reason không được để trống")
    private StockReason reason;

    // Tuỳ chọn: cung cấp để bật kiểm tra idempotency
    private Integer refId;

    private String note;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getQtyDelta() {
        return qtyDelta;
    }

    public void setQtyDelta(Integer qtyDelta) {
        this.qtyDelta = qtyDelta;
    }

    public StockReason getReason() {
        return reason;
    }

    public void setReason(StockReason reason) {
        this.reason = reason;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
