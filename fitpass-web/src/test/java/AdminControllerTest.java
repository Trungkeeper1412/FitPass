package com.ks.fitpass.web.controller;

import com.ks.fitpass.brand.dto.BrandAdminList;
import com.ks.fitpass.brand.service.BrandService;
import com.ks.fitpass.core.entity.User;
import com.ks.fitpass.core.entity.UserDTO;
import com.ks.fitpass.core.service.UserService;
import com.ks.fitpass.credit_card.service.CreditCardService;
import com.ks.fitpass.department.entity.Feature;
import com.ks.fitpass.department.service.DepartmentFeatureService;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminControllerTest {


    @Mock
    private DepartmentFeatureService departmentFeatureService;
    @Mock
    private Model model;


    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAdminIndex() {
        //act
        String result = adminController.getAdminIndex();
        //assert
        assertEquals("admin/index", result);
    }

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
}



