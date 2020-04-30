package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchbyMemo extends JFrame{
    private JFormattedTextField memo;
    private JButton searchbymemo;
    private JButton back;
    private JPanel Searchbymemo;

    public SearchbyMemo(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Searchbymemo);
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
        searchbymemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = memo.getText();
                ArrayList<Event> result_event = new ArrayList<>();
                result_event.addAll(c.getEventSeriesManager().search_memo(message));
                if (result_event.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No event is found by memo your typed",
                            "Search Failed!", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                    frame.setVisible(true);
                } else{
                    dispose();
                    JFrame frame = new ViewSearchedEvents("ViewSearchedEvents", user, c, app, result_event);
                    frame.setVisible(true);
                }
            }
        });
    }
}
