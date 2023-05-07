package application.model.repository;

import java.util.List;

import application.model.BloodType;

public interface BloodTypeRepositoryModels {

    List<BloodType> findAll();

    void save(BloodType bloodType);

    boolean existsById(Long bloodTypeId);

    void deleteById(Long bloodTypeId);
}
