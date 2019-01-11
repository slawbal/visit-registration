package com.sb.scheduler.modules.schedule.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sb.scheduler.modules.services.entity.Service;
import com.sb.scheduler.modules.user.entity.User;

/**
 * Encja reprezentujaca pojedyncze wydarzenie, (wizyta, zgloszenie serwisowe)
 *
 */

@Entity
@Table(name="EVENT")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private Instant startDate;
	
	@ManyToOne
	private User user;
	
    @ManyToMany
    @JoinTable(
        name = "EVENT_SERVICE", 
        joinColumns = { @JoinColumn(name = "EVENT_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") }
    )
	private Set<Service> services;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
	
}
