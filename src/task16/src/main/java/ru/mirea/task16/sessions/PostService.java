package ru.mirea.task16.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.mirea.task16.entities.Post;
import ru.mirea.task16.entities.User;

import java.util.List;

@Component
public class PostService {

    private final Session session;

    public PostService(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public void add(String text, String creationDate, Integer userId) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(new Post(text, creationDate, session.load(User.class, userId)));
        transaction.commit();
    }

    public void remove(Integer id) {
        var transaction = session.beginTransaction();
        String hql = "delete from Post where id= :id";
        session.createQuery(hql).setParameter("id", id).executeUpdate();
        transaction.commit();
    }

    public Post getPost(Integer id) {
        return session.createQuery("select p from Post p where p.id = :id", Post.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User getUserByPost(Integer postId) {
        return session.createQuery("select p from Post p where p.id= :id", Post.class)
                .setParameter("id", postId).getSingleResult().getUser();
    }

    public List<Post> getAll() {
        return session.createQuery("select p from Post p", Post.class).getResultList();
    }
}
