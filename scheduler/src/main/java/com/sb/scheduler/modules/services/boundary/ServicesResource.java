package com.sb.scheduler.modules.services.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.sb.scheduler.modules.services.control.ServiceRepository;
import com.sb.scheduler.modules.services.entity.Service;


/**
 * Zasob REST udostepniajacy interfejs umozliwiajacy dodawanie, 
 * usuwanie, modyfikacje uslug (Service)
 *
 */
@RestController
@RequestMapping("/services")
public class ServicesResource {

	@Autowired
	ServiceRepository repo;

    
    @RequestMapping("/find-all")
    public List<Service> findAll() {
        return Lists.newArrayList(repo.findAll());
    }
}
