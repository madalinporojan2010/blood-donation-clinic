package application.model.repository;

import java.util.List;

import application.model.Donation;

/**
 * Generic donation repository models class.
 */
public interface DonationRepositoryModels {

    /**
     * Generic findAll method for donation repository.
     * 
     * @return list of all donations
     */
    List<Donation> findAll();

    /**
     * Generic save method for donation repository.
     * 
     * @param donation the given donation to save.
     */
    void save(Donation donation);

    /**
     * Generic existsById method for donation repository.
     * 
     * @param donationId the given donation id to search for.
     * @return true if found else false.
     */
    boolean existsById(Long donationId);

    /**
     * Generic deleteById method for donation repository.
     * 
     * @param donationId the given donation id to delete.
     */
    void deleteById(Long donationId);
}
