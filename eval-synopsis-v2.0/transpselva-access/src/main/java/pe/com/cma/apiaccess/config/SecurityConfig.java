package pe.com.cma.apiaccess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pe.com.cma.apiaccess.security.impl.JwtAuthenticationFilter;
import pe.com.cma.apiaccess.security.impl.JwtAuthenticationProvider;

@Configuration
@EnableWebMvc
@ComponentScan("pe.com.cma.apiaccess")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter()
    {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
        http
            .cors()
            .and()
                .csrf()
                .disable()
                .exceptionHandling()
                //.authenticationEntryPoint(unauthorizedHandler)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/v1/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/client").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers(HttpMethod.POST, "/v1/logout").permitAll()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                //.accessDeniedHandler(accessDeniedEntryPoint)
            .and();
        // @formatter:on

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
