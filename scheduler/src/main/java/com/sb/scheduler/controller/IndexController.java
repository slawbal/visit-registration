package com.sb.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sb.scheduler.modules.schedule.control.EventRepository;
import com.sb.scheduler.modules.schedule.entity.Event;

@Controller
@RequestMapping(value = "/ss")
public class IndexController {

	@Autowired
	EventRepository repo;
	
    @RequestMapping(value = "/aa", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
    	Iterable<Event> all = repo.findAll();
        return all.toString();
    }

}
