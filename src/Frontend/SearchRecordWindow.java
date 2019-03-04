package Frontend;

import Backend.Javaconnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchRecordWindow {

    Connection conn = Javaconnect.ConnectDb();
    PreparedStatement pstmt = null;
    ResultSet rst = null;

    private JTextField textField_searchRecord;
    private JButton btn_search;
    private JLabel lbl_nameResult;
    private JLabel lbl_ageResult;
    private JLabel lbl_genderResult;
    private JLabel lbl0;
    private JLabel lbl0_result;
    private JLabel lbl1;
    private JLabel lbl1_result;
    private JButton btn_ok;
    private JButton btn_back;
    public JPanel JPanel_searchRecord;
    private JComboBox<String> comboBox_tableName;
    private JComboBox<String> comboBox_fieldName;

    public SearchRecordWindow(JFrame f) {

        // to open frame into fullscreen
        f.setBounds(390, 150, 580, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Search record");

        comboBox_tableName.insertItemAt("DOCTOR", 0);
        comboBox_tableName.insertItemAt("PATIENT", 1);
        comboBox_tableName.setSelectedIndex(0);

        comboBox_fieldName.insertItemAt("ID", 0);
        comboBox_fieldName.insertItemAt("Name", 1);
        comboBox_fieldName.setSelectedIndex(0);

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new MainWindow(f).JPanel_mainWindow);
                f.setVisible(true);
            }
        });

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String field = null;
                try {
                    if (comboBox_fieldName.getSelectedItem().toString().equals("ID") &&
                            comboBox_tableName.getSelectedItem().toString().equals("DOCTOR")) {
                        field = "Did";
                    } else if (comboBox_fieldName.getSelectedItem().toString().equals("ID") &&
                            comboBox_tableName.getSelectedItem().toString().equals("PATIENT")) {
                        field = "Pid";
                    } else if (comboBox_fieldName.getSelectedItem().toString().equals("Name")) {
                        field = "Name";
                    }

                    String sql = "SELECT * FROM " + comboBox_tableName.getSelectedItem().toString() + " WHERE " +
                            field +" = ?";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, textField_searchRecord.getText());
                    rst = pstmt.executeQuery();

                    lbl_nameResult.setText(rst.getString("Name"));
                    lbl_ageResult.setText(rst.getString("Age"));
                    lbl_genderResult.setText(rst.getString("Gender"));

                    if (comboBox_tableName.getSelectedItem().toString().equals("DOCTOR")) {
                        lbl0.setText("Area of Specialization");
                        lbl0_result.setText(rst.getString("Specialization"));
                        lbl1.setText("Availability");
                        lbl1_result.setText(rst.getString("Availability"));
                    } else {
                        lbl0.setText("Phone No.");
                        lbl0_result.setText(rst.getString("Phoneno"));
                        lbl1.setText("City");
                        lbl1_result.setText(rst.getString("City"));
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Insufficient or invalid details provided!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_nameResult.setText("");
                lbl_ageResult.setText("");
                lbl_genderResult.setText("");
                lbl0.setText("");
                lbl0_result.setText("");
                lbl1.setText("");
                lbl1_result.setText("");

                textField_searchRecord.setText("");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Icon search_icon = new ImageIcon("Logo/search.png");
        btn_search = new JButton(search_icon);

        Icon ok_icon = new ImageIcon("Logo/ok.png");
        btn_ok = new JButton(ok_icon);

        Icon back_icon = new ImageIcon("Logo/back.png");
        btn_back = new JButton(back_icon);
    }
}
