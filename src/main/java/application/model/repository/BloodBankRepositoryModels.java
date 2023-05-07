package application.model.repository;

import java.util.List;

import application.model.BloodBank;

public interface BloodBankRepositoryModels {
    List<BloodBank> findAllBloodBank();

    Long findBloodBankByBloodTypeId(Long bloodTypeId);

    void saveBloodBank(BloodBank bloodBank);

    void updateBloodBank(Long quantity, Long bloodBankId);

    void deleteBloodBank(Long bloodBankId);

    boolean existsById(Long bloodBankId);

    void deleteById(Long bloodBankId);

}