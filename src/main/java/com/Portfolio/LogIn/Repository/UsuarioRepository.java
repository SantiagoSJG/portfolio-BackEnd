package com.Portfolio.LogIn.Repository;

import com.Portfolio.LogIn.Model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findOneByEmail(String email);
}
