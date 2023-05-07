package application.service;

import application.model.Donation;
import application.model.repository.DonationRepositoryModels;
import application.model.response.StatusResponse;
import application.repository.jpa.DonationRepositoryJPA;
import application.repository.jpa.mysql.IDonationRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the donation table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class DonationService {

    private final DonationRepositoryModels donationRepositoryModels;

    /**
     * DonationRepository constructor used for repositories initialization.
     * 
     * @param iDonationRepository Schedule table repository
     */
    public DonationService(IDonationRepository iDonationRepository) {
        this.donationRepositoryModels = new DonationRepositoryJPA(iDonationRepository);
    }

    public DonationService(DonationRepositoryModels donationRepositoryModels) {
        this.donationRepositoryModels = donationRepositoryModels;
    }

    /**
     * Fetches all the entries from the donation table.
     *
     * @return Returns fetched Donations entries in a List.
     */
    public List<Donation> findAllDonations() {
        List<Donation> fetchedDonation = null;
        try {
            fetchedDonation = donationRepositoryModels.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
        }
        return fetchedDonation;
    }

    /**
     * Saves a donation request to donation table.
     *
     * @param donation Given Donation request body.
     * @return Success or error message.
     */
    public StatusResponse saveDonation(Donation donation) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            donationRepositoryModels.save(donation);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Updates a donation entry with the given request body.
     *
     * @param donation Given donation request body.
     * @return Success or error message.
     */
    public StatusResponse updateDonation(Donation donation) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (donationRepositoryModels.existsById(donation.getId())) {
                donationRepositoryModels.save(donation);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Deletes a donation entry with the given id.
     *
     * @param donationId Given donation id.
     * @return Success or error message.
     */
    public StatusResponse deleteDonation(Long donationId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (donationRepositoryModels.existsById(donationId)) {
                donationRepositoryModels.deleteById(donationId);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
