package application.service;

import application.model.Schedule;
import application.model.repository.ScheduleRepositoryModels;
import application.model.response.StatusResponse;
import application.repository.jpa.ScheduleRepositoryJPA;
import application.repository.jpa.mysql.IScheduleRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the schedule table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class ScheduleService {

    private final ScheduleRepositoryModels scheduleRepositoryModels;

    /**
     * IScheduleRepository constructor used for repositories initialization.
     * 
     * @param iScheduleRepository Schedule table repository
     */
    public ScheduleService(IScheduleRepository iScheduleRepository) {
        this.scheduleRepositoryModels = new ScheduleRepositoryJPA(iScheduleRepository);
    }

    public ScheduleService(ScheduleRepositoryModels scheduleRepositoryModels) {
        this.scheduleRepositoryModels = scheduleRepositoryModels;
    }

    /**
     * Fetches all the entries from the schedule table.
     *
     * @return Returns fetched Schedules entries in a List.
     */
    public List<Schedule> findAllSchedules() {
        List<Schedule> fetchedSchedule = null;
        try {
            fetchedSchedule = scheduleRepositoryModels.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            scheduleRepositoryModels.save(schedule);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            if (scheduleRepositoryModels.existsById(schedule.getId())) {
                scheduleRepositoryModels.save(schedule);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            if (scheduleRepositoryModels.existsById(scheduleId)) {
                scheduleRepositoryModels.deleteById(scheduleId);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
