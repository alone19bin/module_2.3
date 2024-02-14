package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.utils.Utils;
import org.maxim.crud.model.Post;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class PostHib implements PostRepository {
    private void rollbackTransaction(Transaction t) {
        if (t != null) {
            t.rollback();
            System.err.println("Roll back");
        }
    }
        @Override
        public Post save (Post post){
            Transaction transaction = null;
            try (Session session = Utils.getSession()) {
                transaction = session.beginTransaction();
                session.persist(post);
                transaction.commit();
                return post;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public List<Post> getAll () {
            Transaction transaction = null;
            try (Session session = Utils.getSession()) {
                transaction = session.beginTransaction();
                List<Post> posts = session.createQuery("FROM Post", Post.class).list();
                transaction.commit();
                return posts;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public Post getById (Integer id){
            Transaction transaction = null;
            try (Session session = Utils.getSession())  {
                transaction = session.beginTransaction();
                Post post = session.get(Post.class, id);
                transaction.commit();
                return post;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public Post update (Post post){
            Transaction transaction = null;
            try (Session session = Utils.getSession())  {
                transaction = session.beginTransaction();
                session.merge(post);
                transaction.commit();
                return post;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public boolean deleteById (Integer id){
            Transaction transaction = null;
            try (Session session = Utils.getSession())  {
                transaction = session.beginTransaction();
                Post post = session.get(Post.class, id);
                post.setStatus(Status.DELETED);
                session.merge(post);
                transaction.commit();
                return true;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                e.printStackTrace();
                return false;
            }
        }
    }


  /*  @Override
    public Optional<Post> save(Post post) {
        if (post != null) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(post);
                session.getTransaction().commit();
            }
        }
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<Post> update(Post post) {
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();
            return Optional.ofNullable(post);
        }
    }

    @Override
    public Optional<Post> getId(Integer id) {
        Post post;
        try (Session session = Utils.getSessionFactory().openSession()) {

            post = session.createQuery("FROM Post p LEFT JOIN FETCH p.labels WHERE p.id = :id", Post.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        return Optional.ofNullable(post);
    }

    @Override
    public Optional<List<Post>> getAll() {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Post> posts = session.createQuery("FROM Post p LEFT JOIN FETCH p.labels WHERE p.status = :status", Post.class).getResultList();
            transaction.commit();
            return Optional.ofNullable(posts);
        }
    }

    @Override
    public Optional<Post> deleteById(Integer id) {
        try (Session session = openSession()) {
            Transaction transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            session.remove(post);
            transaction.commit();
            return Optional.ofNullable(post);
        }
    }*/





