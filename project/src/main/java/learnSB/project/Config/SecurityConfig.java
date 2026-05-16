package learnSB.project.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import learnSB.project.Security.JwtAuthenticationEntryPoint;
import learnSB.project.Security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                // 1. Tắt CSRF vì chúng ta dùng JWT (Stateless)
                                .csrf(csrf -> csrf.disable())

                                // 2. Cấu hình CORS (dựa trên bean bên dưới bạn đã viết)
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                                // 3. Cấu hình xử lý lỗi khi chưa đăng nhập
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(jwtAuthenticationEntryPoint))

                                // 4. Quan trọng: Tắt Session (vì mỗi lần gọi API phải kèm theo Token)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                // 5. Phân quyền các đường dẫn
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/login", "/api/register", "/api/auth/**")
                                                .permitAll() // Các
                                                // API
                                                // công
                                                // khai
                                                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ Admin mới được
                                                                                                   // vào
                                                .requestMatchers("/api/employee/**").hasRole("EMPLOYEE") // only
                                                                                                         // employee
                                                .anyRequest().authenticated() // Các request khác đều phải có Token
                                );
                // 6. THÊM JWT FILTER VÀO TRƯỚC UsernamePasswordAuthenticationFilter
                // Điều này giúp hệ thống kiểm tra token trước khi thực hiện các bước đăng nhập
                // mặc định
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
        // Các Bean hiện tại của bạn như corsConfigurationSource() giữ nguyên...

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(Arrays.asList("*"));
                config.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return source;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }
}
