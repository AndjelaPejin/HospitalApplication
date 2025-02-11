package com.example.hospitalApi.service;

import com.example.hospitalApi.model.Doctor;
import com.example.hospitalApi.repository.IDoctorRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoUploadServiceImpl implements IPhotoUploadService{

    private final IDoctorRepo doctorRepo;
    public static final String PHOTO_DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";

    @Override
    public String uploadPhoto(String id, MultipartFile file) {
        log.info("Saving picture for user ID: {}", id);
        Doctor doctor = doctorRepo.getById(id);
        String photoUrl = photoFunction.apply(id,file);
        doctor.setPhotoUrl(photoUrl);
        doctorRepo.save(doctor);

        return photoUrl;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id,image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try{
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);}
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/doctor/image/" + filename).toUriString();
        } catch (Exception exception){
            throw new RuntimeException("Unable to save image.");
        }

    };
}
