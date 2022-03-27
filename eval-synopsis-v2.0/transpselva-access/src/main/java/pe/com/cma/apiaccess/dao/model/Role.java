
package pe.com.cma.apiaccess.dao.model;

import java.io.Serializable;

import lombok.Data;
import static pe.com.cma.apiaccess.utils.GenConstants.*;

@Data
public class Role implements Serializable
{

    private static final long serialVersionUID = SERIAL_VERSION;

    private String name;

}
