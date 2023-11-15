package com.elbuensabor.elbuensabor.Config;

import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;

    //@Bean
    //CorsConfigurationSource corsConfigurationSource() {
    //    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
    //    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
    //    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", corsConfiguration);
    //    return source;
    //}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        return username -> usuarioRepository.findUsrByUsrname(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not fournd"));
    }

}
