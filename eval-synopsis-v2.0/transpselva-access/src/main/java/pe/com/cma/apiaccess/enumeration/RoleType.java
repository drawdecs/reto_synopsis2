
package pe.com.cma.apiaccess.enumeration;

import lombok.Getter;

@Getter
public enum RoleType
{
    // @formatter:off
    CLIENT("ROLE_CLIENT"), 
    ADMIN("ROLE_ADMIN");
    // @formatter:on

    private String code;

    RoleType(String code)
    {
        this.code = code;
    }

    public boolean isCode(String code)
    {
        return this.code.equals(code);
    }
}
