package pe.com.cma.apiaccess.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.cma.apiaccess.controller.request.LoginRequest;
import pe.com.cma.apiaccess.controller.response.Response;
import pe.com.cma.apiaccess.controller.response.Status;
import pe.com.cma.apiaccess.enumeration.AccessCodeEnum;
import pe.com.cma.apiaccess.exception.AccessLogicException;
import pe.com.cma.apiaccess.security.dto.SecurityToken;
import pe.com.cma.apiaccess.service.LoginService;
import pe.com.cma.apiaccess.service.dto.LoginDTO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1")
public class AccessController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<Response<LoginDTO>> login(@RequestBody LoginRequest login, HttpServletRequest request)
            throws AccessLogicException
    {
        Response<LoginDTO> response = loginService.login(login);
        return new ResponseEntity<Response<LoginDTO>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Status> logout(SecurityToken token, HttpServletRequest request)
    {
        if(token != null) {
            return new ResponseEntity<Status>(loginService.logout(token.getUserInfo()), HttpStatus.OK);
        } else {
            return new ResponseEntity<Status>(AccessCodeEnum.OK.status(), HttpStatus.OK);
        }
    }

}
