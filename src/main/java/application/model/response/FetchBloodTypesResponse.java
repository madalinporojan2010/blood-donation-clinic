package application.model.response;

import application.model.BloodType;

import java.util.List;

/**
 * Class used for the blood type endpoint response.
 */
public class FetchBloodTypesResponse {
    private List<BloodType> fetchedBloodTypes;

    public List<BloodType> getFetchedBloodTypes() {
        return fetchedBloodTypes;
    }

    public void setFetchedBloodTypes(List<BloodType> fetchedBloodTypes) {
        this.fetchedBloodTypes = fetchedBloodTypes;
    }
}
