package supercalendar;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditIndividualAlert extends JFrame{
    private JPanel panel;
    private JFormattedTextField formattedTextField1;
    private JButton confirmButton;
    private JButton cancelButton;

    public EditIndividualAlert(String title, User user, Calendar c, App app, IndividualAlert ia) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);



        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = formattedTextField1.getText();
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(time);
                    c.getAlertManager().change_individual_alert(ia, sd);
                    JOptionPane.showMessageDialog(null, "Individual alert is set to " + time,
                            "Individual alert Created!", JOptionPane.PLAIN_MESSAGE);
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
