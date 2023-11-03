package com.ks.fitpass.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReceiveMessageDTO {
    private int userId;
    private int gymDepartmentId;
}
