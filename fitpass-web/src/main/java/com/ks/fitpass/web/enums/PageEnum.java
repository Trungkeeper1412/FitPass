package com.ks.fitpass.web.enums;

public enum PageEnum {

    XXX_FIRST_PAGE(11),
    XXX_SECOND_PAGE(12),
    XXX_THIRD_PAGE(13);

    private final int code;

    PageEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
