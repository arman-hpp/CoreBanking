package com.bank.utils.utils;

@SuppressWarnings("unused")
public final class StringUtils {
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String capitalize(String value) {
        if (value == null || value.isEmpty())
            return value;

        var result = value.substring(0, 1).toUpperCase();
        if (value.length() > 1)
            result = result + value.substring(1);

        return result;
    }

    public static String ellipsis(String value, Integer threshold) {
        if (value.length() <= threshold || threshold <= 0) {
            return value;
        }

        return value.substring(0, threshold).concat("...");
    }

    public static String truncate(String value, Integer limit) {
        if (value.length() < limit || limit <= 0) {
            return value;
        }

        return value.substring(0, limit);
    }

    public static String stripQuotes(String value) {
        return value
                .replace("'", "")
                .replace("\"", "");
    }
}
