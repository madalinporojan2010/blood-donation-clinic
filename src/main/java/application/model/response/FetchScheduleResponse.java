package application.model.response;

import application.model.Schedule;

import java.util.List;

public class FetchScheduleResponse {
    private List<Schedule> schedules;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedule) {
        this.schedules = schedule;
    }
}
