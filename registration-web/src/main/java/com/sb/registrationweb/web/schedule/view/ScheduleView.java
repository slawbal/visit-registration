package com.sb.registrationweb.web.schedule.view;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import com.sb.registrationweb.components.calendar.EventItem;
import com.sb.registrationweb.components.calendar.PeriodSwitch;
import com.sb.registrationweb.components.pupup.AbstractEventWindow;
import com.sb.registrationweb.components.pupup.CreateEventWindow;
import com.sb.registrationweb.components.pupup.EditEventWindow;
import com.sb.registrationweb.entity.EventDTO;
import com.sb.registrationweb.web.schedule.presenter.SchedulePresenter;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "myview")
public class ScheduleView extends VerticalLayout implements View{

	@Autowired
	SchedulePresenter presenter;

	private final MeetingDataProvider provider = new MeetingDataProvider();
	private final Calendar<EventItem> calendar = createCalendar(provider);
	private final PeriodSwitch periodSwitch = createPeriodSwitch();
	
	@PostConstruct
	public void init(){
		presenter.setView(this);
		createGUI();
	}
	
	private void createGUI(){
		addComponent(new Label("Kalendarz wizyt"));
		addComponents(periodSwitch, calendar);
	}
	
	private Calendar<EventItem> createCalendar(MeetingDataProvider provider){
		Calendar<EventItem> calendar = new Calendar<EventItem>(provider);
        calendar.addStyleName("meetings");
        calendar.setWidth(100.0f, Unit.PERCENTAGE);
        calendar.setHeight(100.0f, Unit.PERCENTAGE);
        calendar.setResponsive(true);
        calendar.withMonth(ZonedDateTime.now().getMonth());
        calendar.setItemCaptionAsHtml(true);
        calendar.setContentMode(ContentMode.PREFORMATTED);
        calendar.resetVisibleHoursOfDay();
        
        calendar.setHandler(this::editEvent);
        calendar.setHandler(this::createNewEvent);

        return calendar;
	}
	
    private void editEvent(CalendarComponentEvents.ItemClickEvent event) {
		EventItem item = (EventItem) event.getCalendarItem();
		AbstractEventWindow eventWindow = new EditEventWindow(item.getEvent(), 
				this::performSaveAction, () -> presenter.getAllServices());

		UI.getCurrent().addWindow(eventWindow);
	}
	  
	private void createNewEvent(CalendarComponentEvents.RangeSelectEvent event) {
	    AbstractEventWindow eventWindow = new CreateEventWindow(event.getStart(), 
	    		this::performSaveAction, () -> presenter.getAllServices());
		eventWindow.setDataSupplier(l -> presenter.getUser(l));
	    UI.getCurrent().addWindow(eventWindow);
	}
	
	private void performSaveAction(EventDTO event){
		presenter.createEvent(event);
		presenter.loadData();
		calendar.markAsDirty();
	}
	  
	public void setEventsForCalendar(List<EventDTO> events){
		List<EventItem> items = events.stream().map(e -> new EventItem(e)).collect(Collectors.toList());
		provider.removeAllEvents();
		items.forEach(i -> provider.addItem(i));
	}
	
	private PeriodSwitch createPeriodSwitch(){
		PeriodSwitch periodSwitch = new PeriodSwitch();
		periodSwitch.setClickAction(e ->{
			switch(e){
			case DAY:
		        calendar.withDay(ZonedDateTime.now());
				break;
			case WEEK:
				calendar.withWeek(ZonedDateTime.now());
				break;
			case MONTH:
				calendar.withMonth(ZonedDateTime.now().getMonth());
				break;
			}
		});
		return periodSwitch;
	}
	
    private final class MeetingDataProvider extends BasicItemProvider<EventItem> {

        void removeAllEvents() {
            this.itemList.clear();
            fireItemSetChanged();
        }
    }

}
