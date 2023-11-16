package com.ks.fitpass.core.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements Serializable {

    private int userId;
    private String userAccount;
    private String userPassword;
    private long userCreateTime;
    private boolean userDeleted;
    private int userDetailId;
    private int createdBy;
}
