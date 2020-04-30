package supercalendar;

import java.util.ArrayList;
import java.util.Date;

public class AlertManager implements java.io.Serializable {
    private ArrayList<Alert> alerts;

    /**
     * Constructs a new AlertManager.
     */
    public AlertManager() {
        this.alerts = new ArrayList<>();
    }

    /**
     * Create a new FrequencyAlert using the parameters given and set association with given event.
     *
     * @param event     The event that this FrequencyAlert will be set on.
     * @param start     The start time of this FrequencyAlert.
     * @param end       The end time of this FrequencyAlert.
     * @param frequency The frequency of this FrequencyAlert.
     */
    public void setFrequencyAlert(Event event, Date start, Date end, long frequency) {
        FrequencyAlert new_alert = new FrequencyAlert("Frequency", start, end, frequency);
        event.addAlert(new_alert);
        this.alerts.add(new_alert);
    }

    /**
     * Create a new IndividualAlert using the parameters given and set association with given event.
     *
     * @param event The event that this IndividualAlert will be set on.
     * @param start The start time of this IndividualAlert.
     */
    public void setIndividualAlert(Event event, Date start) {
        IndividualAlert new_alert = new IndividualAlert("Individual", start);
        event.addAlert(new_alert);
        this.alerts.add(new_alert);
    }

    /**
     * Delete the given alert from this manager.
     *
     * @param alert The alert that will be removed from the calendar.
     */
    public void delete_alert(Alert alert) {
        this.alerts.remove(alert);
        alert.event.removeAlert(alert);
    }

    /**
     * Get the array list of all alerts in this manager, used for other classes since this.alerts is private.
     *
     * @return A array list contains all alerts in this manager.
     */
    public ArrayList<Alert> getAlerts() {
        return this.alerts;
    }

    public void change_individual_alert(IndividualAlert alert, Date date) {
        alert.setStartDate(date);
    }

    public void change_frequency_alert(FrequencyAlert alert, Date startdate, Date enddate, long frequency) {
        FrequencyAlert a = new FrequencyAlert("Frequency", startdate, enddate, frequency);
        this.alerts.add(a);
        alert.event.addAlert(a);
        delete_alert(alert);
    }
}
