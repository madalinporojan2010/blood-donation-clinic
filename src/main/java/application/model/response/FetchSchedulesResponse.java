package application.model.response;

import application.model.Schedule;

import java.util.List;

/**
 * Class used for the schedule endpoint response.
 */
public class FetchSchedulesResponse {
    private List<Schedule> fetchedSchedules;

    public List<Schedule> getFetchedSchedules() {
        return fetchedSchedules;
    }

    public void setFetchedSchedules(List<Schedule> fetchedSchedules) {
        this.fetchedSchedules = fetchedSchedules;
    }
}
