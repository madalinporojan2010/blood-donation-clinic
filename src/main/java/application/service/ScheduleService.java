package application.service;

import application.model.Schedule;
import application.model.response.StatusResponse;
import application.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            Optional<Schedule> entryToBeUpdatedOptional = scheduleRepository.findById(schedule.getId());
            if (entryToBeUpdatedOptional.isPresent()) {
                Schedule entryToBeUpdated = entryToBeUpdatedOptional.get();
                if (schedule.getBloodType() != null)
                    entryToBeUpdated.setBloodType(schedule.getBloodType());
                if (schedule.getArrivalTime() != null)
                    entryToBeUpdated.setArrivalTime(schedule.getArrivalTime());
                if (schedule.getPatient() != null)
                    entryToBeUpdated.setPatient(schedule.getPatient());
                if (schedule.getMedicalStaff() != null)
                    entryToBeUpdated.setMedicalStaff(schedule.getMedicalStaff());
                scheduleRepository.save(entryToBeUpdated);

                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("entry with the given id not present");
            }
        } catch (Exception e) {
            System.out.println("[ServiceSchedule/updateSchedule] error: " + e);
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
