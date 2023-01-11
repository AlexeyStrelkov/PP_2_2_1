package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private Session session;



    @Override
    public void add(User user) {
        session.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = session.createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User userCar(String model, int series) {
        TypedQuery<User> query = session.createQuery("from User where " +
                "car.model = :model and car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }
}
