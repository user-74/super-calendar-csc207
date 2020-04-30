package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCalendar extends JFrame {


    private JPanel search_page;
    private JButton newCalendarButton;
    private JButton SEARCHButton;
    private JLabel question;
    private JList<String> list_of_calendars;
    private JButton logoutButton;

    public SearchCalendar(String title, User user, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(search_page);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);


        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (Calendar c: user.getCalendars()){
            listModel.addElement(c.getName());
        }
        list_of_calendars.setModel(listModel);

        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list_of_calendars.getSelectedIndex();
                if (index >= 0) {
                    Calendar cal = user.getCalendars().get(index);
                    JOptionPane.showMessageDialog(null, "Welcome to Calendar " + cal.getName() + ", " + user.getName(),
                            "Welcome", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    JFrame frame = new HomeScreen("Home Page", user, cal, app);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Cannot Find your Calendar? Maybe create a new one?",
                            "CALENDAR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        newCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateCalendar("New Calendar", user, app);
                frame.setVisible(true);
                dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new LoginScreen("Login Page", app);
                frame.setVisible(true);
            }
        });
    }
}