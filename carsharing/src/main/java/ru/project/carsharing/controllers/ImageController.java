package ru.project.carsharing.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.project.carsharing.model.Image;
import ru.project.carsharing.services.ImageService;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFullName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Boolean delete(@PathVariable Long id){
        return imageService.deleteById(id);
    }
}
