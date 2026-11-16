import java.util.ArrayList;

/* GeneralPractitioner is a subclass of HealthProfessional.
   GeneralPractitioner has the unique attribute of receiving consultation fees. */

public class GeneralPractitioner extends HealthProfessional {
    // unique field for GeneralPractitioner
    private double consultationFee;

    /**
     * Constructor
     * @param id staff id
     * @param name staff name
     * @param workingDays working days
     * @param email work email
     * @param contactPhone work phone
     * @param consultationFee price for one consultation
     */
    public GeneralPractitioner(int id, String name, ArrayList<String> workingDays, String email, String contactPhone, double consultationFee) {
        super(id, name, workingDays, email, contactPhone);
        this.consultationFee = consultationFee;
    }

    /**
     * Return to job type
     * @return profession name
     */
    @Override
    public String getProfession() {
        return "General Practitioner";
    }

    @Override
    public String performDuty() {
        return "Provide diagnosis and treatment for patients and issue prescriptions.";
}

    // Getter
    public double getConsultationFee() {
        return consultationFee;
    }

    /**
     * Show GeneralPractitioner information
     * @return detail string
     */
    @Override
    public String toString() {
        return super.toString() + ", Profession: General Practitioner, Consultation Fee: $" + consultationFee;
    }
}


