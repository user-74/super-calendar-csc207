package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewAlertOfThisEvent extends JFrame {
    private JPanel panel;
    private JList<String> AlertList;
    private JButton editButton;
    private JButton backButton;
    private JButton deleteButton;

    public ViewAlertOfThisEvent(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> alerts = new DefaultListModel<String>();
        for (Alert a: event.getAlerts()) {
            alerts.addElement(a.toString());
        }
        AlertList.setModel(alerts);


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = AlertList.getSelectedIndex();
                if (i >= 0) {
                    Alert To_be_edit = c.getAlertManager().getAlerts().get(i);
                    if (To_be_edit instanceof IndividualAlert) {
                        JFrame edit_individual_frame = new EditIndividualAlert("Edit Individual Alert", user, c, app, (IndividualAlert)To_be_edit);
                        edit_individual_frame.setVisible(true);
                        dispose();
                    }
                    else if (To_be_edit instanceof FrequencyAlert) {
                        JFrame edit_frequency_frame = new EditFrequencyAlert("Edit Frequency ALert", user, c, app, (FrequencyAlert)To_be_edit);
                        edit_frequency_frame.setVisible(true);
                        dispose();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Alert.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = AlertList.getSelectedIndex();
                if (index >= 0) {
                    Alert To_be_deleted = c.getAlertManager().getAlerts().get(index);
                    c.getAlertManager().delete_alert(To_be_deleted);
                    app.WriteToFile("phase2/Storage");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Alert.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame EditFrame = new EditScreen("Edit Screen", user, c, app, event);
                EditFrame.setVisible(true);
                dispose();
            }
        });
    }
}
