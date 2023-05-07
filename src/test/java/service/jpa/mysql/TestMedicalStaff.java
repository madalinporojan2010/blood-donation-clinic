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

import application.model.MedicalStaff;
import application.model.repository.MedicalStaffRepositoryModels;
import application.service.MedicalStaffService;
import application.utils.ResponseMessage;

/**
 * Test class for MedicalStaff service
 */
@DisplayName("MedicalStaff Service")
@ExtendWith(MockitoExtension.class)
public class TestMedicalStaff {
    private MedicalStaffService medicalStaffService;

    @Mock
    private MedicalStaffRepositoryModels medicalStaffRepositoryModels;

    /**
     * SetUp method that instantiates the medicalStaffService;
     */
    @BeforeEach
    void setUp() {
        this.medicalStaffService = new MedicalStaffService(medicalStaffRepositoryModels);
    }

    /**
     * Test method for findAllMedicalStaffs method.
     */
    @Test
    @DisplayName("Ensure `findAllMedicalStaffs` works")
    void testFindAllMedicalStaff() {
        List<MedicalStaff> medicalStaffsListMock = Mockito.mock();
        when(medicalStaffRepositoryModels.findAll()).thenReturn(medicalStaffsListMock);

        assertEquals(medicalStaffsListMock, medicalStaffService.findAllMedicalStaffs());
        verify(medicalStaffRepositoryModels).findAll();
    }

    /**
     * Test method for saveMedicalStaff method.
     */
    @Test
    @DisplayName("Ensure `saveMedicalStaff` works")
    void testSaveMedicalStaff() {
        MedicalStaff medicalStaff = Mockito.mock();

        assertEquals(ResponseMessage.SUCCESS,
                medicalStaffService.saveMedicalStaff(medicalStaff).getMessage());
    }

    /**
     * Test method for updateMedicalStaff method.
     */
    @Test
    @DisplayName("Ensure `updateMedicalStaff` works")
    void testUpdateMedicalStaff() {
        MedicalStaff medicalStaff = Mockito.mock();

        when(medicalStaffRepositoryModels.existsById(medicalStaff.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                medicalStaffService.updateMedicalStaff(medicalStaff).getMessage());

        when(medicalStaffRepositoryModels.existsById(medicalStaff.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                medicalStaffService.updateMedicalStaff(medicalStaff).getMessage());

        verify(medicalStaffRepositoryModels, times(2)).existsById(medicalStaff.getId());
        verify(medicalStaffRepositoryModels, times(1)).save(medicalStaff);
    }

    /**
     * Test method for deleteMedicalStaff method.
     */
    @Test
    @DisplayName("Ensure `deleteMedicalStaff` works")
    void testDeleteMedicalStaff() {
        MedicalStaff medicalStaff = Mockito.mock();

        when(medicalStaffRepositoryModels.existsById(medicalStaff.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                medicalStaffService.deleteMedicalStaff(medicalStaff.getId()).getMessage());

        when(medicalStaffRepositoryModels.existsById(medicalStaff.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                medicalStaffService.deleteMedicalStaff(medicalStaff.getId()).getMessage());

        verify(medicalStaffRepositoryModels, times(2)).existsById(medicalStaff.getId());
        verify(medicalStaffRepositoryModels, times(1)).deleteById(medicalStaff.getId());
    }
}
