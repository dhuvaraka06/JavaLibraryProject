// main/LibraryApp.java
package Main;

import Model.Book;
import Service.LibraryService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static final LibraryService svc = new LibraryService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            menu();
            switch(sc.nextInt()){
                case 1 -> listBooks();
                case 2 -> borrow(sc);
                case 3 -> renew(sc);
                case 4 -> ret(sc);
                case 0 -> System.exit(0);
            }
        }
    }

    private static void menu(){
        System.out.println("""
            1. List books
            2. Borrow book
            3. Renew book
            4. Return book
            0. Exit
            """);
        System.out.print("Choice: ");
    }

    private static void listBooks(){
        try{
            List<Book> list = svc.listBooks();
            list.forEach(b -> System.out.printf(
                "%d | %s | %s | %s | %d/%d%n",
                b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),
                b.getAvailableCopies(),b.getTotalCopies()));
        }catch(SQLException e){e.printStackTrace();}
    }

    private static void borrow(Scanner sc){
        try{
            System.out.print("User ID: "); int u=sc.nextInt();
            System.out.print("Book ID: "); int b=sc.nextInt();
            svc.borrowBook(u,b);
            System.out.println("Borrowed");
        }catch(Exception e){System.out.println(e.getMessage());}
    }

    private static void renew(Scanner sc){
        try{
            System.out.print("User ID: "); int u=sc.nextInt();
            System.out.print("Book ID: "); int b=sc.nextInt();
            svc.renewBook(u,b);
            System.out.println("Renewed");
        }catch(Exception e){System.out.println(e.getMessage());}
    }

    private static void ret(Scanner sc){
        try{
            System.out.print("User ID: "); int u=sc.nextInt();
            System.out.print("Book ID: "); int b=sc.nextInt();
            svc.returnBook(u,b);
            System.out.println("Returned");
        }catch(Exception e){System.out.println(e.getMessage());}
    }
}
