package br.com.alura.bookstore.infra.security;

import br.com.alura.bookstore.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jjwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication){
        User loggedInUser = (User) authentication.getPrincipal();

        return Jwts
                .builder()
                .setId(loggedInUser.getId().toString())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
