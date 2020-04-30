package supercalendar;

import com.sun.javafx.logging.JFRInputEvent;
import com.sun.javaws.util.JfxHelper;
import supercalendar.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTag extends JFrame{
    private JList<String> taglist;
    private JButton editButton;
    private JButton goBackButton;
    private JPanel edittag;

    public EditTag(String title, User user, Calendar c, App app, Event event) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(edittag);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> tag_list = new DefaultListModel<String>();
        for (String tag: c.getEventSeriesManager().get_tags(event)){
            tag_list.addElement(tag);
        }
        taglist.setModel(tag_list);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = taglist.getSelectedIndex();
                if (index >=0) {
                    String old_tag = c.getEventSeriesManager().get_tags(event).get(index);
                    String new_tag = JOptionPane.showInputDialog("Write the new tag here to edit");
                    if (c.getEventSeriesManager().edit_tag(event, old_tag, new_tag)){
                        JOptionPane.showMessageDialog(null, "You have edited tag for this event.",
                                "Edit tag Successful", JOptionPane.PLAIN_MESSAGE);
                        app.WriteToFile("phase2/Storage");
                    }else{
                        JOptionPane.showMessageDialog(null, "Please select one tag.",
                                "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one tag.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new EditScreen("Calender", user, c, app, event);
                frame.setVisible(true);
            }
        });
    }
}
