package com.demo.main;

import com.demo.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

        // Retrieve the named query
        Query<User> query = session.createNamedQuery("findAllEmployees", User.class);

        // Execute the query to fetch employees
        List<User> employees = query.getResultList();

        // Print the retrieved employees
        printUsers(employees);

        // Close the session
        session.close();

        // Close the session factory
        sessionFactory.close();
    }

    // Utility method to print users
    private static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }
}
