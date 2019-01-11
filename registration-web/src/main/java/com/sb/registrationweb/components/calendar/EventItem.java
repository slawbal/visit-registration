package com.sb.registrationweb.components.calendar;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.vaadin.addon.calendar.item.BasicItem;

import com.sb.registrationweb.entity.EventDTO;

public class EventItem extends BasicItem{

	private EventDTO event;
	
	public EventItem(EventDTO event){
		super(event.getUser().getFirstName()+" " + event.getUser().getLastName(), event.getDescription(), 
				convertToZoneTime(event.getStartDate()),
				calculateDuration(event));
		this.event = event;
	}

	public EventDTO getEvent() {
		return event;
	}
	

	private static ZonedDateTime convertToZoneTime(Instant instant){
		return instant.atZone(ZoneId.systemDefault());
	}
	
	private static ZonedDateTime calculateDuration(EventDTO event){
		long sum = event.getServices().stream()
				.mapToLong(s -> s.getDurationInMinutes())
				.sum();
		return convertToZoneTime(event.getStartDate().plus(sum, ChronoUnit.MINUTES));
	}
}
