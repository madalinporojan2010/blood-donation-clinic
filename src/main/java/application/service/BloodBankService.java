package application.service;

import application.model.BloodBank;
import application.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
