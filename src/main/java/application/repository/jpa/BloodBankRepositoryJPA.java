package application.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import application.model.BloodBank;
import application.model.repository.BloodBankRepositoryModels;
import application.repository.jpa.mysql.IBloodBankRepository;

/**
 * JPA repository for bloodBank table.
 */
@Repository
public class BloodBankRepositoryJPA implements BloodBankRepositoryModels {
    private final IBloodBankRepository iBloodBankRepository;

    /**
     * IBloodBankRepository constructor used for repositories initialization.
     * 
     * @param iBloodBankRepository bloodBank table repository
     */
    public BloodBankRepositoryJPA(IBloodBankRepository iBloodBankRepository) {
        this.iBloodBankRepository = iBloodBankRepository;
    }

    @Override
    public List<BloodBank> findAllBloodBank() {
        return iBloodBankRepository.findAllBloodBank();
    }

    @Override
    public BloodBank findBloodBankByBloodTypeId(Long bloodTypeId) {
        return iBloodBankRepository.findBloodBankByBloodTypeId(bloodTypeId);
    }

    @Override
    public void saveBloodBank(BloodBank bloodBank) {
        iBloodBankRepository.save(bloodBank);
    }

    @Override
    public boolean existsById(Long bloodBankId) {
        return iBloodBankRepository.existsById(bloodBankId);
    }

    @Override
    public void deleteById(Long bloodBankId) {
        iBloodBankRepository.deleteById(bloodBankId);
    }

}
