package service.jpa.mysql;

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
import application.model.Schedule;
import application.model.repository.ScheduleRepositoryModels;
import application.service.observe.PatientObserver;

/**
 * Test class for PatientObserver
 */
@DisplayName("PatientObserver Service")
@ExtendWith(MockitoExtension.class)
public class TestPatientObserver {
    private PatientObserver patientObserver;

    @Mock
    private ScheduleRepositoryModels scheduleRepositoryModels;

    /**
     * SetUp method that instantiates the patientObserver;
     */
    @BeforeEach
    void setUp() {
        patientObserver = new PatientObserver(scheduleRepositoryModels);
    }

    /**
     * Test method for update method.
     */
    @Test
    @DisplayName("Ensure `update` works")
    void testUpdate() {
        Long patientIdMock = Long.valueOf(1);
        Patient patient = Mockito.mock();
        int schedulesMockSize = 30;
        List<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < schedulesMockSize; i++) {
            Schedule schedule = Mockito.mock();
            when(schedule.copy()).thenReturn(schedule);
            schedules.add(schedule);
        }

        when(scheduleRepositoryModels.findAllByPatientId(patientIdMock)).thenReturn(schedules);
        when(patient.getId()).thenReturn(patientIdMock);

        patientObserver.update(Mockito.mock(), patient);

        for (int i = 0; i < schedulesMockSize; i++) {
            verify(scheduleRepositoryModels).save(schedules.get(i));
        }
    }
}