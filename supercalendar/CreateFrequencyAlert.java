package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFrequencyAlert extends JFrame {
    private JPanel panel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JRadioButton dayRadioButton;
    private JRadioButton minuteRadioButton;
    private JRadioButton hourRadioButton;
    private JTextField frequency_input;
    private JTextField startdate_input;
    private JTextField enddate_input;
    private String frequency_type;

    public CreateFrequencyAlert(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        ButtonGroup group = new ButtonGroup();
        group.add(minuteRadioButton);
        group.add(hourRadioButton);
        group.add(dayRadioButton);

        minuteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frequency_type = "minute";
            }
        });
        hourRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frequency_type = "hour";
            }
        });
        dayRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frequency_type = "day";

            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Start_time = startdate_input.getText();
                String End_time = enddate_input.getText();
                long frequency = Long.parseLong(frequency_input.getText());
                long frequency_in_millisecond = 0;
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(Start_time);
                    Date ed = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(End_time);
                    switch (frequency_type) {
                        case "minute":
                            frequency_in_millisecond = frequency * 60000;
                            break;
                        case "hour":
                            frequency_in_millisecond = frequency * 3600000;
                            break;
                        case "day":
                            frequency_in_millisecond = frequency * 86400000;
                            break;
                    }
                    c.getAlertManager().setFrequencyAlert(event, sd, ed, frequency_in_millisecond);
                    JOptionPane.showMessageDialog(null, "Frequency alert start on " + sd + " and" +
                                    " end on " + ed + " with every " + frequency + " " + frequency_type + " is created!",
                            "Frequency alert Created!", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");
                    JFrame frame = new SelectedEvent("selected event", user, c, app, event);
                    frame.setVisible(true);
                    dispose();
                }
                catch (Exception wrong){
                    JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new SelectedEvent("selected event", user, c, app, event);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
