package com.Portfolio.LogIn.Controller;

import com.Portfolio.LogIn.Model.Educacion;
import com.Portfolio.LogIn.Repository.EducacionRepository;
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
@RequestMapping("/educacion")
public class EducacionController {
    @Autowired
    private EducacionRepository educacionRepo;
    
    @GetMapping("/traer")
    public List<Educacion> traerEducaciones() {
        return educacionRepo.findAll();
    }
    
    @PostMapping("/agregar")
    public Educacion añadirEducacion(@RequestBody Educacion educacion) {
        educacionRepo.save(educacion);
        
//        return ResponseEntity.ok("{ \"message\": \"La educacion ha sido eliminada correctamente\" }");

        return educacion;
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarEducacion(@PathVariable Integer id, @RequestBody Educacion nuevaEducacion) {
        Educacion educacion = educacionRepo.findById(id).orElse(null);
        
        educacion.setNombre(nuevaEducacion.getNombre());
        educacion.setTipo(nuevaEducacion.getTipo());
        educacion.setInicioPeriodo(nuevaEducacion.getInicioPeriodo());
        educacion.setFinPeriodo(nuevaEducacion.getFinPeriodo());
        
        educacionRepo.save(educacion);
        
//        String mensaje = "La educacion " + educacion + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La educacion ha sido editada correctamente\" }");
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEducacion(@PathVariable Integer id) {
        Educacion educacion = educacionRepo.findById(id).orElse(null);
        
        educacionRepo.deleteById(educacion.getId());
        
//        String mensaje = "La educacion " + educacion + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"La educacion ha sido eliminada correctamente\" }");
    }
}