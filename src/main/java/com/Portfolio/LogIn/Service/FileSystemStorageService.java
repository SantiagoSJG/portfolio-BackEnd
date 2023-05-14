package com.Portfolio.LogIn.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {
    
    // Mapeamos ruta que viene desde application.properties
    @Value("${media.location}")
    private String mediaLocation;
    
    private Path rootLocation;

    // Cambiar nombre a imagen 5: Creamos funcion para obtener extension
    private String getFileExtension(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
    if (originalFileName == null) {
        throw new IllegalArgumentException("El nombre de archivo es nulo");
    }
    int lastDotIndex = originalFileName.lastIndexOf(".");
    if (lastDotIndex == -1) {
        throw new IllegalArgumentException("El archivo no tiene una extensión válida");
    }
    return originalFileName.substring(lastDotIndex + 1);
    }
    
    @Override
    @PostConstruct
    public void init() {
        rootLocation = Paths.get(mediaLocation);
        
        // BOT
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se puede crear el directorio raíz, " + e);
        }
    }

    @Override
    // Cambiar nombre a imagen 3: Agregamos parametro ID
    public String store(MultipartFile file, String id) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Error al guardar archivo vacio");
            }
            
            // Cambiar nombre a imagen 4: Nombre: id(nombre).extension
//            String filename = id + "." + getFileExtension(file);
            String filename = id;
//            String filename = file.getOriginalFilename();
        
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file, " + e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer archivo " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("No se puede leer archivo " + filename);
        }
    }

}
