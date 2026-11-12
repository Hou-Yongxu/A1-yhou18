import java.util.ArrayList;

/* Specialist subclass, inherits from HealthProfessional
   Unique attribute: specialty*/

public class Specialist extends HealthProfessional {
    private String specialty;

    /* Constructor for Specialist
       @param id practitioner id
       @param name practitioner name
       @param workingDays available weekdays
       @param specialty medical specialty */
    
    public Specialist(int id, String name, ArrayList<String> workingDays, String specialty) {
        super(id, name, workingDays);
        if(specialty == null || specialty.isBlank()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        this.specialty = specialty;
    }

    @Override 
    public String getProfession() {
        return "Specialist";
    }

    /* Unique behavior: refer patient to another department */ 
    
    public void referPatient(){
        System.out.println(getName() + " refers patient to another department.");
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override 
    public String toString(){
        return super.toString() + ", Profession: " + getProfession() + ", Specialty: " + specialty;
    }
} 