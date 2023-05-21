package application.controller;

import application.model.Patient;
import application.model.request.PatientRequest;
import application.model.response.FetchPatientsResponse;
import application.model.response.SaveResponse;
import application.model.response.StatusResponse;
import application.service.PatientService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class used for the patient table controller, which manages the requests
 * taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/patient
 */
@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {
    private final PatientService patientService;

    /**
     * PatientController constructor
     *
     * @param patientService PatientService instance
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood type table.
     * @see "/api/{api_version}/patient GET"
     */
    @GetMapping("")
    public ResponseEntity<FetchPatientsResponse> findAllPatients() {
        List<Patient> fetchedPatients = patientService.findAllPatients();
        FetchPatientsResponse fetchPatientResponse = new FetchPatientsResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedPatients != null) {
            fetchPatientResponse.setFetchedPatients(fetchedPatients);
            if (fetchedPatients.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchPatientResponse, httpStatus);
    }

    /**
     * POST method.
     * Saves the blood type request in the database.
     *
     * @param response       Returned status.
     * @param patientRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/patient POST"
     */
    @PostMapping("")
    public ResponseEntity<SaveResponse<Patient>> savePatient(@RequestBody PatientRequest patientRequest) {
        SaveResponse<Patient> saveResponse = patientService.savePatient(patientRequest.getPatient());

        HttpStatus httpStatus = HttpStatus.OK;

        if (saveResponse.getEntity() != null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(saveResponse, httpStatus);
    }

    /**
     * PUT method.
     * Updates the blood type request with the given id in the database.
     *
     * @param response       Returned status.
     * @param patientRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/patient PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updatePatient(@RequestBody PatientRequest patientRequest) {
        StatusResponse statusResponse = patientService.updatePatient(patientRequest.getPatient());

        HttpStatus httpStatus = HttpStatus.OK;

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            httpStatus = HttpStatus.OK;
        } else if (statusResponse.getMessage().equals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(statusResponse, httpStatus);
    }

    /**
     * PUT method.
     * Deletes a blood type entry from the database, with the given id.
     *
     * @param response  Returned status.
     * @param patientId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/patient/{patientId} DELETE"
     */
    @DeleteMapping("/{patientId}")
    public ResponseEntity<StatusResponse> deletePatient(@PathVariable Long patientId) {
        StatusResponse statusResponse = patientService.deletePatient(patientId);

        HttpStatus httpStatus = HttpStatus.OK;

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            httpStatus = HttpStatus.OK;
        } else if (statusResponse.getMessage().equals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(statusResponse, httpStatus);
    }
}
