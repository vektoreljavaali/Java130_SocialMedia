package com.vektorel.VektorelSocialMedia.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    public Optional<String> createToken(Long userid){
        String token = "";
        try {
            token = JWT.create()
                    .withAudience()
                    /**
                     * burada kullanıcının id sini alıp token içerisine ekliyoruz.
                     * Token içerisiinde saklama istedğiimiz verileri bu şekilde alabiliriz.
                     */
                    .withClaim("userid", userid)
                    .withIssuer("vektorel")
                    .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 )))
                    .withIssuedAt(new Date())
                    .sign(Algorithm.HMAC256("secret"));
                    return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public boolean validateToken(String token){
        try {
            JWT
                    .require(Algorithm.HMAC256("secret"))
                    .withIssuer("vektorel")
                    .build().verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
