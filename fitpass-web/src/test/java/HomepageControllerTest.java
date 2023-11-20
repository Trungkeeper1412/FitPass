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
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class HomepageControllerTest {

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
        List<Brand> mockBrandList = Arrays.asList(new Brand(/* populate with necessary values */));
        Map<Integer, List<DepartmentDTO>> mockDepartmentMap = new HashMap<>();
        // Populate mockDepartmentMap with necessary data

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
}

