package application.repository.jpa.mysql;

import application.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the donation table.
 * Usual CRUD database operations.
 */
public interface IDonationRepository extends JpaRepository<Donation, Long> {
}
