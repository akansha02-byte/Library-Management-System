import java.sql.*;
import java.util.Scanner;

public class LibraryOperations {

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://localhost:3300/library_db";
            String user = "root";
            String password = "4321";

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            System.out.println(e);
        }

        return con;
    }

    // VIEW BOOKS
    public static void viewBooks() {

        try {

            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");

            System.out.println("\n------------- BOOK LIST -------------");
            System.out.println("ID\tTitle\t\tAuthor\t\tPublisher\tQuantity");

            if (!rs.isBeforeFirst()) {
                System.out.println("No books available.");
            }

            while (rs.next()) {

                System.out.println(
                        rs.getInt("book_id") + "\t" +
                                rs.getString("title") + "\t\t" +
                                rs.getString("author") + "\t\t" +
                                rs.getString("publisher") + "\t\t" +
                                rs.getInt("quantity")
                );

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // ADD BOOK
    public static void addBook() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Publisher: ");
            String publisher = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            Connection con = getConnection();

            String sql = "INSERT INTO books(title,author,publisher,quantity) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, publisher);
            ps.setInt(4, quantity);

            ps.executeUpdate();

            System.out.println("Book Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // VIEW STUDENTS
    public static void viewStudents() {

        try {

            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM std");

            System.out.println("\n------------- STUDENT LIST -------------");
            System.out.println("ID\tName\t\tEmail");

            if (!rs.isBeforeFirst()) {
                System.out.println("No students found.");
            }

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + "\t" +
                                rs.getString("name") + "\t\t" +
                                rs.getString("email")
                );

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // ISSUE BOOK
    public static void issueBook() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            Connection con = getConnection();

            String sql = "INSERT INTO issue_books(student_id,book_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, bookId);

            ps.executeUpdate();

            System.out.println("Book Issued Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. View Books");
            System.out.println("2. Add Book");
            System.out.println("3. View Students");
            System.out.println("4. Issue Book");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewBooks();
                    break;

                case 2:
                    addBook();
                    break;

                case 3:
                    viewStudents();
                    break;

                case 4:
                    issueBook();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");

            }

        } while (choice != 5);

    }

}