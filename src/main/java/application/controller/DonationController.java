package application.controller;

import application.model.Donation;
import application.model.request.DonationRequest;
import application.model.response.FetchDonationsResponse;
import application.model.response.StatusResponse;
import application.service.DonationService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class used for the donation table controller, which manages the requests
 * taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/donation
 */
@RestController
@RequestMapping("/donation")
@CrossOrigin
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood type table.
     * @see "/api/{api_version}/donation GET"
     */
    @GetMapping("")
    public ResponseEntity<FetchDonationsResponse> findAllDonations() {
        List<Donation> fetchedDonations = donationService.findAllDonations();
        FetchDonationsResponse fetchDonationResponse = new FetchDonationsResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedDonations != null) {
            fetchDonationResponse.setFetchedDonations(fetchedDonations);
            if (fetchedDonations.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchDonationResponse, httpStatus);
    }

    /**
     * POST method.
     * Saves the blood type request in the database.
     *
     * @param response        Returned status.
     * @param donationRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/donation POST"
     */
    @PostMapping("")
    public ResponseEntity<StatusResponse> saveDonation(@RequestBody DonationRequest donationRequest) {
        StatusResponse statusResponse = donationService.saveDonation(donationRequest.getDonation());

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
     * @param response        Returned status.
     * @param donationRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/donation PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updateDonation(@RequestBody DonationRequest donationRequest) {
        StatusResponse statusResponse = donationService.updateDonation(donationRequest.getDonation());

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
     * @param response   Returned status.
     * @param donationId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/donation/{donationId} DELETE"
     */
    @DeleteMapping("/{donationId}")
    public ResponseEntity<StatusResponse> deleteDonation(@PathVariable Long donationId) {
        StatusResponse statusResponse = donationService.deleteDonation(donationId);

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
