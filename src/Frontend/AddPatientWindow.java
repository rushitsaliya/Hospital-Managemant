package Frontend;

import Backend.Javaconnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientWindow {

    Connection conn = Javaconnect.ConnectDb();
    PreparedStatement pstmt = null;

    private JTextField textField_name;
    private JTextField textField_age;
    private JRadioButton radioButton_male;
    private JRadioButton radioButton_female;
    private JTextField textField_phoneNo;
    private JTextField textField_city;
    private JTextField textField_state;
    private JButton btn_save;
    private JButton btn_cancel;
    public JPanel JPanel_addPatient;

    public AddPatientWindow(JFrame f) {

        f.setBounds(370, 120, 580, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Add patient");

        // to group radio buttons for Gender selection (obviously!)
        ButtonGroup gender = new ButtonGroup();
        radioButton_male.setSelected(true);
        gender.add(radioButton_male);
        gender.add(radioButton_female);

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new MainWindow(f).JPanel_mainWindow);
                f.setVisible(true);
            }
        });

        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // to validate whether user has entered sufficient details or not
                    if (textField_name.getText().equals("") || textField_age.getText().equals("") ||
                            textField_phoneNo.getText().equals("") || textField_city.getText().equals("") ||
                            textField_state.getText().equals("")) {
                        throw new SQLException("Insufficient details provided!");
                    } else {
                        String sql = "INSERT INTO PATIENT(Name, Age, Gender, Phoneno, City) VALUES(?, ?, ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, textField_name.getText());
                        pstmt.setString(2, textField_age.getText());

                        if (radioButton_male.isSelected()) {
                            pstmt.setString(3, radioButton_male.getText());
                        } else {
                            pstmt.setString(3, radioButton_female.getText());
                        }

                        pstmt.setString(4, textField_phoneNo.getText());
                        pstmt.setString(5, textField_city.getText() + ", " + textField_state.getText());
                        pstmt.execute();

                        JOptionPane.showMessageDialog(null, "Patient added successfully!", "Success message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Logo/ok.png"));
                        // to set all textField and comboBox to its initial condition after entering record successfully
                        textField_name.setText("");
                        textField_age.setText("");
                        textField_phoneNo.setText("");
                        textField_city.setText("");
                        textField_state.setText("");
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Icon save_icon = new ImageIcon("Logo/save.png");
        btn_save = new JButton(save_icon);

        Icon cancel_icon = new ImageIcon("Logo/cancel.png");
        btn_cancel = new JButton(cancel_icon);
    }
}
