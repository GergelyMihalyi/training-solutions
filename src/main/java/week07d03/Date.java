package week07d03;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        checkParams(year, month, day);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static Date of(int year, int month, int day) {
        return new Date(year, month, day);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Date withYear(int year) {
        checkParams(year, getMonth(), getMonth());
        return of(year, getMonth(), getMonth());
    }

    public Date withMonth(int month) {
        checkParams(getYear(), month, getDay());
        return of(getYear(), month, getDay());
    }

    public Date withDay(int day) {
        checkParams(getYear(), getMonth(), day);
        return of(getYear(), getMonth(), day);
    }

    private static void checkParams(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid Date");
        }

    }

    public static void main(String[] args) {

    }

}
