import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== EMPLOYEE LEAVE MANAGEMENT SYSTEM ===");
        System.out.println("Choose mode:");
        System.out.println("1. Run Demonstration");
        System.out.println("2. Interactive Mode");
        System.out.print("Enter your choice (1 or 2): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    // Run demonstration with sample data
                    system.demonstrateSystem();
                    break;
                case 2:
                    // Run interactive mode
                    system.runInteractiveMode();
                    break;
                default:
                    System.out.println("Invalid choice! Running demonstration mode by default...");
                    system.demonstrateSystem();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Running demonstration mode by default...");
            system.demonstrateSystem();
        }
        
        scanner.close();
    }
}