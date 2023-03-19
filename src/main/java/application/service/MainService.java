package application.service;

import application.model.response.StatusResponse;
import org.springframework.stereotype.*;

@Service
public class MainService {

    public MainService() {
    }
    public StatusResponse checkApplicationHealth() {
        StatusResponse response = new StatusResponse();
        response.setMessage("Blood Clinic Backend Application is up and running!");
        return response;
    }
}
