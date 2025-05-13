package com.example.mtbs.security.jwt;

import com.example.mtbs.config.AppEnv;
import com.example.mtbs.dto.ExtractToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
public class JwtService {

//    private String secret = "K/SFvQsVTpKyBqJ+LB1iyrGbF7RcN5hghXXZ+zirpyc=\n";
    private final AppEnv appEnv;


    public String createJwtToken(TokenPayLoad tokenPayload) {
        return Jwts.builder()
                .setHeaderParam("type",tokenPayload.tokenType().name())
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.issuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public ExtractToken parseToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build().parseClaimsJws(token);

            JwsHeader header = claimsJws.getHeader();

            Claims claimBody = claimsJws.getBody();

            ExtractToken extractToken = new ExtractToken(header,claimBody);

            return extractToken;

        }catch (JwtException e){
            log.warn("exception", e);
            return null;
        }
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(appEnv.getToken().getSecret()));
    }
}
