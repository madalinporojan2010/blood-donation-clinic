package application.model;


import javax.persistence.*;

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
