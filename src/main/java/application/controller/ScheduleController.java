package application.controller;

import application.model.Schedule;
import application.model.request.ScheduleRequest;
import application.model.response.FetchSchedulesResponse;
import application.model.response.StatusResponse;
import application.service.ScheduleService;
import application.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class used for the schedule table controller, which manages the requests
 * taken from the endpoint.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/schedule
 */
@RestController
@RequestMapping("/schedule")
@CrossOrigin
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * ScheduleController constructor
     *
     * @param scheduleService ScheduleService instance
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the schedule table.
     * @see "/api/{api_version}/schedule GET"
     */
    @GetMapping("")
    public ResponseEntity<FetchSchedulesResponse> findAllSchedules() {
        List<Schedule> fetchedSchedule = scheduleService.findAllSchedules();
        FetchSchedulesResponse fetchScheduleResponse = new FetchSchedulesResponse();

        HttpStatus httpStatus = HttpStatus.OK;

        if (fetchedSchedule != null) {
            fetchScheduleResponse.setFetchedSchedules(fetchedSchedule);
            if (fetchedSchedule.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fetchScheduleResponse, httpStatus);
    }

    /**
     * POST method.
     * Saves the schedule request in the database.
     *
     * @param response        Returned status.
     * @param scheduleRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule POST"
     */
    @PostMapping("")
    public ResponseEntity<StatusResponse> saveSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        StatusResponse statusResponse = scheduleService.saveSchedule(scheduleRequest.getSchedule());

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
     * Updates the schedule request with the given id in the database.
     *
     * @param response        Returned status.
     * @param scheduleRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule PUT"
     */
    @PutMapping("")
    public ResponseEntity<StatusResponse> updateSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        StatusResponse statusResponse = scheduleService.updateSchedule(scheduleRequest.getSchedule());

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
     * Deletes a schedule entry from the database, with the given id.
     *
     * @param response   Returned status.
     * @param scheduleId Schedule id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule/{scheduleId} DELETE"
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<StatusResponse> deleteSchedule(@PathVariable Long scheduleId) {
        StatusResponse statusResponse = scheduleService.deleteSchedule(scheduleId);

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
