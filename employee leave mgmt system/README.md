# Employee Leave Management System

A comprehensive Java application for managing employee leave requests, approvals, and leave balances.

## Features

- **Employee Management**: Track employee information and leave balances
- **Leave Request Submission**: Employees can submit various types of leave requests
- **Approval Workflow**: Managers can approve or reject leave requests
- **Leave Balance Tracking**: Automatic tracking of annual, sick, and casual leave balances
- **Leave History**: View complete leave history for each employee
- **Interactive Interface**: User-friendly console interface
- **System Statistics**: Overview of system usage and request statuses

## Leave Types Supported

- Annual Leave
- Sick Leave
- Casual Leave
- Maternity Leave
- Paternity Leave
- Emergency Leave

## Leave Status Types

- Pending Approval
- Approved
- Rejected
- Cancelled

## System Architecture

The system is built using Object-Oriented Programming principles with the following main classes:

- `Employee`: Represents an employee with leave balances
- `LeaveRequest`: Represents a leave request with all details
- `LeaveManagementSystem`: Main system for managing all operations
- `LeaveType`: Enum for different types of leave
- `LeaveStatus`: Enum for request statuses

## Sample Features Demonstrated

1. **Employee Information Display**: Shows all employee details and leave balances
2. **Leave Request Submission**: Creates leave requests with validation
3. **Leave Balance Checking**: Ensures sufficient leave balance before approval
4. **Approval/Rejection Workflow**: Proper approval process with comments
5. **Leave Balance Updates**: Automatic deduction upon approval
6. **System Statistics**: Overview of all requests and their statuses

## How to Run

### Using Command Line:

**For Windows:**
```batch
compile.bat
```

**For Linux/Mac:**
```bash
chmod +x compile.sh
./compile.sh
```

**Manual Compilation:**
```bash
# Create bin directory
mkdir bin

# Compile all Java files
javac -d bin src/*.java

# Run the application
java -cp bin Main
```

## Usage Modes

1. **Demonstration Mode**: Runs with pre-loaded sample data to show all features
2. **Interactive Mode**: Allows manual interaction with the system

## Sample Output

The system provides detailed output including:

- Employee information with current leave balances
- Leave request details with status tracking
- Approval/rejection confirmations with comments
- Updated leave balances after processing
- System statistics and summaries

## Default Leave Balances

- Annual Leave: 25 days
- Sick Leave: 10 days
- Casual Leave: 5 days

## Key Validations

- Sufficient leave balance check before request approval
- Date validation (end date cannot be before start date)
- Employee existence validation
- Request status validation before approval/rejection

## Future Enhancements

- Database integration
- Email notifications
- Web interface
- Reporting features
- Leave calendar view
- Department-wise approvals