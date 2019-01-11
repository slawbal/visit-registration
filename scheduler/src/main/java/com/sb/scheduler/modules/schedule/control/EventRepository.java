package com.sb.scheduler.modules.schedule.control;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sb.scheduler.modules.schedule.entity.Event;
import com.sb.scheduler.modules.user.entity.User;

public interface EventRepository extends  CrudRepository<Event, Long >  {
	List<Event> findByUser(User user);
	
	@Query("SELECT DISTINCT e FROM Event e LEFT JOIN FETCH e.services")
	List<Event> findAllEager();
	
	@Query("SELECT e FROM Event e LEFT JOIN FETCH e.services WHERE e.startDate >= ?1 AND e.startDate <= ?2")
	List<Event> findBetweenEager(Instant since, Instant to);
}
