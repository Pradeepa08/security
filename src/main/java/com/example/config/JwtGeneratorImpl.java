package com.example.config;

import com.example.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtGeneratorImpl {
    public Map<String, String> generateToken(User user) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("x-hm-authorization", jwtToken);
        return jwtTokenGen;
    }
}
