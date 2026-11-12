/* Appointment class, represents one booking
   Implements Comparable to sort by time slot */

public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional hp;
    private String timeSlot;
    private static final String[] VALID_SLOTS = {
            "08:00","08:30","09:00","09:30","10:00","10:30",
            "11:00","11:30","12:00","12:30","13:00","13:30",
            "14:00","14:30","15:00","15:30"
    };

    /* Constructor for Appointment
       @param patient patient object
       @param hp health professional
       @param timeSlot booking time */

    public Appointment(Patient patient, HealthProfessional hp, String timeSlot) {
        if(patient == null) throw new IllegalArgumentException("Patient cannot be null");
        if(hp == null) throw new IllegalArgumentException("Health professional cannot be null");
        if(timeSlot == null || timeSlot.isBlank()) throw new IllegalArgumentException("Time slot cannot be empty");

        boolean valid = false;
        for(String s : VALID_SLOTS) {
            if(s.equals(timeSlot)) {
                valid = true;
                break;
            }
        }
        if(!valid) {
            throw new IllegalArgumentException("Time slot is out of clinic hours (08:00 - 16:00, 30min interval)");
        }
        this.patient = patient;
        this.hp = hp;
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getHp() {
        return hp;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public int compareTo(Appointment other) {
        return this.timeSlot.compareTo(other.timeSlot);
    }

    @Override
    public String toString() {
        return "Time: " + timeSlot + " | " + patient + " | Practitioner: " + hp.getName() + "(ID:" + hp.getId() + ")";
    }
}
