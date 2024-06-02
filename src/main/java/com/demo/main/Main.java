package com.demo.main;

import com.demo.entity.User;

import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

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

            // 1. Retrieve all users
            System.out.println("Retrieving all users:");
            Query queryRetrieveAllUsers = session.createQuery("SELECT u FROM User u", User.class);
            List<User> allUsers = queryRetrieveAllUsers.getResultList();
            for (User user : allUsers) {
                System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
            }

            // 2. Retrieve users based on specific criteria
            System.out.println("\nRetrieving users with name 'John Doe':");
            String jpqlRetrieveUsersByName = "SELECT u FROM User u WHERE u.name = :userName";
            Query queryRetrieveUsersByName = session.createQuery(jpqlRetrieveUsersByName, User.class);
            queryRetrieveUsersByName.setParameter("userName", "kiran");
            List<User> usersByName = queryRetrieveUsersByName.getResultList();
            for (User user : usersByName) {
                System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
            }

            // Commit the transaction
            transaction.commit();
      
            // Close session and session factory
            session.close();
            sessionFactory.close();
        
        }
}
