import java.util.ArrayList;

/* The abstract class of all health professionals.
   Stores the general information of all health professionals. */
public abstract class HealthProfessional {
    private int id;           //Define the ID of HealthProfessional
    private String name;      //Define the name of HealthProfessional
    private ArrayList<String> workingDays;     //Define workingdays of HealthProfessional
    private String email;          //Define email of HealthProfessional
    private String contactPhone;       //Define phone of HealthProfessional

    /**
     * Create health professionals after checking the shared data.
     * @param id unique number for staff
     * @param name staff name
     * @param workingDays days the staff works
     * @param email work email
     * @param contactPhone work phone number
     */
    public HealthProfessional(int id, String name, ArrayList<String> workingDays, String email, String contactPhone) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("Working days cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.workingDays = workingDays;
        this.email = email;
        this.contactPhone = contactPhone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getWorkingDays() {
        return workingDays;
    }

    public String getEmail() {
        return email;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Get the type of this medical worker
     * @return job type string
     */
    public abstract String getProfession();

    /**    (non-Javadoc)
     * Returns the main duty of this professional type
     * @return duty description
     */
    public abstract String performDuty();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) other;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * Show all information of this staff
     * @return string with details
     */
    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Email: " + email +
                ", Phone: " + contactPhone +
                ", Working Days: " + workingDays;
    }
}
