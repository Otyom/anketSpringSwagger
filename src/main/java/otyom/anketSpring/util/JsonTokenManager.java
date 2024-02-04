package otyom.anketSpring.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import otyom.anketSpring.entity.Admin;

import java.util.Date;
import java.util.Optional;

@Service
public class JsonTokenManager {
    @Value("${token-config.secret-key}")
    String secretKey;


    public Optional<String> createToken(Long id){
        String token="";
        Long experiesTime= 1000*60*60l;

        try {
            token= JWT.create()
                    .withClaim("id",id)
                    .withClaim("email","naime-96@hotmail.com")
                    .withIssuer("o")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+experiesTime))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);

        }catch (Exception e){
            return Optional.of(null);
        }
    }

    public Optional<Long>getIdByToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier jwtVerifier=JWT.require(algorithm).withIssuer("o").build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            if (decodedJWT==null){
                return Optional.empty();
            }
            Optional<Long> ıd=Optional.of(decodedJWT.getClaim("id").asLong());
            if (ıd.isEmpty()){
                System.out.println("id bos");
            }
            return ıd;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }



}
