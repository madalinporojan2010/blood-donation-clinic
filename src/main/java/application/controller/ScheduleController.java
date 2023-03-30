package application.controller;

import application.model.Schedule;
import application.model.request.ScheduleRequest;
import application.model.response.FetchScheduleResponse;
import application.model.response.StatusResponse;
import application.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@CrossOrigin
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * GET method.
     * @param response Returned status
     * @return Returns all the entries from the schedule table.
     * @see "/api/{api_version}/schedule GET"
     * */
    @GetMapping("")
    public FetchScheduleResponse findAllSchedules(HttpServletResponse response) {
        List<Schedule> fetchedSchedule = scheduleService.findAllSchedules();
        FetchScheduleResponse fetchScheduleResponse = new FetchScheduleResponse();

        if(fetchedSchedule != null) {
            fetchScheduleResponse.setSchedules(fetchedSchedule);
            if(fetchedSchedule.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchScheduleResponse;
    }


    /**
     * POST method.
     * Saves the schedule request in the database.
     * @param response Returned status.
     * @param scheduleRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule POST"
     * */
    @PostMapping("")
    public StatusResponse saveSchedule(@RequestBody ScheduleRequest scheduleRequest, HttpServletResponse response) {
        StatusResponse statusResponse = scheduleService.saveSchedule(scheduleRequest.getSchedule());

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }


    /**
     * PUT method.
     * Updates the schedule request with the given id in the database.
     * @param response Returned status.
     * @param scheduleRequest Bound request from user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule PUT"
     * */
    @PutMapping("")
    public StatusResponse updateSchedule(@RequestBody ScheduleRequest scheduleRequest, HttpServletResponse response) {
        StatusResponse statusResponse = scheduleService.updateSchedule(scheduleRequest.getSchedule());

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }


    /**
     * PUT method.
     * Deletes a schedule entry from the database, with the given id.
     * @param response Returned status.
     * @param scheduleId Schedule id from the user endpoint access.
     * @return Returns a Success or Error message.
     * @see "/api/{api_version}/schedule/{scheduleId} DELETE"
     * */
    @DeleteMapping("/{scheduleId}")
    public StatusResponse deleteSchedule(@PathVariable Long scheduleId, HttpServletResponse response) {
        StatusResponse statusResponse = scheduleService.deleteSchedule(scheduleId);

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }
}
