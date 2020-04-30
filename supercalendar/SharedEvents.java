package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SharedEvents extends JFrame {


    private JPanel panel;
    private JButton BACKButton;
    private JButton ADDButton;
    private JList<String> to_be_added;
    private JButton DELETEButton;

    public SharedEvents(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> events_list = new DefaultListModel<String>();
        for (Event e: user.getEvent_to_be_added()){
            events_list.addElement(e.toString());
        }
        to_be_added.setModel(events_list);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new HomeScreen("Calender", user, c, app);
                frame.setVisible(true);
            }
        });


        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = to_be_added.getSelectedIndex();
                if (index >= 0) {
                    Event add_this_event = user.getEvent_to_be_added().get(index);
                    c.getEventSeriesManager().add_event(add_this_event);
                    user.getEvent_to_be_added().remove(index);
                    JOptionPane.showMessageDialog(null, "Event " + add_this_event.getName() + " has been added!",
                            "Success!", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new SharedEvents("Shared Events", user, c, app);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select an Event.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = to_be_added.getSelectedIndex();
                if (index >= 0) {
                    Event delete_this_event = user.getEvent_to_be_added().get(index);
                    user.getEvent_to_be_added().remove(index);
                    JOptionPane.showMessageDialog(null, "Event " + delete_this_event.getName() + " has been deleted!",
                            "Success!", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new SharedEvents("Shared Events", user, c, app);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select an Event.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
