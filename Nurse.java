import java.util.ArrayList;

/* Nurse subclass, inherits from HealthProfessional
   Unique attribute: ward */

public class Nurse extends HealthProfessional {
    private String ward;

    /* Constructor for Nurse
       @param id practitioner id
       @param name practitioner name
       @param workingDays available weekdays
       @param ward ward location */
    
    public Nurse(int id, String name, ArrayList<String> workingDays, String ward) {
        super(id, name, workingDays);
        if(ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward cannot be empty");
        }
        this.ward = ward;
    }

    @Override
    public String getProfession() {
        return "Nurse";
    }

    /* Unique behaviour: take blood sample */
    
    public void takeBloodSample() {
        System.out.println(getName() + " takes blood sample for patient.");
    }

    public String getWard() {
        return ward;
    }

    @Override
    public String toString() {
        return super.toString() + ", Profession: " + getProfession() + ", Ward: " + ward;
    }
}
