package com.demo.main;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.entity.Teacher;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.setInterceptor(new AuditInterceptor());
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create a new Teacher
        Teacher teacher = new Teacher();
        teacher.setName("Shiva");

        // Save the teacher (this will trigger the onSave method in the interceptor)
        session.save(teacher);

        // Update the teacher (this will trigger the onFlushDirty method in the interceptor)
        teacher.setName("Surender");
        session.update(teacher);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
