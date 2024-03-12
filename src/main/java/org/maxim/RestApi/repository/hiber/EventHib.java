package org.maxim.RestApi.repository.hiber;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.repository.EventRepository;
import org.maxim.RestApi.utils.HibernateUtil;


import java.util.List;

public class EventHib implements EventRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Event> getAll() {
        try (Session session = sessionFactory.openSession()) {
           return session.createQuery("FROM Event e LEFT JOIN FETCH e.user", Event.class).list();
        } catch (Exception e) {
            System.out.println("Error in Event getAll " + e);
        } return null;
    }

    @Override
    public Event getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event e LEFT JOIN FETCH e.user WHERE e.id = :id", Event.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error in Event getById " + e);
        } return null;
    }

    @Override
    public Event save(Event event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
            return event;
        } catch (Exception e) {
            System.out.println("Error in Event save " + e);
        } return null;
    }

    @Override
    public Event update(Event event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
            return event;
        }catch (Exception e) {
            System.out.println("Error in Event update " + e);
        } return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error in Event deleteById " + e);
        } return false;
    }
}





