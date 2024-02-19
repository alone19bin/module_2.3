package org.maxim.crud.utils;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Writer;

public class HibernateUtil {

        private HibernateUtil() {
        }

        private static SessionFactory sessionFactory;

       public static synchronized SessionFactory getSessionFactory() {
            if (sessionFactory == null || sessionFactory.isClosed()) {
                setUp();
            }
            return sessionFactory;
        }

        private static void setUp() {
            final StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .build();
            try {
                sessionFactory =
                        new MetadataSources(registry)
                                .addAnnotatedClass(Label.class)
                                .addAnnotatedClass(Post.class)
                                .addAnnotatedClass(Writer.class)
                                .buildMetadata()
                                .buildSessionFactory();
            }
            catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we
                // had trouble building the SessionFactory so destroy it manually.
                StandardServiceRegistryBuilder.destroy(registry);

                throw e;
            }
        }
    }


