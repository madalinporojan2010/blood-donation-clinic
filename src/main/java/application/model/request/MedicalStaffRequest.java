package application.model.request;

import application.model.MedicalStaff;

/**
 * Class used for the blood type endpoint request binding.
 */
public class MedicalStaffRequest {
    private MedicalStaff medicalStaff;

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
