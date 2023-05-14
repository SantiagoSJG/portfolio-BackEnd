package com.Portfolio.LogIn.Repository;

import com.Portfolio.LogIn.Model.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Integer> {
    
}
