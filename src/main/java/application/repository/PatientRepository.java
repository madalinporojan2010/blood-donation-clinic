package application.repository;

import application.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the patient table.
 * Usual CRUD database operations.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
