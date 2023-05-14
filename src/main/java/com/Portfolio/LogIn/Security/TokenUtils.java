// PASO 2. Crear los tokens.
package com.Portfolio.LogIn.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenUtils {

    // 1. Creamos una firma (clave secreta) para los tokens JWT.
    private final static String ACCESS_TOKEN_SECRET = "JA09sgh9A0S7GHas97f5as05f7Ud323KJ3";

    // 2. Se define la cantidad de segundos que el token va a ser valido (en este caso 30 dias)
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    // Estas dos se pueden definir en application.properties
    // 3. Creamos un metodo que cree un Token, recibiendo un nombre y un email
    public static String createToken(String nombre, String email) {
        // 4. Creamos una variable expirationTime que obtiene el tiempo de expiracion
        // del token, y lo multiplica por 1000, para obtener los milisegundos.
        Long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;

        // 5. Se crea una instancia de expirationDate que va a definir la fecha
        // de expiracion del Token basada en los milisegundos obtenidos de la
        // variable anterior
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        // 6. Creamos un objeto Map, que obtiene información extra
        // en este caso, el nombre del usuario.
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        // 7. Retornamos un JWTS.builder() para construir un Token.
        return Jwts.builder()
                // 8.Establecemos el mail del usuario
                .setSubject(email)
                // 9. Establecemos la fecha de expiración
                .setExpiration(expirationDate)
                // 10. Utilizamos el 'extra' que en este caso es el nombre de usuario
                .addClaims(extra)
                // 11. Firmamos el token con la clave secreta
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                // 12. Compactamos el Token
                .compact();
        // 13. Esto finalmente genera el Token que será enviado hacia el cliente.
    }

    // 14. Creamos un metodo que retorne un UsernamePasswordAuthenticationToken
    // que se encarga de verificar y extraer la información de un token
    // 15. Recibimos en un parametro el token
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            // 16. Creamos un claim (cuerpos del token) y lo asignamos a
            // JWTS.parserBuilder para crear un objeto JwtParserBuilder para
            // configurar el proceso de analisis y verificacion del token
            Claims claims = Jwts.parserBuilder()
                    // 17. Indicamos la firma del Token
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    // 18. Construimos el objeto JwtParser para analizar el token
                    .build()
                    // 19. Usamos este metodo para analizar y obtener el cuerpo del token
                    .parseClaimsJws(token)
                    // 20. Obtenemos el body
                    .getBody();

            // 21. Obtenemos la identificacion del usuario con claims y su metodo getSubject()
            String email = claims.getSubject();

            // 22. retornamos una instancia de UsernamePasswordAuthenticationToken, enviando como username
            // el email, le enviamos nulo y como tercer parametro una lista vacia.)
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            // 23. Si se envia excepcion si el usuario envia un token expirado o invalido
            return null;
        }

    }
}
