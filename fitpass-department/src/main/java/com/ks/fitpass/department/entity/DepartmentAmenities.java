package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentAmenities {
    private int gymDepartmentId;
    BrandAmenities brandAmenitieId;


}
