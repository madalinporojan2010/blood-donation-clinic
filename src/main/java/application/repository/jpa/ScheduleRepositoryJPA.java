package application.repository.jpa;

import java.util.List;

import application.model.Schedule;
import application.model.repository.ScheduleRepositoryModels;
import application.repository.jpa.mysql.IScheduleRepository;

public class ScheduleRepositoryJPA implements ScheduleRepositoryModels {
    private final IScheduleRepository iScheduleRepository;

    /**
     * IScheduleRepository constructor used for repositories initialization.
     * 
     * @param iScheduleRepository schedule table repository
     */
    public ScheduleRepositoryJPA(IScheduleRepository iScheduleRepository) {
        this.iScheduleRepository = iScheduleRepository;
    }

    @Override
    public List<Schedule> findAll() {
        return iScheduleRepository.findAll();
    }

    @Override
    public void save(Schedule schedule) {
        iScheduleRepository.save(schedule);
    }

    @Override
    public boolean existsById(Long scheduleId) {
        return iScheduleRepository.existsById(scheduleId);
    }

    @Override
    public void deleteById(Long scheduleId) {
        iScheduleRepository.deleteById(scheduleId);
    }

    @Override
    public List<Schedule> findAllByPatientId(Long patientId) {
        return iScheduleRepository.findAllByPatientId(patientId);
    }
}
