
/* Appointment class records one booking.
   It links a patient and a health professional with a time slot. */
public class Appointment {
    private Patient patient;
    private HealthProfessional hp;
    private String timeSlot;

    /**
     * Constructor
     * @param patient the patient who makes this booking
     * @param hp the health professional to visit
     * @param timeSlot appointment time
     */
    public Appointment(Patient patient, HealthProfessional hp, String timeSlot) {
        this.patient = patient;
        this.hp = hp;
        this.timeSlot = timeSlot;
    }

    // Getters
    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHp() {
        return hp;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Show full appointment information
     * @return detail string
     */
    @Override
    public String toString() {
        return "Time: " + timeSlot + " | " + patient + " | Doctor: " + hp.getName() + " (" + hp.getProfession() + ")";
    }
}

