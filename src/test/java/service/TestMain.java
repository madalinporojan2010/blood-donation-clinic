package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import application.service.MainService;
import application.utils.ResponseMessage;

@DisplayName("Main Service")
@ExtendWith(MockitoExtension.class)
public class TestMain {
    private MainService mainService;

    @BeforeEach
    void setUp() {
        mainService = new MainService();
    }

    @Test
    @DisplayName("Ensure `checkApplicationHealth` works")
    void testCheckApplicationHealth() {
        assertEquals(ResponseMessage.APP_RUNNING, mainService.checkApplicationHealth().getMessage());
    }
}
