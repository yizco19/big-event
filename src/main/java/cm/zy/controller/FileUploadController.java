package cm.zy.controller;

import cm.zy.pojo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @Value("${app.file.storage-path}")
    private String storagePath;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // almacenar el contenido del archivo a disco local
        String originalFilename = file.getOriginalFilename();
        // asegurar que el nombre de fichero es unico, para evitar sobreescribir archivos
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File(storagePath + filename));
        return Result.success(" Archivo subido con éxito. Acceso a la dirección del archivo");

    }
}
