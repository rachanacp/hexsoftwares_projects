import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private String libraryName;
    private Map<String, Book> books;
    private Map<String, Member> members;
    private List<String> transactionLog;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.transactionLog = new ArrayList<>();
    }

    // Book Management Methods
    public boolean addBook(String isbn, String title, String author, String genre) {
        if (books.containsKey(isbn)) {
            System.out.println("Book with ISBN " + isbn + " already exists!");
            return false;
        }

        Book book = new Book(isbn, title, author, genre);
        books.put(isbn, book);
        logTransaction("BOOK_ADDED: " + isbn + " - " + title + " by " + author);
        System.out.println("✓ Book added successfully: " + title);
        return true;
    }

    public boolean removeBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Cannot remove book - it is currently issued to member: " + book.getIssuedTo());
            return false;
        }

        books.remove(isbn);
        logTransaction("BOOK_REMOVED: " + isbn + " - " + book.getTitle());
        System.out.println("✓ Book removed successfully: " + book.getTitle());
        return true;
    }

    // Member Management Methods
    public boolean addMember(String memberId, String name, String email, String phoneNumber) {
        if (members.containsKey(memberId)) {
            System.out.println("Member with ID " + memberId + " already exists!");
            return false;
        }

        Member member = new Member(memberId, name, email, phoneNumber);
        members.put(memberId, member);
        logTransaction("MEMBER_ADDED: " + memberId + " - " + name);
        System.out.println("✓ Member added successfully: " + name);
        return true;
    }

    public boolean removeMember(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found!");
            return false;
        }

        if (member.getCurrentBorrowedCount() > 0) {
            System.out.println(
                    "Cannot remove member - they have " + member.getCurrentBorrowedCount() + " books to return first!");
            return false;
        }

        members.remove(memberId);
        logTransaction("MEMBER_REMOVED: " + memberId + " - " + member.getName());
        System.out.println("✓ Member removed successfully: " + member.getName());
        return true;
    }

    // Book Issue and Return Methods
    public boolean issueBook(String isbn, String memberId) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }

        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found!");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued to member: " + book.getIssuedTo());
            return false;
        }

        if (!member.canBorrowMore()) {
            System.out.println("Member has reached maximum borrow limit (" + member.getMaxBorrowLimit() + " books)!");
            return false;
        }

        book.issueBook(memberId);
        member.borrowBook(isbn);
        logTransaction("BOOK_ISSUED: " + isbn + " to member " + memberId + " (Due: " + book.getDueDate() + ")");
        System.out.println("✓ Book issued successfully!");
        System.out.println("  Book: " + book.getTitle());
        System.out.println("  Member: " + member.getName());
        System.out.println("  Due Date: " + book.getDueDate());
        return true;
    }

    public boolean returnBook(String isbn) {
        Book book = books.get(isbn);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }

        if (book.isAvailable()) {
            System.out.println("Book is not currently issued!");
            return false;
        }

        String memberId = book.getIssuedTo();
        Member member = members.get(memberId);

        boolean wasOverdue = book.isOverdue();
        book.returnBook();

        if (member != null) {
            member.returnBook(isbn);
        }

        String overdueInfo = wasOverdue ? " (WAS OVERDUE)" : "";
        logTransaction("BOOK_RETURNED: " + isbn + " from member " + memberId + overdueInfo);
        System.out.println("✓ Book returned successfully!" + overdueInfo);
        System.out.println("  Book: " + book.getTitle());
        if (member != null) {
            System.out.println("  Member: " + member.getName());
        }
        return true;
    }

    // Search and Display Methods
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        System.out.println("\n=== ALL BOOKS IN LIBRARY ===");
        books.values().forEach(System.out::println);
    }

    public void displayAvailableBooks() {
        List<Book> availableBooks = books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());

        if (availableBooks.isEmpty()) {
            System.out.println("No books currently available.");
            return;
        }

        System.out.println("\n=== AVAILABLE BOOKS ===");
        availableBooks.forEach(System.out::println);
    }

    public void displayIssuedBooks() {
        List<Book> issuedBooks = books.values().stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toList());

        if (issuedBooks.isEmpty()) {
            System.out.println("No books currently issued.");
            return;
        }

        System.out.println("\n=== ISSUED BOOKS ===");
        for (Book book : issuedBooks) {
            Member member = members.get(book.getIssuedTo());
            String memberName = member != null ? member.getName() : "Unknown";
            System.out.println(book + " | Member: " + memberName);
        }
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered in the library.");
            return;
        }

        System.out.println("\n=== ALL MEMBERS ===");
        members.values().forEach(System.out::println);
    }

    public void displayOverdueBooks() {
        List<Book> overdueBooks = books.values().stream()
                .filter(Book::isOverdue)
                .collect(Collectors.toList());

        if (overdueBooks.isEmpty()) {
            System.out.println("No overdue books.");
            return;
        }

        System.out.println("\n=== OVERDUE BOOKS ===");
        for (Book book : overdueBooks) {
            Member member = members.get(book.getIssuedTo());
            String memberName = member != null ? member.getName() : "Unknown";
            long daysOverdue = LocalDate.now().toEpochDay() - book.getDueDate().toEpochDay();
            System.out.println(book + " | Member: " + memberName + " | Days Overdue: " + daysOverdue);
        }
    }

    public void searchBooks(String query) {
        List<Book> matchingBooks = books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getGenre().toLowerCase().contains(query.toLowerCase()) ||
                        book.getIsbn().contains(query))
                .collect(Collectors.toList());

        if (matchingBooks.isEmpty()) {
            System.out.println("No books found matching: " + query);
            return;
        }

        System.out.println("\n=== SEARCH RESULTS FOR: " + query + " ===");
        matchingBooks.forEach(System.out::println);
    }

    public void searchMembers(String query) {
        List<Member> matchingMembers = members.values().stream()
                .filter(member -> member.getName().toLowerCase().contains(query.toLowerCase()) ||
                        member.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                        member.getMemberId().contains(query))
                .collect(Collectors.toList());

        if (matchingMembers.isEmpty()) {
            System.out.println("No members found matching: " + query);
            return;
        }

        System.out.println("\n=== MEMBER SEARCH RESULTS FOR: " + query + " ===");
        matchingMembers.forEach(System.out::println);
    }

    public void getBookDetails(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return;
        }

        System.out.println(book.getDetailedInfo());
    }

    public void getMemberDetails(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found!");
            return;
        }

        System.out.println(member.getDetailedInfo());

        // Show borrowed books details
        if (!member.getBorrowedBooks().isEmpty()) {
            System.out.println("=== BORROWED BOOKS DETAILS ===");
            for (String isbn : member.getBorrowedBooks()) {
                Book book = books.get(isbn);
                if (book != null) {
                    System.out.println("• " + book.getTitle() + " (Due: " + book.getDueDate() + ")");
                    if (book.isOverdue()) {
                        System.out.println("  *** OVERDUE ***");
                    }
                }
            }
        }
    }

    // Statistics and Reports
    public void generateLibraryReport() {
        System.out.println("\n========== LIBRARY REPORT ==========");
        System.out.println("Library: " + libraryName);
        System.out.println("Report Date: " + LocalDate.now());
        System.out.println("=====================================");

        System.out.println("Total Books: " + books.size());
        long availableBooks = books.values().stream().filter(Book::isAvailable).count();
        long issuedBooks = books.size() - availableBooks;
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Issued Books: " + issuedBooks);

        long overdueBooks = books.values().stream().filter(Book::isOverdue).count();
        System.out.println("Overdue Books: " + overdueBooks);

        System.out.println("\nTotal Members: " + members.size());
        long activeMembers = members.values().stream().filter(member -> member.getCurrentBorrowedCount() > 0).count();
        System.out.println("Active Members: " + activeMembers);

        // Most popular genres
        Map<String, Long> genreCount = books.values().stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));

        if (!genreCount.isEmpty()) {
            System.out.println("\nGenre Distribution:");
            genreCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " books"));
        }

        System.out.println("=====================================");
    }

    public void displayTransactionLog() {
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        System.out.println("\n=== TRANSACTION LOG ===");
        transactionLog.forEach(System.out::println);
    }

    private void logTransaction(String transaction) {
        String timestamp = LocalDate.now() + " - " + transaction;
        transactionLog.add(timestamp);
    }

    // Utility methods
    public int getTotalBooks() {
        return books.size();
    }

    public int getTotalMembers() {
        return members.size();
    }

    public String getLibraryName() {
        return libraryName;
    }
}