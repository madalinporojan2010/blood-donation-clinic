package application.repository.jpa;

import java.util.List;

import application.model.Patient;
import application.model.repository.PatientRepositoryModels;
import application.repository.jpa.mysql.IPatientRepository;

/**
 * JPA repository for patient table.
 */
public class PatientRepositoryJPA implements PatientRepositoryModels {
    private final IPatientRepository iPatientRepository;

    /**
     * IPatientRepository constructor used for repositories initialization.
     * 
     * @param iPatientRepository patient table repository
     */
    public PatientRepositoryJPA(IPatientRepository iPatientRepository) {
        this.iPatientRepository = iPatientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return iPatientRepository.findAll();
    }

    @Override
    public void save(Patient medicalStaff) {
        iPatientRepository.save(medicalStaff);
    }

    @Override
    public boolean existsById(Long medicalStaffId) {
        return iPatientRepository.existsById(medicalStaffId);
    }

    @Override
    public void deleteById(Long medicalStaffId) {
        iPatientRepository.deleteById(medicalStaffId);
    }
}
