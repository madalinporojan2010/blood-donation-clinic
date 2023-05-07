package application.model.repository;

import java.util.List;

import application.model.Schedule;

/**
 * Generic schedule repository models class.
 */
public interface ScheduleRepositoryModels {
    /**
     * Generic findAll method for schedule repository.
     * 
     * @return list of all schedules types
     */
    List<Schedule> findAll();

    /**
     * Generic save method for schedule repository.
     * 
     * @param schedule the given schedule to save.
     */
    void save(Schedule schedule);

    /**
     * Generic existsById method for schedule repository.
     * 
     * @param scheduleId the given schedule id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long scheduleId);

    /**
     * Generic deleteById method for schedule repository.
     * 
     * @param scheduleId the given schedule id to delete.
     */
    void deleteById(Long scheduleId);

    /**
     * Generic findAllByPatientId method for schedule repository.
     * 
     * @param patientId the given patient id to search for.
     * @return list of all schedules with the given patient id.
     */
    List<Schedule> findAllByPatientId(Long patientId);
}
