package application.service;

import application.model.Patient;
import application.model.repository.PatientRepositoryModels;
import application.model.repository.ScheduleRepositoryModels;
import application.model.response.StatusResponse;
import application.repository.jpa.PatientRepositoryJPA;
import application.repository.jpa.ScheduleRepositoryJPA;
import application.repository.jpa.mysql.IPatientRepository;
import application.repository.jpa.mysql.IScheduleRepository;
import application.service.observe.PatientObservable;
import application.service.observe.PatientObserver;
import application.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class used for the blood type table.
 * Usual calls to the Repository Class, fetching database table data.
 */
@Service
@SuppressWarnings("deprecation")
public class PatientService {

    private final PatientRepositoryModels patientRepositoryModels;
    private final PatientObservable patientObservable;
    private final PatientObserver patientObserver;

    /**
     * PatientService constructor used for repositories and observables
     * initialization.
     * JPA only.
     * 
     * @param iPatientRepository  Patient table repository
     * @param iScheduleRepository Schedule table repository
     */
    @Autowired(required = true)
    public PatientService(IPatientRepository iPatientRepository, IScheduleRepository iScheduleRepository) {
        this.patientRepositoryModels = new PatientRepositoryJPA(iPatientRepository);

        this.patientObserver = new PatientObserver(new ScheduleRepositoryJPA(iScheduleRepository));
        this.patientObservable = new PatientObservable();
        this.patientObservable.addObserver(patientObserver);
    }

    /**
     * PatientService constructor used for repositories and observables
     * initialization.
     * Generic.
     * 
     * @param patientRepositoryModels  Patient table models
     * @param scheduleRepositoryModels Schedule table models
     */
    public PatientService(PatientRepositoryModels patientRepositoryModels,
            ScheduleRepositoryModels scheduleRepositoryModels) {
        this.patientRepositoryModels = patientRepositoryModels;

        this.patientObserver = new PatientObserver(scheduleRepositoryModels);
        this.patientObservable = new PatientObservable();
        this.patientObservable.addObserver(patientObserver);
    }

    /**
     * PatientService constructor used for repositories and observables
     * initialization.
     * Testing only.
     * 
     * @param patientRepositoryModels  Patient table models
     * @param scheduleRepositoryModels Schedule table models
     * @param patientObserver          Patient observer instance
     * @param patientObservable        Patient observable instance
     */
    public PatientService(PatientRepositoryModels patientRepositoryModels,
            ScheduleRepositoryModels scheduleRepositoryModels, PatientObserver patientObserver,
            PatientObservable patientObservable) {
        this.patientRepositoryModels = patientRepositoryModels;

        this.patientObserver = patientObserver;
        this.patientObserver.setScheduleRepositoryModels(scheduleRepositoryModels);

        this.patientObservable = patientObservable;
        this.patientObservable.addObserver(patientObserver);
    }

    /**
     * Fetches all the entries from the patient table.
     *
     * @return Returns fetched Patients entries in a List.
     */
    public List<Patient> findAllPatients() {
        List<Patient> fetchedPatient = null;
        try {
            fetchedPatient = patientRepositoryModels.findAll();
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
        }
        return fetchedPatient;
    }

    /**
     * Saves a patient request to the patient table. The patient is added in the
     * observable patients list.
     *
     * @param patient Given Patient request body.
     * @return Success or error message.
     */
    public StatusResponse savePatient(Patient patient) {
        StatusResponse statusResponse = new StatusResponse();
        try {

            if (patient.getBloodType() == null) {
                patientObservable.addPatient(patient);
            }

            patientRepositoryModels.save(patient);
            statusResponse.setMessage(ResponseMessage.SUCCESS);
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Updates a patient entry with the given request body. If the bloodType of the
     * updated patient is updated also, the observers get notified and the bloodType
     * column from the Schedule Table is changed.
     *
     * @param patient Given patient request body.
     * @return Success or error message.
     */
    public StatusResponse updatePatient(Patient patient) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (patientRepositoryModels.existsById(patient.getId())) {
                if (patient.getBloodType() != null && this.patientObservable.getPatients() != null) {

                    Optional<Patient> newPatientOptional = this.patientObservable.getPatients().stream()
                            .filter(p -> p.getId().equals(patient.getId())).findAny();
                    if (newPatientOptional.isPresent()) {
                        Patient newPatient = newPatientOptional.get();
                        newPatient.setBloodType(patient.getBloodType());

                        int patientIndex = this.patientObservable.getIndexOfPatient(newPatient.getId());
                        if (patientIndex != -1) {
                            this.patientObservable.getPatients().set(patientIndex, newPatient);

                            this.patientObserver.update(this.patientObservable, newPatient);
                        }
                    }
                }

                patientRepositoryModels.save(patient);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }

    /**
     * Deletes a patient entry with the given id. The patients is deleted from the
     * observable patient list also.
     *
     * @param patientId Given patient id.
     * @return Success or error message.
     */
    public StatusResponse deletePatient(Long patientId) {
        StatusResponse statusResponse = new StatusResponse();
        try {
            if (patientRepositoryModels.existsById(patientId)) {
                this.patientObservable.removePatient(patientId);

                patientRepositoryModels.deleteById(patientId);
                statusResponse.setMessage(ResponseMessage.SUCCESS);
            } else {
                statusResponse.setMessage(ResponseMessage.ERROR_ENTRY_NOT_PRESENT);
            }
        } catch (Exception e) {
            ResponseMessage.printMethodErrorString(this.getClass(), e);
            statusResponse.setMessage(ResponseMessage.ERROR);
        }
        return statusResponse;
    }
}
