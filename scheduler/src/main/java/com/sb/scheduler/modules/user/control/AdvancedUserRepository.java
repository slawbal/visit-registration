package com.sb.scheduler.modules.user.control;

import java.util.Optional;

import com.sb.scheduler.modules.user.entity.User;

public interface AdvancedUserRepository {

	Optional<User> findOrCreate(User user);
}
