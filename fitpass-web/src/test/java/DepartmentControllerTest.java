import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.controller.DepartmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private GymPlanService gymPlanService;

    @Mock
    private DepartmentScheduleService departmentScheduleService;

    @Mock
    private DepartmentAlbumsService departmentAlbumsService;

    @Mock
    private DepartmentFeatureService departmentFeatureService;

    @Mock
    private DepartmentAmenitieService departmentAmenitieService;

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetDepartment() {
        // Mock data for successful execution
        Department mockDepartment = new Department();
        when(departmentService.getOne(anyInt())).thenReturn(mockDepartment);

        List<GymPlanDto> mockGymPlans = Arrays.asList(new GymPlanDto());
        when(gymPlanService.getGymPlanDetailsByDepartmentId(anyInt())).thenReturn(mockGymPlans);

        List<DepartmentSchedule> mockDepartmentSchedules = Arrays.asList(new DepartmentSchedule());
        when(departmentScheduleService.getAllByDepartmentID(anyInt())).thenReturn(mockDepartmentSchedules);

        List<DepartmentAlbums> mockDepartmentAlbums = Arrays.asList(new DepartmentAlbums());
        when(departmentAlbumsService.getAllByDepartmentID(anyInt())).thenReturn(mockDepartmentAlbums);

        DepartmentDTO mockDepartmentDTO = new DepartmentDTO();
        when(departmentService.filterDepartmentFeedbacks(anyInt())).thenReturn(mockDepartmentDTO);

        List<DepartmentFeature> mockDepartmentFeatures = Arrays.asList(new DepartmentFeature());
        when(departmentFeatureService.getDepartmentFeatures(anyInt())).thenReturn(mockDepartmentFeatures);

        List<DepartmentAmenities> mockDepartmentAmenities = Arrays.asList(new DepartmentAmenities());
        when(departmentAmenitieService.getAllDepartmentAmenitiesActivate(anyInt())).thenReturn(mockDepartmentAmenities);

        // Mock parameters
        int departmentId = 1;
        int page = 1;
        int size = 7;

        // Call the method
        String viewName = departmentController.getDepartment(departmentId, model, page, size);

        // Assertions
        assertEquals("gym-department-details", viewName);

    }

    @Test
    void testGetDepartmentDuplicateKeyException() {
        // Mock DuplicateKeyException
        when(departmentService.getOne(anyInt())).thenThrow(DuplicateKeyException.class);

        String viewName = departmentController.getDepartment(1, model, 1, 7);
        assertEquals("error/duplicate-key-error", viewName);

    }

    @Test
    void testGetDepartmentEmptyResultDataAccessException() {
        // Mock EmptyResultDataAccessException
        when(departmentService.getOne(anyInt())).thenThrow(EmptyResultDataAccessException.class);

        String viewName = departmentController.getDepartment(1, model, 1, 7);
        assertEquals("error/no-data", viewName);
        // Add more assertions if needed
    }

    @Test
    void testGetDepartmentIncorrectResultSizeDataAccessException() {
        // Mock IncorrectResultSizeDataAccessException
        when(departmentService.getOne(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);

        String viewName = departmentController.getDepartment(1, model, 1, 7);
        assertEquals("error/incorrect-result-size-error", viewName);

    }

}