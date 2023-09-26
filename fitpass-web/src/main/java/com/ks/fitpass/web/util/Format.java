package com.ks.fitpass.web.util;

public class Format {

    public static String orderId(int id) {
        String strId = "" + id;
        int length = 5 - strId.length();

        while (length > 0) {
            strId = "0" + strId;
            --length;
        }
        return strId;
    }

    public static String formatMemberCd(int code) {
        String strId = "" + code;
        int length = 5 - strId.length();

        while (length > 0) {
            strId = "0" + strId;
            --length;
        }
        return strId;
    }

    public static String phoneNumber(String strPhoneNumber) {
        return strPhoneNumber.substring(0, 4) + " "
                + strPhoneNumber.substring(4, 7) + " "
                + strPhoneNumber.substring(7, 10);
    }

    public static String normalizationName(String name) {
        StringBuilder result = new StringBuilder();
        String[] arr = name
                .trim()
                .toLowerCase()
                .replaceAll("\s+", "\s")
                .split("\s");

        for (String s : arr) {
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append("\s");
        }
        return result.toString().trim();
    }
}
