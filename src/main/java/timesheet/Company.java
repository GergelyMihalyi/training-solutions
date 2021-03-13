package timesheet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private InputStream employeesFile;
    private InputStream projectsFile;
    private List<TimeSheetItem> timeSheetItems = new ArrayList<>();
    private List<Employee> employees;
    private List<Project> projects;

    public Company(InputStream employeesFile, InputStream projectsFile) {
        this.employeesFile = employeesFile;
        this.projectsFile = projectsFile;

        employees = employeesFromFile(employeesFile);
        projects = projectsFromFile(projectsFile);
    }

    public List<TimeSheetItem> getTimeSheetItems() {
        return timeSheetItems;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    private List<Employee> employeesFromFile(InputStream employeesFile) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(employeesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] names = line.split(" ");
                String firstname = names[0];
                String lastname = names[1];
                Employee employee = new Employee(firstname, lastname);
                employees.add(employee);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return employees;
    }

    private List<Project> projectsFromFile(InputStream projectsFile) {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(projectsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Project project = new Project(line);
                projects.add(project);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return projects;
    }

    public void addTimeSheetItem(Employee employee, Project project, LocalDateTime beginDate, LocalDateTime endDate) {
        TimeSheetItem timeSheetItem = new TimeSheetItem(employee, project, beginDate, endDate);
        timeSheetItems.add(timeSheetItem);
    }

    public List<ReportLine> calculateProjectByNameYearMonth(String employeeName, int year, int month) {
        Employee findedEmployee = findEmployeeByName(employeeName);
        if (findedEmployee == null) {
            throw new IllegalArgumentException("Can't be found");
        }
        List<TimeSheetItem> timeSheetItemsForEmployee;
        List<ReportLine> reportLines = new ArrayList<>();

        timeSheetItemsForEmployee = findTimeSheetByEmployeeAndYearAndMonth(findedEmployee, year, month);
        boolean addTime = false;
        for (TimeSheetItem timeSheetItem : timeSheetItemsForEmployee) {
            addTime = false;
            for(int i=0; i<reportLines.size();i++){
                if(timeSheetItem.getProject().getName().equals(reportLines.get(i).getProject().getName())){
                    reportLines.get(i).addTime(timeSheetItem.hoursBetweenDates());
                    addTime=true;
                }
            }
            if(!addTime){
                ReportLine newReportLine = new ReportLine(timeSheetItem.getProject(), timeSheetItem.hoursBetweenDates());
                reportLines.add(newReportLine);
            }
        }
        return reportLines;
    }

    private Employee findEmployeeByName(String employeeName) {
        Employee findedEmployee = null;
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                findedEmployee = employee;
            }
        }
        return findedEmployee;
    }

    private List<TimeSheetItem> findTimeSheetByEmployeeAndYearAndMonth(Employee employee, int year, int month) {
        List<TimeSheetItem> timeSheetItemsForEmployee = new ArrayList<>();
        for (TimeSheetItem timeSheetItem : timeSheetItems) {
            if (timeSheetItem.getEmployee().getName().equals(employee.getName()) && timeSheetItem.isInYearAndMont(year, month)) {
                timeSheetItemsForEmployee.add(timeSheetItem);
            }
        }

        return timeSheetItemsForEmployee;
    }

    private long sumTime(List<ReportLine> reportLines) {
        long sum = 0;
        for (ReportLine reportLine : reportLines) {
            sum += reportLine.getTime();
        }
        return sum;
    }

    public void printToFile(String employeeName, int year, int month, Path file) {
        List<ReportLine> reportLines = calculateProjectByNameYearMonth(employeeName, year, month);
        long sumTime = sumTime(reportLines);

        try (DataOutputStream outputStream = new DataOutputStream(
                new BufferedOutputStream(Files.newOutputStream(file)))) {
            String monthNumber = month > 9 ? String.valueOf(month) : String.valueOf(0) + month;
            outputStream.writeUTF(employeeName+"\t"+year+"-"+monthNumber+"\t"+sumTime+"\n");
            for (ReportLine reportLine : reportLines) {
                outputStream.writeUTF(reportLine.getProject().getName() + "\t" + reportLine.getTime() + "\n");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write file", ioe);
        }
    }
}
