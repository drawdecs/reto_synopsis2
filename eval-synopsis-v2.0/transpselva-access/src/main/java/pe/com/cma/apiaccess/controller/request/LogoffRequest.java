
package pe.com.cma.apiaccess.controller.request;

import java.io.Serializable;

public class LogoffRequest implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String sessionId;
    private String environment;

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(String environment)
    {
        this.environment = environment;
    }

}
