import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.department.dto.*;
import com.ks.fitpass.department.entity.Department;
import com.ks.fitpass.department.entity.DepartmentFeature;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.gymplan.dto.BrandGymPlanFixedDTO;
import com.ks.fitpass.gymplan.dto.BrandGymPlanFlexDTO;
import com.ks.fitpass.web.controller.GymOwnerController;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.util.Email;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GymOwnerControllerTest {

    @Mock
    private BindingResult bindingResult;
    @Mock
    private User user;
    @Mock
    private DepartmentScheduleService departmentScheduleService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private DepartmentAlbumsService departmentAlbumsService;
    @Mock
    private BrandAmenitieService brandAmenitieService;
    @Mock
    private DepartmentAmenitieService departmentAmenitieService;
    @Mock
    private UserService userService;
    @Mock
    private GymPlanService gymPlanService;
    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private WalletService walletService;
    @Mock
    private Email emailService;

    @InjectMocks
    private GymOwnerController gymOwnerController;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    // Add this method to initialize mocks
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        // Mock the behavior for successful retrieval of department details
        when(departmentService.getByUserId(anyInt())).thenReturn(Department.builder()
                .departmentId(1) // Set appropriate values for your test
                .build());
        // Mock the behavior for successful retrieval of the user from the session
        when(session.getAttribute("userInfo")).thenReturn(user);

        // Mock the getUserId method of the User object
        when(user.getUserId()).thenReturn(123); // Set an appropriate user ID


    }

    // Test case for a successful employee list page load
    @Test
    public void testGetListOfEmployeeSuccess() {
        // Arrange
        when(session.getAttribute("isFirstTime")).thenReturn(false);

        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department departmentDetails = new Department();
        departmentDetails.setDepartmentId(1);
        when(departmentService.getByUserId(user.getUserId())).thenReturn(departmentDetails);

        List<GymOwnerListDTO> listOfEmployee = new ArrayList<>();
        when(userService.getAllAccountByDepartmentId(departmentDetails.getDepartmentId())).thenReturn(listOfEmployee);

        // Act
        String result = gymOwnerController.getListOfEmployee(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-employee-list", result);
         }

    @Test
    void testGetListOfEmployeeDuplicateKeyException() {
        // Arrange


        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getAllAccountByDepartmentId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = gymOwnerController.getListOfEmployee(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    void testGetListOfEmployeeEmptyResultDataAccessException() {
        // Arrange

        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getAllAccountByDepartmentId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = gymOwnerController.getListOfEmployee(session, model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    void testGetListOfEmployeeIncorrectResultSizeDataAccessException() {
        // Arrange

        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getAllAccountByDepartmentId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = gymOwnerController.getListOfEmployee(session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    void testGetListOfEmployeeDataAccessException() {
        // Arrange


        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getAllAccountByDepartmentId(anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String result = gymOwnerController.getListOfEmployee(session, model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    void testGetEmployeeDetailsSuccessful() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getUserDetailByUserDetailId(anyInt())).thenReturn(new UserDetail());

        // Act
        String result = gymOwnerController.getEmployeeDetails(session, model, 1, 1);

        // Assert
        assertEquals("gym-owner/gym-department-employee-detail", result);
        verify(model, times(1)).addAttribute(eq("employeeInfo"), any(EmployeUpdateDTO.class));
    }

    // Add more tests for exception handling scenarios

    @Test
    void testGetEmployeeDetailsDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        when(userService.getUserDetailByUserDetailId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        String result = gymOwnerController.getEmployeeDetails(session, model, 1, 1);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    void testGetEmployeeDetailsEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        when(userService.getUserDetailByUserDetailId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = gymOwnerController.getEmployeeDetails(session, model, 1, 1);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    void testGetEmployeeDetailsIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(userService.getUserDetailByUserDetailId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = gymOwnerController.getEmployeeDetails(session, model, 1, 1);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    void testGetEmployeeDetailsDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        when(userService.getUserDetailByUserDetailId(anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String result = gymOwnerController.getEmployeeDetails(session, model, 1, 1);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    void testUpdateEmployeeDetailsEmailExists() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("existingEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(true);

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("redirect:/gym-owner/employee/list", result);
        verify(bindingResult, times(1)).rejectValue(eq("email"), eq("error.email"), anyString());
    }

    @Test
    void testUpdateEmployeeDetailsSuccessful() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("newEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("redirect:/gym-owner/employee/list", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());
        verify(userService, times(1)).updateUserDetail(any(UserDetail.class));
        verify(userService, times(1)).updateUserStatusByUserId(anyInt(), anyInt());
    }

    // Add more tests for exception handling scenarios

    @Test
    void testUpdateEmployeeDetailsDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("newEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(DuplicateKeyException.class).when(userService).updateUserDetail(any(UserDetail.class));

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());

    }

    @Test
    void testUpdateEmployeeDetailsEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("newEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(EmptyResultDataAccessException.class).when(userService).updateUserDetail(any(UserDetail.class));

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("error/no-data", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());
    }

    @Test
    void testUpdateEmployeeDetailsIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("newEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(IncorrectResultSizeDataAccessException.class).when(userService).updateUserDetail(any(UserDetail.class));

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());

    }

    @Test
    void testUpdateEmployeeDetailsDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());

        EmployeUpdateDTO employeUpdateDTO = new EmployeUpdateDTO();
        employeUpdateDTO.setEmail("newEmail@example.com");
        employeUpdateDTO.setOldEmail("oldEmail@example.com");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(new CustomDataAccessException("Custom Data Access Exception")).when(userService).updateUserDetail(any(UserDetail.class));

        // Act
        String result = gymOwnerController.updateEmployeeDetails(session, model, employeUpdateDTO, bindingResult);

        // Assert
        assertEquals("error/data-access-error", result);
        verify(bindingResult, never()).rejectValue(eq("email"), eq("error.email"), anyString());

    }

    @Test
    public void testAddEmployeeSuccess() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(session.getAttribute("isFirstTime")).thenReturn(false);

        // Act
        String result = gymOwnerController.addEmployee(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-employee-add", result);
        verify(model, times(1)).addAttribute(eq("employeeInfo"), any(EmployeeCreateDTO.class));
    }


    @Test
    public void testAddEmployeeDuplicateKeyException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(session.getAttribute("isFirstTime")).thenReturn(false);
        doThrow(DuplicateKeyException.class).when(model).addAttribute(eq("employeeInfo"), any(EmployeeCreateDTO.class));

        // Act
        String result = gymOwnerController.addEmployee(session, model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testAddEmployeeEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(session.getAttribute("isFirstTime")).thenReturn(false);
        doThrow(EmptyResultDataAccessException.class).when(model).addAttribute(eq("employeeInfo"), any(EmployeeCreateDTO.class));

        // Act
        String result = gymOwnerController.addEmployee(session, model);

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    public void testAddEmployeeIncorrectResultSizeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(session.getAttribute("isFirstTime")).thenReturn(false);
        doThrow(IncorrectResultSizeDataAccessException.class).when(model).addAttribute(eq("employeeInfo"), any(EmployeeCreateDTO.class));

        // Act
        String result = gymOwnerController.addEmployee(session, model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testAddEmployeeDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(session.getAttribute("isFirstTime")).thenReturn(false);
        doThrow(new CustomDataAccessException("Custom Data Access Exception")).when(model).addAttribute(eq("employeeInfo"), any(EmployeeCreateDTO.class));

        // Act
        String result = gymOwnerController.addEmployee(session, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testCreateEmployeeSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        when(userService.getLastInsertUserDetailId(any())).thenReturn(1);
        when(userService.getNumberOfAccountCreatedByDepartmentId(anyInt())).thenReturn(1);
        when(userService.getLastUserInsertId(any())).thenReturn(1);


        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("redirect:/gym-owner/employee/list", result);
    }

    // Add more tests to cover other scenarios and exceptions

    @Test
    public void testCreateEmployeeEmailExists() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(true);

        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("redirect:/gym-owner/employee/list", result);

    }

    @Test
    public void testCreateEmployeeDuplicateKeyException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(DuplicateKeyException.class).when(userService).insertIntoUserDetail(any());

        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testCreateEmployeeEmptyResultDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(EmptyResultDataAccessException.class).when(userService).insertIntoUserDetail(any());

        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("error/no-data", result);
    }

    @Test
    public void testCreateEmployeeIncorrectResultSizeDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(IncorrectResultSizeDataAccessException.class).when(userService).insertIntoUserDetail(any());

        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
    }

    @Test
    public void testCreateEmployeeDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        when(userService.checkEmailExist(anyString())).thenReturn(false);
        doThrow(new CustomDataAccessException("Custom Data Access Exception")).when(userService).insertIntoUserDetail(any());

        // Act
        String result = gymOwnerController.createEmployee(session, model, new EmployeeCreateDTO(), Mockito.mock(BindingResult.class));

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testGetListOfFeedbackSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        List<UserFeedbackOfBrandOwner> feedbackList = Collections.emptyList();
        when(departmentService.getAllDepartmentFeedbackOfBrandOwner(anyInt())).thenReturn(feedbackList);

        // Act
        String result = gymOwnerController.getListOfFeedback(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-feedback", result);
        verify(model, times(1)).addAttribute(eq("userFeedbackList"), eq(feedbackList));
        verify(model, times(1)).addAttribute(eq("departmentDetails"), eq(department));
    }


    @Test
    public void testGetListOfFeedbackDataAccessException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(departmentService.getAllDepartmentFeedbackOfBrandOwner(anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String result = gymOwnerController.getListOfFeedback(session, model);

        // Assert
        assertEquals("error/data-access-error", result);
    }

    @Test
    public void testAddDepartmentDetailsSuccess() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);

        Department department = new Department();
        department.setDepartmentId(1);
        department.setBrandId(1);
        department.setDepartmentName("TestDepartment");
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        List<BrandGymPlanFixedDTO> listFixedGymPlan = new ArrayList<>();
        List<BrandGymPlanFlexDTO> listFlexGymPlan = new ArrayList<>();
        List<BrandAmenitie> brandAmenitie = new ArrayList<>();
        List<Feature> features = new ArrayList<>();

        when(gymPlanService.getAllGymPlanFixedByBrandIdActive(anyInt())).thenReturn(listFixedGymPlan);
        when(gymPlanService.getAllGymPlanFlexByBrandIdActive(anyInt())).thenReturn(listFlexGymPlan);
        when(brandAmenitieService.getAllByBrandIDActivate(anyInt())).thenReturn(brandAmenitie);
        when(departmentFeatureService.getAllFeatures()).thenReturn(features);

        // Act
        String result = gymOwnerController.addDepartmentDetails(session, model);

        // Assert
        assertEquals("redirect:/gym-owner/department/info", result);
         }



    @Test
    void testGetDepartmentInfo() {
        // Arrange

        User user = new User();
        user.setUserId(1); // Set the user ID as needed

        Department departmentDetails = new Department();
        departmentDetails.setDepartmentId(1); // Set the department ID as needed

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(user.getUserId())).thenReturn(departmentDetails);
        when(departmentScheduleService.getAllByDepartmentID(departmentDetails.getDepartmentId())).thenReturn(Mockito.anyList());

        // Act
        String result = gymOwnerController.getDepartmentInfo(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-info", result);

        }

//    @Test
//    void testUpdateDepartmentInfo_Success() {
//        // Arrange
//        UpdateGymOwnerDepartmentInfo updateInfo = new UpdateGymOwnerDepartmentInfo();
//        BindingResult bindingResult = mock(BindingResult.class);
//        updateInfo.setCapacity(50); // Set a default value for capacity
//
//        Department department = new Department();
//        department.setDepartmentId(1);
//
//        when(session.getAttribute("userInfo")).thenReturn(new User()); // Set the user as needed
//        when(departmentService.getByUserId(anyInt())).thenReturn(department);
//        when(departmentScheduleService.deleteAllDepartmentSchedule(anyInt())).thenReturn(1);
//        when(departmentScheduleService.addDepartmentSchedule(anyMap(), anyInt())).thenReturn(new int[]{1, 1, 1, 1, 1, 1, 1});
//
//        // Act
//        String result = gymOwnerController.updateDepartmentInfo(updateInfo, bindingResult, session, model);
//
//        // Assert
//        assertEquals("redirect:/gym-owner/department/info", result);
//
//          }

    @Test
    void testGetDepartmentAmenities_Success() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        Department department = new Department();

        department.setBrandId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(1)).thenReturn(department);

        List<BrandAmenitie> brandAmenities = Arrays.asList(new BrandAmenitie(), new BrandAmenitie());
        List<DepartmentAmenitie> departmentAmenities = Arrays.asList(new DepartmentAmenitie(), new DepartmentAmenitie());

        when(brandAmenitieService.getAllByBrandIDActivate(1)).thenReturn(brandAmenities);
        when(departmentAmenitieService.getAllAmenitieOfDepartment(1)).thenReturn(departmentAmenities);

        // Act
        String result = gymOwnerController.getDepartmentAmenities(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-amenities", result);

         }


    @Test
    void testUpdateDepartmentAmenities_Success() {
        // Arrange

        when(session.getAttribute("userInfo")).thenReturn(new User());

        List<Integer> selectedAmenitieId = Arrays.asList(1, 2, 3);

        // Mocking successful deletion and insertion
        when(departmentAmenitieService.deleteAllDepartmentAmenitie(anyInt())).thenReturn(1);
        when(departmentAmenitieService.insertDepartmentAmenitie(anyInt(), anyList())).thenReturn(new int[]{1, 1, 1});

        // Act
        String result = gymOwnerController.updateDepartmentAmenities(session, model, selectedAmenitieId, 1);

        // Assert
        assertEquals("redirect:/gym-owner/department/amenities", result);

        // Verify that necessary methods were called
        verify(session, times(1)).getAttribute("userInfo");
        verify(departmentAmenitieService, times(1)).deleteAllDepartmentAmenitie(1);
        verify(departmentAmenitieService, times(1)).insertDepartmentAmenitie(1, selectedAmenitieId);
    }

    @Test
    void testGetDepartmentFeatures() {
        // Arrange
        User user = new User();
        user.setUserId(1); // Set appropriate values

        Department department = new Department();
        department.setDepartmentId(1); // Set appropriate values

        List<Feature> allFeatures = new ArrayList<>();
        // Add some features to the list

        List<DepartmentFeature> departmentFeatures = new ArrayList<>();
        // Add some department features to the list

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(user.getUserId())).thenReturn(department);
        when(departmentFeatureService.getAllFeatures()).thenReturn(allFeatures);
        when(departmentFeatureService.getDepartmentFeatures(department.getDepartmentId())).thenReturn(departmentFeatures);

        // Act
        String result = gymOwnerController.getDepartmentFeatures(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-features", result); // Set the expected view name

    }

    @Test
    void testUpdateDepartmentFeatures() {
        // Arrange
        List<Integer> selectedId = Arrays.asList(1, 2, 3);
        int departmentId = 1;
        when(departmentFeatureService.deleteAllDepartmentFeatures(departmentId)).thenReturn(1);
        when(departmentFeatureService.insertDepartmentFeature(departmentId, selectedId)).thenReturn(new int[]{1, 1, 1});

        // Act
        String result = gymOwnerController.updateDepartmentFeatures(session, model, selectedId, departmentId);

        // Assert
        assertEquals("redirect:/gym-owner/department/features", result);

    }
    @Test
    void testGetDepartmentImages() {
        // Arrange


        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(departmentAlbumsService.getAllByDepartmentID(anyInt())).thenReturn(new ArrayList<>());

        // Act
        String result = gymOwnerController.getDepartmentImages(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-image", result); // Set the expected view name

    }

//    @Test
//    void testUpdateDepartmentImages() {
//        // Arrange
//
//
//        when(session.getAttribute("userInfo")).thenReturn(new User());
//        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
//        when(model.addAttribute(anyString(), any())).thenReturn(model);
//
//        // Act
//        String result = gymOwnerController.updateDepartmentImages(session, model, "logo.jpg", "thumbnail.jpg", "wallpaper.jpg", "album1.jpg,album2.jpg");
//
//        // Assert
//        assertEquals("redirect:/gym-owner/department/image", result);
//
//    }

    @Test
    void testGetDepartmentLocation() {
        // Arrange

        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(departmentService.getByUserId(anyInt())).thenReturn(new Department());
        when(model.addAttribute(anyString(), any())).thenReturn(model);

        // Act
        String result = gymOwnerController.getDepartmentLocation(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-location", result);


    }



    @Test
    void testUpdateDepartmentLocationSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        UpdateGymOwnerDepartmentLocation locationUpdate = new UpdateGymOwnerDepartmentLocation();
        locationUpdate.setLongitude("10.12345");
        locationUpdate.setLatitude("20.54321");
        User user = new User();
        user.setUserId(100);
        Department department = new Department();
        department.setDepartmentId(1);
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(anyInt())).thenReturn(department);

        // Act
        String result = gymOwnerController.updateDepartmentLocation(session, model, locationUpdate, mock(BindingResult.class));

        // Assert
        assertEquals("redirect:/gym-owner/department/location", result);
        // Verify that departmentService.updateLongitudeLatitude is called with the correct parameters
        verify(departmentService, times(1)).updateLongitudeLatitude(eq(1), eq(10.12345), eq(20.54321));
    }

    @Test
    void testGetDepartmentGymPlansSuccess() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setUserId(100);
        Department department = new Department();
        department.setDepartmentId(1);
        department.setBrandId(2);

        // Gym plans data
        List<BrandGymPlanFixedDTO> listFixedGymPlan = Arrays.asList(new BrandGymPlanFixedDTO(), new BrandGymPlanFixedDTO());
        List<BrandGymPlanFlexDTO> listFlexGymPlan = Arrays.asList(new BrandGymPlanFlexDTO(), new BrandGymPlanFlexDTO());

        // Selected gym plans data
        List<GymPlanDepartmentNameDto> listFixedGymPlanSelected = Arrays.asList(new GymPlanDepartmentNameDto(), new GymPlanDepartmentNameDto());
        List<GymPlanDepartmentNameDto> listFlexGymPlanSelected = Arrays.asList(new GymPlanDepartmentNameDto(), new GymPlanDepartmentNameDto());

        when(session.getAttribute("userInfo")).thenReturn(user);

        when(gymPlanService.getAllGymPlanFixedByBrandIdActive(anyInt())).thenReturn(listFixedGymPlan);
        when(gymPlanService.getAllGymPlanFlexByBrandIdActive(anyInt())).thenReturn(listFlexGymPlan);
        when(gymPlanService.getGymPlanDepartmentFixedByDepartmentId(anyInt())).thenReturn(listFixedGymPlanSelected);
        when(gymPlanService.getGymPlanDepartmentFlexByDepartmentId(anyInt())).thenReturn(listFlexGymPlanSelected);

        // Act
        String result = gymOwnerController.getDepartmentGymPlans(session, model);

        // Assert
        assertEquals("gym-owner/gym-department-update-plan", result);

    }

    @Test
    void testUpdateDepartmentGymPlansSuccess() {
        // Arrange

        List<Integer> selectedFixedGymPlanId = Arrays.asList(1, 2, 3);
        List<Integer> selectedFlexGymPlanId = Arrays.asList(4, 5, 6);
        int departmentId = 1;

        when(gymPlanService.deleteAllGymPlanByDepartmentId(anyInt())).thenReturn(2);
        when(gymPlanService.insertGymPlanDepartment(anyInt(), anyList())).thenReturn(new int[]{1, 2, 3});

        // Act
        String result = gymOwnerController.updateDepartmentGymPlans(session, model, selectedFixedGymPlanId, selectedFlexGymPlanId, departmentId);

        // Assert
        assertEquals("redirect:/gym-owner/department/gym-plans", result);
         }

    @Test
    void testCheckAndSetIsFirstTimeFromDB() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setUserId(1);
        Department departmentDetails = new Department();
        departmentDetails.setDepartmentId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(user.getUserId())).thenReturn(departmentDetails);
        when(session.getAttribute("isFirstTime")).thenReturn(true); // Simulate different value in session
        when(departmentService.checkFirstTimeDepartmentCreated(departmentDetails.getDepartmentId())).thenReturn(true);

        // Act
        boolean result = gymOwnerController.checkAndSetIsFirstTime(session, model);

        // Assert
        assertTrue(result);

    }

    @Test
    void testCheckAndSetIsFirstTimeFromSession() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        User user = new User();
        user.setUserId(1);
        Department departmentDetails = new Department();
        departmentDetails.setDepartmentId(1);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(departmentService.getByUserId(user.getUserId())).thenReturn(departmentDetails);
        when(session.getAttribute("isFirstTime")).thenReturn(true); // Simulate the same value in session
        when(departmentService.checkFirstTimeDepartmentCreated(departmentDetails.getDepartmentId())).thenReturn(true);

        // Act
        boolean result = gymOwnerController.checkAndSetIsFirstTime(session, model);

        // Assert
        assertTrue(result);

    }
}
