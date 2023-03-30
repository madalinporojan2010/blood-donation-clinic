package application.controller;

import application.model.response.StatusResponse;
import application.service.MainService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Class used for the main functionalities of the application, e.g. health check.
 * Usual POST, PUT, GET, DELETE methods.
 *
 * @see /api/{api_version}/
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * GET method.
     *
     * @param response Returned status
     * @return Returns all the entries from the blood bank table.
     * @see "/api/{api_version}/health GET"
     */
    @GetMapping("/health")
    public StatusResponse checkApplicationHealth(HttpServletResponse response) {
        StatusResponse statusResponse = mainService.checkApplicationHealth();
        response.setStatus(HttpServletResponse.SC_OK);
        return statusResponse;
    }
}
