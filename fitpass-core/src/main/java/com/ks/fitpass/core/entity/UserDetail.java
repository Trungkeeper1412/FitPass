package com.ks.fitpass.core.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetail {
    private int userDetailId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String gender;
    private String imageUrl;
    private boolean userDeleted;
    private int gymDepartmentId;
    private String securityId;
}
