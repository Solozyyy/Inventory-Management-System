package learnSB.project.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class EmployeeMaterialResponseDTO {
    private int id;
    private String name;
    private String type;
    private int quantity;
}
