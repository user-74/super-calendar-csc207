package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEvent extends JFrame{
    private JPanel createEvent;
    private JButton createButton;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JButton cancelButton;


    public CreateEvent(String title, Calendar c, App app){
        super(title);
        this.setContentPane(createEvent);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String starttime = formattedTextField2.getText();
                String endtime = formattedTextField3.getText();
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(starttime);
                    Date ed = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endtime);
                    if(sd.compareTo(ed) > 0){
                        JOptionPane.showMessageDialog(null, "Why is your start time AFTER end time???",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        c.getEventSeriesManager().create_event(formattedTextField1.getText(), sd, ed);
                        JOptionPane.showMessageDialog(null, "Event " + formattedTextField1.getText() + " is created!",
                                "Event Created!", JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        app.WriteToFile("phase2/Storage");
                    }
                }
                catch (Exception wrong){
                    JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
    }
}
