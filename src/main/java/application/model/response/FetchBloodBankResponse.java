package application.model.response;

import application.model.BloodBank;

import java.util.List;


/**
 * Class used for the blood bank endpoint response.
 */
public class FetchBloodBankResponse {
    private List<BloodBank> fetchedBloodBank;

    public List<BloodBank> getFetchedBloodBank() {
        return fetchedBloodBank;
    }

    public void setFetchedBloodBank(List<BloodBank> fetchedBloodBank) {
        this.fetchedBloodBank = fetchedBloodBank;
    }
}
