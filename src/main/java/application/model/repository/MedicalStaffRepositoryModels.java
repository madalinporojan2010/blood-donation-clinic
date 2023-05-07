package application.model.repository;

import java.util.List;

import application.model.MedicalStaff;

/**
 * Generic medicalStaff repository models class.
 */
public interface MedicalStaffRepositoryModels {
    /**
     * Generic findAll method for medicalStaff repository.
     * 
     * @return list of all medicalStaff
     */
    List<MedicalStaff> findAll();

    /**
     * Generic save method for medicalStaff repository.
     * 
     * @param medicalStaff the given medicalStaff to save.
     */
    void save(MedicalStaff medicalStaff);

    /**
     * Generic existsById method for medicalStaff repository.
     * 
     * @param medicalStaffId the given medicalStaff id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long medicalStaffId);

    /**
     * Generic deleteById method for medicalStaff repository.
     * 
     * @param medicalStaffId the given medicalStaff id to delete.
     */
    void deleteById(Long medicalStaffId);

}
