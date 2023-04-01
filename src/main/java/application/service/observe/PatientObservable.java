package application.service.observe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import application.model.Patient;

public class PatientObservable extends Observable {
    private List<Patient> patients;

    public PatientObservable() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
        setChanged();
        notifyObservers(patient);
    }

    public void removePatient(Long patientId) {
        int index = -1;

        for (Patient p : patients) {
            index++;
            if (p.getId().equals(patientId)) {
                break;
            }
        }

        this.patients.remove(index);
        setChanged();
        notifyObservers();
    }

    public List<Patient> getPatients() {
        return this.patients;
    }

    public int getIndexOfPatient(Patient patient) {
        int index = -1;
        for (Patient p : patients) {
            index++;
            if (p.getId().equals(patient.getId())) {
                break;
            }
        }
        return index;
    }

}
