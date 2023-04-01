package application.service.observe;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application.model.BloodType;
import application.model.Patient;
import application.model.Schedule;
import application.repository.ScheduleRepository;

public class PatientObserver implements Observer {

    private final ScheduleRepository scheduleRepository;

    public PatientObserver(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void update(Observable o, Object arg) {
        Patient patient = (Patient) arg;
        List<Schedule> foundSchedules = scheduleRepository.findAllByPatientId(patient.getId());
        foundSchedules.forEach(schedule -> {
            Schedule newSchedule = schedule.copy();
            newSchedule.setBloodType(patient.getBloodType());
            scheduleRepository.save(newSchedule);
        });
    }
}
