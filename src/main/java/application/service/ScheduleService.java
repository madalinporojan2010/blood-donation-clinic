package application.service;

import application.model.Schedule;
import application.model.response.StatusResponse;
import application.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    /**
     * Fetches all the entries from the schedule table.
     *
     * @return Returns fetched Schedules entries in a List.
     */
    public List<Schedule> findAllSchedules() {
        List<Schedule> fetchedSchedule = null;
        try {
            fetchedSchedule = scheduleRepository.findAll();
        } catch (Exception e) {
            System.out.println("[ScheduleService/findAllSchedules] error: " + e);
        }
        return fetchedSchedule;
    }


    /**
     * Saves a schedule request to schedule table.
     *
     * @param schedule Given Schedule request body.
     * @return Success or error message.
     */
    public StatusResponse saveSchedule(Schedule schedule) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            scheduleRepository.save(schedule);
            statusResponse.setMessage("success");
        } catch (Exception e) {
            System.out.println("[ScheduleService/saveSchedule] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Updates a schedule entry with the given request body.
     *
     * @param schedule Given schedule request body.
     * @return Success or error message.
     */
    public StatusResponse updateSchedule(Schedule schedule) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (scheduleRepository.existsById(schedule.getId())) {
                scheduleRepository.save(schedule);
                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("error: entry with the given id not present");
            }
        } catch (Exception e) {
            System.out.println("[ScheduleService/updateSchedule] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Deletes a schedule entry with the given id.
     *
     * @param scheduleId Given schedule id.
     * @return Success or error message.
     */
    public StatusResponse deleteSchedule(Long scheduleId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (scheduleRepository.existsById(scheduleId)) {
                scheduleRepository.deleteById(scheduleId);
                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("error");
            }
        } catch (Exception e) {
            System.out.println("[ScheduleService/deleteSchedule] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }
}
