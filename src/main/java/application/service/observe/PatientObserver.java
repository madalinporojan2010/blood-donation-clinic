package application.service.observe;

import java.util.Observable;
import java.util.Observer;

import application.model.Patient;
import application.model.Schedule;
import application.model.repository.ScheduleRepositoryModels;

/**
 * Patient Observer class that implements the Observer interface. It is used for
 * monitoring the changes of the DB patients properties
 * and updating the other tables accordingly.
 */
@SuppressWarnings("deprecation")
public class PatientObserver implements Observer {

    private ScheduleRepositoryModels scheduleRepositoryModels;

    /**
     * Constructor that initializes the scheduleRepository object.
     * 
     * @param scheduleRepository The schedule repository that is initialized with.
     */
    public PatientObserver(ScheduleRepositoryModels scheduleRepositoryModels) {
        this.scheduleRepositoryModels = scheduleRepositoryModels;
    }

    public void setScheduleRepositoryModels(ScheduleRepositoryModels scheduleRepositoryModels) {
        this.scheduleRepositoryModels = scheduleRepositoryModels;
    }

    /**
     * Method that updates the Schedule table if the blood type of a patient was
     * changed.
     */
    @Override
    public void update(Observable o, Object arg) {
        Patient patient = (Patient) arg;
        scheduleRepositoryModels.findAllByPatientId(patient.getId()).forEach(schedule -> {
            Schedule newSchedule = schedule.copy();
            newSchedule.setBloodType(patient.getBloodType());
            scheduleRepositoryModels.save(newSchedule);
        });
    }
}
