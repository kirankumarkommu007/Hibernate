package com.demo.main;

import com.demo.entity.User;
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

        // Retrieve user by ID (first time - hits the database)
        User user1 = session.get(User.class, 1);
        System.out.println("First retrieval:");
        printUser(user1);

        // Retrieve the same user by ID (second time - uses first-level cache)
        User user2 = session.get(User.class, 2);
        System.out.println("Second retrieval:");
        printUser(user2);
        
        
        User user5 = session.get(User.class, 1);
        System.out.println("third retrieval:");
        printUser(user5);


        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Open a new session
        Session session1 = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction1 = session1.beginTransaction();

        // Retrieve user by ID (third time - hits the database again)
        User user3 = session1.get(User.class, 1);
        System.out.println("4th retrieval:");
        printUser(user3);
        
        
        User user4 = session1.get(User.class, 1);
        System.out.println("5th retrieval:");
        printUser(user4);

        // Commit the transaction
        transaction1.commit();

        // Close the second session
        session1.close();

        // Close the session factory
        sessionFactory.close();
    }

    // Utility method to print user
    private static void printUser(User user) {
        if (user != null) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }
}
