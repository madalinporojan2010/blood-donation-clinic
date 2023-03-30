package application.service;

import application.model.MedicalStaff;
import application.model.response.StatusResponse;
import application.repository.MedicalStaffRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the blood type table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class MedicalStaffService {

    private final MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }


    /**
     * Fetches all the entries from the medicalStaff table.
     *
     * @return Returns fetched MedicalStaffs entries in a List.
     */
    public List<MedicalStaff> findAllMedicalStaffs() {
        List<MedicalStaff> fetchedMedicalStaff = null;
        try {
            fetchedMedicalStaff = medicalStaffRepository.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
        }
        return fetchedMedicalStaff;
    }


    /**
     * Saves a medicalStaff request to medicalStaff table.
     *
     * @param medicalStaff Given MedicalStaff request body.
     * @return Success or error message.
     */
    public StatusResponse saveMedicalStaff(MedicalStaff medicalStaff) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            medicalStaffRepository.save(medicalStaff);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Updates a medicalStaff entry with the given request body.
     *
     * @param medicalStaff Given medicalStaff request body.
     * @return Success or error message.
     */
    public StatusResponse updateMedicalStaff(MedicalStaff medicalStaff) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (medicalStaffRepository.existsById(medicalStaff.getId())) {
                medicalStaffRepository.save(medicalStaff);
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
     * Deletes a medicalStaff entry with the given id.
     *
     * @param medicalStaffId Given medicalStaff id.
     * @return Success or error message.
     */
    public StatusResponse deleteMedicalStaff(Long medicalStaffId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (medicalStaffRepository.existsById(medicalStaffId)) {
                medicalStaffRepository.deleteById(medicalStaffId);
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
