package supercalendar;

import java.util.ArrayList;
import java.util.Date;

public class MemoManager implements java.io.Serializable{
    private ArrayList<Memo> memos;

    /**
     * Constructs a new MemoManager.
     */
    public MemoManager(){this.memos = new ArrayList<>();}

    /**
     * Create a new memo with given message and set association with given event.
     * @param memo_message The actual information for this new memo.
     * @param associated_events The given event that this new memo will associate with.
     */
    public void create_memo (String memo_message, ArrayList<Event> associated_events){
        Memo new_memo = new Memo(memo_message);
        for (Event associated_event : associated_events) {
            new_memo.setAssociate(associated_event);
        }
        boolean check = false;
        for (Memo each_memo : this.memos){
            if (each_memo.equals(new_memo)) {
                check = true;
                break;
            }
        }
        if (!check) {
            this.memos.add(new_memo);
        }
    }

    /**
     * Get the memo that associated with given event.
     * @param event The event given.
     * @return The memo that the given event associated with.
     */
    public Memo show_memo(Event event){
        return event.getMemo();
    }

    /**
     * Delete the given memo from this manager and related classes.
     * @param memo the give memo that will be deleted.
     */
    public void delete_memo(Memo memo){
        this.memos.remove(memo);
        for (int i = 0; i < memo.getEvent().size(); i++){
            memo.getEvent().get(i).removeMemo();
        }
    }
    //TODO: implement
    public void edit_memo(Memo memo, String new_message){
        memo.change_message(new_message);
    }


    /**
     * Return an array list of all the memos stored in this manager.
     * @return An array list of all the memos of this manager.
     */
    public ArrayList<Memo> getMemo(){
        return this.memos;
    }

}
