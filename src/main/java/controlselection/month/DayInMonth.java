package controlselection.month;

public class DayInMonth {
    public int numberOfDays(int year, String month) {

        switch (month.toLowerCase()) {
            case "január":
                return 31;
            case "február":
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            case "március":
                return 31;
            case "április":
                return 30;
            case "május":
                return 31;
            case "június":
                return 30;
            case "július":
                return 31;
            case "augusztus":
                return 31;
            case "szeptember":
                return 30;
            case "október":
                return 31;
            case "november":
                return 30;
            case "december":
                return 31;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
}
