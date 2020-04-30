package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Registration extends JFrame {
    private JButton register;
    private JPanel registration;
    private JPasswordField password1;
    private JPasswordField password2;
    private JLabel username;
    private JFormattedTextField name;
    private JButton GOBACKButton;
    private ArrayList<String> names = new ArrayList<String>();


    public Registration(String title, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registration);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        for(User u: app.getUsers()){
            names.add(u.getName());
        }

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) name.getText();
                String password = (String) Registration.this.password1.getText();
                String check = (String) (password2.getText());
                if (password.equals(check)) {
                    if(!names.contains(username)) {
                        User newUser = new User(username, password);
                        app.addUser(newUser);
                        app.WriteToFile("phase2/Storage");
                        JOptionPane.showMessageDialog(null, "Your account has been registered, please log in.",
                                "Success!", JOptionPane.PLAIN_MESSAGE);
                        dispose();
                        System.out.println("Created new User " + newUser.getName());
                        JFrame frame = new LoginScreen("Login Page", app);
                        frame.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username already exists.",
                                "Registration", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "password do not match, please re-enter password",
                            "Registration", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new LoginScreen("Login Page", app);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
