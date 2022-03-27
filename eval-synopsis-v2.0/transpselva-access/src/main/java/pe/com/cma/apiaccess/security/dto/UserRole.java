
package pe.com.cma.apiaccess.security.dto;

import org.springframework.security.core.GrantedAuthority;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

public class UserRole implements GrantedAuthority
{

    private static final long serialVersionUID = SERIAL_VERSION;
    private final String authority;

    public UserRole(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String getAuthority()
    {
        return this.authority;
    }

}
