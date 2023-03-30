package application.model.request;

import application.model.Donation;

/**
 * Class used for the donation endpoint request binding.
 */
public class DonationRequest {
    private Donation donation;

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }
}
