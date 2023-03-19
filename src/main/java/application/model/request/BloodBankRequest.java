package application.model.request;

import application.model.BloodBank;

/**
 * Class used for the blood bank endpoint request binding.
 * */
public class BloodBankRequest {
    private BloodBank bloodBank;

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}
