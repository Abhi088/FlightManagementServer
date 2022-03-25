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
	public User saveUser(User user) {
		EntityManager entityManager = entityManagerProvider.get();
		entityManager.persist(user);
		return user;
	}

	@UnitOfWork
	public boolean isUserAndPasswordValid(String username, String password) {

		if (username != null && password != null) {

			EntityManager entityManager = entityManagerProvider.get();

			TypedQuery<User> q = entityManager.createQuery("SELECT x FROM user_data x WHERE username = :usernameParam",
					User.class);
			User user = getSingleResult(q.setParameter("usernameParam", username));

			if (user != null) {

				if (user.getPass_word().equals(password)) {

					return true;
				}

			}

		}

		return false;

	}

	@UnitOfWork
	public boolean isAdmin(String username) {
		if (username != null) {
			EntityManager entityManager = entityManagerProvider.get();

			TypedQuery<User> q = entityManager.createQuery("SELECT x FROM user_data x WHERE username = :usernameParam",
					User.class);
			User user = getSingleResult(q.setParameter("usernameParam", username));

			if (user != null) {
				return user.getIsAdmin();
			}
		}
		return false;
	}

	private static <T> T getSingleResult(TypedQuery<T> query) {
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

}