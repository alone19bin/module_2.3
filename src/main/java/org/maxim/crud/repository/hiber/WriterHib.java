package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.crud.model.Post;
import org.maxim.crud.repository.WriterRepository;
import org.maxim.crud.utils.Utils;
import org.maxim.crud.model.Writer;

import java.util.List;
import java.util.Optional;

public class WriterHib implements WriterRepository {

    private final SessionFactory sessionFactory = Utils.getSessionFactory();


    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    /*public Writer getId(Writer id, Long writerId) {
         Writer writer = null;
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            writer = (Writer) session.createQuery("FROM Writer w LEFT JOIN FETCH w.posts WHERE w.id = :id")
                    .setParameter("id", writerId)
                    .list().get(0);
        } catch (Exception e) {
            System.out.println("Error in getwriters");
        }

        return writer;
    }

    public Writer saveWriter(Writer writer) {
        if (writer != null) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(writer);
                session.getTransaction().commit();
            }
        }
        return writer;
    }

    public Writer update(Writer writer) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(writer);
                session.getTransaction().commit();
                return writer;

        }
        }


    public void deleteById(Integer writerId) {

            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                Writer writer = session.get(Writer.class, writerId);
                writer.setStatus(Status.DELETED);
                session.merge(writer);
                session.getTransaction().commit();
            }
        }



    public List<Writer> getWriters() {
        try (Session session = Utils.getSessionFactory().openSession()) {
            return session.createQuery("FROM Writer w LEFT JOIN FETCH w.posts", Writer.class)
                    .setParameter("status", Status.ACTIVE)
                    .list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }*/

    @Override
    public Optional<Writer> save(Writer writer) {
        if (writer != null) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(writer);
                session.getTransaction().commit();
            }
        }
        return Optional.ofNullable(writer);
    }

    public Optional<Writer> update(Writer writer) {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(writer);
            session.getTransaction().commit();
            return Optional.ofNullable(writer);
        }
    }

    @Override
    public Optional<Writer> getId(Integer id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            transaction.commit();
            return Optional.ofNullable(writer);
        }
    }


    @Override
    public Optional<List<Writer>> getAll() {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Writer> writers = session.createQuery("SELECT w FROM Writer w", Writer.class).getResultList();
            transaction.commit();
            return Optional.ofNullable(writers);
        }
    }

    @Override
    public Optional<Post> deleteById(Integer id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            session.remove(writer);
            transaction.commit();
            return Optional.empty();
        }
    }
}
