/* Appointment class records one booking.
   It links a patient and a health professional with a time slot.
   Time‑slot format example: "Mon 08:00" (weekday + space + HH:mm) */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional hp;
    private String timeSlot;

    /**
     * Only create an appointment when all necessary information is valid.
     * @param patient the patient who makes this booking
     * @param hp the health professional to visit
     * @param timeSlot appointment time, format "Mon 08:00"
     */
    public Appointment(Patient patient, HealthProfessional hp, String timeSlot) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient is required.");
        }
        if (hp == null) {
            throw new IllegalArgumentException("Health professional is required.");
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            throw new IllegalArgumentException("Time slot is required.");
        }

        // split "Mon 08:00" into weekday and HH:mm
        String[] parts = timeSlot.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Time format should be 'Mon 08:00'");
        }
        // validate only the HH:mm segment
        ClinicSchedule.timeToMinutes(parts[1]);

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
     * Sort the reservations by date for comparison.
     * Ignores weekday, only compares HH:mm minutes value.
     * @param other another Appointment object
     * @return negative / zero / positive integer for ordering
     */
    @Override
    public int compareTo(Appointment other) {
        String[] thisParts = this.getTimeSlot().split(" ");
        String[] otherParts = other.getTimeSlot().split(" ");

        int thisMinutes = ClinicSchedule.timeToMinutes(thisParts[1]);
        int otherMinutes = ClinicSchedule.timeToMinutes(otherParts[1]);

        return Integer.compare(thisMinutes, otherMinutes);
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

