import java.util.ArrayList;
import java.util.Collections;

/* AppointmentManager handle all appointment business logic */

public class AppointmentManager {
    private ArrayList<Appointment> appointmentList;

    public AppointmentManager() {
        appointmentList = new ArrayList<>();
    }

    public boolean addAppointment(Appointment app) {
        for(Appointment a : appointmentList) {
            if(a.getHp().equals(app.getHp()) && a.getTimeSlot().equals(app.getTimeSlot())) {
                System.out.println("[ERROR] This practitioner already booked at " + app.getTimeSlot());
                return false;
            }
        }
        appointmentList.add(app);
        System.out.println("[SUCCESS] New appointment added");
        return true;
    }

    public ArrayList<Appointment> findByHpId(int hpId) {
        ArrayList<Appointment> res = new ArrayList<>();
        for(Appointment a : appointmentList) {
            if(a.getHp().getId() == hpId) {
                res.add(a);
            }
        }
        return res;
    }

    public ArrayList<Appointment> findByPhone(String phone) {
        ArrayList<Appointment> res = new ArrayList<>();
        for(Appointment a : appointmentList) {
            if(a.getPatient().getPhoneNumber().equals(phone)) {
                res.add(a);
            }
        }
        return res;
    }

    public void sortAppointments() {
        Collections.sort(appointmentList);
    }

    public boolean cancelByHpAndTime(int hpId, String time) {
        for(int i = 0; i < appointmentList.size(); i++) {
            Appointment a = appointmentList.get(i);
            if(a.getHp().getId() == hpId && a.getTimeSlot().equals(time)) {
                appointmentList.remove(i);
                System.out.println("[SUCCESS] Appointment cancelled");
                return true;
            }
        }
        System.out.println("[ERROR] Cannot find target appointment");
        return false;
    }

    public boolean cancelByPhone(String phone) {
        boolean removed = false;
        for(int i = appointmentList.size()-1; i >=0; i--) {
            Appointment a = appointmentList.get(i);
            if(a.getPatient().getPhoneNumber().equals(phone)) {
                appointmentList.remove(i);
                removed = true;
            }
        }
        if(!removed) {
            System.out.println("[ERROR] No appointment match this phone");
        } else {
            System.out.println("[SUCCESS] All appointments for this patient cancelled");
        }
        return removed;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointmentList;
    }

    public void printAppointmentList(ArrayList<Appointment> list) {
        System.out.println("----------------------------------------");
        if(list.isEmpty()) {
            System.out.println("No appointments available.");
            System.out.println("----------------------------------------");
            return;
        }
        for(Appointment app : list) {
            System.out.println(app);
        }
        System.out.println("----------------------------------------");
    }

    public void clearAllAppointment() {
        appointmentList.clear();
    }
}

