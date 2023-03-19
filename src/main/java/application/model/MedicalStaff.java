package application.model;


import javax.persistence.*;

/**
 * Class used for the medical_staff table entity.
 * */
@Entity
public class MedicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;
    private String specialization;
    private String phone;
    private String cnp;

}
