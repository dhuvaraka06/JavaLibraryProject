// model/Book.java
package Model;

public class Book {
    private int    bookId;
    private String title;
    private String author;
    private String genre;
    private int    totalCopies;
    private int    availableCopies;

    public Book(int bookId, String title, String author, String genre,
                int totalCopies, int availableCopies) {
        this.bookId          = bookId;
        this.title           = title;
        this.author          = author;
        this.genre           = genre;
        this.totalCopies     = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Book() {}

    public int    getBookId()        { return bookId; }
    public void   setBookId(int id)  { this.bookId = id; }
    public String getTitle()         { return title; }
    public void   setTitle(String t) { this.title = t; }
    public String getAuthor()        { return author; }
    public void   setAuthor(String a){ this.author = a; }
    public String getGenre()         { return genre; }
    public void   setGenre(String g) { this.genre = g; }
    public int    getTotalCopies()   { return totalCopies; }
    public void   setTotalCopies(int c){ this.totalCopies = c; }
    public int    getAvailableCopies(){ return availableCopies; }
    public void   setAvailableCopies(int c){ this.availableCopies = c; }
}
