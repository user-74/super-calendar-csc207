package supercalendar;

public class Calendar implements java.io.Serializable{

    private String name;

    private AlertManager alertManager;
    private EventAndSeriesManager esManager;
    private MemoManager memoManager;

    public Calendar(String name){
        this.name = name;
        this.alertManager = new AlertManager();
        this.esManager = new EventAndSeriesManager();
        this.memoManager = new MemoManager();
    }


    public AlertManager getAlertManager() {
        return this.alertManager;
    }

    /**
     * Return the EsManager of this user.
     * @return the EsManager.
     */
    public EventAndSeriesManager getEventSeriesManager() {
        return this.esManager;
    }

    /**
     * Return the MemoManager of this user.
     * @return the MemoManager.
     */
    public MemoManager getMemoManager() {
        return this.memoManager;
    }

    public boolean search(String name){
        return this.name.equals(name);
    }

    public String getName(){
        return this.name;
    }
}
