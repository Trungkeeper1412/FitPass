
import com.ks.fitpass.brand.dto.BrandDetailFeedback;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackPaginition;
import com.ks.fitpass.brand.dto.BrandDetailFeedbackStat;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.department.dto.DepartmentDTO;

import com.ks.fitpass.department.dto.ListBrandDepartmentFeedback;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.department.service.DepartmentService;

import com.ks.fitpass.web.controller.BrandController;
import com.ks.fitpass.web.controller.DepartmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BrandControllerTest {

    @Mock
    private BrandService brandService;
    @Mock
    private BrandAmenitieService brandAmenitieService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private Model model;
    @Mock
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDepartmentSuccess() {
        // Arrange
        int brandId = 1;

        Brand brand = new Brand();
        when(brandService.getOne(brandId)).thenReturn(brand);

        List<BrandAmenitie> brandAmenitieList = new ArrayList<>();
        when(brandAmenitieService.getAllByBrandIDActivate(brandId)).thenReturn(brandAmenitieList);

        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        when(departmentService.getDepartmentByBrandID(brandId)).thenReturn(departmentDTOList);

        List<ListBrandDepartmentFeedback> departmentFeedbackList = new ArrayList<>();
        when(departmentService.getDepartmentFeedbackOfBrandOwner(brandId)).thenReturn(departmentFeedbackList);

        BrandDetailFeedbackStat brandDetailFeedbackStat = new BrandDetailFeedbackStat();
        when(brandService.getFeedbackOfBrandDetailStat(brandId)).thenReturn(brandDetailFeedbackStat);

        List<DepartmentFeature> departmentFeatures = new ArrayList<>();
        when(departmentFeatureService.getDepartmentFeatures(anyInt())).thenReturn(departmentFeatures);

        // Act
        String result = brandController.getDepartment(brandId, model);

        // Assert
        assertEquals("gym-brand-details", result);

    }

    @Test
    public void testGetDepartmentWithDuplicateKeyException() {
        // Arrange
        int brandId = 1;
        when(brandService.getOne(brandId)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandController.getDepartment(brandId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetDepartmentWithEmptyResultDataAccessException() {
        // Arrange
        int brandId = 1;
        when(brandService.getOne(brandId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandController.getDepartment(brandId, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testGetDepartmentWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int brandId = 1;
        when(brandService.getOne(brandId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandController.getDepartment(brandId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testGetDepartmentWithDataAccessException() {
        // Arrange
        int brandId = 1;
        when(brandService.getOne(brandId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandController.getDepartment(brandId, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testGetBrandFeedbackSuccess() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        List<BrandDetailFeedback> brandDetailFeedbackList = new ArrayList<>();
        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenReturn(brandDetailFeedbackList);

        int total = 10;
        when(brandService.countTotalFeedback(brandId, sortRating)).thenReturn(total);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(brandDetailFeedbackList, result.getBody().getFeedbackList());
        assertEquals(2, result.getBody().getTotalPage()); // Assuming total/size = 10/7 = 1.43, rounded up to 2
        assertEquals(page, result.getBody().getCurrentPage());
    }

    @Test
    public void testGetBrandFeedbackWithEmptyResultDataAccessException() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testGetBrandFeedbackWithDuplicateKeyException() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testGetBrandFeedbackWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testGetBrandFeedbackWithIncorrectResultSizeDataAccess() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }


    @Test
    public void testGetBrandFeedbackWithIncorrectResult() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 3;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testGetBrandFeedbackWithDataAccessException() {
        // Arrange
        int brandId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "desc";

        when(brandService.getFeedbackOfBrandDetail(brandId, page, size, sortRating)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<BrandDetailFeedbackPaginition> result = brandController.getBrandFeedback(brandId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }


}
