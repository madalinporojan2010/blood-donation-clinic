package application.model.request;

import application.model.Schedule;

/**
 * Class used for the schedule endpoint request binding.
 */
public class ScheduleRequest {
    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
