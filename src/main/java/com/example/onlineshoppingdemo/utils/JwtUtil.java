package com.example.onlineshoppingdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
        private static final String SECRET_KEY = "WELCOMEINTEPSHOP";
        private static final long TOKE_VALIDITY = 3600 * 5;



        public Date getExpirationDateFromToken(String token){
            return getClaimFromToken(token,Claims::getExpiration);
        }

        public boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
        }
        //Token Expired မဖြစ်ပဲ username က ညီလားစစ်တာ
        public boolean validationToken(String token , UserDetails userDetails){
            var username = getUserEmailFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

        public String getUserEmailFromToken(String token){
            return getClaimFromToken(token,Claims::getSubject);
        }
        public <T> T getClaimFromToken(String token , Function<Claims,T>claimsTFunction){
            return claimsTFunction.apply(getAllClaimsFromToken(token));
        }
        public Claims getAllClaimsFromToken(String token){
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }


        //Generation Of Token
        public String generateToken(UserDetails userDetails){
            Map<String,Object> claims = new HashMap<>();
            return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+TOKE_VALIDITY *1000))
                    .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                    .compact();
        }
}
