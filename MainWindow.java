import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainWindow {
    private JLabel lbl_title;
    private JPanel JPanel_mainWindow;
    private JLabel lbl_adminPanel;
    private JButton btn_doctorAddRecord;
    private JButton btn_doctorViewOrDeleteRecord;
    private JLabel lbl_doctor;
    private JLabel lbl_patient;
    private JButton btn_patientAddRecord;
    private JButton btn_patientViewOrDeleteRecord;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital Management Project");
        frame.setContentPane(new MainWindow().JPanel_mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
