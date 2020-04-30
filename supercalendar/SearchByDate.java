package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchByDate extends JFrame{
    private JFormattedTextField Date;
    private JButton back;
    private JButton searchbyDate;
    private JPanel SearchbyDate;

    public SearchByDate(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(SearchbyDate);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                frame.setVisible(true);
            }
        });
        searchbyDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String starttime = Date.getText();
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd").parse(starttime);
                    ArrayList<Event> result_events = c.getEventSeriesManager().search(sd);
                    if (result_events.size() == 0){
                        System.out.println("1");
                    }
                    dispose();
                    JFrame frame = new ViewSearchedEvents("ViewSearchedEvents", user, c, app, result_events);
                    frame.setVisible(true);
                }
                catch (Exception wrong){
                    JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
    }
}
