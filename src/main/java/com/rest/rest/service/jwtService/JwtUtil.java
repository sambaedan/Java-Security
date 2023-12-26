package com.rest.rest.service.jwtService;

import com.rest.rest.model.user.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Service
public class JwtUtil {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY ;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private final MyUserDetails userDetails;

    public JwtUtil(MyUserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public  Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private  Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private  Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }
    private void setCustomClaims(Map<String,Object>claims, UserDetails userDetails){
        List<String> authorities = new ArrayList<>();
        for(GrantedAuthority auth : userDetails.getAuthorities()){
            authorities.add(auth.getAuthority());
        }
        claims.put("authorities",authorities);
    }
    public List<GrantedAuthority> getAuthoritiesClaimFromToken(String token){
        Claims claims = extractAllClaims(token);
        List<GrantedAuthority> returnValue = null;
        List<String> authorities;
        authorities = (List)claims.get("authorities");
        if(authorities == null) return returnValue;
        returnValue = new ArrayList<>();
        return authorities.stream().map(authority->new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

    }

    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        this.setCustomClaims(claims, userDetails);
        return createToken(claims, userDetails.getUsername(), jwtExpiration);
    }
    public String generateRefreshToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), refreshExpiration);
    }
    private String createToken(Map<String, Object> claims, String subject, long expiration) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration ))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token){
        String userName = extractUsername(token);
        return StringUtils.hasText(userName)  && !this.isTokenExpired(token);
    }


}
