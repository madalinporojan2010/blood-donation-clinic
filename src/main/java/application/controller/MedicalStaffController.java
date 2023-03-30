package application.controller;

import application.model.MedicalStaff;
import application.model.request.MedicalStaffRequest;
import application.model.response.FetchMedicalStaffsResponse;
import application.model.response.StatusResponse;
import application.service.MedicalStaffService;
import application.utils.ResponseMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class used for the blood type table controller, which manages the requests
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
    public FetchMedicalStaffsResponse findAllMedicalStaffs(HttpServletResponse response) {
        List<MedicalStaff> fetchedMedicalStaffs = medicalStaffService.findAllMedicalStaffs();
        FetchMedicalStaffsResponse fetchMedicalStaffResponse = new FetchMedicalStaffsResponse();

        if (fetchedMedicalStaffs != null) {
            fetchMedicalStaffResponse.setFetchedMedicalStaffs(fetchedMedicalStaffs);
            if (fetchedMedicalStaffs.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchMedicalStaffResponse;
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
    public StatusResponse saveMedicalStaff(@RequestBody MedicalStaffRequest medicalStaffRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = medicalStaffService.saveMedicalStaff(medicalStaffRequest.getMedicalStaff());

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
     * @param response            Returned status.
     * @param medicalStaffRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/medicalStaff PUT"
     */
    @PutMapping("")
    public StatusResponse updateMedicalStaff(@RequestBody MedicalStaffRequest medicalStaffRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = medicalStaffService.updateMedicalStaff(medicalStaffRequest.getMedicalStaff());

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
     * @param response       Returned status.
     * @param medicalStaffId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/medicalStaff/{medicalStaffId} DELETE"
     */
    @DeleteMapping("/{medicalStaffId}")
    public StatusResponse deleteMedicalStaff(@PathVariable Long medicalStaffId, HttpServletResponse response) {
        StatusResponse statusResponse = medicalStaffService.deleteMedicalStaff(medicalStaffId);

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
