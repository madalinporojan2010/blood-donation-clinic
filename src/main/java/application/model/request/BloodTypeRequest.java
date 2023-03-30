package application.model.request;

import application.model.BloodType;

/**
 * Class used for the blood type endpoint request binding.
 */
public class BloodTypeRequest {
    private BloodType bloodType;

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
