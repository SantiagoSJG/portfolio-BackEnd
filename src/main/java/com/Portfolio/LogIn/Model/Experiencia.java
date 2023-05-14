package com.Portfolio.LogIn.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String tipo;
    private String nombre;
    
    @DateTimeFormat(pattern = "yyyy")
    private String inicioPeriodo;
    
    @DateTimeFormat(pattern = "yyyy")
    private String finPeriodo;
}
