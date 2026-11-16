import java.util.ArrayList;

/* Fixed daily appointment time slots for the clinic. */
public class ClinicSchedule {
    private ArrayList<String> slots;

    /**
     * Builds time slots from start time up to, but not including end time.
     *
     * @param startHour start hour
     * @param startMinute start minute
     * @param endHour end hour
     * @param endMinute end minute
     * @param stepMinutes minutes between slots
     */
    public ClinicSchedule(int startHour, int startMinute, int endHour, int endMinute, int stepMinutes) {
        if (stepMinutes <= 0) {
            throw new IllegalArgumentException("Step must be positive.");
        }
        int start = startHour * 60 + startMinute;
        int end = endHour * 60 + endMinute;
        if (start >= end) {
            throw new IllegalArgumentException("Start must be before end.");
        }

        slots = new ArrayList<>();
        for (int minute = start; minute < end; minute += stepMinutes) {
            slots.add(formatTime(minute));
        }
    }

    private static String formatTime(int totalMinutes) {
        int hour = totalMinutes / 60;
        int minute = totalMinutes % 60;
        String hourText = hour < 10 ? "0" + hour : "" + hour;
        String minuteText = minute < 10 ? "0" + minute : "" + minute;
        return hourText + ":" + minuteText;
    }

    /**
     * Converts HH:mm text to minutes after midnight.
     * Also validates the format.
     * @param time time text
     * @return minutes after midnight
     */
    public static int timeToMinutes(String time) {
        if (time == null || time.length() != 5 || time.charAt(2) != ':') {
            throw new IllegalArgumentException("Time must use HH:mm format.");
        }
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Invalid time.");
        }
        return hour * 60 + minute;
    }

    public boolean isValidSlot(String time) {
        return slots.contains(time);
    }

    /**
     * Returns a copy of the configured slots.
     * @return copy of slots
     */
    public ArrayList<String> getSlots() {
        return new ArrayList<>(slots);
    }
}



