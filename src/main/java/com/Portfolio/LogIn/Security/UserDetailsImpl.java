// 5. Creamos la implementacion de UserDetailsImpl (UserDetails)
package com.Portfolio.LogIn.Security;

import com.Portfolio.LogIn.Model.Usuario;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// 1. Añadimos la anotación AllArgsConstructor para generar un constructor que recibe
// como argumento un objeto de la clase Usuario
@AllArgsConstructor

public class UserDetailsImpl implements UserDetails {
    
    // 2. Recibimos un usuario
    private final Usuario usuario;
    
    // 3. Retornamos una lista vacia
    // Es util si nuestro usuario tiene permisos o roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    // 4. Getter de password
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    // 5. Getter de username (email)
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }
    
    // 8. Getter de nombre
    public String getNombre() {
        return usuario.getNombre();
    }

    // 6. Definimos todos los demas metodos con true 
    // para indicar que la cuenta del usuario nunca expira,
    // nunca está bloqueada y las credenciales nunca expiran.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 7. isEnabled(): retorna true para indicar que la cuenta del usuario está habilitada.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
