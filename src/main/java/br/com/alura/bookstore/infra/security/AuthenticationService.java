package br.com.alura.bookstore.infra.security;

import br.com.alura.bookstore.dto.LoginFormDto;
import br.com.alura.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public String authenticate(LoginFormDto dto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                dto.getLogin(),
                dto.getPassword()
        );
        authentication = authenticationManager.authenticate(authentication);

        return tokenService.generateToken(authentication);

    }
}
