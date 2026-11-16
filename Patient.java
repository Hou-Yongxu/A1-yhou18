/* Patient class stores patient information.
   This data will be used in appointments. */
public class Patient {
    private String patientName;
    private String patientPhone;

    /**
     * Create the patient after checking the required information.
     * @param patientName name of patient
     * @param patientPhone patient contact number
     */
    public Patient(String patientName, String patientPhone) {
        if (patientName == null || patientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient phone cannot be empty.");
        }
        this.patientName = patientName;
        this.patientPhone = patientPhone;
    }

    // Getters
    public String getPatientName() {
        return patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    /**
     * Show patient information
     * @return detail string
     */
    @Override
    public String toString() {
        return "Patient Name: " + patientName + ", Phone: " + patientPhone;
    }
}


