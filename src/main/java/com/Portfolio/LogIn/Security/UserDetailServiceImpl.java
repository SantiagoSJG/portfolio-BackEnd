// 4. Creamos clase UserDetailServiceImpl que implementa de UserDetailService
// Empezamos a trabajar con la base de datos

package com.Portfolio.LogIn.Security;

import com.Portfolio.LogIn.Model.Usuario;
import com.Portfolio.LogIn.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 1. Anotamos con Service
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    // 2. Injectamos el repositorio del usuario
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    // 3. Utilizamos la clase abstracta loadUserByUsername que recibira el usuario (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        // 4. Creamos un metodo en el usuarioRepository llamado findOneByEmail y que encuentre
        // mediante su parametro un solo email, entre la lista.
        
        // 5. Definimos un objeto usuario, recuperando el objeto usuario que 
        // coincida con el email buscado
        Usuario usuario = usuarioRepo
                .findOneByEmail(email)
                // 6. Si no encuentra el email, retornamos una excepcion.
                .orElseThrow(() -> new UsernameNotFoundException("error"));
        
        // 6. Retornamos el usuario cargado de la base de datos
        // en una implementacion de userDetails
        return new UserDetailsImpl(usuario);
        
    }    
}
