package com.sb.scheduler.modules.services.control;

import org.springframework.data.repository.CrudRepository;

import com.sb.scheduler.modules.services.entity.Service;


public interface ServiceRepository extends  CrudRepository<Service, Long >{

}
