package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    private JPanel home_screen;
    private JLabel title;
    private JButton searchButton;
    private JButton share;
    private JButton createEventSeriesButton;
    private JButton log_out;
    private JButton viewButton;
    private JButton createEventButton;
    private JButton switchCalendarButton;
    private JButton sharedWithMeButton;
    private JButton Viewalert;
    private JButton Viewmemo;

    public HomeScreen(String title, User user, Calendar c, App app){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(home_screen);
        this.setLocationRelativeTo(null);
        this.setSize(800, 600);

        System.out.println("This is the Home Screen for User " + user.getName());

        log_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                app.WriteToFile("phase2/Storage");
                JFrame frame = new LoginScreen("Login Page", app);
                frame.setVisible(true);
            }
        });

        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateEvent("Create Event", c, app);
                frame.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchScreen("Search", user, c, app);
                frame.setVisible(true);
            }
        });
        switchCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchCalendar("Calender", user, app);
                frame.setVisible(true);
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewEvents("View", user, c, app);
                frame.setVisible(true);
            }
        });
        createEventSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateSeries("Create Series", c, app);
                frame.setVisible(true);
            }
        });
        share.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String m = JOptionPane.showInputDialog("Who do you want to share this Calendar with? Input username.");
                if(m.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a username.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    if (app.searchUser(m) != null && !m.equals(user.getName())){
                        app.searchUser(m).addCalendar(c);
                        JOptionPane.showMessageDialog(null, "Calendar " + c.getName() + " has been shared with " + app.searchUser(m).getName() + ".",
                                "SHARED CALENDAR", JOptionPane.PLAIN_MESSAGE);
                        app.WriteToFile("phase2/Storage");
                    }
                    else if (app.searchUser(m) == null){
                        JOptionPane.showMessageDialog(null, "Username does not exists. Try again.",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Don't share with yourself... Share with someone else please!",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        sharedWithMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SharedEvents("Shared Events", user, c, app);
                frame.setVisible(true);
                dispose();
            }
        });
        Viewalert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewAlert("View", user, c, app);
                frame.setVisible(true);

            }
        });
        Viewmemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewMemo("View", user, c, app);
                frame.setVisible(true);

            }
        });
    }
}
