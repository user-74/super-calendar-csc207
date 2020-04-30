package supercalendar;
import java.util.*;

public class Memo implements java.io.Serializable{

    private String message;
    private ArrayList<Event> events;  //'Event' is the object type

    /**
     * Constructs a new memo.
     * @param message The actual information about this memo.
     */
    public Memo(String message){
        this.message = message;
        this.events = new ArrayList<>();
    }

    /**
     * Get the message of this memo, used for other class since this.message is private.
     * @return The message of this memo.
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * Set this memo associated with given event. Add given event to this.events and add this memo to given event.
     * @param event The event that this memo will associate with.
     */
    public void setAssociate(Event event){
        event.addMemo(this);
        if (!this.events.contains(event)){
            this.events.add(event);
        }

    }

    //TODO: implement
    public void change_message(String new_message){
        this.message = new_message;
    }

    /**
     * Get the list that store all associated events, used for other class since this.events is private.
     * @return The Array list of all events associated with this memo.
     */
    public ArrayList<Event> getEvent(){
        return this.events;
    }

    /**
     * How this memo will be displayed on screen.
     * @return The actual things that users can see.
     */
    public String toString(){
        StringBuilder all_event_string = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            all_event_string.append("Event number ").append(i).append(".").append(events.get(i).getName()).append(" ");
        }
        String all_events = all_event_string.toString();
        return "Memo with message " + this.message +
                " and associates with " + all_events;
    }
}
