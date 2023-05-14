// 6. Creamos primer Filtro, JWTAuthentication que extiende
// de UsernamePasswordAuthenticationFilter

package com.Portfolio.LogIn.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 1. Extendemos de UsernamePasswordAuthenticationFilter
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 2. Utilizamos primera clase abstracta (attemptAuthentication) que retorna
    // un objeto de la clase Authentication.
    @Override
    public Authentication attemptAuthentication(
            // 3. Recibe dos parametros, la solicitud y respuesta
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException {
        
        // 3. Creamos una instancia de la clase AuthCredentials
        AuthCredentials authCredentials = new AuthCredentials();
        
        // 4. Si nos envian las credenciales, lo convertimos utilizando ObjectMapper
        try {
            authCredentials = new ObjectMapper()
                    // 5. Leemos los valores y lo parseamos a AuthCrendetials.class
                    .readValue(request.getReader(), AuthCredentials.class);
            // 6. Pasamos un error en caso de que devuelva una excepcion.
        } catch (IOException e) {
            System.out.println("Error al leer las credenciales de autenticación: " + e.getMessage());
        }
        
        // 7. Creamos una instancia de UsernamePasswordAuthenticationToken
        // y le asignamos el getEmail, getPassword y una collecion vacia
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                // Email o nombre de usuario
                authCredentials.getEmail(),
                // Contraseña
                authCredentials.getPassword(),
                // Lista vacia (roles o permisos)
                Collections.emptyList()
        );
        
        // 8. Retornamos usando getAuthenticationManager con su metodo authenticate
        // le enviamos como argumento el usernamePAT
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    
    // 9. Usamos clase abstracta successful para una autenticacion autorizada
    @Override
    protected void successfulAuthentication(
            // 10. Recibe como parametros un request, response, un chain y el resultado
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        // 11. Utilizamos un objeto de UserDetailsImpl y se lo asignamos al
        // metodo getPrincipal del resultado, forzamos que sea un objeto
        // UserDetailsImpl encerrandolo entre parentesis al principio de la linea
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        
        // 12. Creamos un token (tipo String) Utilizando la implementacion
        // tokenUtils y su metodo createToken, enviamos como parametros
        // el getNombre (nombre) y el getUsername (email)
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());
        
        // 13. Añadimos la respuesta en un header, le enviamos como argumentos 
        // el nombre del encabezado, Bearer como prefijo, y el token
        response.addHeader("Authorization", "Bearer " + token);
        
        // BOT
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        
        // 14. Utilizamos el metodo getWriter para enviar la respuesta, y
        // el metodo flush para hacerlo antes de que se cierre la conexión
        response.getWriter().flush();
        
        // 15. Enviamos respuesta
        super.successfulAuthentication(request, response, chain, authResult);
    }
    
    
}
