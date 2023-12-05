import com.ks.fitpass.brand.entity.BrandAmenitie;
import com.ks.fitpass.brand.service.BrandAmenitieService;
import com.ks.fitpass.core.entity.GymOwnerListDTO;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.department.dto.EmployeUpdateDTO;
import com.ks.fitpass.department.dto.EmployeeCreateDTO;
import com.ks.fitpass.department.dto.UserFeedbackOfBrandOwner;
import com.ks.fitpass.department.entity.Department;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GymOwnerControllerTest {

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





}
