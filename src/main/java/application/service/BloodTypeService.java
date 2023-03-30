package application.service;

import application.model.BloodType;
import application.model.response.StatusResponse;
import application.repository.BloodTypeRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the blood type table.
 * Usual calls to the Repository Class, fetching database table data.
 */
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
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
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
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
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
