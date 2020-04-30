package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchScreen extends JFrame {
    private JPanel Searchhome;
    private JButton Bytag;
    private JButton Bymemo;
    private JButton Bydate;
    private JButton Back;
    private JButton SearchByName;

    public SearchScreen(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Searchhome);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        Bytag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new Searchbytag("SearchByTag", user, c, app);
                frame.setVisible(true);
            }
        });
        Bymemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchbyMemo("SearchByMemo", user, c, app);
                frame.setVisible(true);
            }
        });
        Bydate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchByDate("SearchByDate", user, c, app);
                frame.setVisible(true);
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new HomeScreen("Calender", user, c, app);
                frame.setVisible(true);
            }
        });
        SearchByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SearchByName("Calender", user, c, app);
                frame.setVisible(true);
            }
        });
    }
}
