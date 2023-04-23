package ru.mirea.task17.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.mirea.task17.entities.Post;
import ru.mirea.task17.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserService {
    private final Session session;

    public UserService(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public void add(String firstName, String middleName, String lastName, String birthDate) {
        var transaction = session.beginTransaction();
        session.saveOrUpdate(new User(firstName, middleName, lastName, birthDate));
        transaction.commit();
    }

    public void remove(Integer id) {
        var transaction = session.beginTransaction();
        String hql = "delete from User where id= :id";
        session.createQuery(hql).setParameter("id", id).executeUpdate();
        transaction.commit();
    }

    public List<Post> getAllPosts(Integer usedId) {
        return session.createQuery("select p from Post p where p.user.id = :id", Post.class)
                .setParameter("id", usedId).getResultList();
    }

    public List<User> getAll() {
        return session.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> filter(String criteria, String value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = builder.createQuery(User.class);
        Root<User> root = userCriteriaQuery.from(User.class);

        userCriteriaQuery.select(root).where(builder.equal(root.get(criteria), value));
        return session.createQuery(userCriteriaQuery).getResultList();
    }
}
