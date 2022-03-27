package pe.com.cma.apiaccess.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.com.cma.apiaccess.controller.request.LoginRequest;
import pe.com.cma.apiaccess.controller.response.Response;
import pe.com.cma.apiaccess.controller.response.Status;
import pe.com.cma.apiaccess.enumeration.AccessCodeEnum;
import pe.com.cma.apiaccess.enumeration.AccessPropsEnum;
import pe.com.cma.apiaccess.exception.AccessLogicException;
import pe.com.cma.apiaccess.security.dto.SecurityRequest;
import pe.com.cma.apiaccess.security.dto.SecurityToken;
import pe.com.cma.apiaccess.security.dto.UserWrapper;
import pe.com.cma.apiaccess.security.impl.JwtTokenProvider;
import pe.com.cma.apiaccess.service.LoginService;
import pe.com.cma.apiaccess.service.dto.LoginDTO;

@Service
public class LoginServiceImpl implements LoginService {


    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private Environment env;

    @Override
    public Response<LoginDTO> login(LoginRequest login) throws AccessLogicException
    {
        Response<LoginDTO> response = new Response<LoginDTO>(AccessCodeEnum.OK.status());
        try
        {
            SecurityToken token = (SecurityToken) authenticationManager
                    .authenticate(new SecurityRequest(login.getEmail(), login.getPassword(), login));
            SecurityContextHolder.getContext().setAuthentication(token);
            String jwt = tokenProvider.generateToken(token);
            LoginDTO lr = new LoginDTO(token.getUserInfo());

            lr.setSessionId(String.format("%s %s", AccessPropsEnum.JWT_PREFIX.getString(env), jwt));
            response.setData(lr);
            return response;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        throw new AccessLogicException(AccessCodeEnum.NOT_AUTHENTICATED);
    }

    @Override
    public Status logout(UserWrapper request)
    {
        return AccessCodeEnum.OK.status();
    }

}
