import java.util.ArrayList;

/* Nurse is a subclass of HealthProfessional.
   Nurse belongs to a ward. */
public class Nurse extends HealthProfessional {
    // unique field for nurse
    private String ward;

    /**
     * Constructor
     * @param id staff id
     * @param name staff name
     * @param workingDays working days
     * @param email work email
     * @param contactPhone work phone
     * @param ward the ward the nurse works in
     */
    public Nurse(int id, String name, ArrayList<String> workingDays, String email, String contactPhone, String ward) {
        super(id, name, workingDays, email, contactPhone);
        this.ward = ward;
    }

    /**
     * Return the job type
     * @return profession name
     */
    @Override
    public String getProfession() {
        return "Nurse";
    }

    // Getter
    public String getWard() {
        return ward;
    }

    /**
     * Show nurse information
     * @return detail string
     */
    @Override
    public String toString() {
        return super.toString() + ", Profession: Nurse, Ward: " + ward;
    }
}


