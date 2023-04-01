package application.service.observe;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application.model.BloodType;
import application.model.Patient;
import application.model.Schedule;
import application.repository.ScheduleRepository;

/**
 * Patient Observer class that implements the Observer interface. It is used for
 * monitoring the changes of the DB patients properties
 * and updating the other tables accordingly.
 */
public class PatientObserver implements Observer {

    private final ScheduleRepository scheduleRepository;

    /**
     * Constructor that initializes the scheduleRepository object.
     * 
     * @param scheduleRepository The schedule repository that is initialized with.
     */
    public PatientObserver(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * Method that updates the Schedule table if the blood type of a patient was
     * changed.
     */
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
