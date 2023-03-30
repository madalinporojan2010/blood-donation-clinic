package application.service;

import application.model.Schedule;
import application.model.response.StatusResponse;
import application.repository.ScheduleRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the schedule table.
 * Usual calls to the Repository Class, fetching database table data.
 */
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
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
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
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
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
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
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
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
