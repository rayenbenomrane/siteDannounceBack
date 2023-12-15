package com.example.demo.service;

import com.example.demo.Dao.ImageRepo;
import com.example.demo.model.Image;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageService {

    private final ImageRepo imageRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    public List<Image> getAllImages() {
        return imageRepo.findAll();
    }

    public Optional<Image> getImageById(int id) {
        return imageRepo.findById(id);
    }

    public Image saveImage(Image image) {
        
        return imageRepo.save(image);
    }

    public void deleteImage(int id) {
        imageRepo.deleteById(id);
    }
   
 
    
}