package com.react.example.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {

    public static final long EXPIRATIONTIME = 864_000_00;
    public static final String SIGNIN_KEY = "SecretKey";
    public static final String PREFIX = "Bearer";

    public static void addToken(HttpServletResponse response, String username) {
        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNIN_KEY)
                .compact();
        response.addHeader("Authorization", PREFIX + " " + JwtToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    //get token from authorization header
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request. getHeader("Authorization");
        if(token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SIGNIN_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null,
                        Collections.emptyList());

            }
        }
        return null;
    }
}
