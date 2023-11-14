package com.elbuensabor.elbuensabor.Config;

import com.elbuensabor.elbuensabor.Jwt.JwtAuthenticationFilter;
import com.elbuensabor.elbuensabor.enums.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(csrf -> 
                csrf
                .disable())
                    .authorizeHttpRequests(authRequest ->
                        authRequest
                            .requestMatchers(PathRequest.toH2Console()).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/ebs/usuarios/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/ebs/personas/signUp")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/ebs/articulo")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/articulo/findByName")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/articulo/{id}")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/articulo/{id}")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/articulo/paged")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/personas/{id}")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.COCINERO.toString(), Rol.CAJERO.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/pedido")).hasAnyAuthority(Rol.CLIENTE.toString(), Rol.CAJERO.toString(), Rol.COCINERO.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/insumo")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/insumo/{id}")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/insumo/paged")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/rubros")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/rubros/{id}")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/rubroinsumo")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
                            .requestMatchers(new AntPathRequestMatcher("/ebs/rubroinsumo/{id}")).hasAnyAuthority(Rol.COCINERO.toString(), Rol.ADMINISTRADOR.toString())
//                        .requestMatchers(new AntPathRequestMatcher("/ebs/admin/**")).hasAuthority(Rol.ADMINISTRADOR.toString())
//                        .requestMatchers(new AntPathRequestMatcher("/ebs/user/**")).hasAuthority(Rol.ADMINISTRADOR.toString())
//                        .requestMatchers(new AntPathRequestMatcher("/ebs/user/**")).hasAuthority(Rol.CLIENTE.toString())

                )
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
            .sessionManagement(sessionManager->
                sessionManager 
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
