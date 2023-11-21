import com.ks.fitpass.brand.dto.BrandPagnition;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentHomePagePagnition;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.web.controller.HomepageController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomepageControllerTest {

    @Mock
    private BrandService brandService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private HomepageController homepageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static final Logger logger = LoggerFactory.getLogger(HomepageController.class);
    @Test
    void testGetBrandWithPagination() {
        // Mock data
        List<Brand> mockBrandList = Arrays.asList(new Brand());
        Map<Integer, List<DepartmentDTO>> mockDepartmentMap = new HashMap<>();
        // Mocking the behavior of the brandService and departmentService
        when(brandService.getAllByStatus(anyInt(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(mockBrandList);
        when(brandService.countAllBrands(anyInt(), anyString())).thenReturn(mockBrandList.size());
        when(departmentService.getAllDepartmentByBrandId(anyInt(), anyInt(), anyInt())).thenReturn(Arrays.asList(new DepartmentDTO(/* populate with necessary values */)));

        // Calling the controller method
        ResponseEntity<BrandPagnition> responseEntity = homepageController.getBrandWithPagination(1, 2, "sortPrice", "sortRating");

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 is the expected HTTP status code
        BrandPagnition brandPagnition = responseEntity.getBody();
        // Perform assertions on brandPagnition based on your expectations
        assertEquals(mockBrandList, brandPagnition.getListBrand());

        // Logging for success
        logger.info("testGetBrandWithPagination passed successfully. HTTP Status: {}", responseEntity.getStatusCodeValue());

        // Logging for failure
        if (!mockBrandList.equals(brandPagnition.getListBrand())) {
            logger.error("testGetBrandWithPagination failed. Expected: {}, Actual: {}", mockBrandList, brandPagnition.getListBrand());
        }
    }
    @Test
    public void getBrandWithPagination_brandServiceReturnsEmpty_okResponseWithEmptyData() {

        // Arrange
        Mockito.when(brandService.getAllByStatus(1, 1, 2, null, null))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<BrandPagnition> response = homepageController.getBrandWithPagination(1, 2, null, null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Collections.emptyList(), response.getBody().getListBrand());
    }

    @Test
    public void getBrandWithPagination_negativePageNumber_badRequest() {

        // Arrange
        Mockito.when(brandService.getAllByStatus(1, -1, 2, null, null))
                .thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<BrandPagnition> response = homepageController.getBrandWithPagination(-1, 2, null, null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getBrandWithPagination_zeroPageSize_badRequest() {

        // Arrange
        Mockito.when(brandService.getAllByStatus(1, 1, 0, null, null))
                .thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<BrandPagnition> response = homepageController.getBrandWithPagination(1, 0, null, null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void testGetNearByDepartmentList() {
        // Mock data
        List<Department> mockDepartmentList = Collections.singletonList(new Department());
        // Mocking the behavior of the departmentService
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockDepartmentList);
        when(departmentService.countAllDepartment(anyInt(), anyString(), anyString(), anyString(), anyDouble(), anyDouble(), anyString()))
                .thenReturn(mockDepartmentList.size());

        // Calling the controller method
        ResponseEntity<DepartmentHomePagePagnition> responseEntity = homepageController.getNearByDepartmentList(0.0, 0.0, 1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 is the expected HTTP status code
        DepartmentHomePagePagnition departmentHomePagePagnition = responseEntity.getBody();
        // Perform assertions on departmentHomePagePagnition based on your expectations
        assertEquals(mockDepartmentList, departmentHomePagePagnition.getDepartmentList());
    }

    @Test
    public void getNearByDepartmentList_noDepartments_okResponseWithEmptyData() {

        // Arrange
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 0.0, 1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Collections.emptyList(), response.getBody().getDepartmentList());
    }

    @Test
    public void getNearByDepartmentList_negativePageSize_badRequest() {

        // Arrange
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 0.0, -1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    public void getNearByDepartmentList_zeroPageSize_badRequest() {

        // Arrange
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new IllegalArgumentException());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 0.0, 1, 0, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getNearByDepartmentList_internalServerError() {

        // Arrange
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Internal Server Error"));

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 0.0, 1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void getNearByDepartmentList_invalidCoordinates_badRequest() {
        // Arrange
        // Assuming that your application restricts invalid coordinates (latitude and longitude) values
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("Invalid coordinates"));

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(-91.0, 181.0, 1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void getNearByDepartmentList_largePageSize_successfulResponseWithLimitedResults() {
        // Arrange
        List<Department> mockDepartmentList = Arrays.asList(new Department(), new Department(), new Department());
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockDepartmentList);
        when(departmentService.countAllDepartment(anyInt(), anyString(), anyString(), anyString(), anyDouble(), anyDouble(), anyString()))
                .thenReturn(mockDepartmentList.size());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 0.0, 1, 10_000, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockDepartmentList, response.getBody().getDepartmentList());
        // Additional assertions based on your expectations
    }

    //boundary

    @Test
    public void getNearByDepartmentList_outOfRangeLongitude_badRequest() {
        // Arrange
        // Assuming that your application restricts longitude values to a valid range
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(0.0, 181.0, 1, 2, "City", "lowToHigh", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }



    @Test
    public void getNearByDepartmentList_outOfRangeLatitude_badRequest() {
        // Arrange
        // Assuming that your application restricts latitude values to a valid range
        when(departmentService.getAllDepartmentByNearbyLocation(anyInt(), anyInt(), anyDouble(), anyDouble(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<DepartmentHomePagePagnition> response = homepageController.getNearByDepartmentList(-91.0, 0.0, 1, 2, "City", "sortPrice", "sortRating", "10");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}


