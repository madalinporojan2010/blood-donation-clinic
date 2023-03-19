package application.model.response;

import application.model.BloodBank;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FetchBloodBankResponse {
    private List<BloodBank> fetchedBloodBank;

    public List<BloodBank> getFetchedBloodBank() {
        return fetchedBloodBank;
    }

    public void setFetchedBloodBank(List<BloodBank> fetchedBloodBank) {
        this.fetchedBloodBank = fetchedBloodBank;
    }
}
