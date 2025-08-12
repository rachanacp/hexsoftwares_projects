import java.util.*;

/**
 * Main class for the Movie Database Management System
 * Provides console-based user interface for managing movies
 */
public class MovieManager {
    private static MovieDatabase database;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        database = new MovieDatabase();
        scanner = new Scanner(System.in);
        
        System.out.println("=".repeat(60));
        System.out.println("         WELCOME TO MOVIE DATABASE MANAGER");
        System.out.println("=".repeat(60));
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice (1-8): ");
            
            switch (choice) {
                case 1:
                    addNewMovie();
                    break;
                case 2:
                    displayAllMovies();
                    break;
                case 3:
                    searchMovies();
                    break;
                case 4:
                    viewMovieDetails();
                    break;
                case 5:
                    displayTopRatedMovies();
                    break;
                case 6:
                    database.displayStatistics();
                    break;
                case 7:
                    removeMovie();
                    break;
                case 8:
                    System.out.println("\nThank you for using Movie Database Manager!");
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           MAIN MENU");
        System.out.println("=".repeat(40));
        System.out.println("1. Add New Movie");
        System.out.println("2. Display All Movies");
        System.out.println("3. Search Movies");
        System.out.println("4. View Movie Details");
        System.out.println("5. Top Rated Movies");
        System.out.println("6. Database Statistics");
        System.out.println("7. Remove Movie");
        System.out.println("8. Exit");
        System.out.println("=".repeat(40));
    }
    
    private static void addNewMovie() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ADD NEW MOVIE");
        System.out.println("=".repeat(50));
        
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: Title cannot be empty!");
            return;
        }
        
        System.out.print("Enter director name: ");
        String director = scanner.nextLine().trim();
        if (director.isEmpty()) {
            System.out.println("Error: Director name cannot be empty!");
            return;
        }
        
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) {
            System.out.println("Error: Genre cannot be empty!");
            return;
        }
        
        int year = getIntInput("Enter release year: ");
        if (year < 1888 || year > 2030) { // First movie was made in 1888
            System.out.println("Error: Please enter a valid year (1888-2030)!");
            return;
        }
        
        double rating = getDoubleInput("Enter rating (0.0-10.0): ");
        if (rating < 0.0 || rating > 10.0) {
            System.out.println("Error: Rating must be between 0.0 and 10.0!");
            return;
        }
        
        System.out.print("Enter movie description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = "No description available.";
        }
        
        Movie newMovie = new Movie(title, director, genre, year, rating, description);
        
        if (database.addMovie(newMovie)) {
            System.out.println("✓ Movie added successfully!");
            System.out.println("Movie: " + newMovie);
        } else {
            System.out.println("✗ Error: Movie with the same title and year already exists!");
        }
    }
    
    private static void displayAllMovies() {
        List<Movie> movies = database.getAllMovies();
        
        if (movies.isEmpty()) {
            System.out.println("\nNo movies found in the database!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("ALL MOVIES IN DATABASE (" + movies.size() + " movies)");
        System.out.println("=".repeat(90));
        System.out.printf("%-4s %-25s %-20s %-12s %-6s %-6s%n", 
                         "#", "TITLE", "DIRECTOR", "GENRE", "YEAR", "RATING");
        System.out.println("-".repeat(90));
        
        for (int i = 0; i < movies.size(); i++) {
            movies.get(i).displayTableRow(i + 1);
        }
        System.out.println("=".repeat(90));
    }
    
    private static void searchMovies() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("SEARCH MOVIES");
        System.out.println("=".repeat(40));
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Director");
        System.out.println("3. Search by Genre");
        System.out.println("4. Search by Year");
        System.out.println("5. General Search");
        System.out.println("=".repeat(40));
        
        int choice = getIntInput("Enter search type (1-5): ");
        String searchType;
        
        switch (choice) {
            case 1: searchType = "title"; break;
            case 2: searchType = "director"; break;
            case 3: searchType = "genre"; break;
            case 4: searchType = "year"; break;
            case 5: searchType = "general"; break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim();
        
        if (searchTerm.isEmpty()) {
            System.out.println("Error: Search term cannot be empty!");
            return;
        }
        
        List<Movie> results = database.searchMovies(searchTerm, searchType);
        
        if (results.isEmpty()) {
            System.out.println("\nNo movies found matching your search criteria.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("SEARCH RESULTS (" + results.size() + " movies found)");
        System.out.println("=".repeat(90));
        System.out.printf("%-4s %-25s %-20s %-12s %-6s %-6s%n", 
                         "#", "TITLE", "DIRECTOR", "GENRE", "YEAR", "RATING");
        System.out.println("-".repeat(90));
        
        for (int i = 0; i < results.size(); i++) {
            results.get(i).displayTableRow(i + 1);
        }
        System.out.println("=".repeat(90));
    }
    
    private static void viewMovieDetails() {
        displayAllMovies();
        
        if (database.getMovieCount() == 0) {
            return;
        }
        
        int index = getIntInput("\nEnter movie number to view details (0 to cancel): ");
        
        if (index == 0) {
            return;
        }
        
        Movie movie = database.getMovieByIndex(index - 1);
        
        if (movie != null) {
            System.out.println();
            movie.displayDetails();
        } else {
            System.out.println("Error: Invalid movie number!");
        }
    }
    
    private static void displayTopRatedMovies() {
        int count = getIntInput("\nEnter number of top movies to display: ");
        
        if (count <= 0) {
            System.out.println("Error: Please enter a positive number!");
            return;
        }
        
        List<Movie> topMovies = database.getTopRatedMovies(count);
        
        if (topMovies.isEmpty()) {
            System.out.println("No movies found in the database!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(90));
        System.out.println("TOP " + Math.min(count, topMovies.size()) + " RATED MOVIES");
        System.out.println("=".repeat(90));
        System.out.printf("%-4s %-25s %-20s %-12s %-6s %-6s%n", 
                         "#", "TITLE", "DIRECTOR", "GENRE", "YEAR", "RATING");
        System.out.println("-".repeat(90));
        
        for (int i = 0; i < topMovies.size(); i++) {
            topMovies.get(i).displayTableRow(i + 1);
        }
        System.out.println("=".repeat(90));
    }
    
    private static void removeMovie() {
        displayAllMovies();
        
        if (database.getMovieCount() == 0) {
            return;
        }
        
        int index = getIntInput("\nEnter movie number to remove (0 to cancel): ");
        
        if (index == 0) {
            return;
        }
        
        Movie movie = database.getMovieByIndex(index - 1);
        
        if (movie != null) {
            System.out.println("\nMovie to be removed:");
            movie.displayDetails();
            
            System.out.print("Are you sure you want to remove this movie? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y") || confirm.equals("yes")) {
                if (database.removeMovie(index - 1)) {
                    System.out.println("✓ Movie removed successfully!");
                } else {
                    System.out.println("✗ Error removing movie!");
                }
            } else {
                System.out.println("Movie removal cancelled.");
            }
        } else {
            System.out.println("Error: Invalid movie number!");
        }
    }
    
    // Utility method to get integer input with error handling
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }
    
    // Utility method to get double input with error handling
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
            }
        }
    }
}