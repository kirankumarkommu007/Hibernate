package com.demo.main;

import com.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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
        Query<User> queryRetrieveAllUsers = session.createQuery("FROM User", User.class);
        List<User> allUsers = queryRetrieveAllUsers.list();
        for (User user : allUsers) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
        
        

        // 2. Retrieve users based on specific criteria
        System.out.println("\nRetrieving users with name 'John Doe':");
        String hqlRetrieveUsersByName = "FROM User U WHERE U.name = :userName";
        Query<User> queryRetrieveUsersByName = session.createQuery(hqlRetrieveUsersByName, User.class);
        queryRetrieveUsersByName.setParameter("userName", "kiran");
        List<User> usersByName = queryRetrieveUsersByName.list();
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
