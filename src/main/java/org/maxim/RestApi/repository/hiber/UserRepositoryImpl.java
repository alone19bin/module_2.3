package org.maxim.RestApi.repository.hiber;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.UserRepository;
import org.maxim.RestApi.utils.HibernateUtil;

import java.util.List;



public class UserRepositoryImpl implements UserRepository {

    private boolean hasValidValues(User user) {
        if (user.getName() == null ||  user.getName().isBlank() || user.getStatus() == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.createQuery("FROM User u LEFT JOIN FETCH u.events", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getById(Integer id) {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (!hasValidValues(user)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User user) {
        if (user == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (session.get(User.class, user.getId()) == null) {
                throw new Exception("ID is not found. Nothing to update.");
            }
            if (!hasValidValues(user)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return user;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new Exception("ID is not found.Nothing to delete.");
            }
            user.setStatus(Status.DELETED);
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
