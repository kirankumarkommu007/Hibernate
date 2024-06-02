package com.demo.main;

import com.demo.entity.Author;
import com.demo.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create a Hibernate configuration object
        Configuration configuration = new Configuration().configure();

        // Create a session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Create a new author
        Author author = new Author();
        author.setName("John Doe");

        // Create new books
        Book book1 = new Book();
        book1.setTitle("Java Programming");
        book1.setPrice(45.00);
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setTitle("Hibernate in Action");
        book2.setPrice(55.00);
        book2.setAuthor(author);

        // Save the author and books
        session.save(author);
        session.save(book1);
        session.save(book2);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Open a new session for demonstration
        Session newSession = sessionFactory.openSession();

        // Retrieve the author (first time - hits the database)
        System.out.println("Retrieving the author from the database...");
        Author retrievedAuthor = newSession.get(Author.class, author.getId());
        System.out.println("Author retrieved:");
        printAuthor(retrievedAuthor);

        // Access the books (triggers lazy loading)
        System.out.println("Accessing the books (triggers lazy loading)...");
        retrievedAuthor.getBooks().forEach(Main::printBook);

        // Close the second session
        newSession.close();

        // Close the session factory
        sessionFactory.close();
    }

    // Utility method to print author
    private static void printAuthor(Author author) {
        if (author != null) {
            System.out.println("Author ID: " + author.getId() + ", Name: " + author.getName() );
        } else {
            System.out.println("Author not found.");
        }
    }

    // Utility method to print book
    private static void printBook(Book book) {
        if (book != null) {
            System.out.println("Book ID: " + book.getId() + ", Title: " + book.getTitle() + ", Price: " + book.getPrice());
        } else {
            System.out.println("Book not found.");
        }
    }
}
