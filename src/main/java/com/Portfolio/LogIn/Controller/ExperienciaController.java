package com.Portfolio.LogIn.Controller;

import com.Portfolio.LogIn.Model.Experiencia;
import com.Portfolio.LogIn.Repository.ExperienciaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {
    @Autowired
    private ExperienciaRepository experienciaRepo;
    
    @GetMapping("/traer")
    public List<Experiencia> traerExperiencias() {
        return experienciaRepo.findAll();
    }
    
    @PostMapping("/agregar")
    public Experiencia añadirExperiencia(@RequestBody Experiencia experiencia) {
        experienciaRepo.save(experiencia);
        
//        return ResponseEntity.ok("{ \"message\": \"La experiencia ha sido eliminada correctamente\" }");

        return experiencia;
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarExperiencia(@PathVariable Integer id, @RequestBody Experiencia nuevaExperiencia) {
        Experiencia experiencia = experienciaRepo.findById(id).orElse(null);
        
        experiencia.setNombre(nuevaExperiencia.getNombre());
        experiencia.setTipo(nuevaExperiencia.getTipo());
        experiencia.setInicioPeriodo(nuevaExperiencia.getInicioPeriodo());
        experiencia.setFinPeriodo(nuevaExperiencia.getFinPeriodo());
        
        experienciaRepo.save(experiencia);
        
//        String mensaje = "La experiencia " + experiencia + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La experiencia ha sido editada correctamente\" }");
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarExperiencia(@PathVariable Integer id) {
        Experiencia experiencia = experienciaRepo.findById(id).orElse(null);
        
        experienciaRepo.deleteById(experiencia.getId());
        
//        String mensaje = "La experiencia " + experiencia + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La experiencia ha sido eliminada correctamente\" }");
    }
}