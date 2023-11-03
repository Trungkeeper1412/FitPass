package com.ks.fitpass.department.enums;

public enum DepartmentStatusEnum {

    ACTIVE (1, "Hoạt Động"),
    INACTIVE(2, "Không Hoạt Động");

    private final int code;
    private final String label;

    DepartmentStatusEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
