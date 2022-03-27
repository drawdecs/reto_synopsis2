
package pe.com.cma.apiaccess.security.dto;

import lombok.Getter;
import lombok.Setter;
import pe.com.cma.apiaccess.dao.model.User;

import java.util.ArrayList;
import java.util.List;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

@Getter
@Setter
public class UserWrapper extends UserInfo
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private final List<UserRole> roles;
    private final Boolean existImage;
    private final String name;

    public UserWrapper(User userInfo)
    {
        super(userInfo);
        this.name = userInfo.getName();
        this.roles = new ArrayList<>();
        if(userInfo.getRole() != null) {
            this.roles.add(new UserRole(userInfo.getRole()));
        }
        this.existImage = userInfo.getExistImage();
    }

}
