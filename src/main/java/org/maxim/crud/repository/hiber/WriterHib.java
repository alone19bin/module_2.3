package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.WriterRepository;
import org.maxim.crud.utils.Utils;
import org.maxim.crud.model.Writer;

import java.util.*;

public class WriterHib implements WriterRepository {

   private void rollbackTransaction(Transaction t) {
       if (t != null) {
           t.rollback();
           System.err.println("RRoll back");
       }
   }

    @Override
    public Writer save(Writer writer) {
        Transaction transaction = null;
        try (Session session = Utils.getSession()) {
            transaction = session.beginTransaction();
            session.persist(writer);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Writer> getAll() {
        Transaction transaction = null;
        try (Session session = Utils.getSession()) {
            transaction = session.beginTransaction();
            List<Writer> writers = session.createQuery("FROM Writer", Writer.class).list();
            transaction.commit();
            return writers;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
    }
  }

    @Override
    public Writer getById(Integer id) {
        Transaction transaction = null;
        try (Session session = Utils.getSession()) {
            transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Writer update(Writer writer) {
        Transaction transaction = null;
        try (Session session = Utils.getSession()) {
            transaction = session.beginTransaction();
            session.merge(writer);
            transaction.commit();
            return writer;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = Utils.getSession()) {
            transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            writer.setStatus(Status.DELETED);
            session.merge(writer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return false;
        }
    }
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
