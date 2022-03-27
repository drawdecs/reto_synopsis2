
package pe.com.cma.apiaccess.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pe.com.cma.apiaccess.enumeration.AccessPropsEnum;
import pe.com.cma.apiaccess.security.dto.SecurityToken;
import pe.com.cma.apiaccess.security.dto.UserInfo;
import pe.com.cma.apiaccess.security.dto.UserWrapper;

import java.util.Date;

@Component
public class JwtTokenProvider
{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private Environment env;

    @Autowired
    private Gson gson;

    public String generateToken(Authentication authentication)
    {

        SecurityToken userPrincipal = (SecurityToken) authentication;

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + AccessPropsEnum.JWT_EXPIRATION.getLong(env));

        String token = JWT.create().withSubject(gson.toJson(userPrincipal.getUserInfo())).withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC512(AccessPropsEnum.JWT_SECRET.getString(env).getBytes()));

        return token;
    }

    public UserWrapper getUserFromJWT(String token)
    {
        String user = JWT.require(Algorithm.HMAC512(AccessPropsEnum.JWT_SECRET.getString(env).getBytes())).build()
                .verify(token).getSubject();

        return UserInfo.fromJson(user);
    }

    public boolean validateToken(String authToken)
    {
        try
        {
            JWT.require(Algorithm.HMAC512(AccessPropsEnum.JWT_SECRET.getString(env).getBytes())).build()
                    .verify(authToken);
            return true;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

}
