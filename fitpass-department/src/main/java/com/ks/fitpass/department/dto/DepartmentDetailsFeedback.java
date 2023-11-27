package com.ks.fitpass.department.dto;

import com.ks.fitpass.department.entity.UserFeedback;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDetailsFeedback {
    List<UserFeedback> userFeedbacks;
    int totalPage;
    int currentPage;

}
