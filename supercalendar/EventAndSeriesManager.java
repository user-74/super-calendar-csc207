package supercalendar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventAndSeriesManager implements java.io.Serializable{

    private ArrayList<Series> series;
    private ArrayList<Event> events;

    /** Constructor for the EventAndSeriesManager. */
    public EventAndSeriesManager(){
        this.series = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    /**
     * Creates an Event with given parameters in this EventAndSeriesManager.
     *
     * @param event_name name of the event
     * @param start_time start time of the event
     * @param end_time end time of the event
     */
    public void create_event (String event_name, Date start_time, Date end_time){ //create event series complete after eventseries
        Event new_event = new Event(event_name, start_time, end_time);
        this.events.add(new_event);
    }

    /**
     * Add and new event in this EventAndSeriesManager.
     *
     * @param event the event object to be added
     */
    public void add_event (Event event) {
        this.events.add(event);
    }

    /** this method add event to event series
     *  @param series variable for event series
     * @param event variable for event
     */
    public void add_event_to_ES(Series series, Event event){
        series.add(event);
        event.setSeries(series.getName());
    }
    /**
     * Creates a Series in this EventAndSeriesManager, also creates the Events of the series.
     *
     * @param name name of the event series
     * @param duration duration of the event
     * @param frequency how often does the event happen? frequency = 1 means daily.
     * @param number how many events in total for the series
     * @param start_time start time for the first event
     */
    public void create_event_series(String name, int duration, int frequency, int number, Date start_time){
        Series newSeries = new Series(name, duration, frequency, number, start_time);
        this.series.add(newSeries);
        this.events.addAll(newSeries.getEventList());
    }

    /**
     * Creates an EventSeries in this EventAndSeriesManager by linking two Events.
     *
     * @param e1 first event to be linked
     * @param e2 second event to be linked
     * @param name name of the event Series
     */
    public void create_event_series(Event e1, Event e2, String name){
        Series newSeries = new Series(e1, e2, name);
        this.series.add(newSeries);
    }

    /**
     * Creates a list of tags for certain event.
     *
     * @param tags tags that are to be created
     * @param associated_events The given event that this new memo will associate with.
     */
    public void create_tag(ArrayList<String> tags, ArrayList<Event> associated_events){
        for (Event associated_event : associated_events) {
            associated_event.addTag(tags);
        }
    }

    /**
     * Searches this EventAndSeriesManager for events by name.
     *
     * @param name name of the events
     * @return an ArrayList of events that have the same name as the parameter name.
     */
    public ArrayList<Event> search(String name){
        ArrayList<Event> result_list_events = new ArrayList<>();
        for (Event event : this.events){
            if (event.getName().equals(name)){
                result_list_events.add(event);
            }
        }
        return result_list_events;
    }

    /**
     * Searches this EventAndSeriesManager for events by tags.
     *
     * @param tags an ArrayList of Strings tag
     * @return an ArrayList of events that have all the tags in the parameter tags.
     */
    public ArrayList<Event> search(ArrayList<String> tags) {
        ArrayList<Event> result_list_events = new ArrayList<>();//finishing create event delete one for loop
        for (Event event: this.events){
            if (event.search_by_tag(tags)){
                result_list_events.add(event);
            }
        }
        return result_list_events;
    }

    /**
     * Searches this EventAndSeriesManager for events by date.
     *
     * @param date date of the events
     * @return an ArrayList of events that takes place on the specific date.
     */
    public ArrayList<Event> search(Date date){
        ArrayList<Event> result_list_events = new ArrayList<>();
        for (Event event : this.events) {
            if ((date.getYear() == (event.getDate()).getYear()) && (date.getMonth() == (event.getDate()).getMonth()) &&
                    (date.getDate() == (event.getDate()).getDate())) {
                result_list_events.add(event);
            }
        }
        return result_list_events;
    }

    /**
     * Searches this EventAndSeriesManager for events by a memo.
     *
     * @param message memo of the events, could be part of a memo
     * @return an ArrayList of events whose memo contains the parameter message.
     */
    public ArrayList<Event> search_memo(String message){
        //ArrayList<Memo> result_list_memos = new ArrayList<>();
        ArrayList<Event> output = new ArrayList<>();
        for (Event event: this.events){
            if (event.getMemo() != null){
                if (event.getMemo().getMessage().contains(message)){
                    output.add(event);
                }
            }
        }
        return output;
    }


    /**
     * Searches this EventAndSeriesManager for a Series by name.
     *
     * @param name name of the Series
     * @return the Series that has the name, else return null if no series has that name.
     */
    public Series search_series(String name){
        for (Series series : this.series){
            if (series.getName().equals(name)){
                return series;
            }
        }
        return null;
    }
    
    /**
     * Deletes a Series from this EventAndSeriesManager.
     *
     * @param series the Series to be deleted
     */
    public void delete_series(Series series){ //the events will remain in events
        for (Event event : series.getEventList()){
            event.removeSeries();
            this.delete_event(event);
        }
        this.series.remove(series);
    }

    /**
     * Deletes an event from this EventAndSeriesManager.
     *
     * @param event the event to be deleted
     */
    public void delete_event(Event event){
        this.events.remove(event);
    }


    /**
     * link an existing event to an existing series.
     *
     * @param event the existing event to be added
     * @param series_name the target series
     * @return if the event has been added or not
     */
    public boolean change_event_to_another_series(Event event, String series_name){
        if (event.getSeries() != null && this.search_series(series_name) != null){
            this.search_series(event.getSeries()).delete(event);
            this.add_event_to_ES(this.search_series(series_name), event);
            return true;
        }else{
            return false;
        }

    }

    /**
     * Returns an event's tags
     * @param event the event whose tags will be returned
     * @return an ArrayList of an event's tags.
     */
    public ArrayList<String> get_tags(Event event){
        return event.getTag();
    }

    /**
     * Returns an event's memo
     * @param event the event whose tags will be returned
     * @return  the event's memo.
     */
    public Memo find_memo(Event event){
        return event.getMemo();
    }

    public boolean edit_tag(Event event, String old_tag, String new_tag){
        return event.edit_tag(old_tag, new_tag);
    }

    /**
     * Edits the name of an event
     * @param event the event whose name is to be changed
     * @param name the new name for the event
     */
    public void edit_name(Event event, String name){
        event.setName(name);
    }

    /**
     * Edits the start time of an event
     * @param event the event whose start time is to be changed
     * @param start_time the new start time for the event
     */
    public void edit_start_time(Event event, Date start_time){
        event.edit_start_date(start_time);
    }
    /**
     * Edits the end time of an event
     * @param event the event whose end time is to be changed
     * @param end_time the new end time for the event
     */
    public void edit_end_time(Event event, Date end_time){
        event.edit_end_date(end_time);
    }

    /**
     * Gets all Events in this EventAndSeriesManager.
     *
     * @return an ArrayList of all Events.
     */
    public ArrayList<Event> getEvents(){
        return this.events;
    }

    /**
     * Gets all Series in this EventAndSeriesManager
     *
     * @return an ArrayList of all events series
     */
    public ArrayList<Series> getSeries(){
        return this.series;
    }

    /**
     * Postpones a specified Event by a number of days
     * @param e1 the Event to be postponed
     * @param days the number of days to postpone the event by
     */
    public void postponeEvent(Event e1, int days){
        e1.postpone(days);
    }
    /**
     * Postpones a specified Event indefinitely
     * @param e1 the Event to be postponed
     */
    public void postponeEvent(Event e1){
        e1.postpone();
    }

    /**
     * Creates a copy of an event at a given start and end time
     * @param e1 the Event to be duplicated
     * @param start the start date for the duplicate event
     * @param end the end date for the duplicate event
     */
    public void duplicateEvent(Event e1, Date start, Date end){
        Event e2;
        e2 = e1.duplicate(start, end);
        this.events.add(e2);
    }
}
