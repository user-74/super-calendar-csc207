package supercalendar;

import java.util.ArrayList;

public class User implements java.io.Serializable{
    private String name;
    private String password;
    private ArrayList<Calendar> calendars;
    private ArrayList<Event> event_to_be_added;

    //private ArrayList<Event> events;
    //private ArrayList<EventSeries> eventSeries;
    //private ArrayList<Memo> memos;
    //private ArrayList<Alert> alerts;

    /**
     * Construct new User.
     * @param name the username of this user.
     * @param password the password of the user.
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.calendars = new ArrayList<Calendar>();
        this.event_to_be_added = new ArrayList<Event>();
    }

    /**
     * Return string that is the username of this user.
     * @return the username.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the password of this user.
     * @return the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Return the AlertManager of this user.
     * @return the AlertManager.
     */

    public void addCalendar(Calendar c){
        this.calendars.add(c);
    }


    public Calendar searchCalendar(String name){
        for (Calendar c: this.calendars){
            if(c.search(name)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Calendar> getCalendars(){
        return this.calendars;
    }

    public void shared(Event e){
        this.event_to_be_added.add(e);
    }

    public ArrayList<Event> getEvent_to_be_added(){
        return this.event_to_be_added;
    }
}