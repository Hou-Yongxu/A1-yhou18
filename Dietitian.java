import java.util.ArrayList;

/* Dietitian is a subclass of HealthProfessional.
   Dietitian has a focus area for nutrition. */
public class Dietitian extends HealthProfessional {
    // unique field for dietitian
    private String focusArea;

    /**
     * Constructor
     * @param id staff id
     * @param name staff name
     * @param workingDays working days
     * @param email work email
     * @param contactPhone work phone
     * @param focusArea nutrition research area
     */
    public Dietitian(int id, String name, ArrayList<String> workingDays, String email, String contactPhone, String focusArea) {
        super(id, name, workingDays, email, contactPhone);
        this.focusArea = focusArea;
    }

    /**
     * Return to job type
     * @return profession name
     */
    @Override
    public String getProfession() {
        return "Dietitian";
    }

    // Getter
    public String getFocusArea() {
        return focusArea;
    }

    /**
     * Show dietitian information
     * @return detail string
     */
    @Override
    public String toString() {
        return super.toString() + ", Profession: Dietitian, Focus Area: " + focusArea;
    }
}

