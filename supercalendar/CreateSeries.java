package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateSeries  extends JFrame {
    private JLabel title;
    private JButton CREATEButton;
    private JButton CANCELGOBACKButton;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField formattedTextField5;
    private JPanel panel;

    public CreateSeries(String title, Calendar c, App app) {
        super(title);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);


        CANCELGOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        CREATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = formattedTextField1.getText();
                String duration = formattedTextField2.getText();
                String frequency = formattedTextField5.getText();
                String number = formattedTextField3.getText();
                String startTime = formattedTextField4.getText();
                try{
                    Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(startTime);
                    int dur = Integer.parseInt(duration);
                    int freq = Integer.parseInt(frequency);
                    int num = Integer.parseInt(number);
                    c.getEventSeriesManager().create_event_series(name, dur, freq, num, start);
                    JOptionPane.showMessageDialog(null, "Event Series " + name + " is created!",
                            "Event Series Created!", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                    app.WriteToFile("phase2/Storage");
                }
                catch (Exception wrong){
                    JOptionPane.showMessageDialog(null, "Wrong formatting, please check input again.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
