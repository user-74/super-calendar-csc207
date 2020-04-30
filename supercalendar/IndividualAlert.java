package supercalendar;

import javax.swing.*;
import java.util.Date;

public class IndividualAlert extends Alert {
    /**
     * Construct a new IndividualAlert.
     * @param type the type of this IndividualAlert.
     * @param startDate the start date of this IndividualAlert.
     */
    public IndividualAlert(String type, Date startDate) {
        super(type, startDate);
    }

    /**
     * Check the time of this alert with current time.
     */
    public void checkAlert(){
        Date current = new Date(); // new Date() generate current time
        if (current.getSeconds() == 0) {
            if ((current.getYear() == (this.startDate).getYear()) && (current.getMonth() == (this.startDate).getMonth()) &&
                    (current.getDate() == (this.startDate).getDate()) && (current.getHours() == (this.startDate).getHours())
                    && (current.getMinutes() == (this.startDate).getMinutes())) {
                System.out.println("Time's up for " + this.event.getName());
                JOptionPane.showMessageDialog(null, "It's time for " + this.event.getName(),
                        "Individual Alert", JOptionPane.PLAIN_MESSAGE);
            }
        }
    };

    /**
     * Return the string representation of this IndividualAlert.
     * @return the string representation.
     */
    public String toString(){
        return ("Individual Alert for " + this.event.getName() + "is on " + this.startDate);}
}
