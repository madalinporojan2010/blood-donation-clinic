package application.repository.jpa.mysql;

import application.model.Schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository class used for the schedule table.
 * Usual CRUD database operations.
 */
public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT * FROM blood_clinic.schedule WHERE patient_id = :patientId", nativeQuery = true)
    List<Schedule> findAllByPatientId(@Param("patientId") Long patientId);
}
