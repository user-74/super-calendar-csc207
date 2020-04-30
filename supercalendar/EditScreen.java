package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditScreen extends JFrame{
    private JButton editEventStartTimeButton;
    private JPanel EditScreen;
    private JButton editEventNameButton;
    private JButton editTagButton;
    private JButton editIndividualAlertButton;
    private JButton editMemoButton;
    private JButton editEventRelatedSeriesButton;
    private JButton goBackButton;
    private JButton editEventEndTimeButton;
    private JButton editAlert;

    public EditScreen(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(EditScreen);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        editTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new EditTag("Calender", user, c, app, event);
                frame.setVisible(true);
            }
        });
        editMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_memo = JOptionPane.showInputDialog("Write the memo here to edit");
                c.getMemoManager().edit_memo(c.getEventSeriesManager().find_memo(event), new_memo);
                JOptionPane.showMessageDialog(null, "You have edited the memo of this event",
                        "Edit Memo Successful", JOptionPane.PLAIN_MESSAGE);
                app.WriteToFile("phase2/Storage");
            }
        });
        editEventNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_name = JOptionPane.showInputDialog("Write the new event name here to edit");
                c.getEventSeriesManager().edit_name(event, new_name);
                JOptionPane.showMessageDialog(null, "You have edited the name of this event",
                        "Edit Name Successful", JOptionPane.PLAIN_MESSAGE);
                app.WriteToFile("phase2/Storage");
            }
        });
        editAlert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new ViewAlertOfThisEvent("View Alert of this Event", user, c, app, event);
                frame.setVisible(true);
            }
        });

        editEventRelatedSeriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_series = JOptionPane.showInputDialog("Write the new series name here to edit");
                if (c.getEventSeriesManager().change_event_to_another_series(event, new_series)){
                    JOptionPane.showMessageDialog(null, "You have edited the new series of this event",
                            "Edit Series Successful", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");
                }else{
                    JOptionPane.showMessageDialog(null, "You Failed to edit the new series of" +
                                    " this event, Try again",
                            "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        editEventStartTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = JOptionPane.showInputDialog("Write the new Start time here to edit (yyyy/MM/dd HH:mm:ss)");
                if(time != null && !("".equals(time))) {
                    try {
                        Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
                        c.getEventSeriesManager().edit_start_time(event, sd);
                        JOptionPane.showMessageDialog(null, "You have edited the new start time of this event",
                                "Edit Start time Successful", JOptionPane.PLAIN_MESSAGE);
                        app.WriteToFile("phase2/Storage");
                    } catch (Exception wrong) {
                        JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again, yyyy/MM/dd HH:mm:ss.",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        editEventEndTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = JOptionPane.showInputDialog("Write the new End time here to edit (yyyy/MM/dd HH:mm:ss)");
                if(time != null && !("".equals(time))) {
                    try {
                        Date ed = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
                        c.getEventSeriesManager().edit_end_time(event, ed);
                        JOptionPane.showMessageDialog(null, "You have edited the new end time of this event",
                                "Edit End time Successful", JOptionPane.PLAIN_MESSAGE);
                        app.WriteToFile("phase2/Storage");
                    } catch (Exception wrong) {
                        JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again, yyyy/MM/dd HH:mm:ss.",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SelectedEvent("Calender", user, c, app, event);
                frame.setVisible(true);
            }
        });
    }
}
