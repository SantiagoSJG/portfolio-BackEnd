package com.Portfolio.LogIn.Controller;

import com.Portfolio.LogIn.Model.Proyecto;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Portfolio.LogIn.Repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    @Autowired
    private ProyectoRepository proyectoRepo;
    
    @GetMapping("/traer")
    public List<Proyecto> traerProyectos() {
        return proyectoRepo.findAll();
    }
    
    @PostMapping("/agregar")
    public Proyecto añadirProyecto(@RequestBody Proyecto proyecto) {
        proyectoRepo.save(proyecto);
        
//        return ResponseEntity.ok("{ \"message\": \"El proyecto ha sido eliminada correctamente\" }");

        return proyecto;
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProyecto(@PathVariable Integer id, @RequestBody Proyecto nuevoProyecto) {
        Proyecto proyecto = proyectoRepo.findById(id).orElse(null);
        
        proyecto.setEnlace(nuevoProyecto.getEnlace());
        proyecto.setNombre(nuevoProyecto.getNombre());
        proyecto.setDescripcion(nuevoProyecto.getDescripcion());
        proyecto.setLenguajes(nuevoProyecto.getLenguajes());
        
        proyectoRepo.save(proyecto);
        
//        String mensaje = "El proyecto " + proyecto + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"El proyecto ha sido editada correctamente\" }");
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Integer id) {
        Proyecto proyecto = proyectoRepo.findById(id).orElse(null);
        
        proyectoRepo.deleteById(proyecto.getId());
        
//        String mensaje = "El proyecto " + proyecto + " ha sido eliminada.";
//        return new ResponseEntity<>(mensaje, HttpStatus.OK);

        return ResponseEntity.ok("{ \"message\": \"El proyecto ha sido eliminada correctamente\" }");
    }
}