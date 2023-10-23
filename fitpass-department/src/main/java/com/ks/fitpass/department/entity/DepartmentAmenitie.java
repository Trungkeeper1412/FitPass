package com.ks.fitpass.department.entity;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentAmenitie {
    private int amenitieId;

    private int gymDepartmentId;

    private String photoUrl;

    private String amenitieName;

    private String description;
}
