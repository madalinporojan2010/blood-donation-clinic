package application.service;

import application.model.BloodBank;
import application.model.repository.BloodBankRepositoryModels;
import application.model.response.StatusResponse;
import application.repository.jpa.BloodBankRepositoryJPA;
import application.repository.jpa.mysql.IBloodBankRepository;
import application.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

/**
 * Service class used for the blood bank table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class BloodBankService {
    private final BloodBankRepositoryModels bloodBankRepositoryModels;

    /**
     * BloodBankService constructor used for repositories initialization.
     * JPA only
     * 
     * @param iBloodBankRepository Blood Bank table repository
     */
    @Autowired(required = true)
    public BloodBankService(IBloodBankRepository iBloodBankRepository) {
        this.bloodBankRepositoryModels = new BloodBankRepositoryJPA(iBloodBankRepository);
    }

    /**
     * BloodBankService constructor used for repositories initialization.
     * Generic.
     * 
     * @param bloodBankRepositoryModels Blood Bank table models
     */
    public BloodBankService(BloodBankRepositoryModels bloodBankRepositoryModels) {
        this.bloodBankRepositoryModels = bloodBankRepositoryModels;
    }

    /**
     * Fetches all the entries from the blood bank table.
     *
     * @return Returns fetched BloodBank entries in a List.
     */
    public List<BloodBank> findAllBloodBank() {
        List<BloodBank> fetchedBloodBank = null;
        try {
            fetchedBloodBank = bloodBankRepositoryModels.findAllBloodBank();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
        }
        return fetchedBloodBank;
    }

    /**
     * Saves a blood bank request to blood bank table.
     *
     * @param bloodBank Given blood bank request body.
     * @return Success or error message.
     */
    public StatusResponse saveBloodBank(BloodBank bloodBank) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            bloodBankRepositoryModels.saveBloodBank(bloodBank);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Updates a blood bank entry with the given request body.
     *
     * @param bloodBank Given blood bank request body.
     * @return Success or error message.
     */
    public StatusResponse updateBloodBank(BloodBank bloodBank) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (bloodBankRepositoryModels.existsById(bloodBank.getId())) {
                bloodBankRepositoryModels.saveBloodBank(bloodBank);
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
     * Deletes a blood bank entry with the given id.
     *
     * @param bloodBankId Given blood bank id.
     * @return Success or error message.
     */
    public StatusResponse deleteBloodBank(Long bloodBankId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (bloodBankRepositoryModels.existsById(bloodBankId)) {
                bloodBankRepositoryModels.deleteById(bloodBankId);
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
