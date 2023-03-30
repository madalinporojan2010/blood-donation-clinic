package application.controller;

import application.model.MedicalStaff;
import application.model.request.MedicalStaffRequest;
import application.model.response.FetchMedicalStaffsResponse;
import application.model.response.StatusResponse;
import application.service.MedicalStaffService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class used for the medical staff table controller, which manages the requests
 * taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/medicalStaff
 */
@RestController
@RequestMapping("/medicalStaff")
@CrossOrigin
public class MedicalStaffController {
    private final MedicalStaffService medicalStaffService;

    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood type table.
     * @see "/api/{api_version}/medicalStaff GET"
     */
    @GetMapping("")
    public ResponseEntity<FetchMedicalStaffsResponse> findAllMedicalStaffs() {
        List<MedicalStaff> fetchedMedicalStaffs = medicalStaffService.findAllMedicalStaffs();
        FetchMedicalStaffsResponse fetchMedicalStaffResponse = new FetchMedicalStaffsResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedMedicalStaffs != null) {
            fetchMedicalStaffResponse.setFetchedMedicalStaffs(fetchedMedicalStaffs);
            if (fetchedMedicalStaffs.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchMedicalStaffResponse, httpStatus);
    }

    /**
     * POST method.
     * Saves the blood type request in the database.
     *
     * @param response            Returned status.
     * @param medicalStaffRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/medicalStaff POST"
     */
    @PostMapping("")
    public ResponseEntity<StatusResponse> saveMedicalStaff(@RequestBody MedicalStaffRequest medicalStaffRequest) {
        StatusResponse statusResponse = medicalStaffService.saveMedicalStaff(medicalStaffRequest.getMedicalStaff());

        HttpStatus httpStatus = HttpStatus.OK;

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(statusResponse, httpStatus);
    }

    /**
     * PUT method.
     * Updates the blood type request with the given id in the database.
     *
     * @param response            Returned status.
     * @param medicalStaffRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/medicalStaff PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updateMedicalStaff(@RequestBody MedicalStaffRequest medicalStaffRequest) {
        StatusResponse statusResponse = medicalStaffService.updateMedicalStaff(medicalStaffRequest.getMedicalStaff());

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
     * @param response       Returned status.
     * @param medicalStaffId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/medicalStaff/{medicalStaffId} DELETE"
     */
    @DeleteMapping("/{medicalStaffId}")
    public ResponseEntity<StatusResponse> deleteMedicalStaff(@PathVariable Long medicalStaffId) {
        StatusResponse statusResponse = medicalStaffService.deleteMedicalStaff(medicalStaffId);

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
