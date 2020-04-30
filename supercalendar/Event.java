package supercalendar;

import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Event implements java.io.Serializable{
    private Date startTime;
    private Date endTime;
    private String name;
    private ArrayList<Alert> alerts;
    private Memo memo;
    private ArrayList<String> tags;
    private String series_name;

    /**
     * Creates an Event with a name, start time, and end time.
     * This constructor is used to create a standalone Event.
     *
     * @param name  the name of the Event
     * @param startTime the start time of the Event
     * @param endTime   the end time of the Event
     */
    public Event(String name, Date startTime, Date endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.alerts = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.series_name = null;
    }

    /**
     * Creates an Event with a name, start time, end time, and series name.
     * This constructor is used to create an Event as a part of a Series.
     *
     * @param name  the name of the Event
     * @param startTime the start time of the Event
     * @param endTime   the end time of the Event
     * @param series_name   the name of the Series this Event is a part of
     */
    public Event(String name, Date startTime, Date endTime, String series_name){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.alerts = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.series_name = series_name;
    }

    /**
     * Changes the Series that this Event is a part of.
     *
     * @param name  the name of the Series this Event is a part of
     */
    public void setSeries(String name){
        this.series_name = name;
    }

    /**
     * Removes this Event from the Series it was in.
     */
    public void removeSeries(){
        this.series_name = null;
    }

    /**
     * Removes an Alert from this Event.
     *
     * @param alert the Alert to be removed from this event
     */
    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }

    /**
     * Postpones this Event by a specified number of days
     * @param days the number of days this event is to be postponed
     */

    public void postpone(int days){
        Calendar cs = Calendar.getInstance();
        Calendar ce = Calendar.getInstance();
        cs.setTime(this.startTime);
        ce.setTime(this.endTime);
        cs.add(Calendar.DAY_OF_MONTH, days);
        ce.add(Calendar.DAY_OF_MONTH, days);
        this.startTime = cs.getTime();
        this.endTime = ce.getTime();
    }

    public void postpone(){
        this.startTime = null;
        this.endTime = null;
    }

    public Event duplicate(Date start, Date end){
        Event e2 = new Event(this.getName(), start, end);
        e2.setSeries(this.getSeries());
        e2.addTag(this.getTag());
        for (Alert alert : this.alerts){
            e2.addAlert(alert);
        }
        e2.addMemo(this.getMemo());
        return e2;
    }

    /**
     * Removes the memo associated with this Event.
     */
    public void removeMemo() {
        this.memo = null;
    }

    /**
     * Returns the name of the series this Event is a part of.
     *
     * @return the series name of this Event, null if not part of a series.
     */
    public String getSeries(){
        return this.series_name;
    }

    /**
     * Adds multiple tags to this Event.
     *
     * @param tag the tags to be associated to this Event, in an ArrayList.
     */
    public void addTag(ArrayList<String> tag){
        for(String t: tag){
            if (!this.tags.contains(t)){
                this.tags.add(t);
            }
        }
    }


    /**
     * Returns a list of this Event's tags.
     *
     * @return this Event's tags in an ArrayList
     */
    public ArrayList<String> getTag(){
        return this.tags;
    }

    /**
     * Returns this Event's start time.
     *
     * @return the Date of this Event's starttime
     */
    public Date getDate(){
        return this.startTime;
    }

    /**
     * Returns this Event's end time.
     *
     * @return the Date of this Event's endTime
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * Return this Event's memo.
     *
     * @return the Memo associated with this Event
     */
    public Memo getMemo(){
        return this.memo;
    }

    /**
     * Sets a Memo to this Event
     *
     * @param memo the Memo to be associated with this Event
     */
    public void addMemo(Memo memo){
        this.memo = memo;
    }

    /**
     * Adds a alert to this event
     *
     * @param alert the FrequencyAlert to be added to this Event
     */
    public void addAlert(Alert alert){
        this.alerts.add(alert);
        alert.addEvent(this);
    }

    /**
     * get alerts of this event
     *
     */
    public ArrayList<Alert> getAlerts() {
        return this.alerts;
    }

    /**
     * Returns this Event's name
     *
     * @return the name of this Event
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the name of this Event.
     *
     * @param name the new name for this Event
     */
    public void setName(String name){
        this.name = name;
    }

    public boolean search_by_tag(ArrayList<String> tags){
        for (String tag : tags){
            if(! this.tags.contains(tag)){
                return false;
            }
        }
        return true;
    }

    //TODO: implement

    public boolean edit_tag(String old_tag, String new_tag){
        if (!this.tags.contains(old_tag)){
            return false;
        }else{
            this.tags.set(this.tags.indexOf(old_tag), new_tag);
            return true;
        }
    }
    

    public void edit_start_date(Date new_start_date){
        this.startTime = new_start_date;
    }

    public void edit_end_date(Date new_end_date){
        this.endTime = new_end_date;
    }

    /**
     * Returns a string representation of this Event, including the Event's name, start time,
     * end time, alerts, tags, memos, and series names.
     *
     * @return the string representation of this Event
     */


    public String toString(){
        String all_alert;
        if (this.alerts.size() != 0){
            StringBuilder all_alert_string = new StringBuilder();
            for (int i = 0; i < alerts.size(); i++){
                all_alert_string.append("Alert ").append(i + 1).append(". ").append(alerts.get(i)).append(" ");
            }
            all_alert = all_alert_string.toString();
        } else{
            all_alert = "no alert yet";
        }



        String all_tag;
        if (tags.size() != 0) {
            StringBuilder all_tag_string = new StringBuilder();
            for (int i = 0; i < tags.size(); i++) {
                all_tag_string.append("Tag number").append(i).append(".").append(tags.get(i)).append(" ");
            }
            all_tag = all_tag_string.toString();
        } else {
            all_tag = "no tags added";
        }

        String tempMemo;
        if (this.memo == null){
            tempMemo = "(no memo yet)";
        }
        else {
            tempMemo = this.memo.toString();
        }
        String tempSeries;
        if (this.series_name == null){
            tempSeries = "not in any series yet";
        }
        else{
            tempSeries = this.series_name;
        }

        if (this.startTime == null && this.endTime == null){
            return " Event " + this.name + " has no start time and end time; has: " + all_alert + "; and tagged with: " + all_tag +
                    "; and has: " + tempMemo + "; and is in series: " + tempSeries;
        } else if (this.endTime == null) {
            return " Event " + this.name + " starts on " + this.startTime  + " and has no end time; has: " + all_alert + "; and tagged with: " + all_tag +
                "; and has: " + tempMemo + "; and is in series: " + tempSeries;

        } else if (this.startTime == null){
            return " Event " + this.name + " has no start time, it ends on  " + this.endTime + "; has: " + all_alert + "; and tagged with: " + all_tag +
                    "; and has: " + tempMemo + "; and is in series: " + tempSeries;

        } else {
        return " Event " + this.name + " starts on " + this.startTime +
                "; and end on " + this.endTime + "; has: " + all_alert + "; and tagged with: " + all_tag +
                "; and has: " + tempMemo + "; and is in series: " + tempSeries;
        }
    }

}
