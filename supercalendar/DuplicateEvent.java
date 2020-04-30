package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DuplicateEvent extends JFrame{
    private JPanel panel;
    private JFormattedTextField input_st;
    private JFormattedTextField input_et;
    private JButton DUPLICATEButton;
    private JButton BACKButton;

    public DuplicateEvent(String title, Calendar c, App app, Event event) {
        super(title);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);


        DUPLICATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String starttime = input_st.getText();
                String endtime = input_et.getText();
                try{
                    Date sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(starttime);
                    Date ed = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(endtime);
                    if(sd.compareTo(ed) > 0){
                        JOptionPane.showMessageDialog(null, "Why is your start time AFTER end time???",
                                "ERROR", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        c.getEventSeriesManager().duplicateEvent(event, sd, ed);
                        JOptionPane.showMessageDialog(null, "Event " + event.getName() + " is duplicated!",
                                "Event duplicated!", JOptionPane.PLAIN_MESSAGE);
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


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
