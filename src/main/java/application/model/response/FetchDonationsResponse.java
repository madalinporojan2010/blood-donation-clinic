package application.model.response;

import application.model.Donation;

import java.util.List;

/**
 * Class used for the blood type endpoint response.
 */
public class FetchDonationsResponse {
    private List<Donation> fetchedDonations;

    public List<Donation> getFetchedDonations() {
        return fetchedDonations;
    }

    public void setFetchedDonations(List<Donation> fetchedDonations) {
        this.fetchedDonations = fetchedDonations;
    }
}
