package ru.project.carsharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.project.carsharing.exception.NotFoundException;
import ru.project.carsharing.model.Image;
import ru.project.carsharing.repositories.ImageRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image getImageById(Long id){
        return imageRepository.findById(id).orElseThrow(()->new NotFoundException());
    }

    public void addImage(Image image){
        imageRepository.save(image);
    }

    public Image toImage(MultipartFile file){
        try {
            Image image = new Image();
            image.setName(file.getName());
            image.setOriginalFullName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            return image;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(Long id){
        try {
            imageRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
