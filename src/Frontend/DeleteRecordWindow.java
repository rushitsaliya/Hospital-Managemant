package Frontend;

import Backend.Javaconnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecordWindow {

    Connection conn = Javaconnect.ConnectDb();
    PreparedStatement pstmt = null;

    private JTextField textField_index;
    public JPanel JPanel_deleteRecord;
    private JButton btn_delete;
    private JButton btn_cancel;
    private JComboBox<String> comboBox_tableName;

    public DeleteRecordWindow(JFrame f) {

        f.setBounds(490, 225, 400, 180);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Delete Window");

        // to insert area of specialization in the comboBox
        comboBox_tableName.insertItemAt("DOCTOR", 0);
        comboBox_tableName.insertItemAt("PATIENT", 1);
        comboBox_tableName.setSelectedIndex(0);    // to set default item in comboBox

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new MainWindow(f).JPanel_mainWindow);
                f.setVisible(true);
            }
        });

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = null;

                    if (textField_index.getText().equals("")) {
                        throw new SQLException("Invalid or insufficient detail provided!");
                    } else {

                        if (comboBox_tableName.getSelectedItem().toString().equals("DOCTOR")) {
                            sql = "DELETE FROM DOCTOR WHERE Did = ?";
                        } else {
                            sql = "DELETE FROM PATIENT WHERE Pid = ?";
                        }

                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, textField_index.getText());
                        pstmt.execute();

                        JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Success message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Logo/ok.png"));

                        // to set all textField and comboBox to its initial condition after entering record successfully
                        textField_index.setText("");
                        comboBox_tableName.setSelectedIndex(0);
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new DeleteRecordWindow(frame).JPanel_deleteRecord);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Icon delete_icon = new ImageIcon("Logo/cancel.png");
        btn_delete = new JButton(delete_icon);

        Icon back_icon = new ImageIcon("Logo/back.png");
        btn_cancel = new JButton(back_icon);
    }
}

