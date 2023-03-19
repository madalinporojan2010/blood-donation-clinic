package application.model;

import javax.persistence.*;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cause;
    private float raisedAmmount;
    private String phone;

    @ManyToOne
    private MedicalStaff medicalStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public float getRaisedAmmount() {
        return raisedAmmount;
    }

    public void setRaisedAmmount(float raisedAmmount) {
        this.raisedAmmount = raisedAmmount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
