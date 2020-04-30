package supercalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMemo extends JFrame{
    private JButton editmemo;
    private JButton deletememo;
    private JButton backmemo;
    private JList<String> MemoList;
    private JPanel ViewMemo;

    public ViewMemo(String title, User user, Calendar c, App app) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ViewMemo);
        this.setLocationRelativeTo(null);
        this.setSize(700, 500);

        DefaultListModel<String> memo_list = new DefaultListModel<String>();
        for (Memo m: c.getMemoManager().getMemo()){
            memo_list.addElement(m.toString());
        }
        MemoList.setModel(memo_list);


        editmemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = MemoList.getSelectedIndex();
                if (index >=0) {
                    Memo old_memo = c.getMemoManager().getMemo().get(index);
                    String new_memo = JOptionPane.showInputDialog("Write the new memo here to edit");
                    c.getMemoManager().edit_memo(old_memo, new_memo);
                    JOptionPane.showMessageDialog(null, "You have edited memo for this event.",
                            "Edit tag Successful", JOptionPane.PLAIN_MESSAGE);
                    app.WriteToFile("phase2/Storage");

                }
            }
        });

        deletememo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = MemoList.getSelectedIndex();
                if (index >= 0) {
                    Memo To_be_deleted = c.getMemoManager().getMemo().get(index);
                    c.getMemoManager().delete_memo(To_be_deleted);
                    app.WriteToFile("phase2/Storage");
                    dispose();
                    JFrame frame = new ViewMemo("Calender", user, c, app);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please select one Memo.",
                            "ERROR", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        backmemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new HomeScreen("Calender", user, c, app);
                frame.setVisible(true);
                dispose();
            }
        });
    }
}
