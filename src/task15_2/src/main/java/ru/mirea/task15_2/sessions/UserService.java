package ru.mirea.task15_2.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.mirea.task15_2.entities.User;

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
        session.createQuery(hql).setInteger("id", id).executeUpdate();
        transaction.commit();
    }

    public List<User> getAll() {
        return session.createQuery("select u from User u", User.class).getResultList();
    }
}
