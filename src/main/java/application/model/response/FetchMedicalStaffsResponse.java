package application.model.response;

import application.model.MedicalStaff;

import java.util.List;

/**
 * Class used for the blood type endpoint response.
 */
public class FetchMedicalStaffsResponse {
    private List<MedicalStaff> fetchedMedicalStaffs;

    public List<MedicalStaff> getFetchedMedicalStaffs() {
        return fetchedMedicalStaffs;
    }

    public void setFetchedMedicalStaffs(List<MedicalStaff> fetchedMedicalStaffs) {
        this.fetchedMedicalStaffs = fetchedMedicalStaffs;
    }
}
