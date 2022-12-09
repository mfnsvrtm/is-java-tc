package com.github.mfnsvrtm.isjavatc.task3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public class SessionUtil {
    private static final SessionFactory factory = initializeSessionFactory();

    public static Session openSession() {
        return factory.openSession();
    }

    public static void executeInTransaction(Consumer<Session> action) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        }
    }

    public static <R> R applyInTransaction(Function<Session, R> function) {
        R result;
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            result = function.apply(session);
            transaction.commit();
        }
        return result;
    }

    private static SessionFactory initializeSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
}
