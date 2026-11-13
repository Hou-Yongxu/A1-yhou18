import java.util.ArrayList;
import java.util.Scanner;

/* Main class to run the appointment system.
   It provides menu for user to make and manage bookings. */
public class AssignmentOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentManager bookingManager = new AppointmentManager();

        // Pre-arrange and prepare medical staff in advance
        // ===== Three General Practitioners  =====
        ArrayList<String> gpDays1 = new ArrayList<>();
        gpDays1.add("Mon");
        gpDays1.add("Wed");
        HealthProfessional gp1 = new GeneralPractitioner(101, "Dr Alice", gpDays1, "alice@clinic.com", "11111111", 60.0);

        ArrayList<String> gpDays2 = new ArrayList<>();
        gpDays2.add("Tue");
        gpDays2.add("Thu");
        HealthProfessional gp2 = new GeneralPractitioner(102, "Dr Tom", gpDays2, "tom@clinic.com", "11111112", 55.0);

        ArrayList<String> gpDays3 = new ArrayList<>();
        gpDays3.add("Wed");
        gpDays3.add("Fri");
        HealthProfessional gp3 = new GeneralPractitioner(103, "Dr Lisa", gpDays3, "lisa@clinic.com", "11111113", 65.0);

        // ===== Three Specialists  =====
        ArrayList<String> specDays1 = new ArrayList<>();
        specDays1.add("Tue");
        specDays1.add("Thu");
        HealthProfessional spec1 = new Specialist(201, "Dr Bob", specDays1, "bob@clinic.com", "22222221", "Cardiology");

        ArrayList<String> specDays2 = new ArrayList<>();
        specDays2.add("Mon");
        specDays2.add("Fri");
        HealthProfessional spec2 = new Specialist(202, "Dr Emma", specDays2, "emma@clinic.com", "22222222", "Neurology");

        ArrayList<String> specDays3 = new ArrayList<>();
        specDays3.add("Wed");
        specDays3.add("Thu");
        HealthProfessional spec3 = new Specialist(203, "Dr Jack", specDays3, "jack@clinic.com", "22222223", "Orthopedics");

        // ===== Three Nurses =====
        ArrayList<String> nurseDays1 = new ArrayList<>();
        nurseDays1.add("Mon");
        nurseDays1.add("Fri");
        HealthProfessional nurse1 = new Nurse(301, "Nurse Cindy", nurseDays1, "cindy@clinic.com", "33333331", "Emergency Ward");

        ArrayList<String> nurseDays2 = new ArrayList<>();
        nurseDays2.add("Tue");
        nurseDays2.add("Wed");
        HealthProfessional nurse2 = new Nurse(302, "Nurse Amy", nurseDays2, "amy@clinic.com", "33333332", "Children Ward");

        ArrayList<String> nurseDays3 = new ArrayList<>();
        nurseDays3.add("Thu");
        nurseDays3.add("Fri");
        HealthProfessional nurse3 = new Nurse(303, "Nurse Mark", nurseDays3, "mark@clinic.com", "33333333", "Surgery Ward");

        // ===== Three Dietitians =====
        ArrayList<String> dietDays1 = new ArrayList<>();
        dietDays1.add("Wed");
        dietDays1.add("Thu");
        HealthProfessional diet1 = new Dietitian(401, "Dietitian Dave", dietDays1, "dave@clinic.com", "44444441", "Weight Management");

        ArrayList<String> dietDays2 = new ArrayList<>();
        dietDays2.add("Mon");
        dietDays2.add("Tue");
        HealthProfessional diet2 = new Dietitian(402, "Dietitian Zoe", dietDays2, "zoe@clinic.com", "44444442", "Diabetes Diet");

        ArrayList<String> dietDays3 = new ArrayList<>();
        dietDays3.add("Thu");
        dietDays3.add("Fri");
        HealthProfessional diet3 = new Dietitian(403, "Dietitian Ray", dietDays3, "ray@clinic.com", "44444443", "Sports Nutrition");

        // put all staff into array
        HealthProfessional[] staffList = {
                gp1, gp2, gp3,
                spec1, spec2, spec3,
                nurse1, nurse2, nurse3,
                diet1, diet2, diet3
        };

        int choice;
        while (true) {
            System.out.println("\n===== Clinic Appointment System =====");
            System.out.println("1. Make a new appointment");
            System.out.println("2. View all appointments");
            System.out.println("3. Search appointments by doctor ID");
            System.out.println("4. Search appointments by patient phone");
            System.out.println("5. Cancel an appointment");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // User create new appointment
                    System.out.println("\nAvailable medical staff:");
                    for (HealthProfessional hp : staffList) {
                        System.out.println(hp.getId() + " | " + hp.getName() + " | " + hp.getProfession() + " | Working days: " + hp.getWorkingDays());
                    }
                    System.out.print("Enter doctor ID you want to book: ");
                    int selectId = scanner.nextInt();
                    scanner.nextLine();

                    HealthProfessional selectedHp = null;
                    for (HealthProfessional hp : staffList) {
                        if (hp.getId() == selectId) {
                            selectedHp = hp;
                            break;
                        }
                    }
                    if (selectedHp == null) {
                        System.out.println("This ID does not exist.");
                        break;
                    }

                    System.out.print("Enter appointment time (example: Mon 09:00): ");
                    String timeSlot = scanner.nextLine();
                    // Extract the day of the week and determine if it is a working day for healthcare.
                    String dayPart = timeSlot.split(" ")[0];
                    if (!selectedHp.getWorkingDays().contains(dayPart)) {
                        System.out.println("Failed. This staff is not working on " + dayPart);
                        break;
                    }

                    System.out.print("Enter patient name: ");
                    String pName = scanner.nextLine();
                    System.out.print("Enter patient phone number: ");
                    String pPhone = scanner.nextLine();

                    Patient newPatient = new Patient(pName, pPhone);
                    Appointment newApp = new Appointment(newPatient, selectedHp, timeSlot);

                    boolean addResult = bookingManager.addAppointment(newApp);
                    if (addResult) {
                        System.out.println("Appointment created successfully!");
                    } else {
                        System.out.println("Failed. This time slot is already booked.");
                    }
                    break;

                case 2:
                    System.out.println("\n----- All Appointments -----");
                    bookingManager.printAllAppointments();
                    break;

                case 3:
                    System.out.print("\nEnter doctor ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    bookingManager.searchByHpId(searchId);
                    break;

                case 4:
                    System.out.print("\nEnter patient phone to search: ");
                    String searchPhone = scanner.nextLine();
                    bookingManager.searchByPatientPhone(searchPhone);
                    break;

                case 5:
                    System.out.print("\nEnter doctor ID of appointment to cancel: ");
                    int cancelId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the time slot of this appointment: ");
                    String cancelTime = scanner.nextLine();
                    boolean cancelResult = bookingManager.cancelAppointment(cancelId, cancelTime);
                    if (cancelResult) {
                        System.out.println("Appointment cancelled.");
                    } else {
                        System.out.println("Cannot find this appointment.");
                    }
                    break;

                case 6:
                    System.out.println("System closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, please input number 1-6.");
            }
        }
    }
}


