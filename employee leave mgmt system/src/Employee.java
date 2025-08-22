import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String employeeId;
    private String name;
    private String department;
    private String position;
    private int annualLeaveBalance;
    private int sickLeaveBalance;
    private int casualLeaveBalance;
    private List<LeaveRequest> leaveRequests;
    
    public Employee(String employeeId, String name, String department, String position) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.annualLeaveBalance = 25; // Default annual leave days
        this.sickLeaveBalance = 10;   // Default sick leave days
        this.casualLeaveBalance = 5;  // Default casual leave days
        this.leaveRequests = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public int getAnnualLeaveBalance() { return annualLeaveBalance; }
    public int getSickLeaveBalance() { return sickLeaveBalance; }
    public int getCasualLeaveBalance() { return casualLeaveBalance; }
    public List<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    
    public void setAnnualLeaveBalance(int balance) { this.annualLeaveBalance = balance; }
    public void setSickLeaveBalance(int balance) { this.sickLeaveBalance = balance; }
    public void setCasualLeaveBalance(int balance) { this.casualLeaveBalance = balance; }
    
    public void addLeaveRequest(LeaveRequest request) {
        this.leaveRequests.add(request);
    }
    
    public void deductLeave(LeaveType leaveType, int days) {
        switch (leaveType) {
            case ANNUAL:
                this.annualLeaveBalance -= days;
                break;
            case SICK:
                this.sickLeaveBalance -= days;
                break;
            case CASUAL:
                this.casualLeaveBalance -= days;
                break;
        }
    }
    
    public int getLeaveBalance(LeaveType leaveType) {
        switch (leaveType) {
            case ANNUAL: return annualLeaveBalance;
            case SICK: return sickLeaveBalance;
            case CASUAL: return casualLeaveBalance;
            default: return 0;
        }
    }
    
    public void displayEmployeeInfo() {
        System.out.println("=== Employee Information ===");
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Annual Leave Balance: " + annualLeaveBalance + " days");
        System.out.println("Sick Leave Balance: " + sickLeaveBalance + " days");
        System.out.println("Casual Leave Balance: " + casualLeaveBalance + " days");
        System.out.println("Total Leave Requests: " + leaveRequests.size());
        System.out.println("=============================\n");
    }
}