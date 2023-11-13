package com.ks.fitpass.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    //private static final String UPLOAD_DIRECTORY = "fitpass-web/src/main/resources/static/images/";
    private static final String UPLOAD_DIRECTORY = "upload/img/";

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String originalFileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIRECTORY, originalFileName);

            try (var stream = Files.newOutputStream(filePath)) {
                stream.write(file.getBytes());
            }

            return ResponseEntity.ok("File uploaded successfully: " + originalFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @PostMapping("/{type}/{brandId}")
    public ResponseEntity<String> uploadBrandImage(
            @PathVariable String type,
            @PathVariable int brandId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String originalFileName = file.getOriginalFilename();

            // Kiểm tra định dạng của tệp
            String fileExtension = getFileExtension(originalFileName);
            if (!isValidFileExtension(fileExtension)) {
                return ResponseEntity.badRequest().body("Invalid file format. Only PNG and JPEG files are allowed.");
            }

            // Xác định thư mục dựa trên loại hình ảnh
            String directory = type.equals("logo")
                    ? "logos"
                    : type.equals("thumbnail")
                    ? "thumbnails"
                    : type.equals("wallpaper")
                    ? "wallpapers"
                    : "";

            if (!directory.isEmpty()) {
                // Tạo thư mục nếu nó chưa tồn tại
                Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "brands/", directory);
                if (!Files.exists(brandDirectoryPath)) {
                    Files.createDirectories(brandDirectoryPath);
                }

                String fileName = type.equals("logo")
                        ? "logo"
                        : type.equals("thumbnail")
                        ? "thumbnail"
                        : type.equals("wallpaper")
                        ? "wallpaper"
                        : "";

                // Xác định đường dẫn tới tệp đã tải lên
                Path filePath = brandDirectoryPath.resolve(brandId + "_" + fileName + "." + fileExtension);

                // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Trả về đường dẫn tới tệp đã tải lên
                String uploadedFilePath = "/img/brands/" + directory + "/" + brandId + "_" + fileName + "." + fileExtension;

                return ResponseEntity.ok(uploadedFilePath);
            } else {
                return ResponseEntity.badRequest().body("Invalid image type");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot >= 0) {
            return fileName.substring(lastIndexOfDot + 1);
        }
        return "";
    }

    private boolean isValidFileExtension(String fileExtension) {
        return "png".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension);
    }
}

