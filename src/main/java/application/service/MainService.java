package application.service;

import application.model.response.StatusResponse;
import org.springframework.stereotype.*;

/**
 * Main service class meant for general application methods.
 * */
@Service
public class MainService {

    /**
     * Basic application health check service.
     * @return Returns the application status
     * */
    public StatusResponse checkApplicationHealth() {
        StatusResponse response = new StatusResponse();
        response.setMessage("Blood Clinic Backend Application is up and running!");
        return response;
    }
}
