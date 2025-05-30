package com.ks.fitpass.core.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRole {

    private int userRoleId;
    private User user;
    private Role role;
}
