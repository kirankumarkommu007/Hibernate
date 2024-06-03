package com.demo.main;

import com.demo.entity.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a Hibernate configuration object
        Configuration configuration = new Configuration().configure();

        // Create a session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create a list of Product objects
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 999.99));
        products.add(new Product("Smartphone", 599.99));
        products.add(new Product("Headphones", 99.99));
        products.add(new Product("Tablet", 399.99));

        // Set the batch size
        int batchSize = 2;

        // Process batches within a single transaction
        for (int i = 0; i < products.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, products.size());
            List<Product> batchProducts = products.subList(i, endIndex);
            
            // Print batch started
            System.out.println("Batch started: " + batchProducts.size() + " products");

            // Save products in the current batch
            for (Product product : batchProducts) {
                session.save(product);
            }

            // Flush and clear the session to execute the batch
            session.flush();
            session.clear();

            // Print batch ended
            System.out.println("Batch ended: " + batchProducts.size() + " products");
        }

        // Commit the transaction
        transaction.commit();
        session.close();

    }
}
