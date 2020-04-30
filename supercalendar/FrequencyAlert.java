package supercalendar;

import javax.swing.*;
import java.util.*;



public class FrequencyAlert extends Alert{
    private long frequency; // in milliseconds
    private ArrayList<Date> PopupList;

    private Date endDate; //end date of this event

    /**
     * Construct a new FrequencyAlert.
     * @param type the type of this alert.
     * @param startDate the start date of this alert.
     * @param endDate the end date of this alert.
     * @param frequency the frequency of this alert.
     */
    public FrequencyAlert(String type, Date startDate, Date endDate, long frequency) {
        super(type, startDate);
        this.frequency = frequency;
        this.endDate = endDate;
        this.PopupList = new ArrayList<Date>();
        Date temp = new Date(startDate.getTime());
        while (temp.compareTo(endDate) <= 0) { // while temp is before or equals to end date
            Date d = new Date(temp.getTime());
            temp.setTime(temp.getTime() + frequency); // push to the next alert time
            PopupList.add(d);
        }

    }

    /**
     * Get the frequency of this frequency alert.
     * @return the frequency.
     */
    public long getFrequency() {
        return this.frequency;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    @Override
    // Leave the check enddate to Event class
    public void checkAlert() {
        Date current = new Date(); // new Date() generate current time
        if (current.getSeconds() == 0) {
            for (Date date : PopupList) {
                if ((current.getYear() == date.getYear()) && (current.getMonth() == date.getMonth())
                        && (current.getDate() == date.getDate()) &&
                        (current.getHours() == date.getHours())
                        && (current.getMinutes() == date.getMinutes())) {
                    System.out.println("Time's up for " + this.event.getName());
                    JOptionPane.showMessageDialog(null, "It's time for " + this.event.getName(),
                            "Frequency Alert", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    /**
     * Return a string representation of this FrequencyAlert, which has frequency, start date and the end date.
     * @return the string representation of this FrequencyAlert.
     */
    public String toString(){
        return "Frequency Alert for "+ this.event.getName() + " with frequency " + this.frequency/1000 + "seconds and with the start date " + this.startDate +
                " and the end date " + this.endDate;
    }
}
