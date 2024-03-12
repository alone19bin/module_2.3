package org.maxim.RestApi.repository.hiber;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.RestApi.model.File;
import org.maxim.RestApi.repository.FileRepository;
import org.maxim.RestApi.utils.HibernateUtil;

import java.util.List;
public class FileHib implements FileRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public List<File> getAll() {
        try (Session session = sessionFactory.openSession()) {
             return session.createQuery("FROM File", File.class).list();
        } catch (Exception e) {
            System.out.println("Error in File getAll");
        }
        return null;
    }

    @Override
    public File getById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(File.class, id);
        } catch (Exception e) {
            System.out.println("Error in File getById");
        }
        return null;
    }

    @Override
    public File save(File file) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(file);
            transaction.commit();
            return file;
        }catch (Exception e) {
            System.out.println("Error in File save");
        }
        return null;
    }

    @Override
    public File update(File file) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(file);
            transaction.commit();
            return file;

        }catch (Exception e) {
            System.out.println("Error in File update");
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
        }  catch (Exception e) {
                System.out.println("Error in File deleteById ");
            }
            return false;

    }
}