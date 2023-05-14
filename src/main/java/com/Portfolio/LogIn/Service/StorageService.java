package com.Portfolio.LogIn.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();
    
    String store(MultipartFile file, String id);
    
    Resource loadAsResource(String filename);
}
