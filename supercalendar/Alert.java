package supercalendar;

import java.util.*;

public abstract class Alert implements java.io.Serializable{
    protected String type; //"Individual" or "Frequency"
    protected Date startDate;
    protected Event event;


    /**
     * Construct a new Alert.
     * @param type the type of this alert.
     * @param startDate the start date of this alert
     */
    public Alert(String type, Date startDate) {
        this.type = type;
        this.startDate = startDate;
    }

    /**
     * Add the associated event to this alert.
     * @param event the related event.
     */
    public void addEvent(Event event) {
        this.event = event;
    }

    /**
     * Get the type of this alert(individual or frequency).
     * @return The type of this alert.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the start date of this alert.
     * @return The start date of this alert, in Java.date form.
     */
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date date) {
         this.startDate = date;
    }

    /**
     * Check whether this alert should pop.
     */
    public abstract void checkAlert();

}
