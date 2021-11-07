package br.com.alura.bookstore.infra.security;


import br.com.alura.bookstore.model.User;
import br.com.alura.bookstore.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenFilterVerification extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public TokenFilterVerification(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.replace("Bearer", "");
        boolean validToken = tokenService.isValid(token);
        if (validToken) {
            Long userId = tokenService.extractUserId(token);
            User loggedInUser = userRepository.findByIdWithProfiles(userId).get();
            Authentication authentication = new UsernamePasswordAuthenticationToken(loggedInUser, null, loggedInUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }
}
