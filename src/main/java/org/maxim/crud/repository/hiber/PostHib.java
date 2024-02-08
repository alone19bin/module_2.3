package org.maxim.crud.repository.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.maxim.crud.repository.PostRepository;
import org.maxim.crud.utils.Utils;
import org.maxim.crud.model.Post;

import java.util.List;
import java.util.Optional;

public class PostHib implements PostRepository {
    private final SessionFactory sessionFactory = Utils.getSessionFactory();

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
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
            session.beginTransaction();
            post = (Post) session.createQuery("FROM Post p LEFT JOIN FETCH p.labels WHERE p.id = :id")
                    .setParameter("id", id)
                    .list().get(0);
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
    }
}
  /*  public List<Post> getPosts() {
        try (Session session = Utils.getSessionFactory().openSession()) {
            return session.createQuery("FROM Post p LEFT JOIN FETCH p.labels ", Post.class)
                    .setParameter("status", Status.ACTIVE)
                    .list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Post getPostById(Integer postId){
        Post post;
        try (Session session = Utils.getSessionFactory().openSession()) {
            session.beginTransaction();
            post = (Post) session.createQuery("FROM Post p LEFT JOIN FETCH p.labels WHERE p.id = :id")
                    .setParameter("id", postId)
                    .list().get(0);
        }
        if(post != null){
            return post;
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

    public Post savePost(Post post) {
        if (post != null) {
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(post);
                session.getTransaction().commit();
            }
        }
        return post;
    }

    public Post update(Post post){
            try (Session session = Utils.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(post);
                session.getTransaction().commit();
                return post;
            }
        }



    public void deleteById(Integer postId){
        Post post = getPostById(postId);
            try(Session session = Utils.getSessionFactory().openSession()){
                session.beginTransaction();
                post.setStatus(Status.DELETED);
                session.merge(post);
                session.getTransaction().commit();
            }
        }*/



