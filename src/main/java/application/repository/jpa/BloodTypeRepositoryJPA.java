package application.repository.jpa;

import java.util.List;

import application.model.BloodType;
import application.model.repository.BloodTypeRepositoryModels;
import application.repository.jpa.mysql.IBloodTypeRepository;

/**
 * JPA repository for bloodType table.
 */
public class BloodTypeRepositoryJPA implements BloodTypeRepositoryModels {
    private final IBloodTypeRepository iBloodTypeRepository;

    /**
     * IBloodTypeRepository constructor used for repositories initialization.
     * 
     * @param iBloodTypeRepository bloodType table repository
     */
    public BloodTypeRepositoryJPA(IBloodTypeRepository iBloodTypeRepository) {
        this.iBloodTypeRepository = iBloodTypeRepository;
    }

    @Override
    public List<BloodType> findAll() {
        return iBloodTypeRepository.findAll();
    }

    @Override
    public void save(BloodType bloodType) {
        iBloodTypeRepository.save(bloodType);
    }

    @Override
    public boolean existsById(Long bloodTypeId) {
        return iBloodTypeRepository.existsById(bloodTypeId);
    }

    @Override
    public void deleteById(Long bloodTypeId) {
        iBloodTypeRepository.deleteById(bloodTypeId);
    }
}
