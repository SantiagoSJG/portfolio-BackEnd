// 7. SEGUNDA FASE: Creamos segundo filtro, JWT AuthorizationFilter

package com.Portfolio.LogIn.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// 1. Añadimos anotacion de Componente
@Component
// 2. Extendemos de OncePerRequestFilter
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    // 3. Utilizamos clase abstracta doFilterInternal
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        
        // 4. Capturamos el bearerToken, utilizando el metodo getHeader
        // del request desde el header de Authorization
        String bearerToken = request.getHeader("Authorization");
        
        // 5. si el bearerToken NO es nulo y empieza con Bearer 
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // 6. Eliminamos el bearer y tenemos solo el token
            String token = bearerToken.replace("Bearer ", "");
            
            // 7. Utilizamos una instancia usernamePAT
            // usando TokenUtils y su metodo getAuthentication enviando el token
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            
            // 8. Utilizamos SecurityContextHolder.getContext() para establecer
            // la autenticacion, enviandole el usernamePAT
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        
        // 9. Enviamos el doFilter.
        filterChain.doFilter(request, response);
    }
    
    
    
}
