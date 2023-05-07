package service.jpa.mysql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import application.model.BloodBank;
import application.model.repository.BloodBankRepositoryModels;
import application.service.BloodBankService;
import application.utils.ResponseMessage;

/**
 * Test class for bloodBank service
 */
@DisplayName("BloodBank Service")
@ExtendWith(MockitoExtension.class)
public class TestBloodBank {
    private BloodBankService bloodBankService;

    @Mock
    private BloodBankRepositoryModels bloodBankRepositoryModels;

    /**
     * SetUp method that instantiates the bloodBankService;
     */
    @BeforeEach
    void setUp() {
        this.bloodBankService = new BloodBankService(bloodBankRepositoryModels);
    }

    /**
     * Test method for findAllBloodBank method.
     */
    @Test
    @DisplayName("Ensure `findAllBloodBank` works")
    void testFindAllBloodBank() {
        List<BloodBank> bloodBanksListMock = Mockito.mock();
        when(bloodBankRepositoryModels.findAllBloodBank()).thenReturn(bloodBanksListMock);

        assertEquals(bloodBanksListMock, bloodBankService.findAllBloodBank());
        verify(bloodBankRepositoryModels).findAllBloodBank();
    }

    /**
     * Test method for saveBloodBank method.
     */
    @Test
    @DisplayName("Ensure `saveBloodBank` works")
    void testSaveBloodBank() {
        BloodBank bloodBank = Mockito.mock();

        assertEquals(ResponseMessage.SUCCESS,
                bloodBankService.saveBloodBank(bloodBank).getMessage());
    }

    /**
     * Test method for updateBloodBank method.
     */
    @Test
    @DisplayName("Ensure `updateBloodBank` works")
    void testUpdateBloodBank() {
        BloodBank bloodBank = Mockito.mock();

        when(bloodBankRepositoryModels.existsById(bloodBank.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                bloodBankService.updateBloodBank(bloodBank).getMessage());

        when(bloodBankRepositoryModels.existsById(bloodBank.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                bloodBankService.updateBloodBank(bloodBank).getMessage());

        verify(bloodBankRepositoryModels, times(2)).existsById(bloodBank.getId());
        verify(bloodBankRepositoryModels, times(1)).saveBloodBank(bloodBank);
    }

    /**
     * Test method for deleteBloodBank method.
     */
    @Test
    @DisplayName("Ensure `deleteBloodBank` works")
    void testDeleteBloodBank() {
        BloodBank bloodBank = Mockito.mock();

        when(bloodBankRepositoryModels.existsById(bloodBank.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                bloodBankService.deleteBloodBank(bloodBank.getId()).getMessage());

        when(bloodBankRepositoryModels.existsById(bloodBank.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                bloodBankService.deleteBloodBank(bloodBank.getId()).getMessage());

        verify(bloodBankRepositoryModels, times(2)).existsById(bloodBank.getId());
        verify(bloodBankRepositoryModels, times(1)).deleteById(bloodBank.getId());
    }
}
