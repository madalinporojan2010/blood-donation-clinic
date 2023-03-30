package application.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class used for the schedule table entity.
 */
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private MedicalStaff medicalStaff;

    @ManyToOne
    private BloodType bloodType;

    private Date arrivalTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
