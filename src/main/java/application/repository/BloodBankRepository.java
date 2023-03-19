package application.repository;

import application.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository class used for the blood bank table.
 * Usual CRUD database operations.
 * */
@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    /**
     * Retrieves all the entries from blood_bank table.
     * @return Returns a List of BloodBank.
     * */
    @Query(value = "SELECT * FROM blood_clinic.blood_bank", nativeQuery = true)
    List<BloodBank> findAllBloodBank();

    /**
     * Retrieves the blood_bank table entry that has the given bloodTypeId
     * @param bloodTypeId Given bloodTypeId.
     * @return Returns the found bloodTypeId.
     * */
    @Query(value = "SELECT b.blood_type_id FROM blood_clinic.blood_bank b WHERE b.blood_type_id = :bloodTypeId", nativeQuery = true)
    Long findBloodBankByBloodTypeId(@Param("bloodTypeId") Long bloodTypeId);


    /**
     * Inserts a blood bank entry into the database.
     * @param quantity Given quantity.
     * @param bloodTypeId Given bloodTypeId.
     * */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO blood_clinic.blood_bank VALUES (:quantity, :bloodTypeId)", nativeQuery = true)
    void saveBloodBank(@Param("quantity") Long quantity, @Param("bloodTypeId") Long bloodTypeId);

    /**
     * Updates a blood bank entry from the database.
     * @param quantity Given quantity.
     * @param bloodBankId Given bloodBankId.
     * */
    @Transactional
    @Modifying
    @Query(value = "UPDATE blood_clinic.blood_bank b SET b.quantity = :quantity WHERE b.id = :bloodBankId", nativeQuery = true)
    void updateBloodBank(@Param("quantity") Long quantity, @Param("bloodBankId") Long bloodBankId);

    /**
     * Deletes a blood bank entry from the database.
     * @param bloodBankId Given bloodBankId.
     * */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM blood_clinic.blood_bank b WHERE b.id = :bloodBankId", nativeQuery = true)
    void deleteBloodBank(@Param("bloodBankId") Long bloodBankId);


}
