import com.ks.fitpass.department.dto.DepartmentDTO;
import com.ks.fitpass.department.dto.DepartmentDetailsFeedback;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    void testGetDepartmentSuccess() {
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
        // Arrange
        when(departmentService.getOne(anyInt())).thenThrow(DuplicateKeyException.class);
        // Act
        String viewName = departmentController.getDepartment(1, model, 1, 7);
        //Assert
        assertEquals("error/duplicate-key-error", viewName);

    }

    @Test
    void testGetDepartmentEmptyResultDataAccessException() {
        //Assert
        when(departmentService.getOne(anyInt())).thenThrow(EmptyResultDataAccessException.class);
        // Act
        String viewName = departmentController.getDepartment(1, model, 1, 7);
        //Assert
        assertEquals("error/no-data", viewName);

    }

    @Test
    void testGetDepartmentIncorrectResultSizeDataAccessException() {
        // Arrange
        when(departmentService.getOne(anyInt())).thenThrow(IncorrectResultSizeDataAccessException.class);
        // Act
        String viewName = departmentController.getDepartment(1, model, 1, 7);
        //Assert
        assertEquals("error/incorrect-result-size-error", viewName);

    }

    @Test
    void testGetDepartmentFeedbackSuccess() {
        // Arrange
        int departmentId = 1;
        int page = 1;
        int size = 7;
        String sortRating = "asc";
        List<UserFeedback> mockUserFeedbacks = Arrays.asList(new UserFeedback(), new UserFeedback());
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(mockUserFeedbacks);
        when(departmentService.countAllFeedback(anyInt(), anyString())).thenReturn(10);

        // Act
        ResponseEntity<DepartmentDetailsFeedback> responseEntity = departmentController.getDepartmentFeedback(departmentId, page, size, sortRating);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testGetDepartmentFeedbackDuplicateKeyException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt(), anyString())).thenThrow(DuplicateKeyException.class);

        // Act
        ResponseEntity<DepartmentDetailsFeedback> responseEntity = departmentController.getDepartmentFeedback(departmentId, 1, 7, "asc");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetDepartmentFeedbackEmptyResultDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt(), anyString())).thenThrow(EmptyResultDataAccessException.class);

        // Act
        ResponseEntity<DepartmentDetailsFeedback> responseEntity = departmentController.getDepartmentFeedback(departmentId, 1, 7, "asc");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetDepartmentFeedbackIncorrectResultSizeDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt(), anyString())).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        ResponseEntity<DepartmentDetailsFeedback> responseEntity = departmentController.getDepartmentFeedback(departmentId, 1, 7, "asc");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetDepartmentFeedbackDataAccessException() {
        // Arrange
        int departmentId = 1;
        when(departmentService.getDepartmentFeedback(anyInt(), anyInt(), anyInt(), anyString())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<DepartmentDetailsFeedback> responseEntity = departmentController.getDepartmentFeedback(departmentId, 1, 7, "asc");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}