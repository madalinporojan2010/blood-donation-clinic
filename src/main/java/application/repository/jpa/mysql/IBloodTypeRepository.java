package application.repository.jpa.mysql;

import application.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the blood type table.
 * Usual CRUD database operations.
 */
public interface IBloodTypeRepository extends JpaRepository<BloodType, Long> {
}
