package supercalendar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


public class Series implements java.io.Serializable{
    //Series is a series of events
    private String name; //Name of the series
    private ArrayList<Event> eventList; //List of events in this Series

    /**
     * First constructor for a Series of events.
     *
     * @param name the name of the series, also the name for all events created.
     * @param duration the duration for each event.
     * @param frequency how often does the event happen? frequency = 1 means daily.
     * @param number how many events in total for the series
     * @param start_time the start time for the first event
     */
    public Series(String name, int duration, int frequency, int number, Date start_time){
        this.name = name;
        this.eventList = new ArrayList<>();
        int i = 0;
        while(i < number){
            Calendar cal = Calendar.getInstance();
            cal.setTime(start_time);
            cal.add(Calendar.DAY_OF_MONTH, frequency*i);// what if its daily?
            start_time = cal.getTime();
            cal.add(Calendar.HOUR_OF_DAY, duration);
            Date end_time = cal.getTime();
            Event newEvent = new Event(name, start_time, end_time, name);//what if name are not the same
            this.eventList.add(newEvent);
            i++;
        }
    }

    /**
     * Second method to create a Series.
     * This method takes in 2 events, and link them together under a name.
     *
     * @param e1 first event to be linked
     * @param e2 second event to be linked
     * @param name the name of the Series
     */
    public Series(Event e1, Event e2, String name){
        this.name = name;
        e1.setSeries(name);
        e2.setSeries(name);
        this.eventList = new ArrayList<>();
        this.eventList.add(e1);
        this.eventList.add(e2);
        }

    /**
     * Method to return the name of the Series.
     *
     * @return the name of the Series
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method to change the name of the Series
     *
     * @param name the target name of the Series
     */
    public void setName(String name){
        this.name = name;
        for (Event e : this.eventList){
            e.setSeries(name);
        }
    }

    /**
     * Getter for the events inside the event Series
     *
     * @return an ArrayList of events
     */
    public ArrayList<Event> getEventList() { return this.eventList; }

    /**
     * Method to add another event to the event series.
     *
     * @param event the event to be added
     */
    public void add(Event event){
        eventList.add(event);
    }

    //TODO: implement
    public void delete(Event event){
        eventList.remove(event);
    }



    /**
     * This is the toString method for Series.
     *
     * @return the String representation for Series.
     */
    @Override
    public String toString() {
        return "Series called " + this.name + " with " + eventList.size() + "events inside.";
    }
}
