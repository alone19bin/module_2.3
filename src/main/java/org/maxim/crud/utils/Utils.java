package org.maxim.crud.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Writer;

public class Utils {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
            try {
                sessionFactory = new MetadataSources(registry)
                                .addAnnotatedClass(Label.class)
                                .addAnnotatedClass(Post.class)
                                .addAnnotatedClass(Writer.class)
                                .buildMetadata()
                                .buildSessionFactory();
            }
            catch (Exception e) {
                System.out.println("Error in SessionFactory");
            }

        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
