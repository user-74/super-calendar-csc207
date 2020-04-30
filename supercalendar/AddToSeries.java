package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddToSeries extends JFrame{
    private JPanel panel;
    private JButton linkWithSelectedEventButton;
    private JButton addToExistingSeriesButton;
    private JList<String> events;
    private JList<String> eventSeries;
    private JButton cancelButton;
    private ArrayList<Event> without_event;

    public AddToSeries(String title, User user, Calendar c, App app, Event event){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        DefaultListModel<String> listModel1 = new DefaultListModel<String>();
        without_event = new ArrayList<Event>();
        for (Event e: c.getEventSeriesManager().getEvents()){
            if(e != event) {
                listModel1.addElement(e.toString());
                without_event.add(e);
            }
        }
        events.setModel(listModel1);

        DefaultListModel<String> listModel2 = new DefaultListModel<String>();
        for (Series s: c.getEventSeriesManager().getSeries()){
            listModel2.addElement(s.toString());
        }
        eventSeries.setModel(listModel2);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SelectedEvent(title, user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });


        addToExistingSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = eventSeries.getSelectedIndex();
                if (index >= 0) {
                    Series target = c.getEventSeriesManager().getSeries().get(index);
                    JOptionPane.showMessageDialog(null, "Event added to existing Series!",
                            "ADDED!", JOptionPane.PLAIN_MESSAGE);
                    event.setSeries(target.getName());
                    target.add(event);
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new SelectedEvent("Event", user, c, app, event);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select a Series.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        linkWithSelectedEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = events.getSelectedIndex();
                if (index >= 0){
                    String m = JOptionPane.showInputDialog("What do you want to name your new Event Series?");
                    if(m.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please enter a name.",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "2 Events linked together in a series!",
                                "ADDED!", JOptionPane.PLAIN_MESSAGE);
                        Event other_event = without_event.get(index);
                        c.getEventSeriesManager().create_event_series(event, other_event, m);
                        app.WriteToFile("phase2/Storage");
                        dispose();
                        JFrame frame = new SelectedEvent("Event", user, c, app, event);
                        frame.setVisible(true);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select an Event.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

}
