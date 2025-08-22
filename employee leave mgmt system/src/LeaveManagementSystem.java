import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeaveManagementSystem {
    private List<Employee> employees;
    private List<LeaveRequest> allLeaveRequests;
    private Scanner scanner;
    
    public LeaveManagementSystem() {
        this.employees = new ArrayList<>();
        this.allLeaveRequests = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample employees
        addEmployee(new Employee("EMP001", "John Smith", "IT", "Software Engineer"));
        addEmployee(new Employee("EMP002", "Sarah Johnson", "HR", "HR Manager"));
        addEmployee(new Employee("EMP003", "Mike Davis", "Finance", "Accountant"));
        addEmployee(new Employee("EMP004", "Lisa Wilson", "Marketing", "Marketing Specialist"));
        
        // Add sample leave requests
        submitLeaveRequest("EMP001", LeaveType.ANNUAL, 
                         LocalDate.of(2025, 2, 15), LocalDate.of(2025, 2, 19), 
                         "Family vacation");
        
        submitLeaveRequest("EMP002", LeaveType.SICK, 
                         LocalDate.of(2025, 1, 20), LocalDate.of(2025, 1, 22), 
                         "Medical treatment");
        
        submitLeaveRequest("EMP003", LeaveType.CASUAL, 
                         LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 10), 
                         "Personal work");
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public Employee findEmployee(String employeeId) {
        return employees.stream()
                .filter(emp -> emp.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }
    
    public boolean submitLeaveRequest(String employeeId, LeaveType leaveType, 
                                    LocalDate startDate, LocalDate endDate, String reason) {
        Employee employee = findEmployee(employeeId);
        if (employee == null) {
            System.out.println("Employee not found!");
            return false;
        }
        
        LeaveRequest request = new LeaveRequest(employeeId, leaveType, startDate, endDate, reason);
        
        // Check if employee has sufficient leave balance
        if (!hassufficientBalance(employee, leaveType, request.getNumberOfDays())) {
            System.out.println("Insufficient leave balance for " + leaveType + "!");
            return false;
        }
        
        employee.addLeaveRequest(request);
        allLeaveRequests.add(request);
        
        System.out.println("Leave request submitted successfully!");
        System.out.println("Request ID: " + request.getRequestId());
        return true;
    }
    
    private boolean hassufficientBalance(Employee employee, LeaveType leaveType, int days) {
        return employee.getLeaveBalance(leaveType) >= days;
    }
    
    public boolean approveLeaveRequest(String requestId, String comments) {
        LeaveRequest request = findLeaveRequest(requestId);
        if (request == null) {
            System.out.println("Leave request not found!");
            return false;
        }
        
        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("Leave request is not in pending status!");
            return false;
        }
        
        Employee employee = findEmployee(request.getEmployeeId());
        if (employee != null) {
            // Deduct leave balance
            employee.deductLeave(request.getLeaveType(), request.getNumberOfDays());
        }
        
        request.setStatus(LeaveStatus.APPROVED);
        request.setApproverComments(comments);
        request.setApprovalDate(LocalDate.now());
        
        System.out.println("Leave request approved successfully!");
        return true;
    }
    
    public boolean rejectLeaveRequest(String requestId, String comments) {
        LeaveRequest request = findLeaveRequest(requestId);
        if (request == null) {
            System.out.println("Leave request not found!");
            return false;
        }
        
        if (request.getStatus() != LeaveStatus.PENDING) {
            System.out.println("Leave request is not in pending status!");
            return false;
        }
        
        request.setStatus(LeaveStatus.REJECTED);
        request.setApproverComments(comments);
        request.setApprovalDate(LocalDate.now());
        
        System.out.println("Leave request rejected!");
        return true;
    }
    
    public LeaveRequest findLeaveRequest(String requestId) {
        return allLeaveRequests.stream()
                .filter(req -> req.getRequestId().equals(requestId))
                .findFirst()
                .orElse(null);
    }
    
    public void displayAllEmployees() {
        System.out.println("\n========== ALL EMPLOYEES ==========");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        
        for (Employee employee : employees) {
            employee.displayEmployeeInfo();
        }
    }
    
    public void displayEmployeeLeaveBalance(String employeeId) {
        Employee employee = findEmployee(employeeId);
        if (employee == null) {
            System.out.println("Employee not found!");
            return;
        }
        
        System.out.println("\n=== Leave Balance for " + employee.getName() + " ===");
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Annual Leave: " + employee.getAnnualLeaveBalance() + " days");
        System.out.println("Sick Leave: " + employee.getSickLeaveBalance() + " days");
        System.out.println("Casual Leave: " + employee.getCasualLeaveBalance() + " days");
        System.out.println("=======================================\n");
    }
    
    public void displayAllLeaveRequests() {
        System.out.println("\n========== ALL LEAVE REQUESTS ==========");
        if (allLeaveRequests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }
        
        for (LeaveRequest request : allLeaveRequests) {
            request.displayRequestDetails();
        }
    }
    
    public void displayPendingLeaveRequests() {
        System.out.println("\n========== PENDING LEAVE REQUESTS ==========");
        List<LeaveRequest> pendingRequests = allLeaveRequests.stream()
                .filter(req -> req.getStatus() == LeaveStatus.PENDING)
                .toList();
        
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending leave requests.");
            return;
        }
        
        for (LeaveRequest request : pendingRequests) {
            request.displayRequestDetails();
        }
    }
    
    public void displayEmployeeLeaveHistory(String employeeId) {
        Employee employee = findEmployee(employeeId);
        if (employee == null) {
            System.out.println("Employee not found!");
            return;
        }
        
        System.out.println("\n=== Leave History for " + employee.getName() + " ===");
        List<LeaveRequest> employeeRequests = employee.getLeaveRequests();
        
        if (employeeRequests.isEmpty()) {
            System.out.println("No leave requests found for this employee.");
            return;
        }
        
        for (LeaveRequest request : employeeRequests) {
            request.displayRequestDetails();
        }
    }
    
    public void displaySystemStatistics() {
        System.out.println("\n========== SYSTEM STATISTICS ==========");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Leave Requests: " + allLeaveRequests.size());
        
        long pendingCount = allLeaveRequests.stream()
                .filter(req -> req.getStatus() == LeaveStatus.PENDING)
                .count();
        long approvedCount = allLeaveRequests.stream()
                .filter(req -> req.getStatus() == LeaveStatus.APPROVED)
                .count();
        long rejectedCount = allLeaveRequests.stream()
                .filter(req -> req.getStatus() == LeaveStatus.REJECTED)
                .count();
        
        System.out.println("Pending Requests: " + pendingCount);
        System.out.println("Approved Requests: " + approvedCount);
        System.out.println("Rejected Requests: " + rejectedCount);
        System.out.println("=======================================\n");
    }
    
    public void runInteractiveMode() {
        System.out.println("=== EMPLOYEE LEAVE MANAGEMENT SYSTEM ===");
        System.out.println("Welcome to the Leave Management System!");
        
        while (true) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    displayAllEmployees();
                    break;
                case 2:
                    handleLeaveBalanceInquiry();
                    break;
                case 3:
                    handleLeaveRequestSubmission();
                    break;
                case 4:
                    displayPendingLeaveRequests();
                    break;
                case 5:
                    handleLeaveRequestApproval();
                    break;
                case 6:
                    handleLeaveRequestRejection();
                    break;
                case 7:
                    displayAllLeaveRequests();
                    break;
                case 8:
                    handleEmployeeLeaveHistory();
                    break;
                case 9:
                    displaySystemStatistics();
                    break;
                case 0:
                    System.out.println("Thank you for using the Leave Management System!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. View All Employees");
        System.out.println("2. Check Leave Balance");
        System.out.println("3. Submit Leave Request");
        System.out.println("4. View Pending Leave Requests");
        System.out.println("5. Approve Leave Request");
        System.out.println("6. Reject Leave Request");
        System.out.println("7. View All Leave Requests");
        System.out.println("8. View Employee Leave History");
        System.out.println("9. View System Statistics");
        System.out.println("0. Exit");
        System.out.println("===============================");
        System.out.print("Please select an option: ");
    }
    
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void handleLeaveBalanceInquiry() {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        displayEmployeeLeaveBalance(employeeId);
    }
    
    private void handleLeaveRequestSubmission() {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        
        System.out.println("Leave Types:");
        for (int i = 0; i < LeaveType.values().length; i++) {
            System.out.println((i + 1) + ". " + LeaveType.values()[i]);
        }
        System.out.print("Select Leave Type (1-" + LeaveType.values().length + "): ");
        
        int leaveTypeChoice = getChoice();
        if (leaveTypeChoice < 1 || leaveTypeChoice > LeaveType.values().length) {
            System.out.println("Invalid leave type selection!");
            return;
        }
        
        LeaveType leaveType = LeaveType.values()[leaveTypeChoice - 1];
        
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine();
        System.out.print("Enter Reason: ");
        String reason = scanner.nextLine();
        
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            if (endDate.isBefore(startDate)) {
                System.out.println("End date cannot be before start date!");
                return;
            }
            
            submitLeaveRequest(employeeId, leaveType, startDate, endDate, reason);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD format.");
        }
    }
    
    private void handleLeaveRequestApproval() {
        System.out.print("Enter Leave Request ID: ");
        String requestId = scanner.nextLine();
        System.out.print("Enter Approval Comments: ");
        String comments = scanner.nextLine();
        
        approveLeaveRequest(requestId, comments);
    }
    
    private void handleLeaveRequestRejection() {
        System.out.print("Enter Leave Request ID: ");
        String requestId = scanner.nextLine();
        System.out.print("Enter Rejection Comments: ");
        String comments = scanner.nextLine();
        
        rejectLeaveRequest(requestId, comments);
    }
    
    private void handleEmployeeLeaveHistory() {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        displayEmployeeLeaveHistory(employeeId);
    }
    
    public void demonstrateSystem() {
        System.out.println("=== EMPLOYEE LEAVE MANAGEMENT SYSTEM DEMONSTRATION ===\n");
        
        // Display all employees
        displayAllEmployees();
        
        // Show initial leave balances
        System.out.println("=== INITIAL LEAVE BALANCES ===");
        for (Employee emp : employees) {
            displayEmployeeLeaveBalance(emp.getEmployeeId());
        }
        
        // Display all leave requests
        displayAllLeaveRequests();
        
        // Show pending requests
        displayPendingLeaveRequests();
        
        // Approve a leave request
        System.out.println("=== APPROVING LEAVE REQUEST ===");
        if (!allLeaveRequests.isEmpty()) {
            String requestId = allLeaveRequests.get(0).getRequestId();
            approveLeaveRequest(requestId, "Approved by HR Manager - Well deserved break!");
        }
        
        // Reject a leave request
        System.out.println("=== REJECTING LEAVE REQUEST ===");
        if (allLeaveRequests.size() > 1) {
            String requestId = allLeaveRequests.get(1).getRequestId();
            rejectLeaveRequest(requestId, "Insufficient documentation provided for sick leave.");
        }
        
        // Show updated leave balances after approval
        System.out.println("=== UPDATED LEAVE BALANCES AFTER APPROVALS ===");
        for (Employee emp : employees) {
            displayEmployeeLeaveBalance(emp.getEmployeeId());
        }
        
        // Display system statistics
        displaySystemStatistics();
        
        // Show final status of all requests
        System.out.println("=== FINAL STATUS OF ALL LEAVE REQUESTS ===");
        displayAllLeaveRequests();
    }
}