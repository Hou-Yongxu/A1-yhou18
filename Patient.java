/* Patient class stores patient information */

public class Patient {
    private String patientName;
    private String phoneNumber;

    /*  Constructor for Patient
       @param patientName name of patient
       @param phoneNumber contact phone */
       
    public Patient(String patientName, String phoneNumber) {
        if(patientName == null || patientName.isBlank()) {
            throw new IllegalArgumentException("Patient name cannot be empty");
        }
        if(phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient Name: " + patientName + ", Phone: " + phoneNumber;
    }
}

