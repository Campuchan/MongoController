package eu.campuchan.MongoController.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * <h1>Servicio de Imagenes en Servidor Local</h1>
 * <a href="https://medium.com/@kkarththi15/saving-images-locally-in-a-spring-boot-web-application-01405a988bc7">articulo en Medium</a>
 */
@Service
public class ImageService {

    private final Path imageStoragePath = Paths.get("images");

    public String saveImageToStorage(MultipartFile imageFile) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        if (!Files.exists(imageStoragePath)) {
            Files.createDirectories(imageStoragePath);
        }

        Path filePath = imageStoragePath.resolve(uniqueFileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    public byte[] getImage(String imageName) throws IOException {
        Path imagePath = imageStoragePath.resolve(imageName);

        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        } else {
            return null;
        }
    }

    public String deleteImage(String imageName) throws IOException {
        Path imagePath = imageStoragePath.resolve(imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed";
        }
    }
}
