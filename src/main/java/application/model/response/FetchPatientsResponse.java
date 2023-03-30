package application.model.response;

import application.model.Patient;

import java.util.List;

/**
 * Class used for the blood type endpoint response.
 */
public class FetchPatientsResponse {
    private List<Patient> fetchedPatients;

    public List<Patient> getFetchedPatients() {
        return fetchedPatients;
    }

    public void setFetchedPatients(List<Patient> fetchedPatients) {
        this.fetchedPatients = fetchedPatients;
    }
}
