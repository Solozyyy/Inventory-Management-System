package learnSB.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import learnSB.project.DTO.Request.LoginRequestDTO;
import learnSB.project.DTO.Request.RegisterRequestDTO;
import learnSB.project.DTO.Response.LoginResponseDTO;
import learnSB.project.Domain.Member;
import learnSB.project.Repository.UserRepository;
import learnSB.project.Security.CustomUserDetails;
import learnSB.project.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

// required chi khoi tao nhung field co access final
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public boolean registerAuth(RegisterRequestDTO registerRequestDTO) {
        Member m = new Member();
        if (registerRequestDTO.getName() == null || registerRequestDTO.getPassword() == null ||
                registerRequestDTO.getRole() == null || registerRequestDTO.getUserName() == null)
            return false;
        else {
            m.setName(registerRequestDTO.getName());
            m.setRole(registerRequestDTO.getRole());
            String hashedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
            m.setPassword(hashedPassword);
        }

        m.setUserName(registerRequestDTO.getUserName());
        saveMember(m);
        return true;

    }

    public String login(LoginRequestDTO loginRequestDTO) {
        // Xác thực thông tin người dùng
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUserName(),
                        loginRequestDTO.getPassword()));

        // Lưu vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Lấy thông tin user details để tạo token
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Trả về token đã tạo
        return jwtTokenProvider.generateToken(userDetails);
    }

    public Member saveMember(Member m) {
        return this.userRepository.save(m);
    }

    public boolean isUserNameExists(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    public List<Member> getAllMembers() {
        return userRepository.findAll();
    }

}
