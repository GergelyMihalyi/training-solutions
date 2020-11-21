package enumtype.week;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class WorkdayCalculator {

    public List<DayType> dayTypes(Day firstDay, int numberOfDays) {
        List<DayType> dayTypes = new ArrayList<>();
        dayTypes.add(firstDay.getDayType());
        for (int i = 0; i < numberOfDays-1; i++) {
            firstDay = nextDay(firstDay);
            dayTypes.add(firstDay.getDayType());
        }

        return dayTypes;
    }


    private Day nextDay(Day day) {
        int index = day.ordinal()+1;
        if(index == 7){
            index = 0;
        }
        return Day.values()[index];
    }
}
