package application.model;


import javax.persistence.*;

@Entity
public class Patient {

    @Id
    private String cnp;

    private int age;
    private String address;
    private String contactPersonName;
    private String name;
    private String getContactPersonPhone;
    private String phone;

    @ManyToOne
    private BloodType bloodType;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGetContactPersonPhone() {
        return getContactPersonPhone;
    }

    public void setGetContactPersonPhone(String getContactPersonPhone) {
        this.getContactPersonPhone = getContactPersonPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
