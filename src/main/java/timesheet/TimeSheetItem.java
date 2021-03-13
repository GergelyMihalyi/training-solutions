package timesheet;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSheetItem {
    private Employee employee;
    private Project project;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public TimeSheetItem(Employee employee, Project project, LocalDateTime beginDate, LocalDateTime endDate) {
        checkDatesForTimeSheet(beginDate, endDate);
        this.employee = employee;
        this.project = project;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Project getProject() {
        return project;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long hoursBetweenDates() {
        return Duration.between(beginDate, endDate).toHours();
    }


    private void checkDatesForTimeSheet(LocalDateTime beginDate, LocalDateTime endDate) {
        if (!isSameDay(beginDate, endDate)) {
            throw new IllegalArgumentException("The dates not on the same day");
        }
        if (isBeginAfterEnd(beginDate, endDate)) {
            throw new IllegalArgumentException("The end date is earlier than the start date");
        }
    }

    private boolean isSameDay(LocalDateTime beginDate, LocalDateTime endDate) {
        return beginDate.toLocalDate().equals(endDate.toLocalDate());
    }

    private boolean isBeginAfterEnd(LocalDateTime beginDate, LocalDateTime endDate) {
        return beginDate.isAfter(endDate);
    }

    public boolean isInYearAndMont( int year, int month) {
        return beginDate.getYear() == year && beginDate.getMonth().getValue() == month;
    }
}
