import java.util.ArrayList;
import java.util.Collections;

/* AppointmentManager manages all appointment records.
   It can add, search, cancel and print bookings. */
public class AppointmentManager {
    private ArrayList<Appointment> appointmentList;
    private ClinicSchedule schedule;

    /* Constructor, create empty list to store appointments */
    public AppointmentManager(ClinicSchedule schedule) {
        this.schedule = schedule;
        this.appointmentList = new ArrayList<>();
    }

    /**
     * If the time slot is not occupied and professional is working on that weekday, make a new reservation.
     * @param newAppointment the new booking
     * @return true if add successfully, false if time conflict / invalid time / non‑working day
     */
    public boolean addAppointment(Appointment newAppointment) {
        String fullTimeSlot = newAppointment.getTimeSlot();
        String[] parts = fullTimeSlot.split(" ");
        if (parts.length != 2) {
            System.out.println("Failed. Time format must be like 'Mon 08:00'.");
            return false;
        }
        String bookingWeekDay = parts[0];
        String hhmm = parts[1];

        // Check whether time is within clinic opening hours
        if (!schedule.isValidSlot(hhmm)) {
            System.out.println("Failed. " + hhmm + " is not a valid clinic time slot.");
            return false;
        }

        // Check whether health professionals provide services on working days
        HealthProfessional hp = newAppointment.getHp();
        ArrayList<String> workingDays = hp.getWorkingDays();
        if (!workingDays.contains(bookingWeekDay)) {
            System.out.println("Failed. This health professional does not work on " + bookingWeekDay + ".");
            return false;
        }

        // Check duplicate booking: the same mobile phone number and the same full-time schedule
        for (Appointment app : appointmentList) {
            if (app.getHp().equals(newAppointment.getHp())
                    && app.getTimeSlot().equals(newAppointment.getTimeSlot())) {
                System.out.println("Failed. This time slot is already booked for this professional.");
                return false;
            }
        }

        appointmentList.add(newAppointment);
        System.out.println("Appointment created successfully!");
        return true;
    }

    /**
     * Get appointments by health professional ID, sorted by time
     * @param hpId target staff id
     * @return a sorted list of matching appointments
     */
    public ArrayList<Appointment> getAppointmentsByHpId(int hpId) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment app : appointmentList) {
            if (app.getHp().getId() == hpId) {
                result.add(app);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Get appointments by patient phone number, sorted by time
     * @param phone patient phone
     * @return a sorted list of matching appointments
     */
    public ArrayList<Appointment> getAppointmentsByPatientPhone(String phone) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment app : appointmentList) {
            if (app.getPatient().getPatientPhone().equals(phone)) {
                result.add(app);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Cancel an appointment by hp id and time slot
     * @param hpId health professional id
     * @param timeSlot booking time (format: "Mon 08:00")
     * @return true if cancel successfully
     */
    public boolean cancelAppointment(int hpId, String timeSlot) {
        for (int i = appointmentList.size() - 1; i >= 0; i--) {
            Appointment app = appointmentList.get(i);
            if (app.getHp().getId() == hpId && app.getTimeSlot().equals(timeSlot)) {
                appointmentList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Cancel all appointments by patient phone number
     * @param phone patient phone
     * @return number of cancelled appointments
     */
    public int cancelAppointmentsByPatientPhone(String phone) {
        int count = 0;
        for (int i = appointmentList.size() - 1; i >= 0; i--) {
            Appointment app = appointmentList.get(i);
            if (app.getPatient().getPatientPhone().equals(phone)) {
                appointmentList.remove(i);
                count++;
            }
        }
        return count;
    }

    /**
     * Get all existing appointments sorted by time
     * @return a sorted list of all appointments
     */
    public ArrayList<Appointment> getAllSortedAppointments() {
        ArrayList<Appointment> sortedList = new ArrayList<>(appointmentList);
        Collections.sort(sortedList);
        return sortedList;
    }
}
