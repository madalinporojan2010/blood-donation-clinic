package service.jpa.mysql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import application.model.Patient;
import application.service.observe.PatientObservable;

/**
 * Test class for PatientObservable
 */
@DisplayName("PatientObservable Service")
@ExtendWith(MockitoExtension.class)
public class TestPatientObservable {
    private PatientObservable patientObservable;

    /**
     * Test method for addPatient method.
     */
    @Test
    @DisplayName("Ensure `addPatient` works")
    void testAddPatient() {
        List<Patient> patients = Mockito.mock();
        patientObservable = new PatientObservable(patients);
        Patient patient = Mockito.mock();

        patientObservable.addPatient(patient);
        verify(patients).add(patient);
    }

    /**
     * Test method for removePatient method.
     */
    @Test
    @DisplayName("Ensure `removePatient` works")
    void testRemovePatient() {
        List<Patient> patients = new ArrayList<>();
        int patientsMockListSize = 30;

        Patient patientToDelete = Mockito.mock();
        when(patientToDelete.getId()).thenReturn(Long.valueOf(1));

        for (int i = 0; i < patientsMockListSize; i++) {
            Patient patient = Mockito.mock();
            patients.add(patient);
        }
        // no patient to delete
        patientObservable = new PatientObservable(patients);
        patientObservable.removePatient(patientToDelete.getId());
        assertFalse(patients.contains(patientToDelete));

        // patient to delete
        patients.add(patientToDelete);
        patientObservable = new PatientObservable(patients);
        patientObservable.removePatient(patientToDelete.getId());
        assertFalse(patients.contains(patientToDelete));
    }

    /**
     * Test method for getIndexOfPatient method.
     */
    @Test
    @DisplayName("Ensure `getIndexOfPatient` works")
    void testGetIndexOfPatient() {
        List<Patient> patients = new ArrayList<>();
        int patientsMockListSize = 30;

        Patient patientToFind = Mockito.mock();
        when(patientToFind.getId()).thenReturn(Long.valueOf(1));

        for (int i = 0; i < patientsMockListSize; i++) {
            Patient patient = Mockito.mock();
            patients.add(patient);
        }

        // no patient to find
        patientObservable = new PatientObservable(patients);
        assertTrue(-1 == patientObservable.getIndexOfPatient(patientToFind.getId()));

        patients.add(patientToFind);

        patientObservable = new PatientObservable(patients);

        // patient to find
        assertTrue(patients.indexOf(patientToFind) == patientObservable.getIndexOfPatient(patientToFind.getId()));
    }
}
