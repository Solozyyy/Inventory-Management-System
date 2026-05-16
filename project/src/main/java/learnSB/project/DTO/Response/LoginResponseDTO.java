package learnSB.project.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
  private String token;
  private String type = "Bearer";

  public LoginResponseDTO(String token) {
    this.token = token;
  }
}
