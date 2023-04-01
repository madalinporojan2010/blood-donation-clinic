package application.service.observe;

import java.util.Observable;

import application.model.Patient;

public class PatientObservable extends Observable {
    private Patient patient;

    public PatientObservable() {
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        setChanged();
        notifyObservers(patient);
    }

    public Patient getPatient() {
        return this.patient;
    }

}
