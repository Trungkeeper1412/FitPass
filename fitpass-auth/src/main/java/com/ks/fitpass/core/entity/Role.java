package com.ks.fitpass.core.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role implements Serializable {

    private int roleId;
    private String roleName;
}
