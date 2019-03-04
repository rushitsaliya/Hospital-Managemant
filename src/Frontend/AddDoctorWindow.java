package Frontend;

import Backend.Javaconnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDoctorWindow {

    Connection conn = Javaconnect.ConnectDb();
    PreparedStatement pstmt = null;

    public JPanel JPanel_addDoctor;
    private JTextField textField_name;
    private JTextField textField_age;
    private JRadioButton radioButton_male;
    private JRadioButton radioButton_female;
    private JComboBox<String> comboBox_availability;
    private JButton btn_save;
    private JButton btn_cancel;
    private JComboBox<String> comboBox_specialization;

    public AddDoctorWindow(JFrame f) {

        f.setBounds(350, 120, 580, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Add doctor");

        // to group radio buttons for Gender selection (obviously!)
        ButtonGroup gender = new ButtonGroup();
        radioButton_male.setSelected(true);
        gender.add(radioButton_male);
        gender.add(radioButton_female);

        // to insert area of specialization in the comboBox
        comboBox_specialization.insertItemAt("Addiction psychiatrist", 0);
        comboBox_specialization.insertItemAt("Cardiologist", 1);
        comboBox_specialization.insertItemAt("Dermatologist", 2);
        comboBox_specialization.insertItemAt("Family Physician", 3);
        comboBox_specialization.insertItemAt("Gynecologist", 4);
        comboBox_specialization.insertItemAt("Neurologist", 5);
        comboBox_specialization.insertItemAt("Psychiatrist", 6);
        comboBox_specialization.setSelectedIndex(0);    // to set default item in comboBox

        
        // to insert availability time-slots into comboBox
        comboBox_availability.insertItemAt("9am-12pm", 0);
        comboBox_availability.insertItemAt("11am-2pm", 1);
        comboBox_availability.insertItemAt("1pm-4pm", 2);
        comboBox_availability.insertItemAt("3pm-6pm", 3);
        comboBox_availability.insertItemAt("5pm-8pm", 4);
        comboBox_availability.setSelectedIndex(0);  // to set default item in comboBox

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
                    if (textField_name.getText().equals("") || textField_age.getText().equals(""))
                    {
                        throw new SQLException("Insufficient details provided!");
                    } else {
                        String sql = "INSERT INTO DOCTOR(Name, Age, Gender, Specialization, Availability) VALUES(?, ?, ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, "Dr." + textField_name.getText());
                        pstmt.setString(2, textField_age.getText());

                        if (radioButton_male.isSelected()) {
                            pstmt.setString(3, radioButton_male.getText());
                        } else {
                            pstmt.setString(3, radioButton_female.getText());
                        }

                        pstmt.setString(4, comboBox_specialization.getSelectedItem().toString());
                        pstmt.setString(5, comboBox_availability.getSelectedItem().toString());
                        pstmt.execute();

                        JOptionPane.showMessageDialog(null, "Doctor added successfully!", "Success message", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Logo/ok.png"));
                        // to set all textField and comboBox to its initial condition after entering record successfully
                        textField_name.setText("");
                        textField_age.setText("");
                        comboBox_specialization.setSelectedIndex(0);
                        comboBox_availability.setSelectedIndex(0);
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
