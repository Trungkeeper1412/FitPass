import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.GymPlanDto;
import com.ks.fitpass.department.entity.*;
import com.ks.fitpass.department.service.*;
import com.ks.fitpass.gymplan.service.GymPlanService;
import com.ks.fitpass.web.controller.DepartmentController;
import com.ks.fitpass.web.enums.PageEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDepartment_PositiveCase() {
        // Mock data
        Department department = new Department();
        List<GymPlanDto> gymPlans = new ArrayList<>();
        List<DepartmentSchedule> departmentSchedules = new ArrayList<>();
        List<DepartmentAlbums> departmentAlbums = new ArrayList<>();
        List<UserFeedback> userFeedbacks = new ArrayList<>();
        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<DepartmentFeature> departmentFeatures = new ArrayList<>();
        List<DepartmentAmenities> departmentAmenities = new ArrayList<>();

        // Set up mock behavior
        when(departmentService.getOne(anyInt())).thenReturn(department);
        when(gymPlanService.getGymPlanDetailsByDepartmentId(anyInt())).thenReturn(gymPlans);
        when(departmentScheduleService.getAllByDepartmentID(anyInt())).thenReturn(departmentSchedules);
        when(departmentAlbumsService.getAllByDepartmentID(anyInt())).thenReturn(departmentAlbums);
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt())).thenReturn(userFeedbacks);
        when(departmentService.filterDepartmentFeedbacks(anyInt())).thenReturn(departmentDTO);
        when(departmentFeatureService.getDepartmentFeatures(anyInt())).thenReturn(departmentFeatures);
        when(departmentAmenitieService.getAllDepartmentAmenitiesActivate(anyInt())).thenReturn(departmentAmenities);

        // Create a mock model
        Model model = mock(Model.class);

        // Call the method
        String result = departmentController.getDepartment(1, model, 1, 7);

        // Verify the interactions and assertions
        verify(departmentService, times(1)).getOne(anyInt());
        verify(gymPlanService, times(1)).getGymPlanDetailsByDepartmentId(anyInt());
        verify(departmentScheduleService, times(1)).getAllByDepartmentID(anyInt());
        verify(departmentAlbumsService, times(1)).getAllByDepartmentID(anyInt());
        verify(departmentService, times(1)).getDepartmentFeedback(anyInt(), anyInt(), anyInt());
        verify(departmentService, times(1)).filterDepartmentFeedbacks(anyInt());
        verify(departmentFeatureService, times(1)).getDepartmentFeatures(anyInt());
        verify(departmentAmenitieService, times(1)).getAllDepartmentAmenitiesActivate(anyInt());
// Verify the interactions and assertions
        verify(model, times(12)).addAttribute(anyString(), ArgumentMatchers.any());
        assertEquals("gym-department-details", result);
    }

    @Test
    public void testGetDepartment_Negative() {
        // Mock data
        int departmentId = 1;
        int page = 1;
        int size = 7;

        // Mock service method to throw EmptyResultDataAccessException
        when(departmentService.getOne(departmentId)).thenThrow(EmptyResultDataAccessException.class);

        // Mock model
        Model model = mock(Model.class);

        // Perform the test
        String result = departmentController.getDepartment(departmentId, model, page, size);

        // Verify the results
        assertEquals("error/no-data", result);
        verifyNoMoreInteractions(model);
        verify(departmentService).getOne(departmentId);
        verifyNoMoreInteractions(departmentService, gymPlanService, departmentScheduleService, departmentAlbumsService, departmentService, departmentFeatureService, departmentAmenitieService);
    }
}