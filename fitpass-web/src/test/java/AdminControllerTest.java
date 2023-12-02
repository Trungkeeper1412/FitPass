
import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.web.controller.AdminController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminControllerTest {


    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private Model model;
    @Mock
    private BrandService brandService;

    @InjectMocks
    private AdminController adminController;

    @Captor
    private ArgumentCaptor<List<BrandAdminList>> brandListCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAdminIndex() {
        //act
        String result = adminController.getAdminIndex();
        //assert
        assertEquals("admin/index", result);
    }

    @Test
    public void testGetFeature() {
        //Arrange
        List<Feature> featureList = new ArrayList<>();
        featureList.add(new Feature());
        when(departmentFeatureService.getAllFeatureNoStatus()).thenReturn(featureList);

        //Act
        String result = adminController.getFeature(model);

        //Assert
        assertEquals("admin/admin-feature", result);

    }
    
    @Test
    public void testGetFeatureWithDuplicateKeyException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(DuplicateKeyException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testGetFeatureWithEmptyResultDataAccessException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/no-data", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testGetFeatureWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testAddFeatureSuccess() {
        // Arrange
        when(departmentFeatureService.insertFeature(any())).thenReturn(1);

        // Act
        String result = adminController.addFeature("TestFeature", "test-icon");

        // Assert
        assertEquals("redirect:/admin/feature", result);
        verify(departmentFeatureService, times(1)).insertFeature(any());
    }

    @Test
    public void testAddFeatureDuplicateKeyException() {
        // Arrange
        when(departmentFeatureService.insertFeature(any())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = adminController.addFeature("TestFeature", "test-icon");

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(departmentFeatureService, times(1)).insertFeature(any());
    }

    @Test
    public void testAddFeatureEmptyResultDataAccessException() {
        // Arrange
        when(departmentFeatureService.insertFeature(any())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = adminController.addFeature("TestFeature", "test-icon");

        // Assert
        assertEquals("error/no-data", result);
        verify(departmentFeatureService, times(1)).insertFeature(any());
    }

    @Test
    public void testAddFeatureIncorrectResultSizeDataAccessException() {
        // Arrange
        when(departmentFeatureService.insertFeature(any())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = adminController.addFeature("TestFeature", "test-icon");

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
        verify(departmentFeatureService, times(1)).insertFeature(any());
    }
    @Test
    public void testGetFeatureDetailSuccess() {
        // Arrange
        int featureId = 1;
        Feature expectedFeature = new Feature();
        when(departmentFeatureService.getByFeatureId(featureId)).thenReturn(expectedFeature);

        // Act
        ResponseEntity<Feature> result = adminController.getFeatureDetail(featureId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedFeature, result.getBody());
        verify(departmentFeatureService, times(1)).getByFeatureId(featureId);
    }

    @Test
    public void testGetFeatureDetailEmptyResultDataAccessException() {
        // Arrange
        int featureId = 1;
        when(departmentFeatureService.getByFeatureId(featureId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<Feature> result = adminController.getFeatureDetail(featureId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assertions.assertNull(result.getBody());
        verify(departmentFeatureService, times(1)).getByFeatureId(featureId);
    }

    @Test
    public void testGetFeatureDetailDuplicateKeyException() {
        // Arrange
        int featureId = 1;
        when(departmentFeatureService.getByFeatureId(featureId)).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<Feature> result = adminController.getFeatureDetail(featureId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertNull(result.getBody());
        verify(departmentFeatureService, times(1)).getByFeatureId(featureId);
    }

    @Test
    public void testGetFeatureDetailIncorrectResultSizeDataAccessException() {
        // Arrange
        int featureId = 1;
        when(departmentFeatureService.getByFeatureId(featureId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<Feature> result = adminController.getFeatureDetail(featureId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNull(result.getBody());
        verify(departmentFeatureService, times(1)).getByFeatureId(featureId);
    }


    @Test
    public void testUpdateFeatureSuccess() {
        // Arrange
        Feature featureToUpdate = new Feature();
        when(departmentFeatureService.updateFeature(featureToUpdate)).thenReturn(1);

        // Act
        ResponseEntity<String> result = adminController.updateFeature(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Update feature success", result.getBody());
        verify(departmentFeatureService, times(1)).updateFeature(featureToUpdate);
    }

    @Test
    public void testUpdateFeatureDataAccessException() {
        // Arrange
        Feature featureToUpdate = new Feature();
        when(departmentFeatureService.updateFeature(featureToUpdate)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<String> result = adminController.updateFeature(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(departmentFeatureService, times(1)).updateFeature(featureToUpdate);
    }

    // CustomDataAccessException implementation for testing
    private static class CustomDataAccessException extends DataAccessException {
        public CustomDataAccessException(String msg) {
            super(msg);
        }
    }
    @Test
    public void testUpdateFeatureStatusSuccess() {
        // Arrange
        Feature featureToUpdate = new Feature();
        featureToUpdate.setFeatureID(1); // Set a valid feature ID for testing
        featureToUpdate.setFeatureStatus(1); // Set a valid feature status for testing

        // Act
        ResponseEntity<String> result = adminController.updateFeatureStatus(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Update feature status success", result.getBody());
        verify(departmentFeatureService, times(1)).updateFeatureStatus(1, 1);
    }

    @Test
    public void testUpdateFeatureStatusDataAccessException() {
        // Arrange
        Feature featureToUpdate = new Feature();
        featureToUpdate.setFeatureID(1); // Set a valid feature ID for testing
        featureToUpdate.setFeatureStatus(1); // Set a valid feature status for testing
        when(departmentFeatureService.updateFeatureStatus(1, 1)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<String> result = adminController.updateFeatureStatus(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(departmentFeatureService, times(1)).updateFeatureStatus(1, 1);
    }

    @Test
    public void testGetBrandListSuccess() {
        // Arrange
        List<BrandAdminList> mockBrandList = Arrays.asList(new BrandAdminList(), new BrandAdminList());
        when(brandService.getAllBrand()).thenReturn(mockBrandList);

        // Act
        String resultView = adminController.getBrandList(model);

        // Assert
        assertEquals("admin/admin-brand-list", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, times(1)).addAttribute(eq("brandList"), brandListCaptor.capture());
        List<BrandAdminList> capturedBrandList = brandListCaptor.getValue();
        assertEquals(mockBrandList, capturedBrandList);
    }

    @Test
    public void testGetBrandListDataAccessException() {
        // Arrange
        when(brandService.getAllBrand()).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getBrandList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, never()).addAttribute(anyString(), any());

    }


}





