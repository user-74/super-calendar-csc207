package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewSearchedEvents extends JFrame{
    private JButton back;
    private JButton Select;
    private JList<String> SearchedEventList;
    private JPanel viewsearchedevents;

    public ViewSearchedEvents(String title, User user, Calendar c, App app, ArrayList<Event> searched_list) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(viewsearchedevents);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> events_list = new DefaultListModel<String>();
        for (Event e: searched_list){
            events_list.addElement(e.toString());
        }
        SearchedEventList.setModel(events_list);

        Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = SearchedEventList.getSelectedIndex();
                if (index >=0) {
                    Event event = searched_list.get(index);
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
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new HomeScreen("Calender", user, c, app);
                frame.setVisible(true);
            }
        });
    }
}
