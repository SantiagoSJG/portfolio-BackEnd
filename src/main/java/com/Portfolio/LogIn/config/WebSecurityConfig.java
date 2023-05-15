// PASO 1.

package com.Portfolio.LogIn.config;

import com.Portfolio.LogIn.Security.JWTAuthenticationFilter;
import com.Portfolio.LogIn.Security.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// 2. Añadimos Anotacion de Configuration
@Configuration

//// 102. Añadimos anotaciones de AllArgsConstructor y EnableWebSecurity
@EnableWebSecurity
@AllArgsConstructor

// 1. Creamos clase de WebSecurityConfig
public class WebSecurityConfig{
    
    //// 100. Despues de definir los 6 archivos de /Security /////
    //// 101. Injectamos userDetailsService y jwtAuthorizationFilter
    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    // 5. Añadimos anotación de Bean
    @Bean
    // 3. Creamos metodo que retorne un SecurityFilterChain y se llame filterChain()
        // 4. Retorna HttpSecurity y un Authentication Manager
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager
    ) throws Exception {
          
        
        // 102.Creamos una instancia de JWTAuthenticationFilter
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        // 103.Le asignamos el authenticationManager
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        // 104. Asignamos la ruta para el inicio de sesion
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        // 6. Retornamos HTTP y sus metodos
       return http
               .cors()
               .and()
               
               // 7. Deshabilitamos CSRF
               .csrf().disable()
               
               // 8. Reglas de solicitudes: Autorizamos cualquier solicitud que este autenticada
               .authorizeHttpRequests()
//               .requestMatchers("login")
//               .permitAll()
               .anyRequest()
               .authenticated()
               
               // 9. Habilitamos autenticación básica
               .and()
               .httpBasic()
               
               // 10. Gestión de la sesiones: Se establece la politica de creacion de sesiones
               // en STATELESS (Sin estado)
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               
               // 105. Añadimos dos filtros, el authenticationFilter y el AuthorizationFilter
               .and()
               .addFilter(jwtAuthenticationFilter)
               // 106. Este segundo filtro debemos establecer que se ejecuta antes de 
               // UPAuthenticationFilter.class
               .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
               
               // 11. Construimos el SecurityFilterChain
               .build();
    }
    
    // Para probar la implementación.
//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        
//        manager.createUser(User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build());
//        
//        return manager;
//    }
    
     // 12. Creamos la implementación de PasswordEncoder, que retornara
     // BCryptPasswordEncoder.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //  
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        // 
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
    
//    public static void main(String[] args) {
//        System.out.println("pass: " + new BCryptPasswordEncoder().encode("Contraseña"));
//    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://portfoliofrontend.web.app");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}