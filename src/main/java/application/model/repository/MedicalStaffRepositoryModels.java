package application.model.repository;

import java.util.List;

import application.model.MedicalStaff;

public interface MedicalStaffRepositoryModels {

    List<MedicalStaff> findAll();

    void save(MedicalStaff medicalStaff);

    boolean existsById(Long medicalStaffId);

    void deleteById(Long medicalStaffId);

}
