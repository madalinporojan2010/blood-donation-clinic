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

import application.model.BloodType;
import application.model.repository.BloodTypeRepositoryModels;
import application.service.BloodTypeService;
import application.utils.ResponseMessage;

/**
 * Test class for BloodType service
 */
@DisplayName("BloodType Service")
@ExtendWith(MockitoExtension.class)
public class TestBloodType {
    private BloodTypeService bloodTypeService;

    @Mock
    private BloodTypeRepositoryModels bloodTypeRepositoryModels;

    /**
     * SetUp method that instantiates the bloodTypeService;
     */
    @BeforeEach
    void setUp() {
        this.bloodTypeService = new BloodTypeService(bloodTypeRepositoryModels);
    }

    /**
     * Test method for findAllBloodTypes method.
     */
    @Test
    @DisplayName("Ensure `findAllBloodTypes` works")
    void testFindAllBloodType() {
        List<BloodType> bloodTypesListMock = Mockito.mock();
        when(bloodTypeRepositoryModels.findAll()).thenReturn(bloodTypesListMock);

        assertEquals(bloodTypesListMock, bloodTypeService.findAllBloodTypes());
        verify(bloodTypeRepositoryModels).findAll();
    }

    /**
     * Test method for saveBloodType method.
     */
    @Test
    @DisplayName("Ensure `saveBloodType` works")
    void testSaveBloodType() {
        BloodType bloodType = Mockito.mock();

        assertEquals(ResponseMessage.SUCCESS,
                bloodTypeService.saveBloodType(bloodType).getMessage());
    }

    /**
     * Test method for updateBloodType method.
     */
    @Test
    @DisplayName("Ensure `updateBloodType` works")
    void testUpdateBloodType() {
        BloodType bloodType = Mockito.mock();

        when(bloodTypeRepositoryModels.existsById(bloodType.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                bloodTypeService.updateBloodType(bloodType).getMessage());

        when(bloodTypeRepositoryModels.existsById(bloodType.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                bloodTypeService.updateBloodType(bloodType).getMessage());

        verify(bloodTypeRepositoryModels, times(2)).existsById(bloodType.getId());
        verify(bloodTypeRepositoryModels, times(1)).save(bloodType);
    }

    /**
     * Test method for deleteBloodType method.
     */
    @Test
    @DisplayName("Ensure `deleteBloodType` works")
    void testDeleteBloodType() {
        BloodType bloodType = Mockito.mock();

        when(bloodTypeRepositoryModels.existsById(bloodType.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                bloodTypeService.deleteBloodType(bloodType.getId()).getMessage());

        when(bloodTypeRepositoryModels.existsById(bloodType.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                bloodTypeService.deleteBloodType(bloodType.getId()).getMessage());

        verify(bloodTypeRepositoryModels, times(2)).existsById(bloodType.getId());
        verify(bloodTypeRepositoryModels, times(1)).deleteById(bloodType.getId());
    }
}
