package ru.mirea.task17.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.mirea.task17.entities.Post;
import ru.mirea.task17.entities.User;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public Post getPost(Integer id) throws NoResultException {
        return session.createQuery("select p from Post p where p.id = :id", Post.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User getUserByPost(Integer postId) throws NoResultException {
        return session.createQuery("select p from Post p where p.id= :id", Post.class)
                .setParameter("id", postId).getSingleResult().getUser();
    }

    public List<Post> getAll() {
        return session.createQuery("select p from Post p", Post.class).getResultList();
    }

    public List<Post> filter(String criteria, String value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Post> postCriteriaQuery = builder.createQuery(Post.class);
        Root<Post> root = postCriteriaQuery.from(Post.class);

        postCriteriaQuery.select(root).where(builder.equal(root.get(criteria), value));
        return session.createQuery(postCriteriaQuery).getResultList();
    }
}
