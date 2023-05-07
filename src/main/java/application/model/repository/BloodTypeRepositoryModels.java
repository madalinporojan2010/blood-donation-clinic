package application.model.repository;

import java.util.List;

import application.model.BloodType;

/**
 * Generic bloodTypes repository models class.
 */
public interface BloodTypeRepositoryModels {
    /**
     * Generic findAll method for bloodType repository.
     * 
     * @return list of all blood types
     */
    List<BloodType> findAll();

    /**
     * Generic save method for bloodType repository.
     * 
     * @param bloodType the given bloodType to save.
     */
    void save(BloodType bloodType);

    /**
     * Generic existsById method for bloodType repository.
     * 
     * @param bloodTypeId the given bloodType id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long bloodTypeId);

    /**
     * Generic deleteById method for bloodBank repository.
     * 
     * @param bloodTypeId the given bloodType id to delete.
     */
    void deleteById(Long bloodTypeId);
}
