package service.jpa.mysql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import application.model.Patient;
import application.model.repository.PatientRepositoryModels;
import application.model.repository.ScheduleRepositoryModels;
import application.service.PatientService;
import application.service.observe.PatientObservable;
import application.service.observe.PatientObserver;
import application.utils.ResponseMessage;

/**
 * Test class for Patient service
 */
@DisplayName("Patient Service")
@ExtendWith(MockitoExtension.class)
public class TestPatient {
        private PatientService patientService;

        @Mock
        private PatientRepositoryModels patientRepositoryModels;

        @Mock
        private ScheduleRepositoryModels scheduleRepositoryModels;

        @Mock
        private PatientObservable patientObservable;

        @Mock
        private PatientObserver patientObserver;

        /**
         * SetUp method that instantiates the patientService;
         */
        @BeforeEach
        void setUp() {
                this.patientService = new PatientService(patientRepositoryModels, scheduleRepositoryModels,
                                patientObserver,
                                patientObservable);
        }

        /**
         * Test method for findAllPatients method.
         */
        @Test
        @DisplayName("Ensure `findAllPatients` works")
        void testFindAllPatient() {
                List<Patient> datientsListMock = Mockito.mock();
                when(patientRepositoryModels.findAll()).thenReturn(datientsListMock);

                assertEquals(datientsListMock, patientService.findAllPatients());
                verify(patientRepositoryModels).findAll();
        }

        /**
         * Test method for savePatient method.
         */
        @Test
        @DisplayName("Ensure `savePatient` works")
        void testSavePatient() {
                Patient patient = Mockito.mock();

                // test for null blood type
                when(patient.getBloodType()).thenReturn(null);
                when(this.patientRepositoryModels.save(patient)).thenReturn(patient);
                assertEquals(patient, patientService.savePatient(patient).getEntity());

                // test for not null blood type
                when(patient.getBloodType()).thenReturn(Mockito.mock());
                when(this.patientRepositoryModels.save(patient)).thenReturn(patient);
                assertEquals(patient, patientService.savePatient(patient).getEntity());

                verify(patientObservable, times(1)).addPatient(patient);
                verify(patientRepositoryModels, times(2)).save(patient);
        }

        /**
         * Test method for updatePatient method.
         */
        @Test
        @DisplayName("Ensure `updatePatient` works")
        void testUpdatePatient() {
                Patient patient = Mockito.mock();

                // if patient exists in db
                when(patientRepositoryModels.existsById(patient.getId())).thenReturn(true);

                // bloodType null or patientObservable patients null:
                when(patient.getBloodType()).thenReturn(null);
                assertEquals(ResponseMessage.SUCCESS,
                                patientService.updatePatient(patient).getMessage());

                // bloodType not null and patientObservable patients not null:
                when(patient.getBloodType()).thenReturn(Mockito.mock());
                when(patientObservable.getPatients()).thenReturn(Mockito.mock());

                Patient newPatient = Mockito.mock();

                Answer<Stream<Patient>> answerNewPatient = new Answer<Stream<Patient>>() {
                        @Override
                        public Stream<Patient> answer(InvocationOnMock invocation) throws Throwable {
                                return Stream.of(newPatient);
                        }
                };

                when(patientObservable.getPatients().stream()
                                .filter(p -> p.getId().equals(patient.getId())).findAny())
                                .thenAnswer(answerNewPatient);

                assertEquals(ResponseMessage.SUCCESS,
                                patientService.updatePatient(patient).getMessage());

                // if patient doesnt exist in db
                when(patientRepositoryModels.existsById(patient.getId())).thenReturn(false);
                assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                                patientService.updatePatient(patient).getMessage());

                verify(patientRepositoryModels, times(3)).existsById(patient.getId());
                verify(patientRepositoryModels, times(2)).save(patient);
                verify(patient, times(3)).getBloodType();
                verify(patientObservable).getIndexOfPatient(newPatient.getId());
                verify(patientObservable.getPatients()).set(newPatient.getId().intValue(), newPatient);
                verify(patientObserver).update(this.patientObservable, newPatient);
        }

        /**
         * Test method for deletePatient method.
         */
        @Test
        @DisplayName("Ensure `deletePatient` works")
        void testDeletePatient() {
                Patient patient = Mockito.mock();

                when(patientRepositoryModels.existsById(patient.getId())).thenReturn(true);
                assertEquals(ResponseMessage.SUCCESS,
                                patientService.deletePatient(patient.getId()).getMessage());

                when(patientRepositoryModels.existsById(patient.getId())).thenReturn(false);
                assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                                patientService.deletePatient(patient.getId()).getMessage());

                verify(patientRepositoryModels, times(2)).existsById(patient.getId());
                verify(patientRepositoryModels, times(1)).deleteById(patient.getId());
                verify(patientObservable).removePatient(patient.getId());
        }
}
