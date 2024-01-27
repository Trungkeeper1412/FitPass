import com.fasterxml.jackson.core.JsonProcessingException;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryPage;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDetail;
import com.ks.fitpass.core.repository.UserRepository;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.dto.DepartmentNotificationDTO;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.employee.dto.*;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.dto.UserReceiveMessageDTO;
import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.EmployeeController;
import jakarta.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private Model model;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private WalletService walletService;
    @Mock
    private CheckInHistoryService checkInHistoryService;
    @Mock
    private DepartmentService departmentService;
    @Mock
    private WebSocketService webSocketService;
    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_PositiveCase() {
        // Arrange
        int page = 1;
        int size = 6;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        List<CheckInFixedDTO> checkInFixedDTOList = new ArrayList<>();
        int totalListCheckInFixed = 10; // Mock the total number of entries
        int totalPages = (int) Math.ceil((double) totalListCheckInFixed / size);

        FixedPlanPage fixedPlanPage = FixedPlanPage.builder()
                .listCheckInFixed(checkInFixedDTOList)
                .currentPage(page)
                .totalPages(totalPages)
                .departmentId(departmentId)
                .build();

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFixedByDepartmentId(departmentId, page, size)).thenReturn(checkInFixedDTOList);
        when(employeeService.getTotalListNeedCheckInFixedByDepartmentId(departmentId)).thenReturn(totalListCheckInFixed);

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFixedCustomer(departmentId, status, page, size, session);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.getBody() instanceof FixedPlanPage);
        FixedPlanPage resultFixedPlanPage = (FixedPlanPage) result.getBody();
        assertNotNull(resultFixedPlanPage);
        assertEquals(fixedPlanPage.getListCheckInFixed(), resultFixedPlanPage.getListCheckInFixed());
        assertEquals(fixedPlanPage.getCurrentPage(), resultFixedPlanPage.getCurrentPage());
        assertEquals(fixedPlanPage.getTotalPages(), resultFixedPlanPage.getTotalPages());
        assertEquals(fixedPlanPage.getDepartmentId(), resultFixedPlanPage.getDepartmentId());

        // Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
        verify(employeeService).getListNeedCheckInFixedByDepartmentId(departmentId, page, size);
        verify(employeeService).getTotalListNeedCheckInFixedByDepartmentId(departmentId);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_DuplicateKeyException() {
        // Arrange
        String status = "check-in";
        int page = 1;
        int size = 6;
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFixedByDepartmentId(departmentId, page, size))
                .thenThrow(new DuplicateKeyException("Duplicate key"));

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFixedCustomer(departmentId, status, page, size, session);

        // Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("Error: Duplicate key violation.", result.getBody());

        // Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
        verify(employeeService).getListNeedCheckInFixedByDepartmentId(departmentId, page, size);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_EmptyResultDataAccessException() {
        int page = 1;
        int size = 6;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId
        User stubUser = new User();
        stubUser.setUserId(employeeId);

        // Set up mock behavior for EmptyResultDataAccessException
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Call the method
        ResponseEntity<?> result = employeeController.getCheckInListOfFixedCustomer(departmentId, status, page, size, session);

        // Assert
        ResponseEntity<?> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No data found.");
        assertEquals(expected, result);

        // Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_IncorrectResultSizeDataAccessException() {
        int page = 1;
        int size = 6;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);

        // Set up mock behavior for IncorrectResultSizeDataAccessException
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Call the method
        ResponseEntity<?> result = employeeController.getCheckInListOfFixedCustomer(departmentId, status, page, size, session);

        // Verify the interactions and assertions
        ResponseEntity<?> expectedResult = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Incorrect result size.");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetCheckInHistory() {
        int departmentId = 1;
        String plan = "flexible";
        int pageSize = 7;

        // Mock the checkInHistoryService and its methods
        List<CheckInHistoryFlexible> mockListFlexible = new ArrayList<>();
        // Add some mock data to the listFlexible
        int mockTotalListCheckInHistoryFlexible = 28;

        CheckInHistoryService checkInHistoryService = Mockito.mock(CheckInHistoryService.class);
        Mockito.when(checkInHistoryService.getTotalListCheckInHistoryFlexibleByDepartmentId(departmentId))
                .thenReturn(mockTotalListCheckInHistoryFlexible);

        // Create the controller instance using the mocked services
        EmployeeController employeeController = new EmployeeController(employeeService, orderDetailService,
                notificationService, checkInHistoryService, webSocketService,
                departmentService, userService, userRepository);


        // Query CheckInHistoryPage for each page
        for (int page = 1; page <= Math.ceil((double) mockTotalListCheckInHistoryFlexible / pageSize); page++) {
            Mockito.when(checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(departmentId, page, pageSize))
                    .thenReturn(mockListFlexible);

            // Perform the test
            ResponseEntity<CheckInHistoryPage> response = employeeController.getCheckInHistory(departmentId, plan, page, pageSize);

            // Logging the response
            System.out.println("Response for page " + page + ": " + response);

            // Assertions
            Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
            CheckInHistoryPage checkInHistoryPage = response.getBody();
            assertNotNull(checkInHistoryPage);
            Assert.assertEquals(departmentId, checkInHistoryPage.getDepartmentId());
            Assert.assertEquals(page, checkInHistoryPage.getCurrentPage());
            // Assert other properties of the CheckInHistoryPage object

            // Assert the specific properties based on the plan type
            assertNotNull(checkInHistoryPage.getListFlexible());
            Assert.assertEquals(mockListFlexible, checkInHistoryPage.getListFlexible());
            // Assert other properties specific to the flexible plan

            // Verify the method calls on the mock checkInHistoryService
            Mockito.verify(checkInHistoryService, Mockito.times(1)).getListCheckInHistoryFlexibleByDepartmentId(departmentId, page, pageSize);
        }
    }


    @Test
    public void testCheckValidDepartmentParameterWithValidDepartment() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(userRepository.getDepartmentIdByEmployeeId(anyInt())).thenReturn(1);

        // Act
        boolean result = employeeController.checkValidDepartmentParameter(session, 1);

        // Assert
        assert (result);
    }

    @Test
    public void testCheckValidDepartmentParameterWithInvalidDepartment() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(userRepository.getDepartmentIdByEmployeeId(anyInt())).thenReturn(1);

        // Act
        boolean result = employeeController.checkValidDepartmentParameter(session, 2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckValidDepartmentParameterWithEmptyResultDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(null);

        // Act
        boolean result = employeeController.checkValidDepartmentParameter(session, 1);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCheckValidDepartmentParameterWithDataAccessException() {
        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(new User());
        when(userRepository.getDepartmentIdByEmployeeId(anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        boolean result = employeeController.checkValidDepartmentParameter(session, 1);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testChangePasswordWithValidData() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUserPassword(new BCryptPasswordEncoder().encode("currentPassword"));

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.updatePassword(anyString(), anyInt())).thenReturn(true);

        // Act
        String result = employeeController.changePassword("currentPassword", "newPassword", "newPassword", model, session, redirectAttributes);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(userService, times(1)).updatePassword(anyString(), anyInt());
        assertEquals("redirect:/employee/changePassword", result);
        verify(model, never()).addAttribute(eq("error"), anyString());
    }

    @Test
    public void testChangePasswordWithIncorrectCurrentPassword() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUserPassword(new BCryptPasswordEncoder().encode("currentPassword"));

        when(session.getAttribute("userInfo")).thenReturn(user);

        // Act
        String result = employeeController.changePassword("wrongCurrentPassword", "newPassword", "newPassword", model, session, redirectAttributes);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(userService, never()).updatePassword(anyString(), anyInt());
        verify(model, times(1)).addAttribute("currentPasswordError", "Mật khẩu hiện tại không đúng");
        assertEquals("employee/change-password", result);
    }

    @Test
    public void testChangePasswordWithMismatchedPasswords() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUserPassword(new BCryptPasswordEncoder().encode("currentPassword"));

        when(session.getAttribute("userInfo")).thenReturn(user);

        // Act
        String result = employeeController.changePassword("currentPassword", "newPassword", "confirmPassword", model, session, redirectAttributes);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(userService, never()).updatePassword(anyString(), anyInt());
        verify(model, times(1)).addAttribute("passwordMismatchError", "Mật khẩu mới và xác nhận mật khẩu không khớp");
        assertEquals("employee/change-password", result);
    }

    @Test
    public void testChangePasswordWithException() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUserPassword(new BCryptPasswordEncoder().encode("currentPassword"));

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.updatePassword(anyString(), anyInt())).thenThrow(RuntimeException.class);

        // Act
        String result = employeeController.changePassword("currentPassword", "newPassword", "newPassword", model, session, redirectAttributes);

        // Assert
        verify(session, times(1)).getAttribute("userInfo");
        verify(userService, times(1)).updatePassword(anyString(), anyInt());
        verify(model, times(1)).addAttribute("unexpectedError", "Lỗi không xác định");
        verify(model, never()).addAttribute(eq("success"), any());
        assertEquals("employee/change-password", result);
    }

    @Test
    public void testGetCheckInListOfFlexibleCustomerSuccess() {
        // Arrange
        int page = 1;
        int size = 7;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId
        List<CheckInFlexibleDTO> checkInList = new ArrayList<>();

        // Mocking the information for the expected FlexiblePlanPage
        int totalListCheckInFlexibleDTOList = 10; // Example number for total list size
        int totalPages = (int) Math.ceil((double) totalListCheckInFlexibleDTOList / size);

        FlexiblePlanPage expectedFlexiblePlanPage = FlexiblePlanPage.builder()
                .listCheckOutFlexible(null)
                .listCheckInFlexible(checkInList)
                .currentPage(page)
                .totalPages(totalPages)
                .departmentId(departmentId)
                .build();

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFlexibleByDepartmentId(departmentId, page, size)).thenReturn(checkInList);
        when(employeeService.getTotalListNeedCheckInFlexibleByDepartmentId(departmentId)).thenReturn(totalListCheckInFlexibleDTOList);

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFlexibleCustomer(departmentId, status, page, size, session);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        FlexiblePlanPage actualFlexiblePlanPage = (FlexiblePlanPage) result.getBody();
        assertNotNull(actualFlexiblePlanPage);
        assertEquals(expectedFlexiblePlanPage.getCurrentPage(), actualFlexiblePlanPage.getCurrentPage());
        assertEquals(expectedFlexiblePlanPage.getTotalPages(), actualFlexiblePlanPage.getTotalPages());
        assertEquals(expectedFlexiblePlanPage.getDepartmentId(), actualFlexiblePlanPage.getDepartmentId());
        assertEquals(expectedFlexiblePlanPage.getListCheckInFlexible(), actualFlexiblePlanPage.getListCheckInFlexible());

        // Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
        verify(employeeService).getListNeedCheckInFlexibleByDepartmentId(departmentId, page, size);
        verify(employeeService).getTotalListNeedCheckInFlexibleByDepartmentId(departmentId);
    }

    @Test
    public void testGetCheckInListOfFlexibleCustomerWithDuplicateKeyException() {
        // Arrange
        int page = 1;
        int size = 7;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFlexibleCustomer(departmentId, status, page, size, session);

        // Assert
        ResponseEntity<?> expectedResult = ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Duplicate key violation.");
        assertEquals(expectedResult, result);

        //Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
    }

    @Test
    public void testGetCheckInListOfFlexibleCustomerWithEmptyResultDataAccessException() {
        // Arrange
        int page = 1;
        int size = 7;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFlexibleCustomer(departmentId, status, page, size, session);

        // Assert
        ResponseEntity<?> expectedResult = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No data found.");
        assertEquals(expectedResult, result);

        //Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
        verify(employeeService).getListNeedCheckInFlexibleByDepartmentId(departmentId, page, size);
    }

    @Test
    public void testGetCheckInListOfFlexibleCustomerWithIncorrectResultSizeDataAccessException() {
        // Arrange
        int page = 1;
        int size = 7;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        // Arrange
        when(employeeService.getListNeedCheckInFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFlexibleCustomer(departmentId, status, page, size, session);

        // Assert
        ResponseEntity<?> expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Incorrect result size.");

        assertEquals(expected, result);
    }

    @Test
    public void testGetCheckInListOfFlexibleCustomerWithDataAccessException() {
        // Arrange
        int page = 1;
        int size = 7;
        String status = "check-in";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        // Arrange
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.getListNeedCheckInFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<?> result = employeeController.getCheckInListOfFlexibleCustomer(departmentId, status, page, size, session);

        // Assert
        ResponseEntity<?> expectedResult = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error: Data access issue.");
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_searchListByStatus_InvalidSessionOrDepartment() {
        // Arrange
        int departmentId = 1;
        String status = "check-in";
        session = null;

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus("someText", "username", departmentId, status, 1, 6, session);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access denied: Invalid department or session.", response.getBody());
    }
    
    @Test
    public void test_searchListByStatus_CheckIn_Success() {
        // Arrange
        String searchText = "johndoe";
        String searchOption = "username";
        String status = "check-in";
        int page = 1;
        int size = 6;
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId
        int countSearchList = 10;
        List<CheckInFlexibleDTO> searchResults = new ArrayList<>();
        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.countSearchListCheckInByUsername(searchText, departmentId)).thenReturn(countSearchList);
        when(employeeService.searchListCheckInByUsername(searchText, departmentId, page, size)).thenReturn(searchResults);

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus(searchText, searchOption, departmentId, status, page, size, session);

        // Assert
        assertTrue(response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.NO_CONTENT);

        if (response.getStatusCode() == HttpStatus.OK) {
            assertNotNull("Expected body to be not null", response.getBody());
            assertTrue("Expected body to be instance of FlexiblePlanPage",
                    response.getBody() instanceof FlexiblePlanPage);

            FlexiblePlanPage responseBody = (FlexiblePlanPage) response.getBody();
            assertEquals("Expected current page to match", page, responseBody.getCurrentPage());
            assertEquals("Expected total pages to be correct",
                    (int) Math.ceil((double) countSearchList / size),
                    responseBody.getTotalPages());
            assertEquals("Expected search results to match", searchResults, responseBody.getListCheckInFlexible());
        } else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            assertNull("Expected body to be null for NO_CONTENT status", response.getBody());
        }
    }
    @Test
    public void test_searchListByStatus_CheckedIn_NoResults() {
        // Arrange
        String searchText = "doesNotExist";
        String searchOption = "username";
        String status = "checked-in";
        int page = 1;
        int size = 6;
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.countSearchListCheckOutByUsername(anyString(), anyInt())).thenReturn(0);

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus(searchText, searchOption, departmentId, status, page, size, session);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void test_searchListByStatus_InvalidStatus() {
        // Arrange
        String status = "invalid-status";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus("someText", "username", departmentId, status, 1, 6, session);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid search status: " + status, response.getBody());
    }

    @Test
    public void test_searchListByStatus_SuccessfulSearchByPhoneNumber_WithResults() {
        // Arrange
        String status = "check-in";
        String searchOption = "phone-number";
        String searchText = "1234567890";
        int totalRecords = 3; // Assume there are 3 records
        List<CheckInFlexibleDTO> expectedResults = Arrays.asList(
                new CheckInFlexibleDTO(),
                new CheckInFlexibleDTO(),
                new CheckInFlexibleDTO()
        );
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.countSearchListCheckInByPhoneNumber(searchText, departmentId)).thenReturn(totalRecords);
        when(employeeService.searchListCheckInByPhoneNumber(searchText, departmentId, 1, 6)).thenReturn(expectedResults);

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus(
                searchText, searchOption, departmentId, status, 1, 6, session
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        //Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
    }

    @Test
    public void test_searchListByStatus_SearchByPhoneNumber_NoResults() {
        // Arrange
        String status = "check-in";
        String searchOption = "phone-number";
        String searchText = "0987654321";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.countSearchListCheckInByPhoneNumber(searchText, departmentId)).thenReturn(0);
        when(employeeService.searchListCheckInByPhoneNumber(searchText, departmentId, 1, 6)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = employeeController.searchListByStatus(
                searchText, searchOption, departmentId, status, 1, 6, session
        );

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        String expectedMessage = "No content found for the provided search criteria.";
        assertEquals(expectedMessage, response.getHeaders().getFirst("X-Message"));

        //Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
    }

    @Test
    public void testSendCheckinRequest() {
        // Arrange
        int orderDetailId = 123;
        User user = new User();
        user.setUserId(456);
        UserReceiveMessageDTO userReceiveMessageDTO = new UserReceiveMessageDTO();
        userReceiveMessageDTO.setGymDepartmentId(789);
        userReceiveMessageDTO.setUserId(123);
        DepartmentNotificationDTO departmentNotificationDTO = new DepartmentNotificationDTO();
        departmentNotificationDTO.setDepartmentName("Test Department");
        departmentNotificationDTO.setDepartmentLogoUrl("https://example.com/logo.png");
        UserDetail employeeDetail = new UserDetail();
        employeeDetail.setFirstName("John");
        employeeDetail.setLastName("Doe");
        user.setUserDetailId(1);
        String usernameSend = "John Doe";
        int userIdSend = 456;
        int userIdReceived = 123;
        String messageType = "Xác nhận check in";
        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentNotificationDTO.getDepartmentName() + ". Hãy xác nhận ngay!";
        Notification notification = new Notification();
        notification.setOrderDetailId(orderDetailId);
        notification.setUserIdSend(userIdSend);
        notification.setUserIdReceive(userIdReceived);
        notification.setMessageType(messageType);
        notification.setMessage(message);
        notification.setDepartmentId(userReceiveMessageDTO.getGymDepartmentId());
        notification.setDepartmentName(departmentNotificationDTO.getDepartmentName());
        notification.setDepartmentLogoUrl(departmentNotificationDTO.getDepartmentLogoUrl());
        notification.setTimeSend(new Timestamp(System.currentTimeMillis()));
        when(session.getAttribute("userInfo")).thenReturn(user);
        when(employeeService.getUserReceiveMessage(orderDetailId)).thenReturn(userReceiveMessageDTO);
        when(departmentService.getDepartmentNotificationDtoById(userReceiveMessageDTO.getGymDepartmentId())).thenReturn(departmentNotificationDTO);
        when(userService.getUserDetailByUserDetailId(user.getUserId())).thenReturn(employeeDetail);
        when(notificationService.insertNotification(notification)).thenReturn(1);


        // Act
        ResponseEntity<Integer> response = employeeController.sendCheckInRequest(orderDetailId, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testSendCheckoutRequest() throws JsonProcessingException {
        // Arrange
        DataSendCheckOutFlexibleDTO dataSendCheckOutFlexibleDTO = new DataSendCheckOutFlexibleDTO();
        dataSendCheckOutFlexibleDTO.setOrderDetailId(123);
        dataSendCheckOutFlexibleDTO.setDuration(45);
        dataSendCheckOutFlexibleDTO.setCheckInTime(new Timestamp(System.currentTimeMillis()));
        dataSendCheckOutFlexibleDTO.setCheckOutTime(System.currentTimeMillis() + 1000);
        dataSendCheckOutFlexibleDTO.setTotalCredit(100.0);

        User user = new User();
        user.setUserId(456);
        UserReceiveMessageDTO userReceiveMessageDTO = new UserReceiveMessageDTO();
        userReceiveMessageDTO.setGymDepartmentId(789);
        userReceiveMessageDTO.setUserId(123);
        DepartmentNotificationDTO departmentNotificationDTO = new DepartmentNotificationDTO();
        departmentNotificationDTO.setDepartmentName("Test Department");
        departmentNotificationDTO.setDepartmentLogoUrl("https://example.com/logo.png");

        UserDetail employeeDetail = new UserDetail();
        employeeDetail.setFirstName("John");
        employeeDetail.setLastName("Doe");
        user.setUserDetailId(1);

        String usernameSend = employeeDetail.getFirstName().concat("").concat(employeeDetail.getLastName());

        int userIdReceived = 123;
        String employeeMessage = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check out ở phòng tập " +
                departmentNotificationDTO.getDepartmentName() + ". Hãy bấm vào để xem chi tiết.";
        dataSendCheckOutFlexibleDTO.setEmployeeMessage(employeeMessage);
        OrderDetailConfirmCheckOut orderCheckOut = new OrderDetailConfirmCheckOut();
        orderCheckOut.setOrderDetailId(123);
        orderCheckOut.setCreditNeedToPay(100.0);
        orderCheckOut.setDurationHavePractice(45);
        orderCheckOut.setCheckOutTime(new Timestamp(System.currentTimeMillis() + 1000));
        int checkInHistoryId = 456;
        orderCheckOut.setHistoryCheckInId(checkInHistoryId);
        double userBalance = 500.0;

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(employeeService.getUserReceiveMessage(dataSendCheckOutFlexibleDTO.getOrderDetailId())).thenReturn(userReceiveMessageDTO);
        when(departmentService.getDepartmentNotificationDtoById(userReceiveMessageDTO.getGymDepartmentId())).thenReturn(departmentNotificationDTO);
        when(userService.getUserDetailByUserDetailId(user.getUserId())).thenReturn(employeeDetail);
        when(notificationService.insertNotification(any(Notification.class))).thenReturn(1);
        when(orderDetailService.getByOrderDetailId(dataSendCheckOutFlexibleDTO.getOrderDetailId())).thenReturn(orderCheckOut);
        when(walletService.getBalanceByUserId(userIdReceived)).thenReturn(userBalance);
        when(checkInHistoryService.getCheckInHistoryIdByOrderDetailIdAndCheckInTime(dataSendCheckOutFlexibleDTO.getOrderDetailId(), dataSendCheckOutFlexibleDTO.getCheckInTime())).thenReturn(checkInHistoryId);

        // Act
        ResponseEntity<Integer> response = employeeController.sendCheckoutRequest(dataSendCheckOutFlexibleDTO, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testShowDetail() {
        // Arrange
        int orderDetailId = 1;
        Timestamp expectedCheckInTime = Timestamp.valueOf("2023-01-01 12:00:00");
        double expectedPricePerHours = 20.0;

        when(checkInHistoryService.getCheckInTimeByOrderDetailId(orderDetailId)).thenReturn(expectedCheckInTime);
        when(orderDetailService.getPricePerHoursByOrderDetailId(orderDetailId)).thenReturn(expectedPricePerHours);

        // Act
        ResponseEntity<DetailCheckOutDTO> responseEntity = employeeController.showDetail(orderDetailId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testShowDetailDataAccessException() {
        // Arrange
        int orderDetailId = 1;
        when(checkInHistoryService.getCheckInTimeByOrderDetailId(orderDetailId)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<DetailCheckOutDTO> responseEntity = employeeController.showDetail(orderDetailId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testSendCheckinRequestFixed() {
        // Arrange
        int orderDetailId = 123;
        User user = new User();
        user.setUserId(456);
        user.setUserAccount("johndoe@example.com");

        UserDetail employeeDetail = new UserDetail();
        employeeDetail.setFirstName("John");
        employeeDetail.setLastName("Doe");

        UserReceiveMessageDTO userReceiveMessageDTO = new UserReceiveMessageDTO();
        userReceiveMessageDTO.setGymDepartmentId(789);
        userReceiveMessageDTO.setUserId(123);
        DepartmentNotificationDTO departmentNotificationDTO = new DepartmentNotificationDTO();
        departmentNotificationDTO.setDepartmentName("Test Department");
        departmentNotificationDTO.setDepartmentLogoUrl("https://example.com/logo.png");

        int userIdSend = 456;
        int userIdReceived = 123;
        String usernameSend = "John Doe";

        String messageType = "Xác nhận check in";
        String message = "Nhân viên với tên " + usernameSend + " đã gửi cho bạn yêu cầu check in ở phòng tập " + departmentNotificationDTO.getDepartmentName() +
                ". Hãy xác nhận ngay!";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Notification notification = new Notification();
        notification.setNotificationId(0);
        notification.setOrderDetailId(orderDetailId);
        notification.setUserIdSend(userIdSend);
        notification.setUserIdReceive(userIdReceived);
        notification.setMessageType(messageType);
        notification.setMessage(message);
        notification.setDepartmentId(userReceiveMessageDTO.getGymDepartmentId());
        notification.setDepartmentName(departmentNotificationDTO.getDepartmentName());
        notification.setDepartmentLogoUrl(departmentNotificationDTO.getDepartmentLogoUrl());
        notification.setTimeSend(timestamp);

        when(session.getAttribute("userInfo")).thenReturn(user);
        when(userService.getUserDetailByUserDetailId(user.getUserId())).thenReturn(employeeDetail);
        when(employeeService.getUserReceiveMessage(orderDetailId)).thenReturn(userReceiveMessageDTO);
        when(departmentService.getDepartmentNotificationDtoById(userReceiveMessageDTO.getGymDepartmentId())).thenReturn(departmentNotificationDTO);
        when(notificationService.insertNotification((notification))).thenReturn(1);

        // Act
        ResponseEntity<Integer> response = employeeController.sendCheckInRequestFixed(orderDetailId, session);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchListFixedCheckInSuccess() {
        // Arrange
        String searchText = "John";
        String searchOption = "username";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        // Mock the necessary session attribute and repository behavior
        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        List<CheckInFixedDTO> mockedList = new ArrayList<>();
        when(employeeService.searchListCheckInFixedByUsername(searchText, departmentId)).thenReturn(mockedList);

        // Act
        ResponseEntity<List<CheckInFixedDTO>> responseEntity = employeeController.searchListFixedCheckIn(session, searchText, searchOption, departmentId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedList.size(), Objects.requireNonNull(responseEntity.getBody()).size());

        // Verify that session and repository methods were called
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
        verify(employeeService).searchListCheckInFixedByUsername(searchText, departmentId);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testSearchListFixedCheckInBadRequest() {
        // Arrange
        String searchText = "John";
        String searchOption = "invalidOption";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.searchListCheckInFixedByUsername(searchText, departmentId)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<List<CheckInFixedDTO>> responseEntity = employeeController.searchListFixedCheckIn(session, searchText, searchOption, departmentId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        //Verify
        verify(session).getAttribute("userInfo");
        verify(userRepository).getDepartmentIdByEmployeeId(employeeId);
    }

    @Test
    public void testSearchListFixedCheckOutSuccess() {
        // Arrange
        String searchText = "John";
        String searchOption = "username";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);
        List<CheckedInFixedDTO> searchResults = Collections.singletonList(new CheckedInFixedDTO());

        when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.searchListCheckedInFixedByUsername(anyString(), anyInt())).thenReturn(searchResults);

        // Act
        ResponseEntity<List<CheckedInFixedDTO>> responseEntity = employeeController.searchListFixedCheckOut(session, searchText, searchOption, departmentId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(searchResults, responseEntity.getBody());
        verify(employeeService, times(1)).searchListCheckedInFixedByUsername(eq(searchText), eq(departmentId));
    }

    @Test
    public void testSearchListFixedCheckOutBadRequest() {
        // Arrange
        String searchText = "John";
        String searchOption = "invalidOption";
        int departmentId = 1;
        int employeeId = 3; // The employeeId corresponding to the valid departmentId

        User stubUser = new User();
        stubUser.setUserId(employeeId);when(session.getAttribute("userInfo")).thenReturn(stubUser);
        when(userRepository.getDepartmentIdByEmployeeId(employeeId)).thenReturn(departmentId);
        when(employeeService.searchListCheckedInFixedByUsername(anyString(), anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<List<CheckedInFixedDTO>> responseEntity = employeeController.searchListFixedCheckOut(session, searchText, searchOption, departmentId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    public void testGetCheckInHistoryWithInvalidSession() {
        String result = employeeController.getCheckInHistory(1, session);

        assertEquals("error/403", result);
        verifyNoMoreInteractions(model);
    }

    @Test
    public void testGetCheckInHistoryFlexibleSuccess() {
        // Arrange
        int departmentId = 1;
        String plan = "flexible";
        int page = 1;
        int size = 7;
        when(checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(checkInHistoryService.getTotalListCheckInHistoryFlexibleByDepartmentId(anyInt())).thenReturn(0);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.getCheckInHistory(departmentId, plan, page, size);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCheckInHistoryFixedSuccess() {
        // Arrange
        int departmentId = 1;
        String plan = "fixed";
        int page = 1;
        int size = 7;
        when(checkInHistoryService.getListCheckInHistoryFixedByDepartmentId(anyInt(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        when(checkInHistoryService.getTotalListCheckInHistoryFixedByDepartmentId(anyInt())).thenReturn(0);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.getCheckInHistory(departmentId, plan, page, size);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(departmentId, responseEntity.getBody().getDepartmentId());
    }

    @Test
    public void testGetCheckInHistoryEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        String plan = "flexible";
        int page = 1;
        int size = 7;
        when(checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.getCheckInHistory(departmentId, plan, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetCheckInHistoryDataAccessException() {
        // Arrange
        int departmentId = 1;
        String plan = "fixed";
        int page = 1;
        int size = 7;
        when(checkInHistoryService.getListCheckInHistoryFixedByDepartmentId(anyInt(), anyInt(), anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.getCheckInHistory(departmentId, plan, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchFlexSuccess() {
        // Arrange
        int departmentId = 1;
        String username = "john_doe";
        String phoneNumber = "123456789";
        String dateFilter = "2023-01-01";
        int page = 1;
        int size = 7;
        when(checkInHistoryService.searchListHistoryFlexible(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFlex(departmentId, username, phoneNumber, dateFilter, page, size);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchFlexEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        int page = 1;
        int size = 7;
        when(checkInHistoryService.searchListHistoryFlexible(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFlex(departmentId, null, null, null, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchFlexDataAccessException() {
        // Arrange
        int departmentId = 1;
        int page = 1;
        int size = 7;
        when(checkInHistoryService.searchListHistoryFlexible(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));
        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFlex(departmentId, null, null, null, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchFixedSuccess() {
        // Arrange
        int departmentId = 1;
        String username = "john_doe";
        String phoneNumber = "123456789";
        String dateFilter = "2023-01-01";
        int page = 1;
        int size = 7;

        when(checkInHistoryService.searchListHistoryFixed(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFixed(departmentId, username, phoneNumber, dateFilter, page, size);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verify that the CheckInHistoryService method is called
        verify(checkInHistoryService, times(1)).searchListHistoryFixed(anyInt(), any(), any(), any(), anyInt(), anyInt());
    }

    @Test
    public void testSearchFixedEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        int page = 1;
        int size = 7;
        when(checkInHistoryService.searchListHistoryFixed(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFixed(departmentId, null, null, null, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testSearchFixedDataAccessException() {
        // Arrange
        int departmentId = 1;
        int page = 1;
        int size = 7;
        when(checkInHistoryService.searchListHistoryFixed(anyInt(), any(), any(), any(), anyInt(), anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<CheckInHistoryPage> responseEntity = employeeController.searchFixed(departmentId, null, null, null, page, size);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetRegistrationList() {

        // Arrange & Act
        String viewName = employeeController.getRegistrationList();

        // Assert
        assertEquals("employee/change-password", viewName);
    }
}
