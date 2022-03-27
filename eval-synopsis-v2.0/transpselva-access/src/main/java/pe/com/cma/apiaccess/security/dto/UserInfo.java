
package pe.com.cma.apiaccess.security.dto;

import java.io.Serializable;

import com.google.gson.Gson;

import lombok.Getter;
import pe.com.cma.apiaccess.dao.model.User;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

@Getter
public class UserInfo implements Serializable
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private final String email;
    private final String phone;
    private final Long userId;

    public UserInfo(final User userInfo)
    {
        this.email = userInfo.getEmail();
        this.userId = userInfo.getUserId();
        this.phone = userInfo.getPhone();
    }

    public static UserWrapper fromJson(String json)
    {
        Gson gson = new Gson();
        UserWrapper userInfo = gson.fromJson(json, UserWrapper.class);
        return userInfo;
    }

}
