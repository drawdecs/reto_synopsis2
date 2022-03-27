package pe.com.cma.apiaccess.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import static pe.com.cma.apiaccess.utils.GenConstants.*;

@Data
public class LoginRequest implements Serializable {

    public static final Long serialVersionUID = SERIAL_VERSION;

    @NotNull
    private String email;
    private String environment;
    private BigDecimal appVersion;
    private String password;

}
