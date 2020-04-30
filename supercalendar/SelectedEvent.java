package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectedEvent extends JFrame {


    private JPanel panel;
    private JLabel titleLabel;
    private JButton viewButton;
    private JButton createMemoButton;
    private JButton createFAButton;
    private JButton postponeButton;
    private JButton createIAButton;
    private JButton createTagLabel;
    private JButton addToSeriesButton;
    private JButton editButton;
    private JButton homeButton;
    private JButton shareEventButton;
    private JButton deleteEventButton;
    private JButton duplicateEventButton;

    public SelectedEvent(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        createMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateMemo("Create Memo", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, event, "View",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        createIAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateIndividualAlert("Create Individual Alert", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
        createFAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateFrequencyAlert("Create Frequency Alert", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
        postponeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new Postpone("Postpone Event", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
        createTagLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CreateTag("Create tags", user, c, app, event);
                frame.setVisible(true);
            }
        });

        addToSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new AddToSeries("Add to Series", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new EditScreen("Add to Series", user, c, app, event);
                frame.setVisible(true);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new HomeScreen("title", user, c, app);
                frame.setVisible(true);
                dispose();
            }
        });
        shareEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String m = JOptionPane.showInputDialog("Who do you want to share this Event with? Input username.");
                if(m != null && !("".equals(m))) {
                    if(m.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please enter a username.",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                            if (app.searchUser(m) != null && !m.equals(user.getName())) {
                                app.searchUser(m).shared(event);
                                JOptionPane.showMessageDialog(null, "Event " + event.getName() + " has been shared with " + app.searchUser(m).getName() + ".",
                                        "SHARED EVENT", JOptionPane.PLAIN_MESSAGE);
                                app.WriteToFile("phase2/Storage");
                            } else if (m.equals(user.getName())) {
                                    JOptionPane.showMessageDialog(null, "Enter someone else's username please. Stop trying to break our code...",
                                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                                } else {
                                JOptionPane.showMessageDialog(null, "Enter a username please.",
                                        "ERROR", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }
        });
        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Event has been deleted.",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
                c.getEventSeriesManager().delete_event(event);
                if (event.getAlerts().size() != 0) {
                    for (Alert a : event.getAlerts()) {
                        c.getAlertManager().delete_alert(a);
                    }
                }
                if (event.getMemo() != null) {
                    c.getMemoManager().getMemo().remove(event.getMemo());
                }
                app.WriteToFile("phase2/Storage");
                dispose();
                JFrame frame = new HomeScreen("Home Screen", user, c, app);
                frame.setVisible(true);
            }
        });

        duplicateEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new DuplicateEvent("duplicate Event " + event.getName(), c, app, event);
                frame.setVisible(true);
            }
        });
    }
}
