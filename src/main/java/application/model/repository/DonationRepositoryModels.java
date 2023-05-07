package application.model.repository;

import java.util.List;

import application.model.Donation;

public interface DonationRepositoryModels {

    List<Donation> findAll();

    void save(Donation donation);

    boolean existsById(Long donationId);

    void deleteById(Long donationId);
}
