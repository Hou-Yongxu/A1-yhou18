import java.util.ArrayList;

/* Main class for program demonstration */

public class AssignmentOne {
    public static ArrayList<HealthProfessional> createHealthProfessionals() {
        ArrayList<String> days1 = new ArrayList<>();
        days1.add("Mon"); days1.add("Wed");
        HealthProfessional gp1 = new GeneralPractitioner(101, "Dr Alice", days1, 60.0);

        ArrayList<String> days2 = new ArrayList<>();
        days2.add("Tue"); days2.add("Thu");
        HealthProfessional sp1 = new Specialist(102, "Dr Bob", days2, "Cardiology");

        ArrayList<String> days3 = new ArrayList<>();
        days3.add("Mon"); days3.add("Fri");
        HealthProfessional nurse1 = new Nurse(103, "Nurse Cindy", days3, "Emergency");

        ArrayList<String> days4 = new ArrayList<>();
        days4.add("Wed"); days4.add("Thu");
        HealthProfessional diet1 = new Dietitian(104, "Dietitian Dave", days4, "Weight Management");

        ArrayList<HealthProfessional> hpList = new ArrayList<>();
        hpList.add(gp1);
        hpList.add(sp1);
        hpList.add(nurse1);
        hpList.add(diet1);
        return hpList;
    }

    public static void printAllHp(ArrayList<HealthProfessional> hpList) {
        System.out.println("======== All Health Professionals ========");
        for(HealthProfessional hp : hpList) {
            System.out.println(hp);
        }
    }

    public static void runFullTest() {
        ArrayList<HealthProfessional> hpList = createHealthProfessionals();
        printAllHp(hpList);

        HealthProfessional gp1 = hpList.get(0);
        HealthProfessional sp1 = hpList.get(1);

        AppointmentManager manager = new AppointmentManager();

        Patient tom = new Patient("Tom", "0411111111");
        Patient lily = new Patient("Lily", "0422222222");
        Patient jack = new Patient("Jack", "0433333333");

        System.out.println("\n===== Test1: Add normal appointments =====");
        Appointment app1 = new Appointment(tom, gp1, "09:00");
        Appointment app2 = new Appointment(lily, gp1, "09:30");
        Appointment app3 = new Appointment(tom, sp1, "10:00");
        manager.addAppointment(app1);
        manager.addAppointment(app2);
        manager.addAppointment(app3);
        manager.printAppointmentList(manager.getAllAppointments());

        System.out.println("\n===== Test2: Duplicate booking (same HealthProfessional | same time) =====");
        Appointment duplicate = new Appointment(jack, gp1, "09:00");
        manager.addAppointment(duplicate);

        System.out.println("\n===== Test3: Same patient, different HealthProfessional | same time  =====");
        Appointment crossHpApp = new Appointment(tom, sp1, "09:00");
        manager.addAppointment(crossHpApp);
        manager.printAppointmentList(manager.getAllAppointments());

        System.out.println("\n===== Test4: Search appointment by HealthProfessional id 101 =====");
        ArrayList<Appointment> searchResult = manager.findByHpId(101);
        manager.printAppointmentList(searchResult);

        System.out.println("\n===== Test5: Sort appointments by time =====");
        manager.sortAppointments();
        manager.printAppointmentList(manager.getAllAppointments());

        System.out.println("\n===== Test6: Cancel existing appointment HealthProfessional101, time 09:00 =====");
        manager.cancelByHpAndTime(101, "09:00");
        manager.printAppointmentList(manager.getAllAppointments());

        System.out.println("\n===== Test7: Cancel nonexistent appointment =====");
        manager.cancelByHpAndTime(101, "09:00");

        System.out.println("\n===== Test8: Search nonexistent HealthProfessional id =====");
        ArrayList<Appointment> emptyResult = manager.findByHpId(999);
        manager.printAppointmentList(emptyResult);
    }

    public static void main(String[] args) {
        runFullTest();
    }

}

