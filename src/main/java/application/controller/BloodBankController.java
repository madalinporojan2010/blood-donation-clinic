package application.controller;

import application.model.BloodBank;
import application.model.request.BloodBankRequest;
import application.model.response.FetchBloodBankResponse;
import application.model.response.StatusResponse;
import application.service.BloodBankService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<FetchBloodBankResponse> findAllBloodBank() {
        List<BloodBank> fetchedBloodBank = bloodBankService.findAllBloodBank();
        FetchBloodBankResponse fetchBloodBankResponse = new FetchBloodBankResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedBloodBank != null) {
            fetchBloodBankResponse.setFetchedBloodBank(fetchedBloodBank);
            if (fetchedBloodBank.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchBloodBankResponse, httpStatus);
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
    public ResponseEntity<StatusResponse> saveBloodBank(@RequestBody BloodBankRequest bloodBankRequest) {
        StatusResponse statusResponse = bloodBankService.saveBloodBank(bloodBankRequest.getBloodBank());

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
     * Updates the blood bank request with the given id in the database.
     *
     * @param response         Returned status.
     * @param bloodBankRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodBank PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updateBloodBank(@RequestBody BloodBankRequest bloodBankRequest) {
        StatusResponse statusResponse = bloodBankService.updateBloodBank(bloodBankRequest.getBloodBank());

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
     * Deletes a blood bank entry from the database, with the given id.
     *
     * @param response    Returned status.
     * @param bloodBankId Blood bank id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodBank/{bloodBankId} DELETE"
     */
    @DeleteMapping("/{bloodBankId}")
    public ResponseEntity<StatusResponse> deleteBloodBank(@PathVariable Long bloodBankId) {
        StatusResponse statusResponse = bloodBankService.deleteBloodBank(bloodBankId);

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
