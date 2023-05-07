package application.model.repository;

import java.util.List;

import application.model.Schedule;

public interface ScheduleRepositoryModels {

    List<Schedule> findAll();

    void save(Schedule schedule);

    boolean existsById(Long scheduleId);

    void deleteById(Long scheduleId);

    List<Schedule> findAllByPatientId(Long patientId);
}
