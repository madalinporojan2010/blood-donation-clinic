package application.model.repository;

import java.util.List;

import application.model.Patient;

public interface PatientRepositoryModels {

    List<Patient> findAll();

    void save(Patient patient);

    boolean existsById(Long patientId);

    void deleteById(Long patientId);

}
