import java.util.ArrayList;

/* AppointmentManager manages all appointment records.
   It can add, search, cancel and print bookings. */
public class AppointmentManager {
    private ArrayList<Appointment> appointmentList;

    /* Constructor, create empty list to store appointments */
    public AppointmentManager() {
        appointmentList = new ArrayList<>();
    }

    /**
     * Add a new appointment if the time slot is not taken
     * @param newAppointment the new booking
     * @return true if add successfully, false if time conflict
     */
    public boolean addAppointment(Appointment newAppointment) {
        for (Appointment app : appointmentList) {
            // Ensure that the same medical staff cannot be scheduled for the same appointment at the same time.
            if (app.getHp().getId() == newAppointment.getHp().getId()
                    && app.getTimeSlot().equals(newAppointment.getTimeSlot())) {
                return false;
            }
        }
        appointmentList.add(newAppointment);
        return true;
    }

    /**
     * Search appointments by health professional ID
     * @param hpId target staff id
     */
    public void searchByHpId(int hpId) {
        boolean found = false;
        for (Appointment app : appointmentList) {
            if (app.getHp().getId() == hpId) {
                System.out.println(app);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for this ID.");
        }
    }

    /**
     * Search appointments by patient phone number
     * @param phone patient phone
     */
    public void searchByPatientPhone(String phone) {
        boolean found = false;
        for (Appointment app : appointmentList) {
            if (app.getPatient().getPatientPhone().equals(phone)) {
                System.out.println(app);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for this phone.");
        }
    }

    /**
     * Cancel an appointment by hp id and time slot
     * @param hpId health professional id
     * @param timeSlot booking time
     * @return true if cancel successfully
     */
    public boolean cancelAppointment(int hpId, String timeSlot) {
        for (Appointment app : appointmentList) {
            if (app.getHp().getId() == hpId && app.getTimeSlot().equals(timeSlot)) {
                appointmentList.remove(app);
                return true;
            }
        }
        return false;
    }

    /**
     * Print all existing appointments
     */
    public void printAllAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments yet.");
            return;
        }
        for (Appointment app : appointmentList) {
            System.out.println(app);
        }
    }
}


