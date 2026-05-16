package learnSB.project.Domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import learnSB.project.Domain.Enum.RefType;
import learnSB.project.Domain.Enum.StockReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stockledger")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class StockLedger {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    private int qtyDelta;
    private int refId;
    private String createdBy;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private StockReason reason;
    @Enumerated(EnumType.STRING)
    private RefType refType;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
