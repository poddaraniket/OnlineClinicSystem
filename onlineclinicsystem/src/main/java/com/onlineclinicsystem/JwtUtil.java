package com.onlineclinicsystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

	/*
	 * it takes in the token and uses the claimsResolver in order to figure out what the claims are
	 * 
	 * */
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //it takes the expiration from token and check if expired
    
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    
    //it takes user details object that UserDetailsService object will give us 
    //it will create JWT based on the user details
    //it will call create token (private class) 
    //u pass claim here u put anything that u wanna put in the JWT payload
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //it will call the JWT Api that we included in pom.xml(jjwt) 
    //uses a builder pattern and set claims
    //set subjects, subject is tghe person being authenticated 
    //when u authenticated and the expiry
    //signWith->signing the token, using algo(here,HS256) and then ppass secret key 
    //.compact()->end of builder patter
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //it takes username from the token and checks if that username is same as the details passed in 
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
