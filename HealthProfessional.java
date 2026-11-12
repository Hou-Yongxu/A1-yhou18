// Yongxu Hou  | Student ID:25258732

import java.util.ArrayList;

/* The abstract superclass for all medical professionals.
   It can't be directly instantiated.
   Common attributes : ID,Name,Working days.*/

public abstract class HealthProfessional{
   private int id;
   private String name;
   private ArrayList<String> workingDays;

/* Constructor for HealthProfessional
   @param id unique numeric identifier
   @param name name of practitioner
   @param workingDays list of weekdays available */

public HealthProfessional(int id, String name, ArrayList<String> workingDays){
   if(id <= 0){
      throw new IllegalArgumentException("ID must be a positive number.");
   }
   if(name == null || name.isBlank()){
      throw new IllegalArgumentException("Name cannot be empty.");
   }
   if(workingDays == null || workingDays.isEmpty()){
      throw new IllegalArgumentException("At least one working day is required.");
   }
   this.id = id;
   this.name = name;
   this.workingDays = workingDays;
}

//Getters (No setters, encapsulation)
public int getId(){ 
   return id;
}

public String getName(){
   return name;
}

public ArrayList<String> getWorkingDays(){
   return workingDays;
}

/* Abstract method,forcing all subclasses to return the job title
   @return profession name*/

public abstract String getProfession();

@Override
public String toString(){
   return "ID: " + id + ", Name : " + name + ", Working Days: " + workingDays;
}

//Determine whether they are the same medical staff based on the ID
@Override
public boolean equals(Object o){
   if (this == o) return true;
   if (o == null || getClass() != o.getClass()) return false;
   HealthProfessional that = (HealthProfessional) o;
   return id == that.id;
}

@Override
public int hashCode(){
   return id;
}
}