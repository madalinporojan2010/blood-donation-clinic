package application.service;

import application.model.BloodBank;
import application.model.response.StatusResponse;
import application.repository.BloodBankRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.*;

import java.util.List;

/**
 * Service class used for the blood bank table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class BloodBankService {
    private final BloodBankRepository bloodBankRepository;

    public BloodBankService(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }

    /**
     * Fetches all the entries from the blood bank table.
     *
     * @return Returns fetched BloodBank entries in a List.
     */
    public List<BloodBank> findAllBloodBank() {
        List<BloodBank> fetchedBloodBank = null;
        try {
            fetchedBloodBank = bloodBankRepository.findAllBloodBank();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
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
            bloodBankRepository.save(bloodBank);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
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
            if (bloodBankRepository.existsById(bloodBank.getId())) {
                bloodBankRepository.save(bloodBank);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
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
            if (bloodBankRepository.existsById(bloodBankId)) {
                bloodBankRepository.deleteById(bloodBankId);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
