package com.robertorebolledonaharro.bichoapi.security.jwt.access;

import com.robertorebolledonaharro.bichoapi.security.blacklist.BlackListService;
import com.robertorebolledonaharro.bichoapi.security.errorhandling.BlackListTokenException;
import com.robertorebolledonaharro.bichoapi.security.errorhandling.JwtTokenException;
import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.model.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import javax.crypto.SecretKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private int jwtLifeInDays;
    //private int jwtLifeInMinutes;

    @Autowired
    private BlackListService blackListService;

    private JwtParser jwtParser;

    private SecretKey secretKey;



    @PostConstruct
    public void init() {

        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        //jwtParser = Jwts.parserBuilder()
        jwtParser = Jwts.parser()
                //.setSigningKey(secretKey)
                .verifyWith(secretKey)
                .build();
    }


    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return generateToken(user);

    }

    public String generateToken(User user) {
        Date tokenExpirationDateTime =
                Date.from(
                        LocalDateTime
                                .now()
                                .plusDays(jwtLifeInDays)
                                //.plusMinutes(jwtLifeInMinutes)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                );

        return Jwts.builder()
                .header().type(TOKEN_TYPE)
                .and()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(tokenExpirationDateTime)
                .signWith(secretKey)
                .compact();
                /*.setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDateTime)
                .signWith(secretKey)
                .compact();*/

    }


    public UUID getUserIdFromJwtToken(String token) {



        return UUID.fromString(
                //jwtParser.parseClaimsJws(token).getBody().getSubject()
                jwtParser.parseSignedClaims(token).getPayload().getSubject()
        );
    }


    public boolean validateToken(String token) throws BlackListTokenException {

        try {
            //jwtParser.parseClaimsJws(token);
            jwtParser.parse(token);

            blackListService.isBlacklisted(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Error con el token: " + ex.getMessage());
            throw new JwtTokenException(ex.getMessage());
        }
        //return false;

    }

    @Scheduled(cron = "0 0 12")
    public void removeExpiratedToken(){
        blackListService.findAll().stream().forEach(x->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = null;
            try {
                date = simpleDateFormat.parse(LocalDate.now().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if(jwtParser.parseSignedClaims(x.getToken()).getPayload().getExpiration().after(date)){
                blackListService.deleteById(x.getToken());
            }
        });

    }



}
