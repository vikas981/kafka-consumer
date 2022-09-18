package com.vikash.kafkaconsumer.util;

public final class ReferenceUtil {

    private ReferenceUtil() {
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNonNullOrEmpty(String s){
        return s!=null && s.length()>0 && !s.trim().isEmpty();
    }

}
