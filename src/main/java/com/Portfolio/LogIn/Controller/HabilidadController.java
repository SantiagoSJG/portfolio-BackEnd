package com.Portfolio.LogIn.Controller;

import com.Portfolio.LogIn.Model.Habilidad;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Portfolio.LogIn.Repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/habilidades")
public class HabilidadController {
    @Autowired
    private HabilidadRepository habilidadRepo;
    
    @GetMapping("/traer")
    public List<Habilidad> traerHabilidades() {
        return habilidadRepo.findAll();
    }
    
    @PostMapping("/agregar")
    public Habilidad añadirHabilidad(@RequestBody Habilidad habilidad) {
        habilidadRepo.save(habilidad);
        
//        return ResponseEntity.ok("{ \"message\": \"La habilidad ha sido eliminada correctamente\" }");

        return habilidad;
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarHabilidad(@PathVariable Integer id, @RequestBody Habilidad nuevaHabilidad) {
        Habilidad habilidad = habilidadRepo.findById(id).orElse(null);
        
        habilidad.setNivel(nuevaHabilidad.getNivel());
        
        habilidadRepo.save(habilidad);
        
//        String mensaje = "La habilidad " + habilidad + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La habilidad ha sido editada correctamente\" }");
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarHabilidad(@PathVariable Integer id) {
        Habilidad habilidad = habilidadRepo.findById(id).orElse(null);
        
        habilidadRepo.deleteById(habilidad.getId());
        
//        String mensaje = "La habilidad " + habilidad + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La habilidad ha sido eliminada correctamente\" }");
    }
}
