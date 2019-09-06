package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class JpaUserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM user_details.user as em " , User.class);
        return query.getResultList();
    }

    @Override
    public Object findById(long id) {
        Query q = entityManager.createNativeQuery("SELECT em.* FROM user as em WHERE em.id = :id");
        q.setParameter( "id" , id);
        return q.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM user where id =:id");
        query.setParameter("id", id );
        query.executeUpdate();
    }

    @Override
    public void add(User user) {
        Query query = entityManager.createNativeQuery("INSERT INTO user (name, email) VALUES (?, ?)");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getEmail());
        query.executeUpdate();

    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }
}
