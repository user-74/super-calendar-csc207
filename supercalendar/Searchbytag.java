package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Searchbytag extends JFrame{
    private JPanel searchbytag;
    private JFormattedTextField taketag;
    private JButton back;
    private JButton addtag;
    private JButton searchconfirm;
    private ArrayList<String> tag_taken;

    public Searchbytag( String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(searchbytag);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
        this.tag_taken = new ArrayList<>();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                frame.setVisible(true);
            }
        });
        addtag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tag = taketag.getText();
                if (!tag.equals("")){tag_taken.add(tag);
                    taketag = new JFormattedTextField();
                    JOptionPane.showMessageDialog(null, "Tag " + taketag.getText()
                                    + " is added to list of tags you typed! Choose to continue to add or to search",
                            "Tag added!", JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Enter a tag to continue",
                            "Action failed!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        searchconfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tag_taken.size() != 0){
                    ArrayList<Event> result_event = c.getEventSeriesManager().search(tag_taken);
                    if (result_event.size() == 0){
                        JOptionPane.showMessageDialog(null, "No event is found by tags your typed",
                                "Search Failed!", JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        JFrame frame = new SearchScreen("SearchScreen", user, c, app);
                        frame.setVisible(true);
                    }else{
                        dispose();
                        JFrame frame = new ViewSearchedEvents("ViewSearchedEvents", user, c, app, result_event);
                        frame.setVisible(true);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Enter a tag to continue",
                            "No tag has been entered", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
