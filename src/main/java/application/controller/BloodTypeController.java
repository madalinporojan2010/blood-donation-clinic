package application.controller;

import application.model.BloodType;
import application.model.request.BloodTypeRequest;
import application.model.response.FetchBloodTypesResponse;
import application.model.response.StatusResponse;
import application.service.BloodTypeService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class used for the blood type table controller, which manages the requests
 * taken from the endpoint.
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
    public ResponseEntity<FetchBloodTypesResponse> findAllBloodTypes() {
        List<BloodType> fetchedBloodTypes = bloodTypeService.findAllBloodTypes();
        FetchBloodTypesResponse fetchBloodTypeResponse = new FetchBloodTypesResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedBloodTypes != null) {
            fetchBloodTypeResponse.setFetchedBloodTypes(fetchedBloodTypes);
            if (fetchedBloodTypes.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchBloodTypeResponse, httpStatus);
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
    public ResponseEntity<StatusResponse> saveBloodType(@RequestBody BloodTypeRequest bloodTypeRequest) {
        StatusResponse statusResponse = bloodTypeService.saveBloodType(bloodTypeRequest.getBloodType());

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
     * @param response         Returned status.
     * @param bloodTypeRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodType PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updateBloodType(@RequestBody BloodTypeRequest bloodTypeRequest) {
        StatusResponse statusResponse = bloodTypeService.updateBloodType(bloodTypeRequest.getBloodType());

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
     * @param response    Returned status.
     * @param bloodTypeId Blood type id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/bloodType/{bloodTypeId} DELETE"
     */
    @DeleteMapping("/{bloodTypeId}")
    public ResponseEntity<StatusResponse> deleteBloodType(@PathVariable Long bloodTypeId) {
        StatusResponse statusResponse = bloodTypeService.deleteBloodType(bloodTypeId);

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
