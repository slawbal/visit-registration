package com.sb.registrationweb.components.calendar;

import com.sb.registrationweb.components.calendar.PeriodSwitch.PeriodSwitchAction.Action;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

public class PeriodSwitch extends HorizontalLayout{

	private Button day = createButton("Day", Action.DAY);
	private Button week = createButton("Week", Action.WEEK);
	private Button month = createButton("Month", Action.MONTH);
	
	private PeriodSwitchAction clickAction;
	
	public PeriodSwitch(){
		addComponents(day, week, month);
		setSpacing(false);
	}
	
	public void setClickAction(PeriodSwitchAction clickAction) {
		this.clickAction = clickAction;
	}

	private Button createButton(String title, Action action){
		Button button = new NativeButton(title);
		button.addClickListener(c -> {
			if(clickAction != null){
				clickAction.onCliskAction(action);
				button.setStyleName("v-nativebutton v-pressed");
			}
		});
		return button;
	}
	
	public interface PeriodSwitchAction{
		public enum Action{
			DAY, WEEK, MONTH;
		}
		public void onCliskAction(Action action);
	}
}
