package application.controller;

import application.model.BloodBank;
import application.model.request.BloodBankRequest;
import application.model.response.FetchBloodBankResponse;
import application.model.response.StatusResponse;
import application.service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/bloodBank")
@CrossOrigin
public class BloodBankController {
    @Autowired
    private BloodBankService bloodBankService;

    @GetMapping("")
    public FetchBloodBankResponse findAllBloodBank(HttpServletResponse response) {
        List<BloodBank> fetchedBloodBank = bloodBankService.findAllBloodBank();
        FetchBloodBankResponse fetchBloodBankResponse = new FetchBloodBankResponse();

        if(fetchedBloodBank != null) {
            fetchBloodBankResponse.setFetchedBloodBank(fetchedBloodBank);
            if(fetchedBloodBank.size() == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return fetchBloodBankResponse;
    }

    @PostMapping("")
    public StatusResponse saveBloodBank(@RequestBody BloodBankRequest bloodBankRequest, HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.saveBloodBank(bloodBankRequest.getBloodBank());

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }

    @PutMapping("")
    public StatusResponse updateBloodBank(@RequestBody BloodBankRequest bloodBankRequest, HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.updateBloodBank(bloodBankRequest.getBloodBank());

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }

    @DeleteMapping("/{bloodBankId}")
    public StatusResponse deleteBloodBank(@PathVariable Long bloodBankId, HttpServletResponse response) {
        StatusResponse statusResponse = bloodBankService.deleteBloodBank(bloodBankId);

        if(statusResponse.getMessage().toLowerCase().contains("success")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return statusResponse;
    }
}
