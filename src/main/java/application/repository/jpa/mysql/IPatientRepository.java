package application.repository.jpa.mysql;

import application.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the patient table.
 * Usual CRUD database operations.
 */
public interface IPatientRepository extends JpaRepository<Patient, Long> {
}
