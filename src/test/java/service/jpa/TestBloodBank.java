package service.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import application.model.BloodBank;
import application.model.repository.BloodBankRepositoryModels;
import application.repository.jpa.mysql.IBloodBankRepository;
import application.service.BloodBankService;

@DisplayName("BloodBank Service")
@ExtendWith(MockitoExtension.class)
public class TestBloodBank {
    @Mock
    private BloodBankService bloodBankService;

    @Autowired
    IBloodBankRepository iBloodBankRepository;

    List<BloodBank> bloodBanksListMock;

    @BeforeEach
    void setUp() {
        bloodBanksListMock = Mockito.mock();
    }

    @Test
    @DisplayName("Ensure findAllBloodBank works")
    void testFindAllBloodBank() {
        when(bloodBankService.findAllBloodBank()).thenReturn(bloodBanksListMock);

        System.out.println(bloodBanksListMock);
        assertEquals(bloodBanksListMock, bloodBankService.findAllBloodBank());
    }

}
