package com.example.demo.auth;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;

public class TokenHelper {

  public static String createJWT(String id, String issuer, String subject) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    long expMillis = nowMillis + 3600000; // 1 hour
    Key signingKey = getSigningKey(signatureAlgorithm);

    JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).setIssuer(issuer)
        .setExpiration(new Date(expMillis)).signWith(signatureAlgorithm, signingKey);
    return builder.compact();
  }

  public static void parseJWT(String token) {
    Jwts.parser().setSigningKey(getSigningKey(SignatureAlgorithm.HS256)).parseClaimsJws(token);
  }

  private static Key getSigningKey(SignatureAlgorithm signatureAlgorithm) {
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("123apiKey");
    return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
  }
}
