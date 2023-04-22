package ru.mirea.task15_2.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import ru.mirea.task15_2.entities.Post;

import java.util.List;

@Component
public class PostService {

    private final Session session;

    public PostService(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public void add(String text, String creationDate) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(new Post(text, creationDate));
        transaction.commit();
    }

    public void remove(Integer id) {
        var transaction = session.beginTransaction();
        String hql = "delete from Post where id= :id";
        session.createQuery(hql).setInteger("id", id).executeUpdate();
        transaction.commit();
    }

    public List<Post> getAll() {
        return session.createQuery("select p from Post p", Post.class).getResultList();
    }
}
