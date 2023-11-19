package com.ks.fitpass.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GymOwnerListDTO {
    private int userId;
    private int userDetailId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private String gymDepartmentName;
    private boolean userDeleted;
}
