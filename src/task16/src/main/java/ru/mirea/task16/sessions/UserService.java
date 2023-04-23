package ru.mirea.task16.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.mirea.task16.entities.Post;
import ru.mirea.task16.entities.User;

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
}
