import java.util.ArrayList;

/* Specialist is a subclass of HealthProfessional.
   Specialist has a specialty field. */
public class Specialist extends HealthProfessional {
    // unique field for specialist
    private String specialty;

    /**
     * Constructor
     * @param id staff id
     * @param name staff name
     * @param workingDays working days
     * @param email work email
     * @param contactPhone work phone
     * @param specialty the area this specialist focuses on
     */
    public Specialist(int id, String name, ArrayList<String> workingDays, String email, String contactPhone, String specialty) {
        super(id, name, workingDays, email, contactPhone);
        this.specialty = specialty;
    }

    /**
     * Return to job type
     * @return profession name
     */
    @Override
    public String getProfession() {
        return "Specialist";
    }

    @Override
    public String performDuty() {
        return "Provides specialist diagnosis in " + specialty + ".";
}

    // Getter
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Show specialist information
     * @return detail string
     */
    @Override
    public String toString() {
        return super.toString() + ", Profession: Specialist, Specialty: " + specialty;
    }
}
