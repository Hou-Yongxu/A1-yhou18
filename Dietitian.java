import java.util.ArrayList;

/* Dietitian subclass, inherits from HealthProfessional
   Unique attribute: focusArea */

public class Dietitian extends HealthProfessional {
    private String focusArea;

    /* Constructor for Dietitian
       @param id practitioner id
       @param name practitioner name
       @param workingDays available weekdays
       @param focusArea diet focus field */

    public Dietitian(int id, String name, ArrayList<String> workingDays, String focusArea) {
        super(id, name, workingDays);
        if(focusArea == null || focusArea.isBlank()) {
            throw new IllegalArgumentException("Focus area cannot be empty");
        }
        this.focusArea = focusArea;
    }

    @Override
    public String getProfession() {
        return "Dietitian";
    }

    /* Unique behaviour: provide diet plan */
    
    public void giveDietPlan() {
        System.out.println(getName() + " provides a custom diet plan.");
    }

    public String getFocusArea() {
        return focusArea;
    }

    @Override
    public String toString() {
        return super.toString() + ", Profession: " + getProfession() + ", Focus Area: " + focusArea;
    }
}
