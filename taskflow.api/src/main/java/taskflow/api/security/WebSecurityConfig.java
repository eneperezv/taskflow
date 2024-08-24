package taskflow.api.security;

/*
 * @(#)WebSecurityConfig.java 1.0 22/08/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * Clase de configuracion de seguridad JPA y Spring Security
 *
 * @author eliezer.navarro
 * @version 1.0 | 22/08/2024
 * @since 1.0
 */

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import taskflow.api.entity.ErrorDetails;
import taskflow.api.entity.UserMyDetails;
import taskflow.api.webtoken.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    private UserMyDetails userDetailService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/swagger-ui/**").permitAll();
                    registry.requestMatchers("/v3/api-docs/**").permitAll();
                    registry.requestMatchers(HttpMethod.POST, "/api/taskflow/auth").permitAll();
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/user/todos").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/user/**").hasRole("USER");
                    registry.requestMatchers(HttpMethod.POST, "/api/taskflow/user/create").hasRole("USER");
                    registry.requestMatchers(HttpMethod.PUT, "/api/taskflow/user/update").hasRole("USER");
                    /*
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/author/todos").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/author/create").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/book/todos").hasRole("USER");
                    registry.requestMatchers(HttpMethod.POST, "/api/taskflow/book/create").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/publisher/todos").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/publisher/create").hasRole("USER");
                    registry.requestMatchers(HttpMethod.GET, "/api/taskflow/client/todos").hasRole("USER");
                    registry.requestMatchers(HttpMethod.POST, "/api/taskflow/client/create").hasRole("USER");
                    */
                    registry.anyRequest().authenticated();
                })
                .formLogin(login -> login
                        .successHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"Autenticación exitosa\"}");
                        }))
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write(convertObjectToJson(new ErrorDetails(new Date(),HttpStatus.UNAUTHORIZED.toString(),"Credenciales no autorizadas")));
                        }))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) userDetailService;
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService((UserDetailsService) userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    private String convertObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Error al convertir a JSON\"}";
        }
    }

}
