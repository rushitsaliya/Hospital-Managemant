package Frontend;

import Backend.Javaconnect;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRecordWindow {
    Connection conn = Javaconnect.ConnectDb();
    PreparedStatement pstmt = null;
    ResultSet rst = null;

    private JComboBox<String> comboBox1;
    private JButton btn_viewRecord;
    private JTable table;
    public JPanel JPanel_viewRecord;
    private JButton btn_back;

    public ViewRecordWindow(JFrame f) {

        f.setBounds(260, 120, 1000, 450);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("View record");

        comboBox1.insertItemAt("DOCTOR", 0);
        comboBox1.insertItemAt("PATIENT", 1);
        comboBox1.setSelectedIndex(0);

        btn_viewRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT * FROM " + comboBox1.getSelectedItem().toString();
                try {
                    pstmt = conn.prepareStatement(sql);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rst = pstmt.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rst));
                    rst.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setContentPane(new MainWindow(f).JPanel_mainWindow);
                f.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);

        Icon view_icon = new ImageIcon("Logo/view.png");
        btn_viewRecord = new JButton(view_icon);

        Icon back_icon = new ImageIcon("Logo/back.png");
        btn_back = new JButton(back_icon);
    }
}
