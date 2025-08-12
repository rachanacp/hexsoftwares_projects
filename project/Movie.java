/**
 * Movie class representing a single movie entity
 * Contains all movie properties and utility methods
 */
public class Movie {
    private String title;
    private String director;
    private String genre;
    private int year;
    private double rating;
    private String description;
    
    // Constructor
    public Movie(String title, String director, String genre, int year, double rating, String description) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.description = description;
    }
    
    // Getters
    public String getTitle() {
        return title;
    }
    
    public String getDirector() {
        return director;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getYear() {
        return year;
    }
    
    public double getRating() {
        return rating;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Method to display movie details in a formatted way
    public void displayDetails() {
        System.out.println("=" .repeat(60));
        System.out.println("MOVIE DETAILS");
        System.out.println("=" .repeat(60));
        System.out.printf("%-15s: %s%n", "Title", title);
        System.out.printf("%-15s: %s%n", "Director", director);
        System.out.printf("%-15s: %s%n", "Genre", genre);
        System.out.printf("%-15s: %d%n", "Year", year);
        System.out.printf("%-15s: %.1f/10%n", "Rating", rating);
        System.out.printf("%-15s: %s%n", "Description", description);
        System.out.println("=" .repeat(60));
    }
    
    // Method for table row display
    public void displayTableRow(int index) {
        System.out.printf("%-4d %-25s %-20s %-12s %-6d %-6.1f%n", 
                         index, 
                         truncateString(title, 25), 
                         truncateString(director, 20), 
                         truncateString(genre, 12), 
                         year, 
                         rating);
    }
    
    // Utility method to truncate long strings
    private String truncateString(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }
    
    // Method to check if movie matches search criteria
    public boolean matchesSearch(String searchTerm, String searchType) {
        searchTerm = searchTerm.toLowerCase();
        
        switch (searchType.toLowerCase()) {
            case "title":
                return title.toLowerCase().contains(searchTerm);
            case "director":
                return director.toLowerCase().contains(searchTerm);
            case "genre":
                return genre.toLowerCase().contains(searchTerm);
            case "year":
                try {
                    int searchYear = Integer.parseInt(searchTerm);
                    return year == searchYear;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return title.toLowerCase().contains(searchTerm) ||
                       director.toLowerCase().contains(searchTerm) ||
                       genre.toLowerCase().contains(searchTerm);
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d) - Directed by %s", title, year, director);
    }
}