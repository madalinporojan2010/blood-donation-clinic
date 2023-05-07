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

import application.model.Donation;
import application.model.repository.DonationRepositoryModels;
import application.service.DonationService;
import application.utils.ResponseMessage;

@DisplayName("Donation Service")
@ExtendWith(MockitoExtension.class)
public class TestDonation {
    private DonationService donationService;

    @Mock
    private DonationRepositoryModels donationRepositoryModels;

    @BeforeEach
    void setUp() {
        this.donationService = new DonationService(donationRepositoryModels);
    }

    @Test
    @DisplayName("Ensure `findAllDonations` works")
    void testFindAllDonation() {
        List<Donation> donationsListMock = Mockito.mock();
        when(donationRepositoryModels.findAll()).thenReturn(donationsListMock);

        assertEquals(donationsListMock, donationService.findAllDonations());
        verify(donationRepositoryModels).findAll();
    }

    @Test
    @DisplayName("Ensure `saveDonation` works")
    void testSaveDonation() {
        Donation donation = Mockito.mock();

        assertEquals(ResponseMessage.SUCCESS,
                donationService.saveDonation(donation).getMessage());
    }

    @Test
    @DisplayName("Ensure `updateDonation` works")
    void testUpdateDonation() {
        Donation donation = Mockito.mock();

        when(donationRepositoryModels.existsById(donation.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                donationService.updateDonation(donation).getMessage());

        when(donationRepositoryModels.existsById(donation.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                donationService.updateDonation(donation).getMessage());

        verify(donationRepositoryModels, times(2)).existsById(donation.getId());
        verify(donationRepositoryModels, times(1)).save(donation);
    }

    @Test
    @DisplayName("Ensure `deleteDonation` works")
    void testDeleteDonation() {
        Donation donation = Mockito.mock();

        when(donationRepositoryModels.existsById(donation.getId())).thenReturn(true);
        assertEquals(ResponseMessage.SUCCESS,
                donationService.deleteDonation(donation.getId()).getMessage());

        when(donationRepositoryModels.existsById(donation.getId())).thenReturn(false);
        assertEquals(ResponseMessage.ERROR_ENTRY_NOT_PRESENT,
                donationService.deleteDonation(donation.getId()).getMessage());

        verify(donationRepositoryModels, times(2)).existsById(donation.getId());
        verify(donationRepositoryModels, times(1)).deleteById(donation.getId());
    }
}
