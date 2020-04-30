package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEvents extends JFrame {
    private JPanel screen;
    private JButton select;
    private JButton back;
    private JList<String> list_of_events;
    private JList<String> list_of_series;
    private JButton DELETESERIESButton;
    private JButton VIEWSERIESButton;

    public ViewEvents(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(screen);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> events_list = new DefaultListModel<String>();
        for (Event e: c.getEventSeriesManager().getEvents()){
            events_list.addElement(e.toString());
        }
        list_of_events.setModel(events_list);

        DefaultListModel<String> series_list = new DefaultListModel<String>();
        for (Series es: c.getEventSeriesManager().getSeries()){
            series_list.addElement(es.toString());
        }
        list_of_series.setModel(series_list);


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new HomeScreen("Calender", user, c, app);
                frame.setVisible(true);
            }
        });


        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list_of_events.getSelectedIndex();
                if (index >=0) {
                    Event event = c.getEventSeriesManager().getEvents().get(index);
                    dispose();
                    JFrame frame = new SelectedEvent("Event", user, c, app, event);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Event.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        VIEWSERIESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list_of_series.getSelectedIndex();
                if (index >=0) {
                    Series s = c.getEventSeriesManager().getSeries().get(index);
                    dispose();
                    JFrame frame = new SelectedSeries("Event", user, c, app, s);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Series.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        DELETESERIESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list_of_series.getSelectedIndex();
                if (index >=0) {
                    JOptionPane.showMessageDialog(null, "Selected Series has been deleted.",
                            "DELETED", JOptionPane.PLAIN_MESSAGE);
                    Series s = c.getEventSeriesManager().getSeries().get(index);
                    c.getEventSeriesManager().delete_series(s);
                    for (Event event: s.getEventList()){
                        if (event.getAlerts().size() != 0) {
                            for (Alert a : event.getAlerts()) {
                                c.getAlertManager().delete_alert(a);
                            }
                        }
                        if (event.getMemo() != null) {
                            c.getMemoManager().getMemo().remove(event.getMemo());
                        }
                    }
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new ViewEvents("Event", user, c, app);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Series.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
