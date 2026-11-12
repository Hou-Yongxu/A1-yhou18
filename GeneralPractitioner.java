import java.util.ArrayList;

/* General Practitioner subclass, inherits from HealthProfessional
   Unique attribute : consultationFee*/

public class GeneralPractitioner extends HealthProfessional{
    private double consultationFee;

/* Constructor for GeneralPractitioner
   @param id practitioner id
   @param name parctitioner name
   @param workingDays available weekdays
   @param consultationFee consultation price */

   public GeneralPractitioner(int id, String name, ArrayList<String> workingDays, double consultationFee){
        super(id, name, workingDays);
        if(consultationFee <= 0){
            throw new IllegalArgumentException("The fee must be a positive number.");
        }
        this.consultationFee = consultationFee;
   }

   @Override 
   public String getProfession(){
        return "General Practitioner";
   }

/* Unique behaviour: prescribe medicine
   @param drugName name of drug */

   public void prescribeDrug(String drugName){
        System.out.println(getName() + " prescribes " + drugName);
   }

   public double getConsultationFee(){
        return consultationFee;
   }

   @Override 
   public String toString(){
        return super.toString() + ", Profession: " + getProfession() + ", Consultation Fee: $" +consultationFee;
   }
    
}

