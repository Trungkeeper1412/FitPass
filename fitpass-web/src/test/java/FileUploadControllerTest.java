import com.ks.fitpass.web.controller.FileUploadController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FileUploadControllerTest {

    @Mock
    private MultipartFile file;

    @Mock
    private Path mockPath;
    @InjectMocks
    private FileUploadController fileUploadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testUploadFileEmptyFile() {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadFile(file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload", responseEntity.getBody());
    }

    @Test
    void testUploadFileIOException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.txt");
        doThrow(IOException.class).when(file).getBytes();

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadFile(file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to upload file", responseEntity.getBody());
    }



    @Test
    void testUploadBrandImageEmptyFile() {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload", responseEntity.getBody());
    }

    @Test
    void testUploadBrandImageInvalidFileFormat() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.txt");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid file format. Only PNG and JPEG files are allowed.", responseEntity.getBody());
    }

    @Test
    void testUploadBrandImageInvalidImageType() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandImage("invalidType", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid image type", responseEntity.getBody());
    }

    @Test
    void testUploadBrandImageIOException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getInputStream()).thenThrow(IOException.class);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to upload file", responseEntity.getBody());
    }



    @Test
    void testUploadDepartmentImageEmptyFile() {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadDepartmentImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload", responseEntity.getBody());
    }

    @Test
    void testUploadDepartmentImageInvalidFileFormat() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.txt");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadDepartmentImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid file format. Only PNG and JPEG files are allowed.", responseEntity.getBody());
    }

    @Test
    void testUploadDepartmentImageInvalidImageType() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadDepartmentImage("invalidType", 1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid image type", responseEntity.getBody());
    }

    @Test
    void testUploadDepartmentImageIOException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getInputStream()).thenThrow(IOException.class);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadDepartmentImage("logo", 1, file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to upload file", responseEntity.getBody());
    }

    @Test
    void testUploadAvatarEmptyFile() {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadAvatar(file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload", responseEntity.getBody());
    }

    @Test
    void testUploadAvatarInvalidFileFormat() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.txt");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadAvatar(file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid file format. Only PNG and JPEG files are allowed.", responseEntity.getBody());
    }

    @Test
    void testUploadAvatarIOException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getInputStream()).thenThrow(IOException.class);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadAvatar(file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to upload file", responseEntity.getBody());
    }



    @Test
    void testUploadBrandAmenitiesEmptyFile() {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandAmenities(1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Please select a file to upload", responseEntity.getBody());
    }

    @Test
    void testUploadBrandAmenitiesInvalidFileFormat() {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.txt");

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandAmenities(1, file);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid file format. Only PNG and JPEG files are allowed.", responseEntity.getBody());
    }

    @Test
    void testUploadBrandAmenitiesIOException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getInputStream()).thenThrow(IOException.class);

        // Act
        ResponseEntity<String> responseEntity = fileUploadController.uploadBrandAmenities(1, file);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to upload file", responseEntity.getBody());
    }




}
