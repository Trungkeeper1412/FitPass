
import com.ks.fitpass.partner.register.dto.BecomePartnerRequest;
import com.ks.fitpass.partner.register.dto.BecomePartnerUpdateStatus;
import com.ks.fitpass.partner.register.service.BecomePartnerService;
import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import com.ks.fitpass.wallet.service.WalletService;
import com.ks.fitpass.web.controller.AdminController;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminControllerTest {


    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private Model model;
    @Mock
    private BrandService brandService;
    @Mock
    private UserService userService;

    @Mock
    private RequestWithdrawHistoryService requestWithdrawHistoryService;
    @Mock
    private WalletService walletService;

    @Mock
    private HttpSession session;

    @Mock
    private BecomePartnerService becomePartnerService;
    @Captor
    private ArgumentCaptor<List<UserDTO>> userDTOListCaptor;
    @Captor
    private ArgumentCaptor<List<RequestWithdrawHistoryWithBrandName>> historyListPendingCaptor;

    @Captor
    private ArgumentCaptor<List<RequestWithdrawHistoryWithBrandName>> historyListAllCaptor;

    @Captor
    private ArgumentCaptor<RequestHistoryStats> historyStatsCaptor;

    @Captor
    private ArgumentCaptor<List<BecomePartnerRequest>> requestListUpCaptor;

    @Captor
    private ArgumentCaptor<List<BecomePartnerRequest>> requestListDownCaptor;


    @InjectMocks
    private AdminController adminController;



    @Captor
    private ArgumentCaptor<List<BrandAdminList>> brandListCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testGetAdminIndex() {
//        //act
//        String result = adminController.getAdminIndex();
//        //assert
//        assertEquals("admin/index", result);
//    }

    @Test
    public void testGetFeature() {
        //Arrange
        List<Feature> featureList = new ArrayList<>();
        featureList.add(new Feature());
        when(departmentFeatureService.getAllFeatureNoStatus()).thenReturn(featureList);

        //Act
        String result = adminController.getFeature(model);

        //Assert
        assertEquals("admin/admin-feature", result);

    }
    
    @Test
    public void testGetFeatureWithDuplicateKeyException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(DuplicateKeyException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/duplicate-key-error", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testGetFeatureWithEmptyResultDataAccessException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(EmptyResultDataAccessException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/no-data", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testGetFeatureWithIncorrectResultSizeDataAccessException() {
        // Arrange
        when(departmentFeatureService.getAllFeatureNoStatus()).thenThrow(IncorrectResultSizeDataAccessException.class);

        // Act
        String result = adminController.getFeature(model);

        // Assert
        assertEquals("error/incorrect-result-size-error", result);
        verify(departmentFeatureService, times(1)).getAllFeatureNoStatus();
        verify(model, never()).addAttribute(eq("featureList"), any());
    }

    @Test
    public void testUpdateFeatureStatusSuccess() {
        // Arrange
        Feature featureToUpdate = new Feature();
        featureToUpdate.setFeatureID(1); // Set a valid feature ID for testing
        featureToUpdate.setFeatureStatus(1); // Set a valid feature status for testing

        // Act
        ResponseEntity<String> result = adminController.updateFeatureStatus(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Update feature status success", result.getBody());
        verify(departmentFeatureService, times(1)).updateFeatureStatus(1, 1);
    }

    @Test
    public void testUpdateFeatureStatusDataAccessException() {
        // Arrange
        Feature featureToUpdate = new Feature();
        featureToUpdate.setFeatureID(1); // Set a valid feature ID for testing
        featureToUpdate.setFeatureStatus(1); // Set a valid feature status for testing
        when(departmentFeatureService.updateFeatureStatus(1, 1)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<String> result = adminController.updateFeatureStatus(featureToUpdate);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(departmentFeatureService, times(1)).updateFeatureStatus(1, 1);
    }

    @Test
    public void testGetBrandListSuccess() {
        // Arrange
        List<BrandAdminList> mockBrandList = Arrays.asList(new BrandAdminList(), new BrandAdminList());
        when(brandService.getAllBrand()).thenReturn(mockBrandList);

        // Act
        String resultView = adminController.getBrandList(model);

        // Assert
        assertEquals("admin/admin-brand-list", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, times(1)).addAttribute(eq("brandList"), brandListCaptor.capture());
        List<BrandAdminList> capturedBrandList = brandListCaptor.getValue();
        assertEquals(mockBrandList, capturedBrandList);
    }

    @Test
    public void testGetBrandListDataAccessException() {
        // Arrange
        when(brandService.getAllBrand()).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getBrandList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, never()).addAttribute(anyString(), any());

    }
//    @Test
//    public void testUpdateBrandNumberPercentageSuccess() {
//        // Arrange
//        int brandId = 1;
//        int numberPercentage = 50;
//        when(brandService.updateBrandMoneyPercent(brandId, numberPercentage)).thenReturn(1);
//
//        // Act
//        String resultView = adminController.updateBrandNumberPercentage(brandId, numberPercentage);
//
//        // Assert
//        assertEquals("redirect:/admin/brand/list", resultView);
//        verify(brandService, times(1)).updateBrandMoneyPercent(brandId, numberPercentage);
//    }
//
//    @Test
//    public void testUpdateBrandNumberPercentageUpdateFail() {
//        // Arrange
//        int brandId = 1;
//        int numberPercentage = 50;
//        when(brandService.updateBrandMoneyPercent(brandId, numberPercentage)).thenReturn(0);
//
//        // Act
//        String resultView = adminController.updateBrandNumberPercentage(brandId, numberPercentage);
//
//        // Assert
//        assertEquals("error/data-access-error", resultView);
//        verify(brandService, times(1)).updateBrandMoneyPercent(brandId, numberPercentage);
//    }
//
//    @Test
//    public void testUpdateBrandNumberPercentageDataAccessException() {
//        // Arrange
//        int brandId = 1;
//        int numberPercentage = 50;
//        when(brandService.updateBrandMoneyPercent(brandId, numberPercentage)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));
//
//        // Act
//        String resultView = adminController.updateBrandNumberPercentage(brandId, numberPercentage);
//
//        // Assert
//        assertEquals("error/data-access-error", resultView);
//        verify(brandService, times(1)).updateBrandMoneyPercent(brandId, numberPercentage);
//    }

    @Test
    public void testGetAccountBrandListSuccess() {
        // Arrange
        List<BrandAdminList> mockBrandList = Arrays.asList(new BrandAdminList(), new BrandAdminList());
        when(brandService.getAllBrand()).thenReturn(mockBrandList);

        // Act
        String resultView = adminController.getAccountBrandList(model);

        // Assert
        assertEquals("admin/admin-account-brand", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, times(1)).addAttribute(eq("brandList"), brandListCaptor.capture());
        List<BrandAdminList> capturedBrandList = brandListCaptor.getValue();
        assertEquals(mockBrandList, capturedBrandList);
    }

    @Test
    public void testGetAccountBrandListDataAccessException() {
        // Arrange
        when(brandService.getAllBrand()).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getAccountBrandList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);
        verify(brandService, times(1)).getAllBrand();
        verify(model, never()).addAttribute(anyString(), any());
        // You may add more assertions based on your error handling logic
    }

    @Test
    public void testGetAccountUserListSuccess() {
        // Arrange
        List<UserDTO> mockUserDTOList = Arrays.asList(new UserDTO(), new UserDTO());
        when(userService.getAllAccountUser()).thenReturn(mockUserDTOList);

        // Act
        String resultView = adminController.getAccountUserList(model);

        // Assert
        assertEquals("admin/admin-account-user", resultView);
        verify(userService, times(1)).getAllAccountUser();
        verify(model, times(1)).addAttribute(eq("userDTOList"), userDTOListCaptor.capture());
        List<UserDTO> capturedUserDTOList = userDTOListCaptor.getValue();
        assertEquals(mockUserDTOList, capturedUserDTOList);
    }

    @Test
    public void testGetAccountUserListDataAccessException() {
        // Arrange
        when(userService.getAllAccountUser()).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getAccountUserList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);
        verify(userService, times(1)).getAllAccountUser();
        verify(model, never()).addAttribute(anyString(), any());
        // You may add more assertions based on your error handling logic
    }

    @Test
    public void testGetWithdrawalListSuccess() {
        // Arrange
        List<RequestWithdrawHistoryWithBrandName> mockHistoryListPending = Arrays.asList(new RequestWithdrawHistoryWithBrandName(), new RequestWithdrawHistoryWithBrandName());
        List<RequestWithdrawHistoryWithBrandName> mockHistoryListAll = Arrays.asList(new RequestWithdrawHistoryWithBrandName(), new RequestWithdrawHistoryWithBrandName());
        RequestHistoryStats mockHistoryStats = new RequestHistoryStats();
        when(requestWithdrawHistoryService.getAllByStatusWithBrandName("Đang xử lý")).thenReturn(mockHistoryListPending);
        when(requestWithdrawHistoryService.getAllWithBrandName()).thenReturn(mockHistoryListAll);
        when(requestWithdrawHistoryService.getAllStats()).thenReturn(mockHistoryStats);

        // Act
        String resultView = adminController.getWithdrawalList(model);

        // Assert
        assertEquals("admin/admin-withdrawal-list", resultView);
        verify(requestWithdrawHistoryService, times(1)).getAllByStatusWithBrandName("Đang xử lý");
        verify(requestWithdrawHistoryService, times(1)).getAllWithBrandName();
        verify(requestWithdrawHistoryService, times(1)).getAllStats();
        verify(model, times(1)).addAttribute(eq("requestWithdrawHistoryListPending"), historyListPendingCaptor.capture());
        verify(model, times(1)).addAttribute(eq("requestWithdrawHistoryListAll"), historyListAllCaptor.capture());
        verify(model, times(1)).addAttribute(eq("requestHistoryStats"), historyStatsCaptor.capture());

        List<RequestWithdrawHistoryWithBrandName> capturedHistoryListPending = historyListPendingCaptor.getValue();
        List<RequestWithdrawHistoryWithBrandName> capturedHistoryListAll = historyListAllCaptor.getValue();
        RequestHistoryStats capturedHistoryStats = historyStatsCaptor.getValue();

        assertEquals(mockHistoryListPending, capturedHistoryListPending);
        assertEquals(mockHistoryListAll, capturedHistoryListAll);
        assertEquals(mockHistoryStats, capturedHistoryStats);
    }

    @Test
    public void testGetWithdrawalListDataAccessException() {
        // Arrange
        when(requestWithdrawHistoryService.getAllByStatusWithBrandName("Đang xử lý")).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getWithdrawalList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);
        verify(requestWithdrawHistoryService, times(1)).getAllByStatusWithBrandName("Đang xử lý");
        verify(requestWithdrawHistoryService, never()).getAllWithBrandName();
        verify(requestWithdrawHistoryService, never()).getAllStats();
        verify(model, never()).addAttribute(anyString(), any());

    }

    @Test
    public void testGetWithdrawalDetailSuccess() {
        // Arrange
        int requestHistoryId = 1;
        RequestHistoryAdmin mockRequestWithdrawHistory = new RequestHistoryAdmin();
        when(requestWithdrawHistoryService.getById(requestHistoryId)).thenReturn(mockRequestWithdrawHistory);

        // Act
        ResponseEntity<RequestHistoryAdmin> result = adminController.getWithdrawalDetail(requestHistoryId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockRequestWithdrawHistory, result.getBody());
        verify(requestWithdrawHistoryService, times(1)).getById(requestHistoryId);
    }

    @Test
    public void testGetWithdrawalDetailDataAccessException() {
        // Arrange
        int requestHistoryId = 1;
        when(requestWithdrawHistoryService.getById(requestHistoryId)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<RequestHistoryAdmin> result = adminController.getWithdrawalDetail(requestHistoryId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertNull(result.getBody() ); // Ensure the body is null for a bad request
        verify(requestWithdrawHistoryService, times(1)).getById(requestHistoryId);
    }

    @Test
    public void testGetWithdrawalNumberPercentageSuccess() {
        // Arrange
        int requestHistoryId = 1;
        int mockNumberPercentage = 50;
        when(requestWithdrawHistoryService.getNumberPercentage(requestHistoryId)).thenReturn(mockNumberPercentage);

        // Act
        ResponseEntity<Integer> result = adminController.getWithdrawalNumberPercentage(requestHistoryId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockNumberPercentage, result.getBody());
        verify(requestWithdrawHistoryService, times(1)).getNumberPercentage(requestHistoryId);
    }

    @Test
    public void testGetWithdrawalNumberPercentageDataAccessException() {
        // Arrange
        int requestHistoryId = 1;
        when(requestWithdrawHistoryService.getNumberPercentage(requestHistoryId)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<Integer> result = adminController.getWithdrawalNumberPercentage(requestHistoryId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertNull(result.getBody()); // Ensure the body is null for a bad request
        verify(requestWithdrawHistoryService, times(1)).getNumberPercentage(requestHistoryId);
    }

//    @Test
//    public void testUpdateWithdrawalStatusSuccess() {
//        // Arrange
//        int requestHistoryId = 1;
//        User mockAdmin = new User();
//        mockAdmin.setUserId(123); // Set a valid admin user ID for testing
//        when(session.getAttribute("userInfo")).thenReturn(mockAdmin);
//        when(requestWithdrawHistoryService.updateStatus(requestHistoryId, "Thành công")).thenReturn(1);
//
//        int userId = 456; // Set a valid user ID for testing
//        int adminId = mockAdmin.getUserId();
//
//        double userBalance = 100.0; // Set a valid user balance for testing
//        double adminBalance = 200.0; // Set a valid admin balance for testing
//        long amountCredit = 50;
//        RequestHistoryAdmin mockRequestWithdrawHistory = new RequestHistoryAdmin();
//        mockRequestWithdrawHistory.setAmountCredit(amountCredit); // Set a valid amount credit for testing
//        when(requestWithdrawHistoryService.getById(requestHistoryId)).thenReturn(mockRequestWithdrawHistory);
//        when(requestWithdrawHistoryService.getUserIdByRequestHistoryId(requestHistoryId)).thenReturn(userId);
//
//        when(walletService.getBalanceByUserId(userId)).thenReturn(userBalance);
//        when(walletService.getBalanceByUserId(adminId)).thenReturn(adminBalance);
//
//        // Act
//        String resultView = adminController.updateWithdrawalStatus(requestHistoryId, session);
//
//        // Assert
//        assertEquals("redirect:/admin/withdrawal", resultView);
//        verify(requestWithdrawHistoryService, times(1)).updateStatus(requestHistoryId, "Thành công");
//        verify(requestWithdrawHistoryService, times(1)).getUserIdByRequestHistoryId(requestHistoryId);
//        verify(requestWithdrawHistoryService, times(1)).getById(requestHistoryId);
//        verify(walletService, times(1)).getBalanceByUserId(userId);
//        verify(walletService, times(1)).getBalanceByUserId(adminId);
//        verify(walletService, times(1)).updateBalanceByUderId(userId, userBalance - mockRequestWithdrawHistory.getAmountCredit());
//        verify(walletService, times(1)).updateBalanceByUderId(adminId, adminBalance + mockRequestWithdrawHistory.getAmountCredit());
//    }

//    @Test
//    public void testUpdateWithdrawalStatusUpdateFail() {
//        // Arrange
//        int requestHistoryId = 1;
//        User mockAdmin = new User();
//        when(session.getAttribute("userInfo")).thenReturn(mockAdmin);
//        when(requestWithdrawHistoryService.updateStatus(requestHistoryId, "Thành công")).thenReturn(0);
//
//        // Act
//        String resultView = adminController.updateWithdrawalStatus(requestHistoryId, session);
//
//        // Assert
//        assertEquals("error/data-access-error", resultView);
//
//    }

//    @Test
//    public void testUpdateWithdrawalStatusDataAccessException() {
//        // Arrange
//        int requestHistoryId = 1;
//        when(session.getAttribute("userInfo")).thenReturn(new User());
//        when(requestWithdrawHistoryService.updateStatus(requestHistoryId, "Thành công")).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));
//
//        // Act
//        String resultView = adminController.updateWithdrawalStatus(requestHistoryId, session);
//
//        // Assert
//        assertEquals("error/data-access-error", resultView);
//
//    }

    @Test
    public void testGetRegistrationListSuccess() {
        // Arrange
        List<BecomePartnerRequest> mockRequestListPending = Arrays.asList(new BecomePartnerRequest(), new BecomePartnerRequest());
        List<BecomePartnerRequest> mockRequestListHandle = Arrays.asList(new BecomePartnerRequest(), new BecomePartnerRequest());
        List<BecomePartnerRequest> mockRequestListSuccess = Arrays.asList(new BecomePartnerRequest(), new BecomePartnerRequest());
        List<BecomePartnerRequest> mockRequestListFail = Arrays.asList(new BecomePartnerRequest(), new BecomePartnerRequest());

        when(becomePartnerService.getAllBecomePartnerRequestByStatus("Đang chờ xử lý")).thenReturn(mockRequestListPending);
        when(becomePartnerService.getAllBecomePartnerRequestByStatus("Đang xử lý")).thenReturn(mockRequestListHandle);
        when(becomePartnerService.getAllBecomePartnerRequestByStatus("Thành công")).thenReturn(mockRequestListSuccess);
        when(becomePartnerService.getAllBecomePartnerRequestByStatus("Từ chối đơn")).thenReturn(mockRequestListFail);

        // Act
        String resultView = adminController.getRegistrationList(model);

        // Assert
        assertEquals("admin/admin-registration-list", resultView);

    }

    @Test
    public void testGetRegistrationListDataAccessException() {
        // Arrange
        when(becomePartnerService.getAllBecomePartnerRequestByStatus(anyString())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        String resultView = adminController.getRegistrationList(model);

        // Assert
        assertEquals("error/data-access-error", resultView);


    }
    @Test
    public void testGetRegistrationDetailSuccess() {
        // Arrange
        int becomeAPartnerRequestId = 1;
        BecomePartnerRequest mockBecomePartnerRequest = new BecomePartnerRequest();
        when(becomePartnerService.getById(becomeAPartnerRequestId)).thenReturn(mockBecomePartnerRequest);

        // Act
        ResponseEntity<BecomePartnerRequest> result = adminController.getRegistrationDetail(becomeAPartnerRequestId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(becomePartnerService, times(1)).getById(becomeAPartnerRequestId);
    }

    @Test
    public void testGetRegistrationDetailDataAccessException() {
        // Arrange
        int becomeAPartnerRequestId = 1;
        when(becomePartnerService.getById(becomeAPartnerRequestId)).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<BecomePartnerRequest> result = adminController.getRegistrationDetail(becomeAPartnerRequestId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(becomePartnerService, times(1)).getById(becomeAPartnerRequestId);
    }

    @Test
    public void testUpdateRegistrationStatusSuccess() {
        // Arrange
        BecomePartnerUpdateStatus mockUpdateStatus = new BecomePartnerUpdateStatus();
        mockUpdateStatus.setBecomeAPartnerRequestId(1);
        mockUpdateStatus.setStatus("Đang xử lý");

        when(becomePartnerService.updateStatus(mockUpdateStatus.getBecomeAPartnerRequestId(), mockUpdateStatus.getStatus())).thenReturn(1);

        // Act
        ResponseEntity<String> result = adminController.updateRegistrationStatus(mockUpdateStatus);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Cập nhật thành công", result.getBody());
        verify(becomePartnerService, times(1)).updateStatus(mockUpdateStatus.getBecomeAPartnerRequestId(), mockUpdateStatus.getStatus());

    }

    @Test
    public void testUpdateRegistrationStatusUpdateFail() {
        // Arrange
        BecomePartnerUpdateStatus mockUpdateStatus = new BecomePartnerUpdateStatus();
        mockUpdateStatus.setBecomeAPartnerRequestId(1);
        mockUpdateStatus.setStatus("Đang xử lý");

        when(becomePartnerService.updateStatus(mockUpdateStatus.getBecomeAPartnerRequestId(), mockUpdateStatus.getStatus())).thenReturn(0);

        // Act
        ResponseEntity<String> result = adminController.updateRegistrationStatus(mockUpdateStatus);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

    @Test
    public void testUpdateRegistrationStatusDataAccessException() {
        // Arrange
        BecomePartnerUpdateStatus mockUpdateStatus = new BecomePartnerUpdateStatus();
        mockUpdateStatus.setBecomeAPartnerRequestId(1);
        mockUpdateStatus.setStatus("Đang xử lý");

        when(becomePartnerService.updateStatus(mockUpdateStatus.getBecomeAPartnerRequestId(), mockUpdateStatus.getStatus())).thenThrow(new CustomDataAccessException("Custom Data Access Exception"));

        // Act
        ResponseEntity<String> result = adminController.updateRegistrationStatus(mockUpdateStatus);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

    }

}





