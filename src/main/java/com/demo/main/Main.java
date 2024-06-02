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


            // Define named queries (Different)
            String hqlRetrieveUsersByStatus = "FROM User u WHERE u.name = :userName";
            String jpqlRetrieveUsersByStatus = "SELECT u FROM User u WHERE u.name = :userName";

            // Execute HQL named query to retrieve users by status
            Query hqlQuery = session.createQuery(hqlRetrieveUsersByStatus, User.class);
            hqlQuery.setParameter("userName", "Kiran");
            List<User> hqlUsers = hqlQuery.getResultList();
            System.out.println("Users retrieved by HQL (Different):");
            printUsers(hqlUsers);

            // Execute JPQL named query to retrieve users by status
            Query jpqlQuery = session.createQuery(jpqlRetrieveUsersByStatus, User.class);
            jpqlQuery.setParameter("userName", "Shiva");
            List<User> jpqlUsers = jpqlQuery.getResultList();
            System.out.println("\nUsers retrieved by JPQL (Different):");
            printUsers(jpqlUsers);

            // Commit the transaction
            transaction.commit();
  
        
            session.close();
            sessionFactory.close();
        
    }

    // Utility method to print users
    private static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail() );
        }
    }
}
