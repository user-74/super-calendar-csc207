package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateIndividualAlert extends JFrame {
    private JPanel panel;
    private JFormattedTextField formattedTextField1;
    private JButton createButton;
    private JButton cancelButton;

    public CreateIndividualAlert(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = formattedTextField1.getText();
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(time);
                    c.getAlertManager().setIndividualAlert(event, sd);
                    JOptionPane.showMessageDialog(null, "Individual alert at " + time + " is created!",
                            "Individual alert Created!", JOptionPane.PLAIN_MESSAGE);
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
                JFrame frame1 = new SelectedEvent("selected event", user, c, app, event);
                frame1.setVisible(true);
                dispose();
            }
        });
    }
}
