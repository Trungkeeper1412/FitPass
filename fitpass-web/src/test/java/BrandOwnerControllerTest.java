
import com.ks.fitpass.brand.dto.BrandOwnerProfile;
import com.ks.fitpass.brand.dto.ServiceUpdateDTO;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.controller.BrandOwnerController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BrandOwnerControllerTest {

    @Mock
    private BrandService brandService;
    @Mock
    private HttpSession session;
    @Mock
    private Model model;

    @Mock
    private BrandAmenitieService brandAmenitieService;

    @Mock
    private GymPlanService gymPlanService;
    @Mock
    private DepartmentScheduleService departmentScheduleService;
    @Mock
    private DepartmentAlbumsService departmentAlbumsService;
    @Mock
    private DepartmentAmenitieService departmentAmenitieService;
    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private BrandOwnerController brandOwnerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBrandProfileSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        Brand brandDetails = new Brand();
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brandDetails);

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("brand-owner/gym-brand-update-profile", result);
        verify(model, times(1)).addAttribute(eq("brandDetails"), eq(brandDetails));
        verify(model, times(1)).addAttribute(eq("time"), any(Long.class));
    }

    @Test
    public void testGetBrandProfileWithDuplicateKeyException() {
        User user = new User();
        user.setUserId(1);
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetBrandProfileWithEmptyResultDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testGetBrandProfileWithIncorrectResultSizeDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testGetBrandProfileWithDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testUpdateBrandProfileSuccess() {
        // Arrange
        BrandOwnerProfile brandOwnerProfile = new BrandOwnerProfile();
        when(brandService.updateBrandDetail(brandOwnerProfile)).thenReturn(1);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBrandProfile(brandOwnerProfile);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody());
    }

    @Test
    public void testUpdateBrandProfileWithDuplicateKeyException() {
        // Arrange
        BrandOwnerProfile brandOwnerProfile = new BrandOwnerProfile();
        when(brandService.updateBrandDetail(brandOwnerProfile)).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBrandProfile(brandOwnerProfile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testUpdateBrandProfileWithEmptyResultDataAccessException() {
        // Arrange
        BrandOwnerProfile brandOwnerProfile = new BrandOwnerProfile();
        when(brandService.updateBrandDetail(brandOwnerProfile)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBrandProfile(brandOwnerProfile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testUpdateBrandProfileWithIncorrectResultSizeDataAccessException() {
        // Arrange
        BrandOwnerProfile brandOwnerProfile = new BrandOwnerProfile();
        when(brandService.updateBrandDetail(brandOwnerProfile)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBrandProfile(brandOwnerProfile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testUpdateBrandProfileWithDataAccessException() {
        // Arrange
        BrandOwnerProfile brandOwnerProfile = new BrandOwnerProfile();
        when(brandService.updateBrandDetail(brandOwnerProfile)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBrandProfile(brandOwnerProfile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testGetListOfDepartmentSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        Brand brand = new Brand();
        brand.setBrandId(10);

        List<DepartmentListByBrandDTO> departmentDTOList = new ArrayList<>();
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);
        when(departmentService.getAllDepartmentListOfBrand(brand.getBrandId())).thenReturn(departmentDTOList);

        // Act
        String result = brandOwnerController.getListOfDepartment(model, session);

        // Assert
        assertEquals("brand-owner/gym-brand-department-list", result);
        verify(model, times(1)).addAttribute(eq("brandId"), eq(brand.getBrandId()));
        verify(model, times(1)).addAttribute(eq("departmentList"), eq(departmentDTOList));
    }

    @Test
    public void testGetListOfDepartmentWithDuplicateKeyException() {

        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfDepartment(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetListOfDepartmentWithEmptyResultDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfDepartment(model, session);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testGetListOfDepartmentWithIncorrectResultSizeDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfDepartment(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testGetListOfDepartmentWithDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getListOfDepartment(model, session);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testGetDepartmentDetailsSuccess() {
        // Arrange
        int departmentId = 1;
        Department department = new Department();
        when(departmentService.getOne(departmentId)).thenReturn(department);

        List<GymPlanDto> gymPlans = new ArrayList<>();
        when(gymPlanService.getGymPlanDetailsByDepartmentId(departmentId)).thenReturn(gymPlans);

        List<DepartmentSchedule> departmentSchedules = new ArrayList<>();
        when(departmentScheduleService.getAllByDepartmentID(departmentId)).thenReturn(departmentSchedules);

        List<DepartmentAlbums> departmentAlbums = new ArrayList<>();
        when(departmentAlbumsService.getAllByDepartmentID(departmentId)).thenReturn(departmentAlbums);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        when(departmentService.filterDepartmentFeedbacks(departmentId)).thenReturn(departmentDTO);

        List<DepartmentAmenitie> departmentAmenitieList = new ArrayList<>();
        when(departmentAmenitieService.getAllAmenitieOfDepartment(departmentId)).thenReturn(departmentAmenitieList);

        List<DepartmentFeature> departmentFeatures = new ArrayList<>();
        when(departmentFeatureService.getDepartmentFeatures(departmentId)).thenReturn(departmentFeatures);

        // Act
        String result = brandOwnerController.getDepartmentDetails(departmentId, model);

        // Assert
        assertEquals("brand-owner/gym-brand-department-detail", result);
        verify(model, times(1)).addAttribute(eq("department"), eq(department));
        verify(model, times(1)).addAttribute(eq("gymPlans"), eq(gymPlans));
        verify(model, times(1)).addAttribute(eq("departmentSchedules"), eq(departmentSchedules));
        verify(model, times(1)).addAttribute(eq("departmentAlbums"), eq(departmentAlbums));
        verify(model, times(1)).addAttribute(eq("departmentFeedbacks"), eq(departmentDTO));
        verify(model, times(1)).addAttribute(eq("departmentAmenitieList"), eq(departmentAmenitieList));
        verify(model, times(1)).addAttribute(eq("departmentFeatures"), eq(departmentFeatures));
        verify(model, times(1)).addAttribute(eq("departmentId"), eq(departmentId));
    }

    @Test
    public void testGetDepartmentDetailsWithDuplicateKeyException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getDepartmentDetails(departmentId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetDepartmentDetailsWithEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getDepartmentDetails(departmentId, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testGetDepartmentDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getDepartmentDetails(departmentId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testGetDepartmentDetailsWithDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getDepartmentDetails(departmentId, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testUpdateStatusDepartmentSuccess() {
        // Arrange
        int status = 1;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenReturn(1);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.ok(1), result);
        verify(departmentService, times(1)).updateDepartmentStatus(status, departmentId);
        verify(departmentService, never()).updateDepartmentGymOwner(anyInt(), anyInt());
    }

    @Test
    public void testUpdateStatusDepartmentWithStatusZero() {
        // Arrange
        int status = 0;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenReturn(1);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.ok(1), result);
        verify(departmentService, times(1)).updateDepartmentStatus(status, departmentId);
        verify(departmentService, times(1)).updateDepartmentGymOwner(departmentId, 0);
    }

    @Test
    public void testUpdateStatusDepartmentWithDuplicateKeyException() {
        // Arrange
        int status = 1;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);

    }

    @Test
    public void testUpdateStatusDepartmentWithEmptyResultDataAccessException() {
        // Arrange
        int status = 1;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);

    }

    @Test
    public void testUpdateStatusDepartmentWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int status = 1;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);

    }

    @Test
    public void testUpdateStatusDepartmentWithDataAccessException() {
        // Arrange
        int status = 1;
        int departmentId = 1;
        when(departmentService.updateDepartmentStatus(status, departmentId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateStatusDepartment(status, departmentId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);

    }
    @Test
    public void testAddDepartmentSuccess() {
        // Arrange
        int brandId = 1;

        // Act
        String result = brandOwnerController.addDepartment(brandId, model);

        // Assert
        assertEquals("brand-owner/gym-brand-department-add", result);
        verify(model, times(1)).addAttribute("brandId", brandId);
    }

    @Test
    public void testAddDepartmentWithDuplicateKeyException() {
        // Arrange
        int brandId = 1;
        when(model.addAttribute(eq("brandId"), anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.addDepartment(brandId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testAddDepartmentWithEmptyResultDataAccessException() {
        // Arrange
        int brandId = 1;
        when(model.addAttribute(eq("brandId"), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.addDepartment(brandId, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testAddDepartmentWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int brandId = 1;
        when(model.addAttribute(eq("brandId"), anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.addDepartment(brandId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testAddDepartmentWithDataAccessException() {
        // Arrange
        int brandId = 1;
        when(model.addAttribute(eq("brandId"), anyInt())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.addDepartment(brandId, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testCreateDepartmentSuccess() {
        // Arrange
        int brandId = 1;
        String brandName = "TestBrand";

        // Act
        String result = brandOwnerController.createDepartment(brandId, brandName, model);

        // Assert
        assertEquals("redirect:/brand-owner/department/list?id=" + brandId, result);
        verify(model, never()).addAttribute(eq("errorMessage"), anyString());
        verify(departmentService, times(1)).createDepartmentWithBrandId(brandId, brandName);
    }

    @Test
    public void testCreateDepartmentWithEmptyBrandName() {
        // Arrange
        int brandId = 1;
        String brandName = "";

        // Act
        String result = brandOwnerController.createDepartment(brandId, brandName, model);

        // Assert
        assertEquals("brand-owner/gym-brand-department-add", result);
        verify(model, times(1)).addAttribute("errorMessage", "Brand Name can't be empty");
        verify(departmentService, never()).createDepartmentWithBrandId(anyInt(), anyString());
    }

    @Test
    public void testCreateDepartmentWithNullBrandName() {
        // Arrange
        int brandId = 1;
        String brandName = null;

        // Act
        String result = brandOwnerController.createDepartment(brandId, brandName, model);

        // Assert
        assertEquals("brand-owner/gym-brand-department-add", result);
        verify(model, times(1)).addAttribute("errorMessage", "Brand Name can't be empty");
        verify(departmentService, never()).createDepartmentWithBrandId(anyInt(), anyString());
    }

    @Test
    public void testCreateDepartmentWithDuplicateKeyException() {
        // Arrange
        int brandId = 1;
        String brandName = "TestBrand";
        doThrow(DuplicateKeyException.class).when(departmentService).createDepartmentWithBrandId(brandId, brandName);

        // Act
        String result = brandOwnerController.createDepartment(brandId, brandName, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetListOfDepartmentFeedbackSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Brand brand = new Brand();
        brand.setBrandId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        List<ListBrandDepartmentFeedback> departments = new ArrayList<>();
        when(departmentService.getDepartmentFeedbackOfBrandOwner(brand.getBrandId())).thenReturn(departments);

        // Act
        String result = brandOwnerController.getListOfDepartmentFeedback(model, session);

        // Assert
        assertEquals("brand-owner/gym-brand-feedback-list", result);
        verify(model, times(1)).addAttribute("listDepartment", departments);
    }

    @Test
    public void testGetListOfDepartmentFeedbackWithDuplicateKeyException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfDepartmentFeedback(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testGetListOfDepartmentFeedbackWithEmptyResultDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(departmentService.getDepartmentFeedbackOfBrandOwner(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfDepartmentFeedback(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    public void testGetListOfDepartmentFeedbackWithIncorrectResultSizeDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(departmentService.getDepartmentFeedbackOfBrandOwner(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfDepartmentFeedback(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testGetListOfDepartmentFeedbackWithDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(departmentService.getDepartmentFeedbackOfBrandOwner(anyInt())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getListOfDepartmentFeedback(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testGetDepartmentFeedbackDetailsSuccess() {
        // Arrange
        int departmentId = 1;
        Department department = new Department();
        List<UserFeedbackOfBrandOwner> userFeedbackList = new ArrayList<>();

        when(departmentService.getOne(departmentId)).thenReturn(department);
        when(departmentService.getAllDepartmentFeedbackOfBrandOwner(departmentId)).thenReturn(userFeedbackList);

        // Act
        String result = brandOwnerController.getDepartmentFeedbackDetails(departmentId, model);

        // Assert
        assertEquals("brand-owner/gym-brand-feedback-list-detail", result);
        verify(model, times(1)).addAttribute("department", department);
        verify(model, times(1)).addAttribute("userFeedbackList", userFeedbackList);
    }

    @Test
    public void testGetDepartmentFeedbackDetailsWithDuplicateKeyException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getDepartmentFeedbackDetails(departmentId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testGetDepartmentFeedbackDetailsWithEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenReturn(null);

        // Act
        String result = brandOwnerController.getDepartmentFeedbackDetails(departmentId, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    public void testGetDepartmentFeedbackDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenReturn(new Department());
        when(departmentService.getAllDepartmentFeedbackOfBrandOwner(departmentId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getDepartmentFeedbackDetails(departmentId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testGetDepartmentFeedbackDetailsWithDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getOne(departmentId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getDepartmentFeedbackDetails(departmentId, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testGetListOfServiceSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Brand brand = new Brand();
        brand.setBrandId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        List<BrandAmenitie> brandAmenitieList = new ArrayList<>();
        when(brandAmenitieService.getAllByBrandID(brand.getBrandId())).thenReturn(brandAmenitieList);

        // Act
        String result = brandOwnerController.getListOfService(model, session);

        // Assert
        assertEquals("brand-owner/gym-brand-service-list", result);
        verify(model, times(1)).addAttribute("brandAmenitiesList", brandAmenitieList);
    }

    @Test
    public void testGetListOfServiceWithDuplicateKeyException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfService(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testGetListOfServiceWithEmptyResultDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(brandAmenitieService.getAllByBrandID(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfService(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    public void testGetListOfServiceWithIncorrectResultSizeDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(brandAmenitieService.getAllByBrandID(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfService(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testGetListOfServiceWithDataAccessException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("userInfo")).thenReturn(new User());

        when(brandService.getBrandDetail(anyInt())).thenReturn(new Brand());
        when(brandAmenitieService.getAllByBrandID(anyInt())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getListOfService(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testGetServiceDetailsSuccess() {
        // Arrange
        int id = 1;
        BrandAmenitie brandAmenitie = new BrandAmenitie();

        when(brandAmenitieService.getAmenitieDetail(id)).thenReturn(brandAmenitie);

        // Act
        String result = brandOwnerController.getServiceDetails(id, model);

        // Assert
        assertEquals("brand-owner/gym-brand-service-detail", result);
        verify(model, times(1)).addAttribute("brandAmenitie", brandAmenitie);
    }

    @Test
    public void testGetServiceDetailsWithDuplicateKeyException() {
        // Arrange
        int id = 1;
        when(brandAmenitieService.getAmenitieDetail(id)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getServiceDetails(id, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }



    @Test
    public void testGetServiceDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int id = 1;
        when(brandAmenitieService.getAmenitieDetail(id)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getServiceDetails(id, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testGetServiceDetailsWithDataAccessException() {
        // Arrange
        int id = 1;
        when(brandAmenitieService.getAmenitieDetail(id)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.getServiceDetails(id, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }
    @Test
    public void testUpdateServiceDetailsSuccess() {
        // Arrange
        ServiceUpdateDTO serviceUpdateDTO = new ServiceUpdateDTO();
        serviceUpdateDTO.setAmenitieId(1);
        serviceUpdateDTO.setPhotoUrl("test-url");
        serviceUpdateDTO.setAmenitieName("Test Service");
        serviceUpdateDTO.setDescription("Test description");
        serviceUpdateDTO.setStatus(true);

        when(brandAmenitieService.updateBrandAmenitie(any())).thenReturn(1);

        BindingResult bindingResult = mock(BindingResult.class);

        // Act
        String result = brandOwnerController.updateServiceDetails(serviceUpdateDTO, bindingResult);

        // Assert
        assertEquals("redirect:/brand-owner/service/list", result);
        verify(brandAmenitieService, times(1)).updateBrandAmenitie(any());
    }

    @Test
    public void testUpdateServiceDetailsWithValidationError() {
        // Arrange
        ServiceUpdateDTO serviceUpdateDTO = new ServiceUpdateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.updateServiceDetails(serviceUpdateDTO, bindingResult);

        // Assert
        assertEquals("brand-owner/gym-brand-service-detail", result);

    }





}
