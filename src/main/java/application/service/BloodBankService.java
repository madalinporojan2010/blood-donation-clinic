package application.service;

import application.model.BloodBank;
import application.model.response.StatusResponse;
import application.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


import java.util.List;

@Service
public class BloodBankService {
    @Autowired
    private BloodBankRepository bloodBankRepository;

    public List<BloodBank> findAllBloodBank() {
        List<BloodBank> fetchedBloodBank = null;
        try {
            fetchedBloodBank = bloodBankRepository.findAllBloodBank();
        } catch (Exception e) {
            System.out.println("[BloodBankService/getBloodBank] error: " + e);
        }
        return fetchedBloodBank;
    }

    public StatusResponse saveBloodBank(BloodBank bloodBank) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            bloodBankRepository.save(bloodBank);
            statusResponse.setMessage("success");
        } catch (Exception e) {
            System.out.println("[BloodBankService/saveBloodBank] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

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

    public StatusResponse deleteBloodBank(Long bloodBankId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            bloodBankRepository.deleteBloodBank(bloodBankId);
            statusResponse.setMessage("success");
        } catch (Exception e) {
            System.out.println("[BloodBankService/deleteBloodBank] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }
}
