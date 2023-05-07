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

import application.model.Schedule;
import application.model.repository.ScheduleRepositoryModels;
import application.service.ScheduleService;
import application.utils.ResponseMessage;

@DisplayName("Schedule Service")
@ExtendWith(MockitoExtension.class)
public class TestSchedule {
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepositoryModels scheduleRepositoryModels;

    @BeforeEach
    void setUp() {
        this.scheduleService = new ScheduleService(scheduleRepositoryModels);
    }

    @Test
    @DisplayName("Ensure `findAllSchedules` works")
    void testFindAllSchedule() {
        List<Schedule> schedulesListMock = Mockito.mock();
        when(scheduleRepositoryModels.findAll()).thenReturn(schedulesListMock);

        assertEquals(schedulesListMock, scheduleService.findAllSchedules());
        verify(scheduleRepositoryModels).findAll();
    }

    @Test
    @DisplayName("Ensure `saveSchedule` works")
    void testSaveSchedule() {
        Schedule schedule = Mockito.mock();

        assertEquals(ResponseMessage.SUCCESS,
                scheduleService.saveSchedule(schedule).getMessage());
    }

    @Test
    @DisplayName("Ensure `updateSchedule` works")
    void testUpdateSchedule() {
        Schedule schedule = Mockito.mock();

        when(scheduleRepositoryModels.existsById(schedule.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                scheduleService.updateSchedule(schedule).getMessage());

        when(scheduleRepositoryModels.existsById(schedule.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                scheduleService.updateSchedule(schedule).getMessage());

        verify(scheduleRepositoryModels, times(2)).existsById(schedule.getId());
        verify(scheduleRepositoryModels, times(1)).save(schedule);
    }

    @Test
    @DisplayName("Ensure `deleteSchedule` works")
    void testDeleteSchedule() {
        Schedule schedule = Mockito.mock();

        when(scheduleRepositoryModels.existsById(schedule.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                scheduleService.deleteSchedule(schedule.getId()).getMessage());

        when(scheduleRepositoryModels.existsById(schedule.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                scheduleService.deleteSchedule(schedule.getId()).getMessage());

        verify(scheduleRepositoryModels, times(2)).existsById(schedule.getId());
        verify(scheduleRepositoryModels, times(1)).deleteById(schedule.getId());
    }
}
