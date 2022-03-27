
package pe.com.cma.apiaccess.service.dto;

import lombok.Getter;
import lombok.Setter;
import pe.com.cma.apiaccess.security.dto.UserRole;

@Getter
@Setter
public class UserRoleDTO
{

    private String authority;

    UserRoleDTO(UserRole userRole)
    {
        this.authority = userRole.getAuthority();
    }
}
