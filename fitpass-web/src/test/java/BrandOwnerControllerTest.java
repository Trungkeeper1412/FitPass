
import com.ks.fitpass.brand.dto.*;
import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.credit_card.dto.CreditCard;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentAlbums;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.DepartmentSchedule;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.dto.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistory;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.BrandOwnerController;
import com.ks.fitpass.web.util.WebUtil;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
    private RequestWithdrawHistoryService requestWithdrawHistoryService;
    @Mock
    private CreditCardService creditCardService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private UserService userService;
    @Mock
    private BrandAmenitieService brandAmenitieService;

    @Mock
    private GymPlanService gymPlanService;
    @Mock
    private DepartmentScheduleService departmentScheduleService;
    @Mock
    private WalletService walletService;
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
    public static GymOwnerUpdateDTO createMockGymOwnerUpdateDTO() {
        GymOwnerUpdateDTO gymOwnerUpdateDTO = mock(GymOwnerUpdateDTO.class);

        when(gymOwnerUpdateDTO.getUserDetailId()).thenReturn(1);
        when(gymOwnerUpdateDTO.getFirstName()).thenReturn("John");
        when(gymOwnerUpdateDTO.getLastName()).thenReturn("Doe");
        when(gymOwnerUpdateDTO.getEmail()).thenReturn("john.doe@example.com");
        when(gymOwnerUpdateDTO.getPhone()).thenReturn("0123456789");
        when(gymOwnerUpdateDTO.getAddress()).thenReturn("123 Main Street");
        when(gymOwnerUpdateDTO.getDateOfBirth()).thenReturn(LocalDate.of(1990, 1, 1));
        when(gymOwnerUpdateDTO.getIdCard()).thenReturn("123456789012");
        when(gymOwnerUpdateDTO.getGender()).thenReturn("Male");
        when(gymOwnerUpdateDTO.getImageUrl()).thenReturn("path/to/image.jpg");
        when(gymOwnerUpdateDTO.getActive()).thenReturn("Active");
        when(gymOwnerUpdateDTO.isUserDeleted()).thenReturn(false);
        when(gymOwnerUpdateDTO.getOldDepartmentId()).thenReturn(2);
        when(gymOwnerUpdateDTO.getDepartmentId()).thenReturn(3);
        when(gymOwnerUpdateDTO.getUserId()).thenReturn(4);
        when(gymOwnerUpdateDTO.getOldEmail()).thenReturn("old.email@example.com");

        return gymOwnerUpdateDTO;
    }


    @Test
    public void testGetBrandProfileSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        Brand brandDetails = new Brand();
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brandDetails);

        // Act
        String result = brandOwnerController.getBrandProfile(session, model);

        // Assert
        assertEquals("brand-owner/gym-brand-update-profile", result);

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


    @Test
    public void testCreateServiceSuccess() {
        // Arrange
        ServiceCreateDTO serviceCreateDTO = new ServiceCreateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Brand brand = new Brand();
        brand.setBrandId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        when(brandAmenitieService.createBrandAmenitie(any())).thenReturn(1);

        // Act
        String result = brandOwnerController.createService(serviceCreateDTO, bindingResult, session);

        // Assert
        assertEquals("redirect:/brand-owner/service/list", result);
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(brandAmenitieService, times(1)).createBrandAmenitie(any());
    }

    @Test
    public void testCreateServiceValidationError() {
        // Arrange
        ServiceCreateDTO serviceCreateDTO = new ServiceCreateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.createService(serviceCreateDTO, bindingResult, null);

        // Assert
        assertEquals("brand-owner/gym-brand-service-add", result);
        verify(brandService, never()).getBrandDetail(anyInt());
        verify(brandAmenitieService, never()).createBrandAmenitie(any());
    }

    @Test
    public void testCreateServiceDuplicateKeyException() {
        // Arrange
        ServiceCreateDTO serviceCreateDTO = new ServiceCreateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(brandAmenitieService.createBrandAmenitie(any())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.createService(serviceCreateDTO, bindingResult, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(brandAmenitieService, times(1)).createBrandAmenitie(any());
    }

    @Test
    public void testGetListOfGymOwnerSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Brand brand = new Brand();
        brand.setBrandId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        List<GymOwnerListDTO> gymOwnerList = new ArrayList<>();
        when(userService.getAllAccountByBrandId(brand.getBrandId())).thenReturn(gymOwnerList);

        // Act
        String result = brandOwnerController.getListOfGymOwner(session, model);

        // Assert
        assertEquals("brand-owner/gym-brand-owner-list", result);
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(userService, times(1)).getAllAccountByBrandId(brand.getBrandId());
        verify(model, times(1)).addAttribute(eq("gymOwnerList"), anyList());
    }

    @Test
    public void testGetListOfGymOwnerDuplicateKeyException() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(userService.getAllAccountByBrandId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfGymOwner(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(userService, times(1)).getAllAccountByBrandId(anyInt());
        verify(model, never()).addAttribute(eq("gymOwnerList"), anyList());
    }

    @Test
    public void testGetGymOwnerDetailsSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Brand brand = new Brand();
        brand.setBrandId(1);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        List<DepartmentListByBrandDTO> departmentList = new ArrayList<>();
        when(departmentService.getAllDepartmentListOfBrand(brand.getBrandId())).thenReturn(departmentList);

        UserDetail userDetail = new UserDetail();
        when(userService.getUserDetailByUserDetailId(anyInt())).thenReturn(userDetail);

        // Act
        String result = brandOwnerController.getGymOwnerDetails(1, 2, model, session);

        // Assert
        assertEquals("brand-owner/gym-brand-owner-detail", result);
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(departmentService, times(1)).getAllDepartmentListOfBrand(brand.getBrandId());
        verify(userService, times(1)).getUserDetailByUserDetailId(2);
        verify(model, times(1)).addAttribute(eq("gymOwner"), any());
        verify(model, times(1)).addAttribute(eq("filteredList"), anyList());
    }

    @Test
    public void testUpdateGymOwnerDetailsSuccess() {
        // Arrange
        GymOwnerUpdateDTO gymOwnerUpdateDTO = createMockGymOwnerUpdateDTO();

        BindingResult bindingResult = mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        when(departmentService.updateDepartmentGymOwner(anyInt(), anyInt())).thenReturn(1);

        // Act
        String result = brandOwnerController.updateGymOwnerDetails(gymOwnerUpdateDTO, bindingResult);

        // Assert
        assertEquals("redirect:/brand-owner/gym-owner/list", result);
        verify(userService, times(1)).updateUserDetail(any());
        verify(userService, times(1)).updateUserStatusByUserId(anyInt(), anyInt());
        verify(departmentService, times(2)).updateDepartmentGymOwner(anyInt(), anyInt());
    }


    @Test
    public void testUpdateGymOwnerDetailsValidationError() {
        // Arrange
        GymOwnerUpdateDTO gymOwnerUpdateDTO = createMockGymOwnerUpdateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.updateGymOwnerDetails(gymOwnerUpdateDTO, bindingResult);

        // Assert
        assertEquals("brand-owner/gym-brand-owner-detail", result);
        verify(userService, never()).updateUserDetail(any());
        verify(userService, never()).updateUserStatusByUserId(anyInt(), anyInt());
        verify(departmentService, never()).updateDepartmentGymOwner(anyInt(), anyInt());
    }

    @Test
    public void testUpdateGymOwnerDetailsDataAccessException() {
        // Arrange
        GymOwnerUpdateDTO gymOwnerUpdateDTO = createMockGymOwnerUpdateDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(userService.checkEmailExist(anyString())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.updateGymOwnerDetails(gymOwnerUpdateDTO, bindingResult);

        // Assert
        assertEquals("error/data-access-error", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());
    }


    @Test
    public void testCreateGymOwnerDataAccessException() {
        // Arrange
        GymOwnerCreateDTO gymOwnerCreateDTO = createMockGymOwnerCreateDTO();
        when(userService.checkEmailExist(gymOwnerCreateDTO.getEmail())).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        String result = brandOwnerController.createGymOwner(gymOwnerCreateDTO, mock(BindingResult.class), session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    private GymOwnerCreateDTO createMockGymOwnerCreateDTO() {
        // Implement a method to create a mock GymOwnerCreateDTO instance with valid values
        GymOwnerCreateDTO gymOwnerCreateDTO = new GymOwnerCreateDTO();
        gymOwnerCreateDTO.setFirstName("John");
        gymOwnerCreateDTO.setLastName("Doe");
        gymOwnerCreateDTO.setUsername("john_doe");
        gymOwnerCreateDTO.setEmail("john.doe@example.com");
        gymOwnerCreateDTO.setPhone("0987654321");
        gymOwnerCreateDTO.setAddress("123 Main Street, City");
        gymOwnerCreateDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        gymOwnerCreateDTO.setIdCard("123456789012");
        gymOwnerCreateDTO.setGender("Male");
        gymOwnerCreateDTO.setImageUrl("https://example.com/johndoe.jpg");

        return gymOwnerCreateDTO;
    }

    @Test
    void testGetListOfFlexibleGymPlansSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);

        Brand brand = new Brand();
        brand.setBrandId(1);

        List<BrandGymPlanFlexDTO> listFlexGymPlan = Collections.singletonList(new BrandGymPlanFlexDTO());

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);
        when(gymPlanService.getAllGymPlanFlexByBrandId(brand.getBrandId())).thenReturn(listFlexGymPlan);

        // Act
        String result = brandOwnerController.getListOfFlexibleGymPlans(session, model);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(gymPlanService, times(1)).getAllGymPlanFlexByBrandId(brand.getBrandId());
        verify(model, times(1)).addAttribute("listFlexGymPlan", listFlexGymPlan);
        assertEquals("brand-owner/gym-brand-plan-flexible-list", result);
    }

    @Test
    void testGetListOfFlexibleGymPlansWithDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfFlexibleGymPlans(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetListOfFlexibleGymPlansWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfFlexibleGymPlans(session, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetListOfFlexibleGymPlansWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfFlexibleGymPlans(session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetListOfFlexibleGymPlansWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getListOfFlexibleGymPlans(session, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testGetFlexibleGymPlanDetailsSuccess() {
        // Arrange
        int gymPlanId = 1;
        BrandUpdateGymPlanFlexDTO brandUpdateGymPlanFlexDTO = new BrandUpdateGymPlanFlexDTO();
        when(gymPlanService.getGymPlanFlexDetail(gymPlanId)).thenReturn(brandUpdateGymPlanFlexDTO);

        // Act
        String result = brandOwnerController.getFlexibleGymPlanDetails(gymPlanId, model);

        // Assert
        verify(gymPlanService, times(1)).getGymPlanFlexDetail(gymPlanId);
        verify(model, times(1)).addAttribute("b", brandUpdateGymPlanFlexDTO);
        assertEquals("brand-owner/gym-brand-plan-flexible-detail", result);
    }

    @Test
    void testGetFlexibleGymPlanDetailsWithDuplicateKeyException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFlexDetail(gymPlanId)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getFlexibleGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetFlexibleGymPlanDetailsWithEmptyResultDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFlexDetail(gymPlanId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getFlexibleGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetFlexibleGymPlanDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFlexDetail(gymPlanId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getFlexibleGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetFlexibleGymPlanDetailsWithDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFlexDetail(gymPlanId)).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getFlexibleGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testUpdateFlexibleGymPlanDetailsSuccess() throws Exception {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        brandDetails.setGymPlanId(1);
        brandDetails.setStatus(1);

        when(gymPlanService.checkGymPlanInDepartmentUse(brandDetails.getGymPlanId())).thenReturn(0);

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        verify(gymPlanService, times(1)).checkGymPlanInDepartmentUse(brandDetails.getGymPlanId());
        verify(gymPlanService, times(1)).updateGymPlanFlex(brandDetails);
        assertEquals("redirect:/brand-owner/gym-plans/flexible/list", result);
    }


    @Test
    void testUpdateFlexibleGymPlanDetailsWithValidationError() {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        assertEquals("brand-owner/gym-brand-plan-flexible-detail", result);
    }

    @Test
    void testUpdateFlexibleGymPlanDetailsWithDuplicateKeyException() {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        when(gymPlanService.updateGymPlanFlex(any())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testUpdateFlexibleGymPlanDetailsWithEmptyResultDataAccessException() {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        when(gymPlanService.updateGymPlanFlex(any())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testUpdateFlexibleGymPlanDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        when(gymPlanService.updateGymPlanFlex(any())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testUpdateFlexibleGymPlanDetailsWithDataAccessException() {
        // Arrange
        BrandUpdateGymPlanFlexDTO brandDetails = new BrandUpdateGymPlanFlexDTO();
        when(gymPlanService.updateGymPlanFlex(any())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.updateFlexibleGymPlanDetails(brandDetails, bindingResult);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testAddFlexibleGymPlanSuccess() throws Exception {
        // Arrange
        when(model.addAttribute(eq("brandCreateGymPlanFlexDTO"), any(BrandCreateGymPlanFlexDTO.class))).thenReturn(model);

        // Act
        String result = brandOwnerController.addFlexibleGymPlan(model);

        // Assert
        verify(model, times(1)).addAttribute(eq("brandCreateGymPlanFlexDTO"), any(BrandCreateGymPlanFlexDTO.class));
        assertEquals("brand-owner/gym-brand-plan-flexible-add", result);
    }

    @Test
    void testAddFlexibleGymPlanWithDataAccessException() {
        // Arrange
        when(model.addAttribute(eq("brandCreateGymPlanFlexDTO"), any(BrandCreateGymPlanFlexDTO.class)))
                .thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.addFlexibleGymPlan(model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testCreateFlexibleGymPlanSuccess()  {
        // Arrange
        BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO = new BrandCreateGymPlanFlexDTO();
        User user = new User();
        user.setUserId(1);
        Brand brand = new Brand();
        brand.setBrandId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        // Act
        String result = brandOwnerController.createFlexibleGymPlan(brandCreateGymPlanFlexDTO, bindingResult, session);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(gymPlanService, times(1)).createGymPlanFlex(brandCreateGymPlanFlexDTO);
        assertEquals("redirect:/brand-owner/gym-plans/flexible/list", result);
    }

    @Test
    void testCreateFlexibleGymPlanWithValidationError() {
        // Arrange
        BrandCreateGymPlanFlexDTO brandCreateGymPlanFlexDTO = new BrandCreateGymPlanFlexDTO();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.createFlexibleGymPlan(brandCreateGymPlanFlexDTO, bindingResult, session);

        // Assert
        assertEquals("brand-owner/gym-brand-plan-flexible-add", result);
    }

    @Test
    void testGetListOfFixedGymPlansSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        Brand brand = new Brand();
        brand.setBrandId(1);
        List<BrandGymPlanFixedDTO> listFixedGymPlan = Collections.singletonList(new BrandGymPlanFixedDTO()) ;

                when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);
        when(gymPlanService.getAllGymPlanFixedByBrandId(brand.getBrandId())).thenReturn(listFixedGymPlan);

        // Act
        String result = brandOwnerController.getListOfFixedGymPlans(session, model);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(gymPlanService, times(1)).getAllGymPlanFixedByBrandId(brand.getBrandId());
        verify(model, times(1)).addAttribute("listFixedGymPlan", listFixedGymPlan);
        assertEquals("brand-owner/gym-brand-plan-fixed-list", result);
    }

    @Test
    void testGetListOfFixedGymPlansWithDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getListOfFixedGymPlans(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetListOfFixedGymPlansWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfFixedGymPlans(session, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetListOfFixedGymPlansWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getListOfFixedGymPlans(session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetListOfFixedGymPlansWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getListOfFixedGymPlans(session, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testGetFixedGymPlanDetailsSuccess() {
        // Arrange
        int gymPlanId = 1;
        BrandUpdateGymPlanFixedDTO brandUpdateGymPlanFixedDTO = new BrandUpdateGymPlanFixedDTO();

        when(gymPlanService.getGymPlanFixedDetail(gymPlanId)).thenReturn(brandUpdateGymPlanFixedDTO);

        // Act
        String result = brandOwnerController.getFixedGymPlanDetails(gymPlanId, model);

        // Assert
        verify(gymPlanService, times(1)).getGymPlanFixedDetail(gymPlanId);
        verify(model, times(1)).addAttribute("b", brandUpdateGymPlanFixedDTO);
        assertEquals("brand-owner/gym-brand-plan-fixed-detail", result);
    }

    @Test
    void testGetFixedGymPlanDetailsWithDuplicateKeyException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFixedDetail(gymPlanId)).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getFixedGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetFixedGymPlanDetailsWithEmptyResultDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFixedDetail(gymPlanId)).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getFixedGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetFixedGymPlanDetailsWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFixedDetail(gymPlanId)).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getFixedGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetFixedGymPlanDetailsWithDataAccessException() {
        // Arrange
        int gymPlanId = 1;
        when(gymPlanService.getGymPlanFixedDetail(gymPlanId)).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getFixedGymPlanDetails(gymPlanId, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testUpdateFixedGymPlanDetailsSuccess() {
        // Arrange
        BrandUpdateGymPlanFixedDTO brandDetails = new BrandUpdateGymPlanFixedDTO();
        brandDetails.setGymPlanId(1);

        when(gymPlanService.checkGymPlanInDepartmentUse(brandDetails.getGymPlanId())).thenReturn(0);

        // Act
        String result = brandOwnerController.updateFixedGymPlanDetails(brandDetails, mock(BindingResult.class));

        // Assert
        verify(gymPlanService, times(1)).checkGymPlanInDepartmentUse(brandDetails.getGymPlanId());
        verify(gymPlanService, times(1)).updateGymPlanFixed(brandDetails);
        assertEquals("redirect:/brand-owner/gym-plans/fixed/list", result);
    }



    @Test
    void testAddFixedGymPlanSuccess() {
        // Act
        String result = brandOwnerController.addFixedGymPlan(model);

        // Assert
        verify(model, times(1)).addAttribute("brandCreateGymPlanFixedDTO", new BrandCreateGymPlanFixedDTO());
        assertEquals("brand-owner/gym-brand-plan-fixed-add", result);
    }

    @Test
    void testAddFixedGymPlanWithDataAccessException() {
        // Arrange
        doThrow(new DataAccessException("Simulated DataAccessException") {}).when(model).addAttribute(anyString(), any());

        // Act
        String result = brandOwnerController.addFixedGymPlan(model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testCreateFixedGymPlanSuccess() {
        // Arrange
        BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO = new BrandCreateGymPlanFixedDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        User user = new User();
        Brand brand = new Brand();
        brand.setBrandId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);

        // Act
        String result = brandOwnerController.createFixedGymPlan(brandCreateGymPlanFixedDTO, bindingResult, session);

        // Assert
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(gymPlanService, times(1)).createGymPlanFixed(brandCreateGymPlanFixedDTO);
        assertEquals("redirect:/brand-owner/gym-plans/fixed/list", result);
    }

    @Test
    void testCreateFixedGymPlanWithValidationError() {
        // Arrange
        BrandCreateGymPlanFixedDTO brandCreateGymPlanFixedDTO = new BrandCreateGymPlanFixedDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = brandOwnerController.createFixedGymPlan(brandCreateGymPlanFixedDTO, bindingResult, session);

        // Assert
        assertEquals("brand-owner/gym-brand-plan-fixed-add", result);
    }


    @Test
    void testGetWithdrawalSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        Brand brand = new Brand();
        brand.setBrandId(1);
        List<RequestWithdrawHistory> requestWithdrawHistoryListPending = new ArrayList<>();
        List<RequestWithdrawHistory> requestWithdrawHistoryListAll = new ArrayList<>();
        RequestHistoryStats requestHistoryStats = new RequestHistoryStats();
        List<CreditCard> creditCardList = new ArrayList<>();
        double userBalance = 100.0;

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(brand);
        when(requestWithdrawHistoryService.getAllByUserIdAndStatus(user.getUserId(), "Đang xử lý")).thenReturn(requestWithdrawHistoryListPending);
        when(requestWithdrawHistoryService.getAllByUserId(user.getUserId())).thenReturn(requestWithdrawHistoryListAll);
        when(requestWithdrawHistoryService.getStatsByUserId(user.getUserId())).thenReturn(requestHistoryStats);
        when(creditCardService.getAllByUserId(user.getUserId())).thenReturn(creditCardList);
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(userBalance);

        // Act
        String result = brandOwnerController.getWithdrawal(model, session);

        // Assert
        verify(brandService, times(1)).getBrandDetail(user.getUserId());
        verify(requestWithdrawHistoryService, times(1)).getAllByUserIdAndStatus(user.getUserId(), "Đang xử lý");
        verify(requestWithdrawHistoryService, times(1)).getAllByUserId(user.getUserId());
        verify(requestWithdrawHistoryService, times(1)).getStatsByUserId(user.getUserId());
        verify(creditCardService, times(1)).getAllByUserId(user.getUserId());
        verify(walletService, times(1)).getBalanceByUserId(user.getUserId());

        assertEquals("brand-owner/gym-brand-withdrawal-list", result);
    }

    @Test
    void testGetWithdrawalWithDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getWithdrawal(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetWithdrawalWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getWithdrawal(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetWithdrawalWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getWithdrawal(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetWithdrawalWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(brandService.getBrandDetail(anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getWithdrawal(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }


    @Test
    void testAddWithdrawalSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        double userBalance = 100.0;
        long creditAmount = 50L;
        long moneyAmount = 50L;
        int cardId = 1;

        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(userBalance);

        // Act
        String result = brandOwnerController.addWithdrawal(cardId, creditAmount, moneyAmount, session, model);

        // Assert
        verify(walletService, times(1)).getBalanceByUserId(user.getUserId());
        verify(requestWithdrawHistoryService, times(1)).create(any(RequestWithdrawHistory.class));
        assertEquals("redirect:/brand-owner/withdrawal/list", result);
    }

    @Test
    void testAddWithdrawalWithInsufficientBalance() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        double userBalance = 50.0; // Less than creditAmount
        long creditAmount = 100L;
        long moneyAmount = 100L;
        int cardId = 1;
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(walletService.getBalanceByUserId(user.getUserId())).thenReturn(userBalance);

        // Act
        String result = brandOwnerController.addWithdrawal(cardId, creditAmount, moneyAmount, session, model);

        // Assert
        verify(walletService, times(1)).getBalanceByUserId(user.getUserId());
        verify(requestWithdrawHistoryService, never()).create(any(RequestWithdrawHistory.class));
        assertEquals("redirect:/brand-owner/withdrawal/list", result); // It should redirect without creating a withdrawal request
    }

    @Test
    void testAddWithdrawalWithDuplicateKeyException() {
        // Arrange
        User user = new User();
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(walletService.getBalanceByUserId(anyInt())).thenReturn(100.0);
        when(requestWithdrawHistoryService.create(any(RequestWithdrawHistory.class))).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.addWithdrawal(1, 50L, 50L, session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testAddWithdrawalWithEmptyResultDataAccessException() {
        // Arrange
        User user = new User();
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(walletService.getBalanceByUserId(anyInt())).thenReturn(100.0);
        when(requestWithdrawHistoryService.create(any(RequestWithdrawHistory.class))).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.addWithdrawal(1, 50L, 50L, session, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testAddWithdrawalWithIncorrectResultSizeDataAccessException() {
        // Arrange
        User user = new User();
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(walletService.getBalanceByUserId(anyInt())).thenReturn(100.0);

        when(requestWithdrawHistoryService.create(any(RequestWithdrawHistory.class))).thenThrow(IncorrectResultSizeDataAccessException.class);
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        // Act
        String result = brandOwnerController.addWithdrawal(1, 50L, 50L, session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testAddWithdrawalWithDataAccessException() {
        // Arrange
        User user = new User();
        when(brandService.getBrandDetail(user.getUserId())).thenReturn(new Brand());
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(walletService.getBalanceByUserId(anyInt())).thenReturn(100.0);
        when(requestWithdrawHistoryService.create(any(RequestWithdrawHistory.class))).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.addWithdrawal(1, 50L, 50L, session, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testGetWithdrawalCardSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(new CreditCard());

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(creditCardService.getAllByUserId(user.getUserId())).thenReturn(creditCardList);

        // Act
        String result = brandOwnerController.getWithdrawalCard(model, session);

        // Assert
        verify(creditCardService, times(1)).getAllByUserId(user.getUserId());
        assertEquals("brand-owner/gym-brand-withdrawal-card-add", result);
        verify(model, times(1)).addAttribute("creditCardList", creditCardList);
    }

    @Test
    void testGetWithdrawalCardWithDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(creditCardService.getAllByUserId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = brandOwnerController.getWithdrawalCard(model, session);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    void testGetWithdrawalCardWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(creditCardService.getAllByUserId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = brandOwnerController.getWithdrawalCard(model, session);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    void testGetWithdrawalCardWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(creditCardService.getAllByUserId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = brandOwnerController.getWithdrawalCard(model, session);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    void testGetWithdrawalCardWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(creditCardService.getAllByUserId(anyInt())).thenThrow(new DataAccessException("Simulated DataAccessException") {});

        // Act
        String result = brandOwnerController.getWithdrawalCard(model, session);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    void testAddBankCardSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        CreditCard creditCard = new CreditCard();

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(creditCardService.checkCreditCardExist(creditCard, user.getUserId())).thenReturn(false);
        when(creditCardService.createCreditCard(creditCard)).thenReturn(1);
        when(creditCardService.getLastCreditCardId()).thenReturn(123);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.addBankCard(creditCard, session, bindingResult);

        // Assert
        assertEquals(ResponseEntity.ok(123), result);
    }

    @Test
    void testAddBankCardValidationErrors() {
        // Arrange
        CreditCard creditCard = new CreditCard();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.addBankCard(creditCard, session, bindingResult);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testAddBankCardCreditCardExists() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        CreditCard creditCard = new CreditCard();

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(creditCardService.checkCreditCardExist(creditCard, user.getUserId())).thenReturn(true);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.addBankCard(creditCard, session, bindingResult);

        // Assert
        assertEquals(ResponseEntity.ok(-1), result);
    }

    @Test
    void testGetBankCardDetailsSuccess() {
        // Arrange
        int creditCardId = 1;
        CreditCard expectedCreditCard = new CreditCard();

        when(creditCardService.getOne(creditCardId)).thenReturn(expectedCreditCard);

        // Act
        ResponseEntity<CreditCard> result = brandOwnerController.getBankCardDetails(creditCardId);

        // Assert
        assertEquals(ResponseEntity.ok(expectedCreditCard), result);
    }

    @Test
    void testGetBankCardDetailsDataAccessException() {
        // Arrange
        int creditCardId = 1;

        when(creditCardService.getOne(creditCardId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<CreditCard> result = brandOwnerController.getBankCardDetails(creditCardId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testUpdateBankCardSuccess() {
        // Arrange
        CreditCard creditCard = new CreditCard();
        int expectedRowAffect = 1;

        when(creditCardService.checkCreditCardExist(creditCard, creditCard.getUserId())).thenReturn(false);
        when(creditCardService.updateCreditCard(creditCard)).thenReturn(expectedRowAffect);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBankCard(creditCard, mock(BindingResult.class));

        // Assert
        assertEquals(ResponseEntity.ok(expectedRowAffect), result);
    }

    @Test
    void testUpdateBankCardValidationError() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBankCard(new CreditCard(), bindingResult);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

    @Test
    void testUpdateBankCardDuplicateCardExist() {
        // Arrange
        CreditCard creditCard = new CreditCard();

        when(creditCardService.checkCreditCardExist(creditCard, creditCard.getUserId())).thenReturn(true);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBankCard(creditCard, mock(BindingResult.class));

        // Assert
        assertEquals(ResponseEntity.ok(-1), result);
    }

    @Test
    void testUpdateBankCardDataAccessException() {
        // Arrange
        CreditCard creditCard = new CreditCard();

        when(creditCardService.checkCreditCardExist(creditCard, creditCard.getUserId())).thenReturn(false);
        when(creditCardService.updateCreditCard(creditCard)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<Integer> result = brandOwnerController.updateBankCard(creditCard, mock(BindingResult.class));

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }


    @Test
    void testDeleteBankCardSuccess() {
        // Arrange
        Integer creditCardId = 1;
        int expectedRowAffect = 1;

        when(creditCardService.deleteCreditCard(creditCardId)).thenReturn(expectedRowAffect);

        // Act
        ResponseEntity<Integer> result = brandOwnerController.deleteBankCard(creditCardId);

        // Assert
        assertEquals(ResponseEntity.ok(expectedRowAffect), result);
    }

    @Test
    void testDeleteBankCardDataAccessException() {
        // Arrange
        Integer creditCardId = 1;

        when(creditCardService.deleteCreditCard(creditCardId)).thenThrow(new CustomDataAccessException("Simulated DataAccessException"));

        // Act
        ResponseEntity<Integer> result = brandOwnerController.deleteBankCard(creditCardId);

        // Assert
        assertEquals(ResponseEntity.badRequest().build(), result);
    }

//    @Test
//    void testChangePasswordSuccess() {
//        // Arrange
//        String currentPassword = "currentPass";
//        String newPassword = "newPass";
//        String confirmPassword = "newPass";
//        Model model = mock(Model.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User user = new User();
//        user.setUserId(1);
//        user.setUserPassword(new BCryptPasswordEncoder().encode(currentPassword));
//
//        when(session.getAttribute("userInfo")).thenReturn(user);
//        when(userService.updatePassword(anyString(), eq(user.getUserId()))).thenReturn(true);
//
//        // Act
//        String result = brandOwnerController.changePassword(currentPassword, newPassword, confirmPassword, model, session);
//
//        // Assert
//        assertEquals("redirect:/brand-owner/password", result);
//        verify(model, times(1)).addAttribute("success", true);
//    }

//    @Test
//    void testChangePasswordIncorrectCurrentPassword() {
//        // Arrange
//        String currentPassword = "incorrectPass";
//        String newPassword = "newPass";
//        String confirmPassword = "newPass";
//        Model model = mock(Model.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User user = new User();
//        user.setUserId(1);
//        user.setUserPassword(new BCryptPasswordEncoder().encode("currentPass"));
//
//        when(session.getAttribute("userInfo")).thenReturn(user);
//
//        // Act
//        String result = brandOwnerController.changePassword(currentPassword, newPassword, confirmPassword, model, session);
//
//        // Assert
//        assertEquals("brand-owner/gym-brand-update-password", result);
//        verify(model, times(1)).addAttribute("error", "Mật khẩu hiện tại không đúng");
//    }

//    @Test
//    void testChangePasswordMismatchedNewPassword() {
//        // Arrange
//        String currentPassword = "currentPass";
//        String newPassword = "newPass";
//        String confirmPassword = "mismatchedPass";
//        Model model = mock(Model.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User user = new User();
//        user.setUserId(1);
//        user.setUserPassword(new BCryptPasswordEncoder().encode(currentPassword));
//
//        when(session.getAttribute("userInfo")).thenReturn(user);
//
//        // Act
//        String result = brandOwnerController.changePassword(currentPassword, newPassword, confirmPassword, model, session);
//
//        // Assert
//        assertEquals("brand-owner/gym-brand-update-password", result);
//        verify(model, times(1)).addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp");
//    }

//    @Test
//    void testChangePasswordMismatchedNewPasswordAndConfirmPassword() {
//        // Arrange
//        String currentPassword = "currentPass";
//        String newPassword = "newPass";
//        String confirmPassword = "mismatchedPass";
//        Model model = mock(Model.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User user = new User();
//        user.setUserId(1);
//        user.setUserPassword(new BCryptPasswordEncoder().encode(currentPassword));
//
//        when(session.getAttribute("userInfo")).thenReturn(user);
//
//        // Act
//        String result = brandOwnerController.changePassword(currentPassword, newPassword, confirmPassword, model, session);
//
//        // Assert
//        assertEquals("brand-owner/gym-brand-update-password", result);
//        verify(model, times(1)).addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp");
//    }

//    @Test
//    void testChangePasswordException() {
//        // Arrange
//        String currentPassword = "currentPass";
//        String newPassword = "newPass";
//        String confirmPassword = "newPass";
//        Model model = mock(Model.class);
//        HttpSession session = mock(HttpSession.class);
//
//        User user = new User();
//        user.setUserId(1);
//        user.setUserPassword(new BCryptPasswordEncoder().encode(currentPassword));
//
//        when(session.getAttribute("userInfo")).thenReturn(user);
//        when(userService.updatePassword(anyString(), eq(user.getUserId()))).thenThrow(RuntimeException.class);
//
//        // Act
//        String result = brandOwnerController.changePassword(currentPassword, newPassword, confirmPassword, model, session);
//
//        // Assert
//        assertEquals("brand-owner/gym-brand-update-password", result);
//        verify(model, times(1)).addAttribute("error", "Đã xảy ra lỗi khi thay đổi mật khẩu");
//    }

}
