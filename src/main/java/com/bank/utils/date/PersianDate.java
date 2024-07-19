package com.bank.utils.date;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
public final class PersianDate implements Serializable, Comparable<PersianDate> {
    private final LocalDate _dateTime;

    @Serial
    private static final long serialVersionUID = 1L;

    public PersianDate() {
        _dateTime = LocalDate.MIN;
    }

    public PersianDate(LocalDate dateTime) {
        _dateTime = LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
    }

    public PersianDate(int year, int month, int day) {
        var gDate = jalali_to_gregorian(year, month, day);
        _dateTime = LocalDate.of(gDate[0], gDate[1], gDate[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersianDate that = (PersianDate) o;
        return Objects.equals(_dateTime, that._dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_dateTime);
    }

    @Override
    public String toString() {
        return toString("d");
    }

    @Override
    public int compareTo(PersianDate o) {
        return _dateTime.compareTo(o._dateTime);
    }

    public String toString(String format) {
        var jDate = gregorian_to_jalali(_dateTime.getYear(), _dateTime.getMonthValue(), _dateTime.getDayOfMonth());
        var dayOfWeek = PersianDateResource.getWeekDayName(_dateTime.getDayOfWeek());
        var monthName = PersianDateResource.getMonthName(jDate[1]);
        return PersianDateFormat.format(jDate[0], jDate[1], jDate[2], dayOfWeek, monthName, format);
    }

    public int toInteger() {
        var jDate = gregorian_to_jalali(_dateTime.getYear(), _dateTime.getMonthValue(), _dateTime.getDayOfMonth());
        return Integer.parseInt(PersianDateFormat.format(jDate));
    }

    private LocalDate toLocalDate() {
        return LocalDate.of(_dateTime.getYear(), _dateTime.getMonthValue(), _dateTime.getDayOfMonth());
    }

    public static PersianDate now() {
        return new PersianDate(LocalDate.now());
    }

    /**
     * Author: JDF.SCR.IR =>> Download Full Version :  <a href="http://jdf.scr.ir/jdf">...</a> License: GNU/LGPL _ Open
     * Source & Free :: Version: 2.80 : [2020=1399]
     */
    private static int[] gregorian_to_jalali(int gy, int gm, int gd) {
        int[] out = {
                (gm > 2) ? (gy + 1) : gy,
                0,
                0
        };

        {
            int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
            out[2] = 355666 + (365 * gy) + ((out[0] + 3) / 4) - ((out[0] + 99) / 100)
                    + ((out[0] + 399) / 400) + gd + g_d_m[gm - 1];
        }

        out[0] = -1595 + (33 * (out[2] / 12053));
        out[2] %= 12053;
        out[0] += 4 * (out[2] / 1461);
        out[2] %= 1461;
        if (out[2] > 365) {
            out[0] += (out[2] - 1) / 365;
            out[2] = (out[2] - 1) % 365;
        }
        if (out[2] < 186) {
            out[1] = 1 + (out[2] / 31);
            out[2] = 1 + (out[2] % 31);
        } else {
            out[1] = 7 + ((out[2] - 186) / 30);
            out[2] = 1 + ((out[2] - 186) % 30);
        }

        return out;
    }

    /**
     * Author: JDF.SCR.IR =>> Download Full Version :  <a href="http://jdf.scr.ir/jdf">...</a> License: GNU/LGPL _ Open
     * Source & Free :: Version: 2.80 : [2020=1399]
     */
    private static int[] jalali_to_gregorian(int jy, int jm, int jd) {
        jy += 1595;
        int[] out = {
                0,
                0,
                -355668 + (365 * jy) + ((jy / 33) * 8) + (((jy % 33) + 3) / 4) + jd + (
                        (jm < 7) ? (jm - 1) * 31 : ((jm - 7) * 30) + 186)
        };

        out[0] = 400 * (out[2] / 146097);
        out[2] %= 146097;
        if (out[2] > 36524) {
            out[0] += 100 * (--out[2] / 36524);
            out[2] %= 36524;
            if (out[2] >= 365) {
                out[2]++;
            }
        }

        out[0] += 4 * (out[2] / 1461);
        out[2] %= 1461;
        if (out[2] > 365) {
            out[0] += (out[2] - 1) / 365;
            out[2] = (out[2] - 1) % 365;
        }

        int[] sal_a = {0, 31, ((out[0] % 4 == 0 && out[0] % 100 != 0) || (out[0] % 400 == 0)) ? 29 : 28,
                31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (out[2]++; out[1] < 13 && out[2] > sal_a[out[1]]; out[1]++) {
            out[2] -= sal_a[out[1]];
        }

        return out;
    }
}
