package com.sb.registrationweb.components.pupup;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;

import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.entity.ServiceDTO;
import com.sb.registrationweb.entity.UserDTO;
import com.sb.registrationweb.suppliers.SimpleDataSupplier;

public class CreateEventWindow extends AbstractEventWindow{

	public CreateEventWindow(ZonedDateTime date, SubmitAction submitAction, SimpleDataSupplier<List<ServiceDTO>> itemSupplier) {
		super(createEmptyBean(), submitAction, itemSupplier);
		startDate.setValue(date.toLocalDateTime());
		setCaption("Utwórz wizytę");
	}

	private static EventDTO createEmptyBean(){
		EventDTO event = new EventDTO();
		event.setUser(new UserDTO());
		event.setServices(new HashSet<ServiceDTO>());
		event.setStartDate(Instant.now());
		return event;
	}
}
