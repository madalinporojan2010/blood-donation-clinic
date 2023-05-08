package application.service.observe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import application.model.Patient;

/**
 * Patient Observable class meant for Patient table, that extends the Observable
 * class.
 */
@SuppressWarnings("deprecation")
public class PatientObservable extends Observable {
    private List<Patient> patients;

    /**
     * Constructor that initializes the patients List.
     */
    public PatientObservable() {
        this.patients = new ArrayList<>();
    }

    /**
     * Constructor that initializes the patients List with a given list.
     * 
     * @param patients the list of patients.
     */
    public PatientObservable(List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Method that adds a patient to the patients list.
     * 
     * @param patient The added patient.
     */
    public void addPatient(Patient patient) {
        this.patients.add(patient);
        setChanged();
        notifyObservers(patient);
    }

    /**
     * Method that removes a patient from the patients list.
     * 
     * @param patientId The id of the patient that need to be removed.
     */
    public void removePatient(Long patientId) {
        int index = 0;

        for (Patient p : patients) {
            if (p.getId() != null && p.getId().equals(patientId)) {
                break;
            }
            index++;
        }

        if (index >= 0 && index < this.patients.size()) {
            this.patients.remove(index);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Method that obtains the index of the stored patient inside the patients List.
     * 
     * @param patientId The id of the patient.
     * @return The index of the patient with the given id.
     */
    public int getIndexOfPatient(Long patientId) {
        int index = 0;
        for (Patient p : patients) {
            if (p.getId() != null && p.getId().equals(patientId)) {
                break;
            }
            index++;
        }
        if (index >= 0 && index < this.patients.size()) {
            return index;
        }
        return -1;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return this.patients;
    }
}
