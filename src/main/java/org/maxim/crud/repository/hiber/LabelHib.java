package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.maxim.crud.utils.Utils;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;

import java.util.Collections;
import java.util.List;

public class LabelHib {
    public Label getLabelById(Integer label_id) {
        Label label;
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            label = (Label) session.createQuery("FROM Label WHERE id = :id")
                    .setParameter("id", label_id)
                    .list().get(0);
        }


        return label;
    }

    public Label saveLabel(Label label) {
        if (label != null) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(label);
                session.getTransaction().commit();
            }
        }
        return label;
    }

    public Label update(Label label) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(label);
                session.getTransaction().commit();
                return label;
            }

    }
    public void deleteById(Integer integer){

        getLabelById(integer);
        if(getLabelById(integer).getStatus() != Status.DELETED){
            try(Session session = Utils.getSessionFactory().openSession()){
                session.beginTransaction();
                Label label = session.get(Label.class, integer);
                label.setStatus(Status.DELETED);
                session.merge(label);
                session.getTransaction().commit();
            }
        } else {
            throw new IllegalArgumentException("Label not found");
        }
    }
    public List<Label> getLabels() {
        try (Session session = Utils.getSessionFactory().openSession()) {
            return session.createQuery("FROM Label ", Label.class)
                    .setParameter("status", Status.ACTIVE)
                    .list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
}
    }
