package model;

public class Book {
    private int bookId;                // Maps to BOOK_ID
    private String title;             // Maps to TITLE
    private String author;            // Maps to AUTHOR
    private String genre;             // Maps to GENRE
    private int totalCopies;          // Maps to TOTAL_COPIES
    private int availableCopies;      // Maps to AVAILABLE_COPIES

    // Default constructor
    public Book() {}

    // Parameterized constructor
    public Book(int bookId, String title, String author, String genre, int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
               "bookId=" + bookId +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", genre='" + genre + '\'' +
               ", totalCopies=" + totalCopies +
               ", availableCopies=" + availableCopies +
               '}';
    }
}
