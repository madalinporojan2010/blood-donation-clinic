package service.jpa.mysql;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import application.model.Patient;
import application.service.observe.PatientObservable;

@DisplayName("PatientObservable Service")
@ExtendWith(MockitoExtension.class)
public class TestPatientObservable {
    private PatientObservable patientObservable;

    @Mock
    private List<Patient> patients;

    @BeforeEach
    void setUp() {
        patientObservable = new PatientObservable(patients);
    }

    @Test
    @DisplayName("Ensure `addPatient` works")
    void testAddPatient() {
        Patient patient = Mockito.mock();

        patientObservable.addPatient(patient);
        verify(patients).add(patient);
    }

    @Test
    @DisplayName("Ensure `removePatient` works")
    void testRemovePatient() {
        Patient testPatient = Mockito.mock();
        Long testPatientId = Long.valueOf(1);

        when(testPatient.getId()).thenReturn(testPatientId);

        List<Patient> patients = new ArrayList<Patient>() {
            {
                add(new Patient());
                add(new Patient());
                add(new Patient());
                add(testPatient);
                add(new Patient());
                add(new Patient());
            }
        };
        int initSize = patients.size();

        patientObservable = new PatientObservable(patients);

        patientObservable.removePatient(testPatient.getId());
        assertTrue(patients.size() == initSize - 1);
    }

    @Test
    @DisplayName("Ensure `getIndexOfPatient` works")
    void testGetIndexOfPatient() {
        Patient testPatient = Mockito.mock();
        Long testPatientId = Long.valueOf(1);
        int testPatientIndex = 3;

        when(testPatient.getId()).thenReturn(testPatientId);

        List<Patient> patients = new ArrayList<Patient>() {
            {
                add(new Patient());
                add(new Patient());
                add(new Patient());
                add(testPatient);
                add(new Patient());
                add(new Patient());
            }
        };

        patientObservable = new PatientObservable(patients);

        assertTrue(testPatientIndex == patientObservable.getIndexOfPatient(testPatientId));
    }
}
