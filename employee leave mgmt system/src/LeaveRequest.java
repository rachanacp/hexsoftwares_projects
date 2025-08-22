import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LeaveRequest {
    private static int requestCounter = 1000;
    
    private String requestId;
    private String employeeId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfDays;
    private String reason;
    private LeaveStatus status;
    private String approverComments;
    private LocalDate requestDate;
    private LocalDate approvalDate;
    
    public LeaveRequest(String employeeId, LeaveType leaveType, LocalDate startDate, 
                       LocalDate endDate, String reason) {
        this.requestId = "LR" + (++requestCounter);
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        this.reason = reason;
        this.status = LeaveStatus.PENDING;
        this.requestDate = LocalDate.now();
    }
    
    // Getters and Setters
    public String getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public LeaveType getLeaveType() { return leaveType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getNumberOfDays() { return numberOfDays; }
    public String getReason() { return reason; }
    public LeaveStatus getStatus() { return status; }
    public String getApproverComments() { return approverComments; }
    public LocalDate getRequestDate() { return requestDate; }
    public LocalDate getApprovalDate() { return approvalDate; }
    
    public void setStatus(LeaveStatus status) { this.status = status; }
    public void setApproverComments(String comments) { this.approverComments = comments; }
    public void setApprovalDate(LocalDate date) { this.approvalDate = date; }
    
    public void displayRequestDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        System.out.println("=== Leave Request Details ===");
        System.out.println("Request ID: " + requestId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Start Date: " + startDate.format(formatter));
        System.out.println("End Date: " + endDate.format(formatter));
        System.out.println("Number of Days: " + numberOfDays);
        System.out.println("Reason: " + reason);
        System.out.println("Status: " + status);
        System.out.println("Request Date: " + requestDate.format(formatter));
        
        if (approvalDate != null) {
            System.out.println("Approval Date: " + approvalDate.format(formatter));
        }
        if (approverComments != null && !approverComments.isEmpty()) {
            System.out.println("Approver Comments: " + approverComments);
        }
        System.out.println("==============================\n");
    }
}