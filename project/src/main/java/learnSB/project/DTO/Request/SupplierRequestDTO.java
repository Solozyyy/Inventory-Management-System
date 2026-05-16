package learnSB.project.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class SupplierRequestDTO {
    private String name;
    private String contact;
    private String address;
}
