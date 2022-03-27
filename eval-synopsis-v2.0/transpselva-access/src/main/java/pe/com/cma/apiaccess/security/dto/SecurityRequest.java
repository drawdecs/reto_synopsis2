
package pe.com.cma.apiaccess.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import pe.com.cma.apiaccess.controller.request.LoginRequest;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

import java.util.Collection;

public class SecurityRequest extends UsernamePasswordAuthenticationToken
{
    private final LoginRequest request;

    private static final long serialVersionUID = SERIAL_VERSION;

    public SecurityRequest(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
            LoginRequest request)
    {
        super(principal, credentials, authorities);
        this.request = request;
    }

    public SecurityRequest(Object principal, Object credentials, LoginRequest request)
    {
        super(principal, credentials);
        this.request = request;
    }

    public LoginRequest getRequest()
    {
        return request;
    }

}
