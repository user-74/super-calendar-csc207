package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCalendar extends JFrame {


    private JPanel createCalendar;
    private JLabel label;
    private JFormattedTextField calendarName;
    private JButton create;

    public CreateCalendar(String title, User user, App app){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(createCalendar);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);



        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = calendarName.getText();
                if (user.searchCalendar(name) == null){
                    JOptionPane.showMessageDialog(null, "Great! You have created a Calendar named " + name + "!",
                            "Congrats!", JOptionPane.PLAIN_MESSAGE);
                    Calendar c = new Calendar(name);
                    user.addCalendar(c);
                    app.WriteToFile("phase2/storage");
                    System.out.println("created Calendar " + name);
                    dispose();
                    JFrame frame = new HomeScreen("Home Screen", user, c, app);
                    frame.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null, "There already exists a Calendar named " + name,
                            "Already Exists", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
