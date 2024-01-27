package com.ks.fitpass.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    //private static final String UPLOAD_DIRECTORY = "fitpass-web/src/main/resources/static/images/";
    private static final String UPLOAD_DIRECTORY = "upload/img/";
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private String determineDirectoryBasedOnType(String type) {
        switch (type) {
            case "logo":
                return "logos";
            case "thumbnail":
                return "thumbnails";
            case "wallpaper":
                return "wallpapers";
            default:
                return ""; // Invalid type
        }
    }
    private String determineFileNameBasedOnType(String type) {
        switch (type) {
            case "logo":
                return "logo";
            case "thumbnail":
                return "thumbnail";
            case "wallpaper":
                return "wallpaper";
            default:
                return ""; // Invalid type
        }
    }
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

//    @PostMapping("/{type}/{brandId}")
//    public ResponseEntity<String> uploadBrandImage(
//            @PathVariable String type,
//            @PathVariable int brandId,
//            @RequestParam("file") MultipartFile file) {
//
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("Please select a file to upload");
//        }
//
//        try {
//            String originalFileName = file.getOriginalFilename();
//
//            // Kiểm tra định dạng của tệp
//            String fileExtension = getFileExtension(originalFileName);
//            if (!isValidFileExtension(fileExtension)) {
//                return ResponseEntity.badRequest().body("Invalid file format. Only PNG and JPEG files are allowed.");
//            }
//
//            // Xác định thư mục dựa trên loại hình ảnh
//            String directory = type.equals("logo")
//                    ? "logos"
//                    : type.equals("thumbnail")
//                    ? "thumbnails"
//                    : type.equals("wallpaper")
//                    ? "wallpapers"
//                    : "";
//
//            if (!directory.isEmpty()) {
//                // Tạo thư mục nếu nó chưa tồn tại
//                Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "brands/", directory);
//                if (!Files.exists(brandDirectoryPath)) {
//                    Files.createDirectories(brandDirectoryPath);
//                }
//
//                String fileName = type.equals("logo")
//                        ? "logo"
//                        : type.equals("thumbnail")
//                        ? "thumbnail"
//                        : "wallpaper";
//
//                // Xác định đường dẫn tới tệp đã tải lên
//                Path filePath = brandDirectoryPath.resolve(brandId + "_" + fileName + "." + fileExtension);
//
//                // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
//                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                // Trả về đường dẫn tới tệp đã tải lên
//                String uploadedFilePath = "/img/brands/" + directory + "/" + brandId + "_" + fileName + "." + fileExtension;
//
//                return ResponseEntity.ok(uploadedFilePath);
//            } else {
//                return ResponseEntity.badRequest().body("Invalid image type");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Failed to upload file");
//        }
//    }
@PostMapping("/{type}/{brandId}")
public ResponseEntity<String> uploadBrandImage(
        @PathVariable String type,
        @PathVariable int brandId,
        @RequestParam("file") MultipartFile file) {

    logger.info("Received a request to upload an image for brandId: {} and image type: {}", brandId, type);

    if (file.isEmpty()) {
        logger.warn("File upload failed because the file was empty");
        return ResponseEntity.badRequest().body("Please select a file to upload");
    }

    try {
        String originalFileName = file.getOriginalFilename();
        logger.info("Original file name: {}", originalFileName);

        // Kiểm tra định dạng của tệp
        String fileExtension = getFileExtension(originalFileName);
        if (!isValidFileExtension(fileExtension)) {
            logger.warn("Invalid file format for file: {}", originalFileName);
            return ResponseEntity.badRequest().body("Invalid file format. Only PNG and JPEG files are allowed.");
        }

        // Xác định thư mục dựa trên loại hình ảnh
        String directory = determineDirectoryBasedOnType(type);
        logger.info("Determined directory: {}", directory);

        if (!directory.isEmpty()) {
            // Tạo thư mục nếu nó chưa tồn tại
            Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "brands/", directory);
            if (!Files.exists(brandDirectoryPath)) {
                logger.info("Directory does not exist, creating: {}", brandDirectoryPath);
                Files.createDirectories(brandDirectoryPath);
            }

            String fileName = determineFileNameBasedOnType(type);
            // Xác định đường dẫn tới tệp đã tải lên
            Path filePath = brandDirectoryPath.resolve(brandId + "_" + fileName + "." + fileExtension);
            logger.info("Resolved file path: {}", filePath);

            // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Successfully copied file to: {}", filePath);

            // Trả về đường dẫn tới tệp đã tải lên
            String uploadedFilePath = "/img/brands/" + directory + "/" + brandId + "_" + fileName + "." + fileExtension;
            logger.info("Returning uploaded file path: {}", uploadedFilePath);

            return ResponseEntity.ok(uploadedFilePath);
        } else {
            logger.warn("Invalid image type: {}", type);
            return ResponseEntity.badRequest().body("Invalid image type");
        }
    } catch (IOException e) {
        logger.error("Failed to upload file due to IOException", e);
        return ResponseEntity.status(500).body("Failed to upload file");
    }
}

    @PostMapping("/department/{type}/{departmentId}")
    public ResponseEntity<String> uploadDepartmentImage(
            @PathVariable String type,
            @PathVariable int departmentId,
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
                    : type.equals("album")
                    ? "albums"
                    : "";

            if (!directory.isEmpty()) {
                // Tạo thư mục nếu nó chưa tồn tại
                Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "departments/", directory);
                if (!Files.exists(brandDirectoryPath)) {
                    Files.createDirectories(brandDirectoryPath);
                }

                String fileName = type.equals("logo")
                        ? "logo"
                        : type.equals("thumbnail")
                        ? "thumbnail"
                        : type.equals("wallpaper")
                        ? "wallpaper"
                        : type.equals("album")
                        ? "albums_" + System.currentTimeMillis()
                        : "";

                // Xác định đường dẫn tới tệp đã tải lên
                Path filePath = brandDirectoryPath.resolve(departmentId + "_" + fileName + "." + fileExtension);

                // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // Trả về đường dẫn tới tệp đã tải lên
                String uploadedFilePath = "/img/departments/" + directory + "/" + departmentId + "_" + fileName + "." + fileExtension;

                return ResponseEntity.ok(uploadedFilePath);
            } else {
                return ResponseEntity.badRequest().body("Invalid image type");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @PostMapping("/uploadAvatar")
    public ResponseEntity<String> uploadAvatar(
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

            // Tạo thư mục nếu nó chưa tồn tại
            Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "user/");
            if (!Files.exists(brandDirectoryPath)) {
                Files.createDirectories(brandDirectoryPath);
            }

            String fileName = "avatar";
            long userId = System.currentTimeMillis();

            // Xác định đường dẫn tới tệp đã tải lên
            Path filePath = brandDirectoryPath.resolve(userId + "_" + fileName + "." + fileExtension);

            // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn tới tệp đã tải lên
            String uploadedFilePath = "/img/user/" + userId + "_" + fileName + "." + fileExtension;

            return ResponseEntity.ok(uploadedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @PostMapping("/uploadImageBrandAmenities/{amenitieId}")
    public ResponseEntity<String> uploadBrandAmenities(
            @PathVariable int amenitieId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        try {
            String originalFileName = file.getOriginalFilename();

            // Kiểm tra định dạng của tệp
            String fileExtension = getFileExtension(originalFileName);
            if (!isValidFileExtension(fileExtension)) {
                return ResponseEntity.badRequest().body("Invalid file format. Only JPG, PNG and JPEG files are allowed.");
            }

            // Tạo thư mục nếu nó chưa tồn tại
            Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "brandAmenities/");
            if (!Files.exists(brandDirectoryPath)) {
                Files.createDirectories(brandDirectoryPath);
            }

            String fileName = "brandAmenities";
            long time = System.currentTimeMillis();

            // Xác định đường dẫn tới tệp đã tải lên
            Path filePath = brandDirectoryPath.resolve(time + "_" + fileName + "." + fileExtension);

            // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn tới tệp đã tải lên
            String uploadedFilePath = "/img/brandAmenities/" + time + "_" + fileName + "." + fileExtension;

            return ResponseEntity.ok(uploadedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @PostMapping("/uploadImageFeatures/{featureId}")
    public ResponseEntity<String> uploadFeatures(
            @PathVariable int featureId,
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

            // Tạo thư mục nếu nó chưa tồn tại
            Path brandDirectoryPath = Paths.get(UPLOAD_DIRECTORY + "features/");
            if (!Files.exists(brandDirectoryPath)) {
                Files.createDirectories(brandDirectoryPath);
            }

            String fileName = "features";
            long time = System.currentTimeMillis();

            // Xác định đường dẫn tới tệp đã tải lên
            Path filePath = brandDirectoryPath.resolve(time + "_" + fileName + "." + fileExtension);

            // Sử dụng StandardCopyOption.REPLACE_EXISTING để ghi đè tệp nếu nó tồn tại
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn tới tệp đã tải lên
            String uploadedFilePath = "/img/features/" + time + "_" + fileName + "." + fileExtension;

            return ResponseEntity.ok(uploadedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
    }

    @PostMapping("/deleteImage")
    public ResponseEntity<String> deleteImage(@RequestBody Map<String, String> imagePath) {
        File imageFile = new File("upload/" + imagePath.get("imagePath"));

        if (imageFile.exists()) {
            if (imageFile.delete()) {
                return ResponseEntity.ok("Tệp hình ảnh đã được xóa thành công.");
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa tệp hình ảnh.");
            }
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy tệp hình ảnh.") ;
        }
    }


    private String getFileExtension(String fileName) {
        try {
            int lastIndexOfDot = fileName.lastIndexOf(".");
            if (lastIndexOfDot >= 0) {
                return fileName.substring(lastIndexOfDot + 1);
            }
            return "";
        }catch (IndexOutOfBoundsException e) {
            // Handle the IndexOutOfBoundsException
            System.err.println("Error: IndexOutOfBoundsException occurred");
            e.printStackTrace(); // You can log or print the stack trace for debugging
            return ""; // Or throw a custom exception, depending on your requirements
        } catch (Exception e) {
            // Handle other exceptions if necessary
            System.err.println("Error: An unexpected exception occurred");
            e.printStackTrace(); // You can log or print the stack trace for debugging
            return ""; // Or throw a custom exception, depending on your requirements
        }
    }

    private boolean isValidFileExtension(String fileExtension) {
        return "png".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension);
    }
}

