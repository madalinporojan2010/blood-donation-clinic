package application.model.repository;

import java.util.List;

import application.model.BloodBank;

/**
 * Generic bloodBank repository models class.
 */
public interface BloodBankRepositoryModels {
    /**
     * Generic findAll method for bloodBank repository.
     * 
     * @return list of all blood banks
     */
    List<BloodBank> findAllBloodBank();

    /**
     * Generic findById method for bloodBank repository.
     * 
     * @param bloodTypeId the given bloodtype id.
     * @return a bloodbank with the given id.
     */
    BloodBank findBloodBankByBloodTypeId(Long bloodTypeId);

    /**
     * Generic save method for bloodBank repository.
     * 
     * @param bloodBank the given bloodBank to save.
     */
    void saveBloodBank(BloodBank bloodBank);

    /**
     * Generic existsById method for bloodBank repository.
     * 
     * @param bloodBankId the given bloodBank id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long bloodBankId);

    /**
     * Generic deleteById method for bloodBank repository.
     * 
     * @param bloodBankId the given bloodBank id to delete.
     */
    void deleteById(Long bloodBankId);

}