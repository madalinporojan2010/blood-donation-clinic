package service.jpa.mysql;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
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

@DisplayName("PatientObserver Service")
@ExtendWith(MockitoExtension.class)
public class TestPatientObserver {
    private PatientObserver patientObserver;

    @Mock
    private ScheduleRepositoryModels scheduleRepositoryModels;

    @BeforeEach
    void setUp() {
        patientObserver = new PatientObserver(scheduleRepositoryModels);
    }

    @Test
    @DisplayName("Ensure `update` works")
    void testUpdate() {
        // Patient patient = Mockito.mock();

        // Long patientId = Long.valueOf(1);

        // when(patient.getId()).thenReturn(patientId);

        // List<Schedule> schedules = Mockito.mock();

        // when(scheduleRepositoryModels.findAllByPatientId(patientId)).thenReturn(schedules);

        // verify(schedules).forEach(schedule -> {
        // Schedule newSchedule = schedule.copy();
        // newSchedule.setBloodType(patient.getBloodType());
        // scheduleRepositoryModels.save(newSchedule);
        // });
    }
}