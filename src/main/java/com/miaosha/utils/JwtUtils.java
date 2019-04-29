package com.miaosha.utils;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static long EXPIRATION_TIME = 60 * 60 * 24;
    private static String SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSdMDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd==MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd==MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd==";

    /**
     * 生成jwtToken
     *
     * @param account
     * @return
     */
    public static String generateToken(String account) {
        HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
        map.put("account", account);
        String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        return jwt;
    }

    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
    public static String validateToken(String token)  {
        if (token != null) {
            Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String account = (String) (body.get("account"));
            if (account == null || account.isEmpty()) {
                return null;
            } else {
                return account;
            }
        } else {
            return null;
        }
    }

    public static long getEXPIRATION_TIME(){
        return JwtUtils.EXPIRATION_TIME;
    }



}