package learnSB.project.Domain;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "saleinvoice")
public class SaleInvoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private int numberOfMaterials;
    private LocalDate paymentDate;

}
