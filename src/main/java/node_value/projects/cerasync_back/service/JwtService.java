package node_value.projects.cerasync_back.service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.signing.key}")
    private static String KEY;
    
    public String generateToken(UserDetails uD) {
        return generateToken(new HashMap<>(), uD);
    }

    public String generateToken(Map<String, Object> extractClaims, UserDetails uD) {
        return Jwts.builder()
            .setClaims(extractClaims)
            .setSubject(uD.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 48))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();

    }

    public boolean isTokenValid(String jwt, UserDetails uD) {
        final String username = extractUsername(jwt);
        return (username.equals(uD.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        return claimResolver.apply(extractAllClaims(jwt));
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
    }
}
