
package pe.com.cma.apiaccess.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Boolean existImage;

}
