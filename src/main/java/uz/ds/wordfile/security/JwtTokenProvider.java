package uz.ds.wordfile.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.ds.wordfile.entity.User;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecretKey}")
    private String kalituzingizBratan;

//    @Value("${app.jwtExpireInMilSec}")
//    private Long expireTime;

    public String generateToken(Authentication authentication) {
        Date yashashMuddati = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10);
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(yashashMuddati)
                .signWith(SignatureAlgorithm.HS512, kalituzingizBratan)
                .compact();
    }
}
