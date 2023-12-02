import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryFlexible;
import com.ks.fitpass.checkInHistory.dto.CheckInHistoryPage;
import com.ks.fitpass.checkInHistory.service.CheckInHistoryService;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.service.DepartmentService;
import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.notification.service.NotificationService;
import com.ks.fitpass.notification.service.WebSocketService;
import com.ks.fitpass.order.service.OrderDetailService;
import com.ks.fitpass.transaction.service.TransactionService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.EmployeeController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_PositiveCase() {
        // Mock data
        int departmentId = 1;
        List<CheckInFixedDTO> checkInFixedDTOList = new ArrayList<>();
        List<CheckedInFixedDTO> checkedInDTOList = new ArrayList<>();

        // Set up mock behavior
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt())).thenReturn(checkInFixedDTOList);
        when(employeeService.getListCheckedInFixedByDepartmentId(anyInt())).thenReturn(checkedInDTOList);

        // Call the method
        String result = employeeController.getCheckInListOfFixedCustomer(departmentId, model);

        // Verify the interactions and assertions
        verify(employeeService, times(1)).getListNeedCheckInFixedByDepartmentId(departmentId);
        verify(employeeService, times(1)).getListCheckedInFixedByDepartmentId(departmentId);

        verify(model, times(1)).addAttribute("checkInList", checkInFixedDTOList);
        verify(model, times(1)).addAttribute("checkedInList", checkedInDTOList);
        verify(model, times(1)).addAttribute("departmentId", departmentId);

        assertEquals("employee/employee-check-in-fixed", result);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_DuplicateKeyException() {
        int departmentId = 1;

        // Set up mock behavior for DuplicateKeyException
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt())).thenThrow(DuplicateKeyException.class);

        // Call the method
        String result = employeeController.getCheckInListOfFixedCustomer(departmentId, model);

        // Verify the interactions and assertions
        assertEquals("error/duplicate-key-error", result);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_EmptyResultDataAccessException() {
        int departmentId = 1;

        // Set up mock behavior for EmptyResultDataAccessException
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        // Call the method
        String result = employeeController.getCheckInListOfFixedCustomer(departmentId, model);

        // Verify the interactions and assertions
        assertEquals("error/no-data", result);
    }

    @Test
    public void testGetCheckInListOfFixedCustomer_IncorrectResultSizeDataAccessException() {
        int departmentId = 1;

        // Set up mock behavior for IncorrectResultSizeDataAccessException
        when(employeeService.getListNeedCheckInFixedByDepartmentId(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Call the method
        String result = employeeController.getCheckInListOfFixedCustomer(departmentId, model);

        // Verify the interactions and assertions
        assertEquals("error/incorrect-result-size-error", result);
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

        // Create mock objects for the service dependencies
        EmployeeService employeeService = Mockito.mock(EmployeeService.class);
        OrderDetailService orderDetailService = Mockito.mock(OrderDetailService.class);
        NotificationService notificationService = Mockito.mock(NotificationService.class);
        WalletService walletService = Mockito.mock(WalletService.class);
        WebSocketService webSocketService = Mockito.mock(WebSocketService.class);
        BrandService brandService = Mockito.mock(BrandService.class);
        DepartmentService departmentService = Mockito.mock(DepartmentService.class);
        TransactionService transactionService = Mockito.mock(TransactionService.class);
        UserService userService = Mockito.mock(UserService.class);

        // Create the controller instance using the mocked services
        EmployeeController employeeController = new EmployeeController(employeeService, orderDetailService,
                notificationService, checkInHistoryService, walletService, webSocketService, brandService,
                departmentService, transactionService, userService);


        // Query CheckInHistoryPage for each page
        for (int page = 1; page <= Math.ceil((double) mockTotalListCheckInHistoryFlexible / pageSize); page++) {
            Mockito.when(checkInHistoryService.getListCheckInHistoryFlexibleByDepartmentId(departmentId, page, pageSize))
                    .thenReturn(mockListFlexible);

            // Perform the test
            ResponseEntity<CheckInHistoryPage> response = employeeController.getCheckInHistory(departmentId, plan, page, pageSize);

            // Logging the response
            System.out.println("Response for page " + page + ": " + response);

            // Assertions
            Assert.assertEquals(HttpStatus.OK, ((ResponseEntity<?>) response).getStatusCode());
            CheckInHistoryPage checkInHistoryPage = response.getBody();
            Assert.assertNotNull(checkInHistoryPage);
            Assert.assertEquals(departmentId, checkInHistoryPage.getDepartmentId());
            Assert.assertEquals(page, checkInHistoryPage.getCurrentPage());
            // Assert other properties of the CheckInHistoryPage object

            // Assert the specific properties based on the plan type
            Assert.assertNotNull(checkInHistoryPage.getListFlexible());
            Assert.assertEquals(mockListFlexible, checkInHistoryPage.getListFlexible());
            // Assert other properties specific to the flexible plan

            // Verify the method calls on the mock checkInHistoryService
            Mockito.verify(checkInHistoryService, Mockito.times(1)).getListCheckInHistoryFlexibleByDepartmentId(departmentId, page, pageSize);
        }
    }
}
