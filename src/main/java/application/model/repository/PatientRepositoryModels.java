package application.model.repository;

import java.util.List;

import application.model.Patient;

/**
 * Generic patient repository models class.
 */
public interface PatientRepositoryModels {
    /**
     * Generic findAll method for patient repository.
     * 
     * @return list of all patients
     */
    List<Patient> findAll();

    /**
     * Generic save method for patient repository.
     * 
     * @param patient the given patient to save.
     * @return the saved patient with the id from DB
     */
    Patient save(Patient patient);

    /**
     * Generic existsById method for patient repository.
     * 
     * @param patientId the given patient id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long patientId);

    /**
     * Generic deleteById method for patient repository.
     * 
     * @param patientId the given patient id to delete.
     */
    void deleteById(Long patientId);

}
