package application.repository.jpa;

import java.util.List;

import application.model.MedicalStaff;
import application.model.repository.MedicalStaffRepositoryModels;
import application.repository.jpa.mysql.IMedicalStaffRepository;

/**
 * JPA repository for medicalStaff table.
 */
public class MedicalStaffRepositoryJPA implements MedicalStaffRepositoryModels {
    private final IMedicalStaffRepository iMedicalStaffRepository;

    /**
     * IMedicalStaffRepository constructor used for repositories initialization.
     * 
     * @param iMedicalStaffRepository medicalStaff table repository
     */
    public MedicalStaffRepositoryJPA(IMedicalStaffRepository iMedicalStaffRepository) {
        this.iMedicalStaffRepository = iMedicalStaffRepository;
    }

    @Override
    public List<MedicalStaff> findAll() {
        return iMedicalStaffRepository.findAll();
    }

    @Override
    public void save(MedicalStaff medicalStaff) {
        iMedicalStaffRepository.save(medicalStaff);
    }

    @Override
    public boolean existsById(Long medicalStaffId) {
        return iMedicalStaffRepository.existsById(medicalStaffId);
    }

    @Override
    public void deleteById(Long medicalStaffId) {
        iMedicalStaffRepository.deleteById(medicalStaffId);
    }
}
