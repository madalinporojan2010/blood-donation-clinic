package application.repository;

import application.model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class used for the blood type table.
 * Usual CRUD database operations.
 */
@Repository
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
}
