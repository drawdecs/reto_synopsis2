
package pe.com.cma.apiaccess.security.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.com.cma.apiaccess.enumeration.AccessPropsEnum;
import pe.com.cma.apiaccess.security.dto.SecurityInfo;
import pe.com.cma.apiaccess.security.dto.SecurityToken;
import pe.com.cma.apiaccess.security.dto.UserWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter
{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private Environment env;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {

            String jwt = getJwtFromRequest(request);

            if (StringUtils.isNotEmpty(jwt) && tokenProvider.validateToken(jwt))
            {

                UserWrapper userInfo = tokenProvider.getUserFromJWT(jwt);
                UserDetails userDetails = new SecurityInfo(userInfo);

                UsernamePasswordAuthenticationToken authentication = new SecurityToken(userDetails, null,
                        userDetails.getAuthorities(), userInfo);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception ex)
        {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request)
    {
        String bearerToken = "";
        bearerToken = request.getHeader(AccessPropsEnum.JWT_HEADER.getString(env));
        if (StringUtils.isBlank(bearerToken) && HttpMethod.GET.matches(request.getMethod()))
        {
            bearerToken = request.getParameter(AccessPropsEnum.JWT_HEADER.getString(env));
        }
        String prefix = AccessPropsEnum.JWT_PREFIX.getString(env);
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith(prefix))
        {
            return bearerToken.substring(prefix.length() + 1, bearerToken.length());
        }
        return null;
    }
}
