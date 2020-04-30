package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CreateMemo extends JFrame{
    private JPanel memoPanel;
    private JButton createMemo;
    private JButton cancelButton;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JLabel msgLable;
    private JLabel eventNameLable;
    private JLabel topLable;

    public CreateMemo(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(memoPanel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        createMemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String memoMsg = formattedTextField1.getText();
                    String eventName = formattedTextField2.getText();
                    ArrayList<Event> events = c.getEventSeriesManager().search(eventName);
                    events.add(event);
                    if(eventName.isEmpty()){
                        c.getMemoManager().create_memo(memoMsg, events);
                        JOptionPane.showMessageDialog(null, "Memo is created!",
                                "Memo Created!", JOptionPane.PLAIN_MESSAGE);
                        JFrame frame = new SelectedEvent("Select Event", user, c, app, event);
                        frame.setVisible(true);
                        dispose();
                        app.WriteToFile("phase2/Storage");
                    }
                    else {
                        if (events.size() == 1) {
                            JOptionPane.showMessageDialog(null, "Event not found.",
                                    "ERROR", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            c.getMemoManager().create_memo(memoMsg, events);
                            JOptionPane.showMessageDialog(null, "Memo is created!",
                                    "Memo Created!", JOptionPane.PLAIN_MESSAGE);
                            JFrame frame = new SelectedEvent("Select Event", user, c, app, event);
                            frame.setVisible(true);
                            dispose();
                            app.WriteToFile("phase2/Storage");
                        }
                    }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SelectedEvent("Select Event", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }

        });
    }
}
