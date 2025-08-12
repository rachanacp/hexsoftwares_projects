import java.util.*;

/**
 * MovieDatabase class to manage the collection of movies
 * Provides methods for adding, searching, and retrieving movies
 */
public class MovieDatabase {
    private List<Movie> movies;
    
    // Constructor
    public MovieDatabase() {
        this.movies = new ArrayList<>();
        initializeSampleData();
    }
    
    // Initialize with some sample movies
    private void initializeSampleData() {
        addMovie(new Movie("The Shawshank Redemption", "Frank Darabont", "Drama", 1994, 9.3,
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));
        
        addMovie(new Movie("The Godfather", "Francis Ford Coppola", "Crime", 1972, 9.2,
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
        
        addMovie(new Movie("The Dark Knight", "Christopher Nolan", "Action", 2008, 9.0,
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests."));
        
        addMovie(new Movie("Pulp Fiction", "Quentin Tarantino", "Crime", 1994, 8.9,
                "The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption."));
        
        addMovie(new Movie("Forrest Gump", "Robert Zemeckis", "Drama", 1994, 8.8,
                "The presidencies of Kennedy and Johnson, the Vietnam War, and other historical events unfold from the perspective of an Alabama man."));
    }
    
    // Add a new movie to the database
    public boolean addMovie(Movie movie) {
        if (movie == null) {
            return false;
        }
        
        // Check if movie already exists (same title and year)
        for (Movie existingMovie : movies) {
            if (existingMovie.getTitle().equalsIgnoreCase(movie.getTitle()) && 
                existingMovie.getYear() == movie.getYear()) {
                return false; // Movie already exists
            }
        }
        
        movies.add(movie);
        return true;
    }
    
    // Get all movies
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }
    
    // Search movies by various criteria
    public List<Movie> searchMovies(String searchTerm, String searchType) {
        List<Movie> results = new ArrayList<>();
        
        for (Movie movie : movies) {
            if (movie.matchesSearch(searchTerm, searchType)) {
                results.add(movie);
            }
        }
        
        return results;
    }
    
    // Get movie by index
    public Movie getMovieByIndex(int index) {
        if (index >= 0 && index < movies.size()) {
            return movies.get(index);
        }
        return null;
    }
    
    // Get total number of movies
    public int getMovieCount() {
        return movies.size();
    }
    
    // Remove movie by index
    public boolean removeMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            movies.remove(index);
            return true;
        }
        return false;
    }
    
    // Get movies by genre
    public List<Movie> getMoviesByGenre(String genre) {
        return searchMovies(genre, "genre");
    }
    
    // Get movies by year
    public List<Movie> getMoviesByYear(int year) {
        return searchMovies(String.valueOf(year), "year");
    }
    
    // Get movies by director
    public List<Movie> getMoviesByDirector(String director) {
        return searchMovies(director, "director");
    }
    
    // Get top rated movies
    public List<Movie> getTopRatedMovies(int count) {
        List<Movie> sortedMovies = new ArrayList<>(movies);
        sortedMovies.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
        
        return sortedMovies.subList(0, Math.min(count, sortedMovies.size()));
    }
    
    // Get statistics
    public void displayStatistics() {
        if (movies.isEmpty()) {
            System.out.println("No movies in database.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DATABASE STATISTICS");
        System.out.println("=".repeat(50));
        
        System.out.printf("Total Movies: %d%n", movies.size());
        
        // Calculate average rating
        double averageRating = movies.stream()
                                    .mapToDouble(Movie::getRating)
                                    .average()
                                    .orElse(0.0);
        System.out.printf("Average Rating: %.1f%n", averageRating);
        
        // Find most recent and oldest movie
        Movie newestMovie = movies.stream()
                                 .max(Comparator.comparing(Movie::getYear))
                                 .orElse(null);
        Movie oldestMovie = movies.stream()
                                 .min(Comparator.comparing(Movie::getYear))
                                 .orElse(null);
        
        if (newestMovie != null) {
            System.out.printf("Newest Movie: %s (%d)%n", newestMovie.getTitle(), newestMovie.getYear());
        }
        if (oldestMovie != null) {
            System.out.printf("Oldest Movie: %s (%d)%n", oldestMovie.getTitle(), oldestMovie.getYear());
        }
        
        // Genre distribution
        Map<String, Integer> genreCount = new HashMap<>();
        for (Movie movie : movies) {
            genreCount.put(movie.getGenre(), genreCount.getOrDefault(movie.getGenre(), 0) + 1);
        }
        
        System.out.println("\nGenre Distribution:");
        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            System.out.printf("  %s: %d movies%n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("=".repeat(50));
    }
}