package DevirationInClass;

/**
 * Represents a calendar date with day, month, and year components
 * @author Vu Cong Bui
 */
public class Date {

    private int month;
    private int day;
    private int year;

    public Date() {
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Set day of the BankAccountManagement.Date with function to check leap years
     * @param day - the new day to set
     * @return boolean value, false if the day is invalid for the given month or year
     */
    public boolean setDay(int day) {
        int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};

        if (month == 2 && day == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            this.day = day;
            return true;
        }
        else if (day < 1 || day > days[month]) {
            this.day = 1;
            return false;
        }
        else {
            this.day = day;
            return true;
        }
    }

    /**
     * Set the month of the BankAccountManagement.Date
     * @param month - the new month to set
     * @return boolean value, false if month is outside the valid range (12 months)
     */
    public boolean setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
            return true;
        }
        return false;
    }

    /**
     * Set the year of the BankAccountManagement.Date
     * @param year - the new year to set
     * @return boolean value, false if year is outside the valid range (0 - 99)
     */
    public boolean setYear(int year) {
        if (year >= 1900 && year <= 2026) {
            this.year = year;
            return true;
        }
        return false;

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * @return a String of the full date
     */
    public String toString() {
        return day + " " + month + " " + year;
    }
}
