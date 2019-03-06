package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JButton btn_addDoctor;
    private JButton btn_addPatient;
    public JPanel JPanel_mainWindow;
    private JButton btn_viewRecord;
    private JButton searchRecordButton;
    private JButton logoutButton;
    private JButton deleteRecordButton;

    public MainWindow(JFrame f) {

        f.setBounds(450, 140, 520, 430);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Main window");

        btn_addDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new AddDoctorWindow(f).JPanel_addDoctor);
                f.setVisible(true);
            }
        });

        btn_addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new AddPatientWindow(f).JPanel_addPatient);
                f.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new LoginForm(f).JPanel_login);
                f.setVisible(true);
            }
        });

        searchRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new SearchRecordWindow(f).JPanel_searchRecord);
                f.setVisible(true);
            }
        });

        btn_viewRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new ViewRecordWindow(f).JPanel_viewRecord);
                f.setVisible(true);
            }
        });

        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new DeleteRecordWindow(f).JPanel_deleteRecord);
                f.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new MainWindow(frame).JPanel_mainWindow);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        Icon logout_icon = new ImageIcon("Logo/logout.png");
        logoutButton = new JButton(logout_icon);

        Icon doctor_icon = new ImageIcon("Logo/doctor.png");
        btn_addDoctor = new JButton(doctor_icon);

        Icon patient_icon = new ImageIcon("Logo/patient.png");
        btn_addPatient = new JButton(patient_icon);

        Icon search_icon = new ImageIcon("Logo/search.png");
        searchRecordButton = new JButton(search_icon);

        Icon view_icon = new ImageIcon("Logo/view.png");
        btn_viewRecord = new JButton(view_icon);

        Icon cancel_icon = new ImageIcon("Logo/cancel.png");
        deleteRecordButton = new JButton(cancel_icon);
    }
}
