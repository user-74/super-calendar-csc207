package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectedSeries extends JFrame {

    private JPanel panel;
    private JButton SELECTEVENTButton;
    private JList<String> list_of_events;
    private JButton BACKButton;

    public SelectedSeries(String title, User user, Calendar c, App app, Series s) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> events_list = new DefaultListModel<String>();
        for (Event e: s.getEventList()){
            events_list.addElement(e.toString());
        }
        list_of_events.setModel(events_list);


        SELECTEVENTButton.addActionListener(new ActionListener() {
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

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewEvents("View", user, c, app);
                frame.setVisible(true);
            }
        });
    }
}
