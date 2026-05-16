package learnSB.project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learnSB.project.DTO.Request.LoginRequestDTO;
import learnSB.project.DTO.Request.RegisterRequestDTO;
import learnSB.project.DTO.Response.LoginResponseDTO;
import learnSB.project.Service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MemberController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> Register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }

        boolean isAuth = userService.registerAuth(registerRequestDTO);

        if (isAuth) {
            return ResponseEntity.ok("Register successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ngu");
        }

    }

    @PostMapping("/api/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String token = userService.login(loginRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tên đăng nhập hoặc mật khẩu");
        }
    }

}
