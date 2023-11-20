import com.ks.fitpass.brand.dto.BrandPagnition;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.web.controller.HomepageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
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


}

