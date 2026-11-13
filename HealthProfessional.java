import java.util.ArrayList;

/* The abstract class of medical staff.
   Stores the general information of all health professionals. */
public abstract class HealthProfessional {
    private int id;           //Define the ID of HealthProfessional
    private String name;      //Define the name of HealthProfessional
    private ArrayList<String> workingDays;     //Define workingdays of HealthProfessional
    private String email;          //Define email of HealthProfessional
    private String contactPhone;       //Define phone of HealthProfessional

    /**
     * Constructor
     * @param id unique number for staff
     * @param name staff name
     * @param workingDays days the staff works
     * @param email work email
     * @param contactPhone work phone number
     */
    public HealthProfessional(int id, String name, ArrayList<String> workingDays, String email, String contactPhone) {
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
