
package pe.com.cma.apiaccess.security.dto;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

public class SecurityToken extends UsernamePasswordAuthenticationToken
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private final UserWrapper userInfo;

    public SecurityToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
            UserWrapper userInfo)
    {
        super(principal, credentials, authorities);
        this.userInfo = userInfo;
    }

    public UserWrapper getUserInfo()
    {
        return userInfo;
    }

}