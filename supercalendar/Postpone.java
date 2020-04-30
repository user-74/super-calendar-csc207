package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Postpone extends JFrame{
    private JPanel panel;
    private JTextField days_input;
    private JButton confirmButton;
    private JButton cancelButton;

    public Postpone(String title,User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String days = days_input.getText();
                    if (days.equals("")){
                        c.getEventSeriesManager().postponeEvent(event);
                    } else{
                        c.getEventSeriesManager().postponeEvent(event, Integer.parseInt(days));
                    }
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new SelectedEvent(title, user, c, app, event);
                    frame.setVisible(true);
                    
                } catch (Exception wrong) {
                    JOptionPane.showMessageDialog(null, "Wrong Format! Please enter again.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SelectedEvent(title, user, c, app, event);
                frame.setVisible(true);
            }
        });
    }
}
