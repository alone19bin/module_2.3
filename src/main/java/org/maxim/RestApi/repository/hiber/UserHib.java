package org.maxim.RestApi.repository.hiber;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.UserRepository;
import org.maxim.RestApi.utils.HibernateUtil;

import java.util.*;



public class UserHib implements UserRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User u LEFT JOIN FETCH u.events", User.class).list();
        } catch (Exception e) {
            System.out.println("Error in user getall " + e);
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.id = :id", User.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error in user getById " + e);
        }
        return null;
    }

    @Override
    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            System.out.println("Error in user save " + e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            System.out.println("Error in user update " + e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error in user deleteById " + e);
        }
            return false;
    }
}
