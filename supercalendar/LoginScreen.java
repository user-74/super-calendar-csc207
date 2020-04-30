package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class LoginScreen extends JFrame {
    private JButton login;
    private JButton newUser;
    private JFormattedTextField formattedTextField1;
    private JPasswordField passwordField1;
    private JPanel Log_in_screen;
    private App appForWorker;


    public LoginScreen(String title, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Log_in_screen);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
        this.appForWorker = app;


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) (formattedTextField1.getText());
                if (app.searchUser(username) == null) {
                    JOptionPane.showMessageDialog(null, "Cannot Find Username",
                            "CALENDAR", JOptionPane.PLAIN_MESSAGE);
                } else {
                    User user = app.searchUser(username);
                    String password = (String) (passwordField1.getText());
                    if (app.checkPassword(user, password)) {
                        JOptionPane.showMessageDialog(null, "Welcome to Super-Calendar, " + username,
                                "CALENDAR", JOptionPane.PLAIN_MESSAGE);
                        start();
                        System.out.println("This is User " + user.getName());
                        JFrame frame = new SearchCalendar("Calendar", user, app);
                        frame.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Password Incorrect, " + username,
                                "CALENDAR", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Registration("Registration", app);
                frame.setVisible(true);
                dispose();
            }
        });
    }

    private void start() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                String username = (String) (formattedTextField1.getText());
                User cu = appForWorker.searchUser(username);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        for (Calendar calendar : cu.getCalendars()) {
                            for (Alert a : calendar.getAlertManager().getAlerts()) {
                                a.checkAlert();
                            }
                        }
                    }
                };
                timer.schedule(task, 0, 1000);  // 3rd parameter is the update rate in ms
                return null;
            }
        };
        worker.execute();
    }
}
