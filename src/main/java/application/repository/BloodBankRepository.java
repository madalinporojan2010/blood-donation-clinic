package application.repository;

import application.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    @Query(value = "SELECT * FROM blood_clinic.blood_bank", nativeQuery = true)
    List<BloodBank> findAllBloodBank();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO blood_clinic.blood_bank VALUES (:quantity, :bloodTypeId)", nativeQuery = true)
    void saveBloodBank(@Param("quantity") Long quantity, @Param("bloodTypeId") Long bloodTypeId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE blood_clinic.blood_bank b SET b.quantity = :quantity WHERE b.id = :bloodBankId", nativeQuery = true)
    void updateBloodBank(@Param("quantity") Long quantity, @Param("bloodBankId") Long bloodBankId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM blood_clinic.blood_bank b WHERE b.id = :bloodBankId", nativeQuery = true)
    void deleteBloodBank(@Param("bloodBankId") Long bloodBankId);


}
