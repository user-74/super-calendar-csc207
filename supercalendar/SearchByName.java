package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchByName extends JFrame{
    private JPanel Searchbyname;
    private JButton Back;
    private JButton Search;
    private JFormattedTextField name;

    public SearchByName(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Searchbyname);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                frame.setVisible(true);
            }
        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String EventName = name.getText();
                ArrayList<Event> result_event = c.getEventSeriesManager().search(EventName);
                if (result_event.size() == 0){
                    JOptionPane.showMessageDialog(null, "No event is found by name your typed",
                            "Search Failed!", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                    frame.setVisible(true);
                }else{
                    dispose();
                    JFrame frame = new ViewSearchedEvents("ViewSearchedEvents", user, c, app, result_event);
                    frame.setVisible(true);
                }
            }
        });
    }
}
