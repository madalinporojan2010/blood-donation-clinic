package application.controller;

import application.model.Patient;
import application.model.request.PatientRequest;
import application.model.response.FetchPatientsResponse;
import application.model.response.StatusResponse;
import application.service.PatientService;
import application.utils.ResponseMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public FetchPatientsResponse findAllPatients(HttpServletResponse response) {
        List<Patient> fetchedPatients = patientService.findAllPatients();
        FetchPatientsResponse fetchPatientResponse = new FetchPatientsResponse();

        if (fetchedPatients != null) {
            fetchPatientResponse.setFetchedPatients(fetchedPatients);
            if (fetchedPatients.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchPatientResponse;
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
    public StatusResponse savePatient(@RequestBody PatientRequest patientRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = patientService.savePatient(patientRequest.getPatient());

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
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
    public StatusResponse updatePatient(@RequestBody PatientRequest patientRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = patientService.updatePatient(patientRequest.getPatient());

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (statusResponse.getMessage().equals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
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
    public StatusResponse deletePatient(@PathVariable Long patientId, HttpServletResponse response) {
        StatusResponse statusResponse = patientService.deletePatient(patientId);

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (statusResponse.getMessage().equals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }
}
