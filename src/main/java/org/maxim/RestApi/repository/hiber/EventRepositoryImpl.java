package org.maxim.RestApi.repository.hiber;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.Event;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.model.User;
import org.maxim.RestApi.repository.EventRepository;
import org.maxim.RestApi.utils.HibernateUtil;

import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private boolean hasValidValues(Event event) {
        if (event.getUser() == null
                || event.getFile() == null
                || event.getStatus() == null
        ) {
            return false;
        }
        return true;
    }

    @Override
    public List<Event> getAll() {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.createQuery("FROM Event e LEFT JOIN FETCH e.user", Event.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Event getById(Integer id) {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.createQuery("FROM Event e LEFT JOIN FETCH e.user WHERE e.id = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Event save(Event event) {
        if (event == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (!hasValidValues(event)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Event update(Event event) {
        if (event == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (session.get(User.class, event.getId()) == null) {
                throw new Exception("ID is not found. Nothing to update.");
            }
            if (!hasValidValues(event)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
            return event;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            Event event = session.get(Event.class, id);
            if (event == null) {
                throw new Exception("ID is not found. Nothing to delete");
            }
            event.setStatus(Status.DELETED);
            transaction = session.beginTransaction();
            session.merge(event);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}





