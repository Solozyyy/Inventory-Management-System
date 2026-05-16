package learnSB.project.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import learnSB.project.Domain.Member;
import learnSB.project.Repository.UserRepository;
import learnSB.project.Security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Member m = (Member) userRepository.findByUserName(username);

        if (m == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(m);
    }
}