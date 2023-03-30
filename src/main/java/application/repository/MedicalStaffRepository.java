package application.repository;

import application.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the medical staff table.
 * Usual CRUD database operations.
 */
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {
}
