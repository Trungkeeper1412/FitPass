package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentAlbums {
    private int albumId;

    private int departmentId;

    private String photoUrl;

    private String description;
}
