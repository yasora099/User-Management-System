package com.hms.management.usermanagement.repository;

import com.hms.management.usermanagement.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    public User findById(long id) {
        User user = entityManager.find( User.class, id );
        return user;
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createNativeQuery("DELETE FROM user_details.user as em where em.id IN :id",
                User.class);
        query.setParameter("id", id );


    }

    @Override
    public void add(User user) {
        entityManager.merge(user);

    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }
}
