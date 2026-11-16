import java.util.ArrayList;
import java.util.Scanner;

/* The main class of the clinic appointment system.
   It runs a scripted demonstration and an interactive menu. */
public class AssignmentOne {
    private static final ClinicSchedule schedule = new ClinicSchedule(8, 0, 16, 0, 30);
    private static final AppointmentManager bookingManager = new AppointmentManager(schedule);

    public static void main(String[] args) {
        ArrayList<HealthProfessional> staffList = createStaff();
        //Display all staff details and their duties
        printAllStaff(staffList);
        //Demonstration of normal conditions and abnormal conditions
        runScriptedDemonstration(staffList);
        //Interactive menu after demonstration
        runInteractiveMenu(staffList);
    }

    // Initialize the list of health professionals.
    private static ArrayList<HealthProfessional> createStaff() {
        ArrayList<HealthProfessional> staff = new ArrayList<>();
        // General Practitioners
        staff.add(new GeneralPractitioner(101, "Dr Alice", createDays("Mon", "Wed"), "alice@clinic.com", "11111111", 60.0));
        staff.add(new GeneralPractitioner(102, "Dr Tom", createDays("Tue", "Thu"), "tom@clinic.com", "11111112", 55.0));
        staff.add(new GeneralPractitioner(103, "Dr Lisa", createDays("Wed", "Fri"), "lisa@clinic.com", "11111113", 65.0));
        // Specialists
        staff.add(new Specialist(201, "Dr Bob", createDays("Tue", "Thu"), "bob@clinic.com", "33333333", "Cardiology"));
        staff.add(new Specialist(202, "Dr Emma", createDays("Mon", "Fri"), "emma@clinic.com", "33333334", "Neurology"));
        staff.add(new Specialist(203, "Dr Jack", createDays("Wed", "Thu"), "jack@clinic.com", "33333335", "Orthopedics"));
        // Nurses
        staff.add(new Nurse(301, "Nurse Cindy", createDays("Mon", "Fri"), "cindy@clinic.com", "55555555", "Emergency Ward"));
        staff.add(new Nurse(302, "Nurse Amy", createDays("Tue", "Wed"), "amy@clinic.com", "55555556", "Children Ward"));
        staff.add(new Nurse(303, "Nurse Mark", createDays("Thu", "Fri"), "mark@clinic.com", "55555557", "Surgery Ward"));
        // Dietitians
        staff.add(new Dietitian(401, "Dietitian Dave", createDays("Wed", "Thu"), "dave@clinic.com", "77777777", "Weight Management"));
        staff.add(new Dietitian(402, "Dietitian Zoe", createDays("Mon", "Tue"), "zoe@clinic.com", "77777778", "Diabetes Diet"));
        staff.add(new Dietitian(403, "Dietitian Ray", createDays("Thu", "Fri"), "ray@clinic.com", "77777779", "Sports Nutrition"));
        return staff;
    }

    //  Create an assistant class for the ArrayList of work days.
    private static ArrayList<String> createDays(String day1, String day2) {
        ArrayList<String> days = new ArrayList<>();
        days.add(day1);
        days.add(day2);
        return days;
    }

    // Prints all staff details.
    private static void printAllStaff(ArrayList<HealthProfessional> staffList) {
        System.out.println("========== ALL HEALTH PROFESSIONALS ==========");
        for (HealthProfessional hp : staffList) {
            System.out.println(hp);
            System.out.println("Main Duty: " + hp.performDuty());
            System.out.println("--------------------------------------------------");
        }
    }

    // Scripted demonstration covering normal operations and error cases.
    private static void runScriptedDemonstration(ArrayList<HealthProfessional> staffList) {
        System.out.println("\n========== SCRIPTED DEMONSTRATION ==========");
        String slot1 = "Mon 08:00";
        String slot2 = "Tue 08:30";
        String slot3 = "Wed 09:00";

        // Add normal appointments
        System.out.println(">>> Adding normal appointments...");
        bookingManager.addAppointment(new Appointment(new Patient("John", "00861111111"), staffList.get(0), slot1));
        bookingManager.addAppointment(new Appointment(new Patient("Mary", "00862222222"), staffList.get(4), slot2));
        bookingManager.addAppointment(new Appointment(new Patient("Jack", "00863333333"), staffList.get(6), slot1));
        bookingManager.addAppointment(new Appointment(new Patient("John", "00861111111"), staffList.get(9), slot3));

        // Display sorted appointments
        System.out.println("\n>>> Displaying all sorted appointments:");
        printAppointments(bookingManager.getAllSortedAppointments());

        // Filter by ID and Phone
        System.out.println("\n>>> Filtering by Doctor ID (101):");
        printAppointments(bookingManager.getAppointmentsByHpId(101));
        System.out.println("\n>>> Filtering by Patient Phone (00861111111):");
        printAppointments(bookingManager.getAppointmentsByPatientPhone("00861111111"));

        // Error Cases
        System.out.println("\n>>> Error Case: Double booking the same doctor at " + slot1);
        bookingManager.addAppointment(new Appointment(new Patient("Test", "0000"), staffList.get(0), slot1));

        System.out.println("\n>>> Error Case: Invalid time slot (07:00)");
        bookingManager.addAppointment(new Appointment(new Patient("Test", "0000"), staffList.get(1), "Tue 07:00"));

        System.out.println("\n>>> Error Case: Booking on health professional's non‑working day");
        bookingManager.addAppointment(new Appointment(new Patient("Test", "0000"), staffList.get(0), "Tue 08:00"));

        // Cancellations
        System.out.println("\n>>> Cancelling appointment for Dr Alice at " + slot1);
        if (bookingManager.cancelAppointment(101, slot1)) {
            System.out.println("Cancelled successfully.");
        }

        System.out.println("\n>>> Cancelling all appointments for phone 00863333333");
        int cancelledCount = bookingManager.cancelAppointmentsByPatientPhone("00863333333");
        System.out.println("Cancelled " + cancelledCount + " appointment(s).");

        // Final state
        System.out.println("\n>>> Remaining appointments after cancellation:");
        printAppointments(bookingManager.getAllSortedAppointments());
    }

    // Interactive menu for user operation.
    private static void runInteractiveMenu(ArrayList<HealthProfessional> staffList) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n======== Clinic Appointment System Menu ========");
                System.out.println("1. Make a new appointment");
                System.out.println("2. View all appointments (Sorted by time)");
                System.out.println("3. Search appointments by professional ID");
                System.out.println("4. Search appointments by patient phone");
                System.out.println("5. Cancel an appointment (by ID & Time)");
                System.out.println("6. Cancel appointments (by Patient Phone)");
                System.out.println("0. Exit");
                System.out.print("Please enter your choice: ");
                int choice = readIntInput(scanner);
                switch (choice) {
                    case 1:
                        makeAppointmentInteractive(staffList, scanner);
                        break;
                    case 2:
                        System.out.println("\n----- All Appointments -----");
                        printAppointments(bookingManager.getAllSortedAppointments());
                        break;
                    case 3:
                        System.out.print("\nEnter professional ID to search: ");
                        printAppointments(bookingManager.getAppointmentsByHpId(readIntInput(scanner)));
                        break;
                    case 4:
                        System.out.print("\nEnter patient phone to search: ");
                        printAppointments(bookingManager.getAppointmentsByPatientPhone(scanner.nextLine()));
                        break;
                    case 5:
                        System.out.print("\nEnter professional ID to cancel: ");
                        int cancelId = readIntInput(scanner);
                        System.out.print("Enter the time slot (e.g., Mon 08:30): ");
                        String cancelTime = scanner.nextLine();
                        if (bookingManager.cancelAppointment(cancelId, cancelTime)) {
                            System.out.println("Appointment cancelled successfully.");
                        } else {
                            System.out.println("Cannot find this appointment.");
                        }
                        break;
                    case 6:
                        System.out.print("\nEnter patient phone to cancel all appointments: ");
                        int count = bookingManager.cancelAppointmentsByPatientPhone(scanner.nextLine());
                        System.out.println(count > 0 ? "Successfully cancelled " + count + " appointment(s)." : "No appointments found.");
                        break;
                    case 0:
                        System.out.println("System closed.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option, please input number 0‑6.");
                }
            }
        }
    }

    // Create the interactive mode for making new appointments.
    private static void makeAppointmentInteractive(ArrayList<HealthProfessional> staffList, Scanner scanner) {
        System.out.println("\nAvailable medical staff:");
        for (HealthProfessional hp : staffList) {
            System.out.println(hp.getId() + " | " + hp.getName() + " | " + hp.getProfession());
        }

        System.out.print("Enter professional ID you want to book: ");
        HealthProfessional selectedHp = findProfessionalById(staffList, readIntInput(scanner));
        if (selectedHp == null) {
            System.out.println("Error: This ID does not exist.");
            return;
        }
        System.out.println("Available time slots (HH:mm format): " + schedule.getSlots());
        System.out.print("Enter appointment time (e.g., Mon 09:00): ");
        String timeSlot = scanner.nextLine();
        System.out.print("Enter patient name: ");
        String pName = scanner.nextLine();
        System.out.print("Enter patient phone number: ");
        String pPhone = scanner.nextLine();
        try {
            Patient newPatient = new Patient(pName, pPhone);
            Appointment newApp = new Appointment(newPatient, selectedHp, timeSlot);
            bookingManager.addAppointment(newApp);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    // Search for the assistant of the professional in the list using the ID.
    private static HealthProfessional findProfessionalById(ArrayList<HealthProfessional> staffList, int id) {
        for (HealthProfessional hp : staffList) {
            if (hp.getId() == id) return hp;
        }
        return null;
    }

    // Read an integer safely from the console.
    private static int readIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); 
        }
        int val = scanner.nextInt();
        scanner.nextLine(); 
        return val;
    }

    // The assistant for printing the appointment list.
    private static void printAppointments(ArrayList<Appointment> list) {
        if (list.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment app : list) {
            System.out.println(app);
        }
    }
}



