import com.ks.fitpass.employee.dto.CheckInFixedDTO;
import com.ks.fitpass.employee.dto.CheckedInFixedDTO;
import com.ks.fitpass.employee.service.EmployeeService;
import com.ks.fitpass.web.controller.EmployeeController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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


}
