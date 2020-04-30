package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CreateTag extends JFrame {
    private JPanel createTagPane;
    private JTextField enterTheTagHereTextField;
    private JButton createButton;
    private JButton backButton;
    private JLabel noteLabel;

    public CreateTag(String title, User user, Calendar c, App app, Event event){
        super(title);
        this.setContentPane(createTagPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tagInput = enterTheTagHereTextField.getText();
                String[] tagArray = tagInput.split(",");
                ArrayList<String> tagList = new ArrayList<>();
                Collections.addAll(tagList, tagArray);
                ArrayList<Event> event_associate= new ArrayList<>();
                event_associate.add(event);
                c.getEventSeriesManager().create_tag(tagList, event_associate);
                JOptionPane.showMessageDialog(null, "Tag(s) created",
                        "Done", JOptionPane.PLAIN_MESSAGE);
                app.WriteToFile("phase2/storage");
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
