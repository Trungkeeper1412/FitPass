import com.ks.fitpass.become_a_partner.dto.BecomePartnerForm;
import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.service.BecomePartnerService;
import com.ks.fitpass.web.controller.BORegisterController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BORegisterControllerTest {

    @Mock
    private BecomePartnerService becomePartnerService;
    @Mock
    private Model model;

    @InjectMocks
    private BORegisterController boRegisterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBrandOwnerRegisterSuccess() {
        // Arrange
        when(model.addAttribute(eq("becomePartnerForm"), any())).thenReturn(model);

        // Act
        String result = boRegisterController.getBrandOwnerRegister(model);

        // Assert
        assertEquals("gym-brand-registration", result);

    }

    @Test
    public void testGetBrandOwnerRegisterWithDuplicateKeyException() {
        // Arrange
        doThrow(DuplicateKeyException.class).when(model).addAttribute(eq("becomePartnerForm"), any());

        // Act
        String result = boRegisterController.getBrandOwnerRegister(model);

        // Assert
        assertEquals("error/duplicate-key-error", result);

    }

    @Test
    public void testGetBrandOwnerRegisterWithEmptyResultDataAccessException() {
        // Arrange
        doThrow(EmptyResultDataAccessException.class).when(model).addAttribute(eq("becomePartnerForm"), any());

        // Act
        String result = boRegisterController.getBrandOwnerRegister(model);

        // Assert
        assertEquals("error/no-data", result);

    }

    @Test
    public void testGetBrandOwnerRegisterWithIncorrectResultSizeDataAccessException() {
        // Arrange
        doThrow(IncorrectResultSizeDataAccessException.class).when(model).addAttribute(eq("becomePartnerForm"), any());

        // Act
        String result = boRegisterController.getBrandOwnerRegister(model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);

    }

    @Test
    public void testGetBrandOwnerRegisterWithDataAccessException() {
        // Arrange
        doThrow(new CustomDataAccessException("Simulated DataAccessException")).when(model).addAttribute(eq("becomePartnerForm"), any());

        // Act
        String result = boRegisterController.getBrandOwnerRegister(model);

        // Assert
        assertEquals("error/data-access-error", result);

    }

    @Test
    public void testPostBrandOwnerRegisterSuccess() {
        // Arrange
        BecomePartnerForm form = new BecomePartnerForm();
        form.setBrandName("TestBrand");
        form.setBrandOwnerName("TestOwner");
        // Set other form properties as needed

        // Mock successful form submission
        when(becomePartnerService.create(any(BecomePartnerRequest.class))).thenReturn(1);

        // Act
        String result = boRegisterController.postBrandOwnerRegister(form, mock(BindingResult.class));

        // Assert
        assertEquals("redirect:/become-a-partner/successful", result);
        verify(becomePartnerService, times(1)).create(any(BecomePartnerRequest.class));
    }

    @Test
    public void testPostBrandOwnerRegisterWithValidationError() {
        // Arrange
        BecomePartnerForm form = new BecomePartnerForm();
        // Set form properties with validation errors

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String result = boRegisterController.postBrandOwnerRegister(form, bindingResult);

        // Assert
        assertEquals("gym-brand-registration", result);
        verify(becomePartnerService, never()).create(any(BecomePartnerRequest.class));
    }

    @Test
    public void testPostBrandOwnerRegisterWithDuplicateKeyException() {
        // Arrange
        BecomePartnerForm form = new BecomePartnerForm();
        // Set form properties

        when(becomePartnerService.create(any(BecomePartnerRequest.class))).thenThrow(DuplicateKeyException.class);

        // Act
        String result = boRegisterController.postBrandOwnerRegister(form, mock(BindingResult.class));

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(becomePartnerService, times(1)).create(any(BecomePartnerRequest.class));
    }


}
