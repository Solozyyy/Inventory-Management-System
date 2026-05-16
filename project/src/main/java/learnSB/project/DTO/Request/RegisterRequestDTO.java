package learnSB.project.DTO.Request;

import learnSB.project.Service.validator.RegisterChecked;
import learnSB.project.Service.validator.StrongPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterChecked
public class RegisterRequestDTO {
    private String name, role, userName;

    @StrongPassword(message = "password nhu con cac")
    private String password;
    private String confirmPassword;
}
