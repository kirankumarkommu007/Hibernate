package com.demo.main;

import com.demo.entity.Author;
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

        // Create a new author and save it
        Author author = new Author();
        author.setName("kommu kirankumar");
        author.setEmail("Kiran.kumar@example.com");
        session.save(author);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Open a new session for demonstration
        Session newSession = sessionFactory.openSession();
        Transaction newTransaction = newSession.beginTransaction();

        // Retrieve the author and modify its name
        Author retrievedAuthor = newSession.get(Author.class, author.getId());
        System.out.println("Author retrieved:");
        printAuthor(retrievedAuthor);

        // Modify the author's name
        retrievedAuthor.setName("Shiva Kumar");
        System.out.println("Author name changed to 'Shiva kumar'.");

        // Commit the transaction (this triggers dirty checking and updates the database)
        newTransaction.commit();

        // Close the second session
        newSession.close();

        // Close the session factory
        sessionFactory.close();
    }

    // Utility method to print author
    private static void printAuthor(Author author) {
        if (author != null) {
            System.out.println("Author ID: " + author.getId() + ", Name: " + author.getName() + ", Email: " + author.getEmail());
        } else {
            System.out.println("Author not found.");
        }
    }
}
