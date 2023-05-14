package com.Portfolio.LogIn.Controller;

import com.Portfolio.LogIn.Service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("media")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class MediaController {
    private final StorageService storage;
    private final HttpServletRequest request;
    
    @PostMapping("upload")
    // Cambiar nombre a imagen 1: Agregar RequestParam id
    public Map<String, String> uploadFile(@RequestParam("id") String id, @RequestParam("file") MultipartFile multipartFile) {
        
    // Cambiar nombre a imagen 2: Agregar id a servicio
        String path = storage.store(multipartFile, id);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        
        
        
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        
        return Map.of("url", url);
    }
    
//    @GetMapping("{filename:.+}")
//    public ResponseEntity<Map<String, Object>> getFile(@PathVariable String filename) throws IOException {
//        Resource file = storage.loadAsResource(filename);
//        String contentType = Files.probeContentType(file.getFile().toPath());
//        
//        Map<String, Object> response = new HashMap<>();
//        response.put("name", filename);
//        response.put("size", file.contentLength());
//        response.put("contentType", contentType);
//        response.put("url", "http://localhost:8080/media/" + filename);
//        
//        return ResponseEntity.ok().body(response);
//    }
    
    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = storage.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
//    
}
