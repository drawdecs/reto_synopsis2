package pe.com.cma.apiaccess.service;

import pe.com.cma.apiaccess.controller.request.LoginRequest;
import pe.com.cma.apiaccess.controller.response.Response;
import pe.com.cma.apiaccess.controller.response.Status;
import pe.com.cma.apiaccess.exception.AccessLogicException;
import pe.com.cma.apiaccess.security.dto.UserWrapper;
import pe.com.cma.apiaccess.service.dto.LoginDTO;

public interface LoginService {

    public Response<LoginDTO> login(LoginRequest login) throws AccessLogicException;

    public Status logout(UserWrapper request);

}
