package application.repository;

import application.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    @Query(value = "SELECT * FROM blood_clinic.blood_bank", nativeQuery = true)
    List<BloodBank> findAllBloodBank();

}
