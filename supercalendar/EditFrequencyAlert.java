package supercalendar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditFrequencyAlert extends JFrame {
    private JPanel panel;
    private JTextField startdate_input;
    private JTextField enddate_input;
    private JRadioButton minuteRadioButton;
    private JRadioButton secondRadioButton;
    private JRadioButton hourRadioButton;
    private JRadioButton dayRadioButton;
    private JTextField frequency_input;
    private JButton confirmButton;
    private JButton cancelButton;
    private String frequency_type;

    public EditFrequencyAlert(String title, User user, Calendar c, App app, FrequencyAlert fa) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);
        ButtonGroup group = new ButtonGroup();
        group.add(secondRadioButton);
        group.add(minuteRadioButton);
        group.add(hourRadioButton);
        group.add(dayRadioButton);

        secondRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frequency_type = "second";
            }
        });
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
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(Start_time);
                    Date ed = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(End_time);
                    switch (frequency_type) {
                        case "second":
                            frequency = frequency * 1000;
                            break;
                        case "minute":
                            frequency = frequency * 60000;
                            break;
                        case "hour":
                            frequency = frequency * 3600000;
                            break;
                        case "day":
                            frequency = frequency * 86400000;
                            break;
                    }
                    c.getAlertManager().change_frequency_alert(fa, sd, ed, frequency);
                    JOptionPane.showMessageDialog(null, "Frequency alert start has been changed",
                            "Frequency alert Created!", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");
                    JFrame frame = new ViewAlert("View Alert", user, c, app);
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
                JFrame frame1 = new ViewAlert("View Alert", user, c, app);
                frame1.setVisible(true);
                dispose();
            }
        });
    }

}

