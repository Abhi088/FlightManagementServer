package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import models.User;

import ninja.jpa.UnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;


public class UserDao {
    
    @Inject
    Provider<EntityManager> entityManagerProvider;
    
    @Transactional
    public User saveUser(String username, String password, String fullname) {
    	User user = new User(username, password, fullname);
    	EntityManager entityManager = entityManagerProvider.get();
    	entityManager.persist(user);
    	return user;
    }
    
    
    @UnitOfWork
    public boolean isUserAndPasswordValid(String username, String password) {
        
        if (username != null && password != null) {
            
            EntityManager entityManager = entityManagerProvider.get();
            
            TypedQuery<User> q = entityManager.createQuery("SELECT x FROM user_data x WHERE username = :usernameParam", User.class);
            User user = getSingleResult(q.setParameter("usernameParam", username));

            if (user != null) {
                
                if (user.password.equals(password)) {

                    return true;
                }
                
            }

        }
        
        return false;
 
    }

    /**
     * Get single result without throwing NoResultException, you can of course just catch the
     * exception and return null, it's up to you.
     */
    private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

}