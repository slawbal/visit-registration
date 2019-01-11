package com.sb.scheduler.modules.user.boundary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.sb.scheduler.modules.user.control.UserRepository;
import com.sb.scheduler.modules.user.entity.User;


@RestController
@RequestMapping("/users")
public class UsersResource {

	@Autowired
	UserRepository repo;

    
    @RequestMapping(value ="/{login}/get", method = RequestMethod.GET)
    public User getByLogin(@PathVariable("login") String login) {
         Optional<User> findByLogin = repo.findByLogin(login);
         if(findByLogin.isPresent()){
        	 return findByLogin.get();
         }else{
        	 return null;
         }
    }
    
    @RequestMapping(value ="/find-all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
         return Lists.newArrayList(repo.findAll());
    }
}
