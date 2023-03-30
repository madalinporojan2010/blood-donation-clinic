package application.service;

import application.model.BloodType;
import application.model.response.StatusResponse;
import application.repository.BloodTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodTypeService {

    private final BloodTypeRepository bloodTypeRepository;

    public BloodTypeService(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }


    /**
     * Fetches all the entries from the bloodType table.
     *
     * @return Returns fetched BloodTypes entries in a List.
     */
    public List<BloodType> findAllBloodTypes() {
        List<BloodType> fetchedBloodType = null;
        try {
            fetchedBloodType = bloodTypeRepository.findAll();
        } catch (Exception e) {
            System.out.println("[BloodTypeService/findAllBloodTypes] error: " + e);
        }
        return fetchedBloodType;
    }


    /**
     * Saves a bloodType request to bloodType table.
     *
     * @param bloodType Given BloodType request body.
     * @return Success or error message.
     */
    public StatusResponse saveBloodType(BloodType bloodType) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            bloodTypeRepository.save(bloodType);
            statusResponse.setMessage("success");
        } catch (Exception e) {
            System.out.println("[BloodTypeService/saveBloodType] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Updates a bloodType entry with the given request body.
     *
     * @param bloodType Given bloodType request body.
     * @return Success or error message.
     */
    public StatusResponse updateBloodType(BloodType bloodType) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (bloodTypeRepository.existsById(bloodType.getId())) {
                bloodTypeRepository.save(bloodType);
                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("error: entry with the given id not present");
            }
        } catch (Exception e) {
            System.out.println("[BloodTypeService/updateBloodType] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }

    /**
     * Deletes a bloodType entry with the given id.
     *
     * @param bloodTypeId Given bloodType id.
     * @return Success or error message.
     */
    public StatusResponse deleteBloodType(Long bloodTypeId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (bloodTypeRepository.existsById(bloodTypeId)) {
                bloodTypeRepository.deleteById(bloodTypeId);
                statusResponse.setMessage("success");
            } else {
                statusResponse.setMessage("error");
            }
        } catch (Exception e) {
            System.out.println("[BloodTypeService/deleteBloodType] error: " + e);
            statusResponse.setMessage("error");
        }
        return statusResponse;
    }
}
