package application.model.request;

import application.model.Patient;

/**
 * Class used for the patient endpoint request binding.
 */
public class PatientRequest {
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
