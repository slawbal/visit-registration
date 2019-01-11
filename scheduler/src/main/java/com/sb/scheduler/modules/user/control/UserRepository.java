package com.sb.scheduler.modules.user.control;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sb.scheduler.modules.user.entity.User;

public interface UserRepository extends  CrudRepository<User, Long >, AdvancedUserRepository{

	Optional<User> findByLogin(String login);
	
}
