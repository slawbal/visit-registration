package com.sb.scheduler.modules.services.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sb.scheduler.modules.schedule.entity.Event;

import lombok.Data;

/**
 * Encja reprezentujaca pojedyncza usluge. (Strzyzenie, naprawa, itp)
 *
 */

@Entity
@Data
@Table(name="SERVICE")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private Long durationInMinutes;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "services")
	private Set<Event> events;

}
