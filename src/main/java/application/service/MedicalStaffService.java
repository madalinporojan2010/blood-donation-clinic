package application.service;

import application.model.MedicalStaff;
import application.model.repository.MedicalStaffRepositoryModels;
import application.model.response.StatusResponse;
import application.repository.jpa.MedicalStaffRepositoryJPA;
import application.repository.jpa.mysql.IMedicalStaffRepository;
import application.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the medical staff table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class MedicalStaffService {

    private final MedicalStaffRepositoryModels medicalStaffRepositoryModels;

    /**
     * MedicalStaffService constructor used for repositories initialization.
     * JPA only.
     * 
     * @param iMedicalStaffRepository medicalStaff table repository
     */
    @Autowired(required = true)
    public MedicalStaffService(IMedicalStaffRepository iMedicalStaffRepository) {
        this.medicalStaffRepositoryModels = new MedicalStaffRepositoryJPA(iMedicalStaffRepository);
    }

    /**
     * MedicalStaffService constructor used for repositories initialization.
     * Generic.
     * 
     * @param medicalStaffRepositoryModels medicalStaff table models
     */
    public MedicalStaffService(MedicalStaffRepositoryModels medicalStaffRepositoryModels) {
        this.medicalStaffRepositoryModels = medicalStaffRepositoryModels;
    }

    /**
     * Fetches all the entries from the medicalStaff table.
     *
     * @return Returns fetched MedicalStaffs entries in a List.
     */
    public List<MedicalStaff> findAllMedicalStaffs() {
        List<MedicalStaff> fetchedMedicalStaff = null;
        try {
            fetchedMedicalStaff = medicalStaffRepositoryModels.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            medicalStaffRepositoryModels.save(medicalStaff);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            if (medicalStaffRepositoryModels.existsById(medicalStaff.getId())) {
                medicalStaffRepositoryModels.save(medicalStaff);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
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
            if (medicalStaffRepositoryModels.existsById(medicalStaffId)) {
                medicalStaffRepositoryModels.deleteById(medicalStaffId);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
