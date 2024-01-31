package com.dubs.core.server.security;

import com.dubs.core.server.entity.Credentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.Optional;

import java.security.Key;

@Service
@Slf4j
public class JWTService {
    
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.ms}")
    private int expireTimeMs;

    public Optional<String> extractToken(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if(null != authHeader && authHeader.startsWith("Bearer ")){
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }


    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // TOKEN PARSERS
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(jwtToken).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // TOKEN VALIDATORS
    public boolean checkJwtExpiry(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    public boolean checkJwtToken(String jwtStr) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parse(jwtStr);
            return true;
        } catch (MalformedJwtException malformedErr) {
            log.error("JWT Error:" + malformedErr);
        } catch (ExpiredJwtException expiredErr) {
            log.error("JWT Error:" + expiredErr);
        } catch (UnsupportedJwtException unsupportedErr) {
            log.error("JWT Error:" + unsupportedErr);
        } catch (IllegalArgumentException illegalArgumentErr) {
            log.error("JWT Error:" + illegalArgumentErr);
        } catch (SignatureException signatureErr) {
            log.error("JWT Error:" + signatureErr);
        }
        return false;
    }

    public Boolean validateToken(String jwtStr, Credentials creds) {
        final String username = extractUsername(jwtStr);
        return (username.equals(creds.getUsername()) && !checkJwtExpiry(jwtStr));
    }

    // TOKEN GENERATORS
    public String generateToken(String subject) {
        log.info("Generate Token  Subject: {}",subject);
        return generateToken(subject, new HashMap<>());
    }

    public String generateToken(String subject, Map<String, Object> customClaims) {
        log.info("Generate Custom Token Subject: {} with Custom Claims: {}", subject,customClaims);
        return buildToken(subject, customClaims);
    }

    public String buildToken(String subject, Map<String, Object> customClaims) {
        log.info(" Building Token");
        return Jwts
                .builder()
                .setClaims(customClaims) // NOTE: This will override existing claims. is this necessary?
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
