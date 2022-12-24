package com.motivity.mlionlineoppbooking.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class JwtUtil {
    @Value("${app.secrete}")
    private String secrete;
    // 1 : Generate Token
    public String generateToken(String subject) {

        String token = Jwts.builder().setSubject(subject).setIssuer("motivity")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .signWith(SignatureAlgorithm.HS512, secrete.getBytes()).compact();
        return token;
    }

    // 2 : Read Claims

    public Claims readClimes(String token) {

        return Jwts.parser().setSigningKey(secrete.getBytes()).parseClaimsJws(token).getBody();
    }
    // 3 : read Expiration date from token

    public Date getExpiration(String token) {
        return readClimes(token).getExpiration();

    }
    // 4 : read username  from token

    public String getUserName(String token) {
        return readClimes(token).getSubject();

    }
    // 5 : is Expired or not date from token

    public boolean isTokenExpire(String token) {
        Date expd = getExpiration(token);

        return expd.before(new Date(System.currentTimeMillis()));
    }
    // 6 : validate username in token && database and expired or not

    public boolean isValidToken(String token,String username)
    {
        String tokenUsername=getUserName(token);
        return (username.equals(tokenUsername)&& !isTokenExpire(token));

    }
}
