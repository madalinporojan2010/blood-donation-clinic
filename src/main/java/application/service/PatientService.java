package application.service;

import application.model.Patient;
import application.model.response.StatusResponse;
import application.repository.PatientRepository;
import application.utils.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class used for the blood type table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Fetches all the entries from the patient table.
     *
     * @return Returns fetched Patients entries in a List.
     */
    public List<Patient> findAllPatients() {
        List<Patient> fetchedPatient = null;
        try {
            fetchedPatient = patientRepository.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
        }
        return fetchedPatient;
    }

    /**
     * Saves a patient request to patient table.
     *
     * @param patient Given Patient request body.
     * @return Success or error message.
     */
    public StatusResponse savePatient(Patient patient) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            patientRepository.save(patient);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass().getName(), e.getStackTrace()[0].getMethodName(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Updates a patient entry with the given request body.
     *
     * @param patient Given patient request body.
     * @return Success or error message.
     */
    public StatusResponse updatePatient(Patient patient) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (patientRepository.existsById(patient.getId())) {
                patientRepository.save(patient);
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
     * Deletes a patient entry with the given id.
     *
     * @param patientId Given patient id.
     * @return Success or error message.
     */
    public StatusResponse deletePatient(Long patientId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (patientRepository.existsById(patientId)) {
                patientRepository.deleteById(patientId);
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
