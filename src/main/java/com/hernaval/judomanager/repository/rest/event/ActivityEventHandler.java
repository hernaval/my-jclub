package com.hernaval.judomanager.repository.rest.event;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.hernaval.judomanager.model.Activity;
import com.hernaval.judomanager.model.Schedule;

@Component
@RepositoryEventHandler(Activity.class)
public class ActivityEventHandler {
	
	/**
	 * To handle the bidirectionnal way in JPA
	 * @param activity
	 */
	@HandleBeforeCreate
	public void handleActivityCreate(Activity activity) {
		for(Schedule schedule : activity.getSchedules()) {
			schedule.setActivity(activity);
		}
	}

}
