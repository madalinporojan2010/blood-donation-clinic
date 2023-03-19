package application.controller;

import application.model.response.StatusResponse;
import application.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
@CrossOrigin
public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/health")
    public StatusResponse checkApplicationHealth(HttpServletResponse response) {
        StatusResponse statusResponse = mainService.checkApplicationHealth();
        response.setStatus(HttpServletResponse.SC_OK);
        return statusResponse;
    }
}
