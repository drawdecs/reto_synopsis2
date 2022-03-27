
package pe.com.cma.apiaccess.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

public class SecurityInfo implements UserDetails
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private final UserWrapper userInfo;

    public SecurityInfo(UserWrapper userInfo)
    {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.userInfo.getRoles();
    }

    @Override
    public String getPassword()
    {
        return "";
    }

    @Override
    public String getUsername()
    {
        return this.getUserInfo().getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

}
