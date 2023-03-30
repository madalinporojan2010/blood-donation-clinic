package application.controller;

import application.model.BloodType;
import application.model.request.BloodTypeRequest;
import application.model.response.FetchBloodTypesResponse;
import application.model.response.StatusResponse;
import application.service.BloodTypeService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class used for the blood type table controller, which manages the requests taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/bloodType
 */
@RestController
@RequestMapping("/bloodType")
@CrossOrigin
public class BloodTypeController {
    private final BloodTypeService bloodTypeService;

    public BloodTypeController(BloodTypeService bloodTypeService) {
        this.bloodTypeService = bloodTypeService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood type table.
     * @see "/api/{api_version}/bloodType GET"
     */
    @GetMapping("")
    public FetchBloodTypesResponse findAllBloodTypes(HttpServletResponse response) {
        List<BloodType> fetchedBloodTypes = bloodTypeService.findAllBloodTypes();
        FetchBloodTypesResponse fetchBloodTypeResponse = new FetchBloodTypesResponse();

        if (fetchedBloodTypes != null) {
            fetchBloodTypeResponse.setFetchedBloodTypes(fetchedBloodTypes);
            if (fetchedBloodTypes.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchBloodTypeResponse;
    }


    /**
     * POST method.
     * Saves the blood type request in the database.
     *
     * @param response         Returned status.
     * @param bloodTypeRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodType POST"
     */
    @PostMapping("")
    public StatusResponse saveBloodType(@RequestBody BloodTypeRequest bloodTypeRequest, HttpServletResponse response) {
        StatusResponse statusResponse = bloodTypeService.saveBloodType(bloodTypeRequest.getBloodType());

        if (statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (statusResponse.getMessage().toLowerCase().contains("id already present")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }


    /**
     * PUT method.
     * Updates the blood type request with the given id in the database.
     *
     * @param response         Returned status.
     * @param bloodTypeRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodType PUT"
     */
    @PutMapping("")
    public StatusResponse updateBloodType(@RequestBody BloodTypeRequest bloodTypeRequest, HttpServletResponse response) {
        StatusResponse statusResponse = bloodTypeService.updateBloodType(bloodTypeRequest.getBloodType());

        if (statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (statusResponse.getMessage().toLowerCase().contains("id not present")) {
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
     * @param response    Returned status.
     * @param bloodTypeId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodType/{bloodTypeId} DELETE"
     */
    @DeleteMapping("/{bloodTypeId}")
    public StatusResponse deleteBloodType(@PathVariable Long bloodTypeId, HttpServletResponse response) {
        StatusResponse statusResponse = bloodTypeService.deleteBloodType(bloodTypeId);

        if (statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }
}
