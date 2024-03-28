package org.maxim.RestApi.repository.hiber;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.File;
import org.maxim.RestApi.model.Status;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.utils.HibernateUtil;

import java.util.List;

public class FileRepositoryImpl implements FileRepository {


    private boolean hasValidValues(File file) {
        if (file.getName() == null ||  file.getName().isBlank()
                || file.getFilePath() == null || file.getFilePath() == null
                || file.getStatus() == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<File> getAll() {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.createQuery("FROM File", File.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File getById(Integer id) {
        try (Session session = HibernateUtil.getNewSession()) {
            return session.get(File.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File save(File file) {
        if (file == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (!hasValidValues(file)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.persist(file);
            transaction.commit();
            return file;
        } catch (Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public File update(File file) {
        if (file == null) {
            return null;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            if (session.get(File.class, file.getId()) == null) {
                throw new Exception("ID is not found. Nothing to update");
            }
            if (!hasValidValues(file)) {
                throw new Exception("Incorrect field values");
            }
            transaction = session.beginTransaction();
            session.merge(file);
            transaction.commit();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getNewSession()) {
            File file = session.get(File.class, id);
            if (file == null) {
                throw new Exception("ID is not found. Nothing to delete.");
            }
            file.setStatus(Status.DELETED);
            transaction = session.beginTransaction();
            session.merge(file);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}