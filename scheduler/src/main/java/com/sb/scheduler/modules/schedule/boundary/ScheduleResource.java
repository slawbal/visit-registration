package com.sb.scheduler.modules.schedule.boundary;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.scheduler.modules.schedule.control.EventRepository;
import com.sb.scheduler.modules.schedule.entity.Event;
import com.sb.scheduler.modules.user.control.UserRepository;
import com.sb.scheduler.modules.user.entity.User;

/**
 * Zasob REST udostepniajacy interfejs umozliwiajacy dodawanie, 
 * usuwanie, modyfikacje wydarzen (Event)
 *
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleResource {

	@Autowired
	EventRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
    @RequestMapping("/find-between")
    public List<Event> findBetween(@RequestParam(value = "since", required = false) Instant since, 
    		@RequestParam(value = "to", required = false) Instant to) {
    	if(since == null){
    		since = Instant.now();
    	}
    	if(to == null){
    		to = Instant.now().plus(7, ChronoUnit.DAYS);
    	}
        return repo.findBetweenEager(since, to);
    }
    
    @RequestMapping("/find-all")
    public List<Event> findAll() {
        return repo.findAllEager();
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Event event) {
    	Optional<User> user = userRepo.findOrCreate(event.getUser());
    	event.setUser(user.get());
        repo.save(event);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Event event){
    	repo.save(event);
    }
    
}
