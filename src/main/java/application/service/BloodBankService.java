package application.service;

import application.model.BloodBank;
import application.model.response.StatusResponse;
import application.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


import java.util.List;

/**
 * Service class used for the blood bank table.
 * Usual calls to the Repository Class, fetching database table data.
 * */
@Service
public class BloodBankService {
    private final BloodBankRepository bloodBankRepository;

    public BloodBankService(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }


    /**
     * Fetches all the entries from the blood bank table.
     * @return Returns fetched BloodBank entries in a List.
     * */
    public List<BloodBank> findAllBloodBank() {
        List<BloodBank> fetchedBloodBank = null;
        try {
            fetchedBloodBank = bloodBankRepository.findAllBloodBank();
        } catch (Exception e) {
            System.out.println("[BloodBankService/getBloodBank] error: " + e);
        }
        return fetchedBloodBank;
    }


    /**
     * Saves a blood bank request to blood bank table.
     * @param bloodBank Given blood bank request body.
     * @return Success or error message.
     * */
    public StatusResponse saveBloodBank(BloodBank bloodBank) {
        StatusResponse statusResponse = new StatusResponse();
        Long foundId = null;
        try {
            foundId = bloodBankRepository.findBloodBankByBloodTypeId(bloodBank.getBloodType().getId());
            if(foundId != null) {
                statusResponse.setMessage("error: blood type already present");
            } else {
                bloodBankRepository.save(bloodBank);
                statusResponse.setMessage("success");
            }
        } catch (Exception e) {
            System.out.println("[BloodBankService/saveBloodBank] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Updates a blood bank entry with the given request body.
     * @param bloodBank Given blood bank request body.
     * @return Success or error message.
     * */
    public StatusResponse updateBloodBank(BloodBank bloodBank) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            bloodBankRepository.updateBloodBank(bloodBank.getQuantity(), bloodBank.getId());
            statusResponse.setMessage("success");
        } catch (Exception e) {
            System.out.println("[BloodBankService/updateBloodBank] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Deletes a blood bank entry with the given id.
     * @param bloodBankId Given blood bank id.
     * @return Success or error message.
     * */
    public StatusResponse deleteBloodBank(Long bloodBankId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if(bloodBankRepository.existsById(bloodBankId)) {
                bloodBankRepository.deleteBloodBank(bloodBankId);
                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("error");
            }
        } catch (Exception e) {
            System.out.println("[BloodBankService/deleteBloodBank] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }
}
