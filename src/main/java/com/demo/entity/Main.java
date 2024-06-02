package com.demo.entity;

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

        // Create two user objects (Transient state)
        NewUser user1 = new NewUser();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        NewUser user2 = new NewUser();
        user2.setId(2);
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        // Save the user objects (Persistent state)
        session.save(user1);
        session.save(user2);

        // Commit the transaction
        transaction.commit();

        // The user objects are now in the detached state
        session.close();

        System.out.println("Users saved successfully");

        // Re-open a session to demonstrate detached state
        Session newSession = sessionFactory.openSession();
        Transaction newTransaction = newSession.beginTransaction();

        // The user objects are still detached
        user1.setName("John Doe Updated");
        newSession.update(user1); // Now user1 is persistent again

        newTransaction.commit();
        newSession.close();

        sessionFactory.close();
    }
}
