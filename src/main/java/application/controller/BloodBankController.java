package application.controller;

import application.model.BloodBank;
import application.model.request.BloodBankRequest;
import application.model.response.FetchBloodBankResponse;
import application.model.response.StatusResponse;
import application.service.BloodBankService;
import application.utils.ResponseMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class used for the blood bank table controller, which manages the requests
 * taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/bloodBank
 */
@RestController
@RequestMapping("/bloodBank")
@CrossOrigin
public class BloodBankController {
    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood bank table.
     * @see "/api/{api_version}/bloodBank GET"
     */
    @GetMapping("")
    public FetchBloodBankResponse findAllBloodBank(HttpServletResponse response) {
        List<BloodBank> fetchedBloodBank = bloodBankService.findAllBloodBank();
        FetchBloodBankResponse fetchBloodBankResponse = new FetchBloodBankResponse();

        if (fetchedBloodBank != null) {
            fetchBloodBankResponse.setFetchedBloodBank(fetchedBloodBank);
            if (fetchedBloodBank.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchBloodBankResponse;
    }

    /**
     * POST method.
     * Saves the blood bank request in the database.
     *
     * @param response         Returned status.
     * @param bloodBankRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodBank POST"
     */
    @PostMapping("")
    public StatusResponse saveBloodBank(@RequestBody BloodBankRequest bloodBankRequest, HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.saveBloodBank(bloodBankRequest.getBloodBank());

        if (statusResponse.getMessage().equals(ResponseMessage.SUCCESS)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }

    /**
     * PUT method.
     * Updates the blood bank request with the given id in the database.
     *
     * @param response         Returned status.
     * @param bloodBankRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodBank PUT"
     */
    @PutMapping("")
    public StatusResponse updateBloodBank(@RequestBody BloodBankRequest bloodBankRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.updateBloodBank(bloodBankRequest.getBloodBank());

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
     * Deletes a blood bank entry from the database, with the given id.
     *
     * @param response    Returned status.
     * @param bloodBankId Blood bank id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodBank/{bloodBankId} DELETE"
     */
    @DeleteMapping("/{bloodBankId}")
    public StatusResponse deleteBloodBank(@PathVariable Long bloodBankId, HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.deleteBloodBank(bloodBankId);

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
