*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asset;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Qzheng
 */
public class Schedule {

    public String startLocation;
    public String destination;
    public Calendar departTime;
    public static ArrayList<Schedule> scheduleList = new ArrayList<>();

    public Schedule(String startLocation, String destination, Calendar dateTime) {
        this.startLocation = startLocation;
        this.destination = destination;
        this.departTime = dateTime;
    }

    public Schedule(String startLocation, String destination) {
        this.startLocation = startLocation;
        this.destination = destination;
        this.departTime = Calendar.getInstance();
    }

    public static Calendar setupTime(int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);

        return c;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        int hours = departTime.get(Calendar.HOUR);
        int minutes = departTime.get(Calendar.MINUTE);

        return String.format("%02d:%02d", hours, minutes);
    }

    public static void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
    }
}
