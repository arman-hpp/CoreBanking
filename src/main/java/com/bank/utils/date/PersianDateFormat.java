package com.bank.utils.date;

public final class PersianDateFormat {
    public static String format(int[] jDate)
    {
        return format(jDate[0], jDate[1], jDate[2], "", "", "");
    }

    public static String format(int year, int month, int day, String dayOfWeek, String monthName, String format)
    {
        return switch (format) {
            case "d" -> String.format("%d/%d/%d", year, month, day);
            case "D" -> String.format("%s %d %s %d", dayOfWeek, day, monthName, year);
            case "is" -> String.format("%02d/%02d/%02d", year, month, day);
            default -> String.format("%04d%02d%02d", year, month, day);
        };
    }
}
