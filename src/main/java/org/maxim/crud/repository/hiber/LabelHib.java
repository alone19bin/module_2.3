package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxim.crud.repository.LabelRepository;
import org.maxim.crud.utils.HibernateUtil;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;

import java.util.List;

public class LabelHib implements LabelRepository {
    private void rollbackTransaction(Transaction t) {
        if (t != null) {
            t.rollback();
            System.err.println("Roll back");
        }
    }

    @Override
    public Label save(Label label) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(label);
            transaction.commit();
            return label;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Label> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Label> labels = session.createQuery("FROM Label", Label.class).list();
            transaction.commit();
            return labels;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label getById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Label label = session.get(Label.class, id);
            transaction.commit();
            return label;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Label update(Label label) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(label);
            transaction.commit();
            return label;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Label label = session.get(Label.class, id);
            label.setStatus(Status.DELETED);
            session.merge(label);
            transaction.commit();
            return true;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
            return false;
        }
    }
}