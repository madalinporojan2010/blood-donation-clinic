package application.repository;

import application.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class used for the schedule table.
 * Usual CRUD database operations.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
