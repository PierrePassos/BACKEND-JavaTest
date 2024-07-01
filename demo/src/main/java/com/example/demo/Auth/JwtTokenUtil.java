package com.example.demo.Auth;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
// import java.util.Date;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import org.springframework.security.core.Authentication;

// @Component
public class JwtTokenUtil {

    // @Value("${jwt.secret}") 
    // private String jwtSecret;

    // @Value("${jwt.expirationInMs}")
    // private int jwtExpirationInMs;

    // public String generateToken(Authentication authentication) {
    //     UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    //     Date now = new Date();
    //     Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    //     return Jwts.builder()
    //             .setSubject(userDetails.getUsername())
    //             .setIssuedAt(new Date())
    //             .setExpiration(expiryDate)
    //             .signWith(SignatureAlgorithm.HS512, jwtSecret)
    //             .compact();
    // }
}
