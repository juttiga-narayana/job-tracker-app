package com.JobTracker_Backend.backend.user.security;

import com.JobTracker_Backend.backend.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT token generation, extraction, and validation.
 *
 * Responsibilities:
 *  - Generates JWT tokens for authenticated users.
 *  - Extracts claims and username from JWT tokens.
 *  - Validates tokens and checks expiration.
 *
 * Fields:
 *  - secretKey: Base64-encoded secret key used for signing JWT tokens.
 *
 * Notes:
 *  - Uses HmacSHA256 algorithm for key generation.
 *  - Tokens include custom claims (userId) and standard claims (subject, issuedAt, expiration).
 */
@Service
public class JWTService {

    /** Base64-encoded secret key for signing JWT tokens */
    private String secretKey = "";

    /**
     * Constructor generates a secret key for JWT signing using HmacSHA256 algorithm.
     */
    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a JWT token for a given user.
     *
     * @param user User entity for which the token is generated
     * @return JWT token string
     */
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUserName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 500)) // Token valid for ~30 minutes
                .and()
                .signWith(getKey())
                .compact();
    }

    /**
     * Returns the secret key as a SecretKey object for JWT signing and verification.
     *
     * @return SecretKey object
     */
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extracts the username (subject) from a JWT token.
     *
     * @param token JWT token string
     * @return username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from a JWT token using a claim resolver function.
     *
     * @param token JWT token string
     * @param claimResolver Function to extract specific claim from Claims object
     * @param <T> Type of the claim
     * @return Extracted claim value
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     *
     * @param token JWT token string
     * @return Claims object containing all claims in the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validates a JWT token against a user's details.
     *
     * @param token JWT token string
     * @param userDetails UserDetails object
     * @return true if the token is valid and not expired, false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if a JWT token is expired.
     *
     * @param token JWT token string
     * @return true if token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token JWT token string
     * @return Date object representing token expiration
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}