package com.sb.scheduler.modules.user.control;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.sb.scheduler.modules.user.entity.User;

public class AdvancedUserRepositoryImpl implements AdvancedUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Optional<User> findOrCreate(User user) {
		List<User> result = em.createQuery("FROM User u WHERE u.login LIKE :login", User.class)
				.setParameter("login", user.getLogin())
				.getResultList();
		
		if(result.isEmpty()){
			em.persist(user);
			return Optional.of(user);
		}
		return Optional.of(result.get(0));
	}

}
