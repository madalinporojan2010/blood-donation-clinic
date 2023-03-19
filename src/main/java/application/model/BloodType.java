package application.model;

import javax.persistence.*;

/**
 * Class used for the blood_type table entity.
 * */
@Entity
public class BloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bloodType;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
