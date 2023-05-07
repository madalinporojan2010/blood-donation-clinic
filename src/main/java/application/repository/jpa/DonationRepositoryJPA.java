package application.repository.jpa;

import java.util.List;

import application.model.Donation;
import application.model.repository.DonationRepositoryModels;
import application.repository.jpa.mysql.IDonationRepository;

public class DonationRepositoryJPA implements DonationRepositoryModels {
    private final IDonationRepository iDonationRepository;

    /**
     * IDonationRepository constructor used for repositories initialization.
     * 
     * @param iDonationRepository donation table repository
     */
    public DonationRepositoryJPA(IDonationRepository iDonationRepository) {
        this.iDonationRepository = iDonationRepository;
    }

    @Override
    public List<Donation> findAll() {
        return iDonationRepository.findAll();
    }

    @Override
    public void save(Donation donation) {
        iDonationRepository.save(donation);
    }

    @Override
    public boolean existsById(Long donationId) {
        return iDonationRepository.existsById(donationId);
    }

    @Override
    public void deleteById(Long donationId) {
        iDonationRepository.deleteById(donationId);
    }
}
