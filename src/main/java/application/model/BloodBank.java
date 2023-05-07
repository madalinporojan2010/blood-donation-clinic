package application.model;

import javax.persistence.*;

/**
 * Class used for the blood_bank table entity.
 */
@Entity
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BloodType bloodType;
    private Long quantity;

    public BloodBank() {
        super();
    }

    public BloodBank(Long id, BloodType bloodType, Long quantity) {
        this.id = id;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
