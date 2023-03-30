package application.controller;

import application.model.Donation;
import application.model.request.DonationRequest;
import application.model.response.FetchDonationsResponse;
import application.model.response.StatusResponse;
import application.service.DonationService;
import application.utils.ResponseMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public FetchDonationsResponse findAllDonations(HttpServletResponse response) {
        List<Donation> fetchedDonations = donationService.findAllDonations();
        FetchDonationsResponse fetchDonationResponse = new FetchDonationsResponse();

        if (fetchedDonations != null) {
            fetchDonationResponse.setFetchedDonations(fetchedDonations);
            if (fetchedDonations.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchDonationResponse;
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
    public StatusResponse saveDonation(@RequestBody DonationRequest donationRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = donationService.saveDonation(donationRequest.getDonation());

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
     * @param response        Returned status.
     * @param donationRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/donation PUT"
     */
    @PutMapping("")
    public StatusResponse updateDonation(@RequestBody DonationRequest donationRequest,
            HttpServletResponse response) {
        StatusResponse statusResponse = donationService.updateDonation(donationRequest.getDonation());

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
     * @param response   Returned status.
     * @param donationId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/donation/{donationId} DELETE"
     */
    @DeleteMapping("/{donationId}")
    public StatusResponse deleteDonation(@PathVariable Long donationId, HttpServletResponse response) {
        StatusResponse statusResponse = donationService.deleteDonation(donationId);

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
