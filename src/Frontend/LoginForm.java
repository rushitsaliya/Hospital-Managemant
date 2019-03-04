package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JTextField textField_username;
    public JPanel JPanel_login;
    private JButton btn_login;
    private JButton btn_quit;
    private JPasswordField passwordField_password;

    public LoginForm(JFrame f) {

        f.setBounds(510, 230, 350, 200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Login");

        // to set default focus of username text field
        textField_username.requestFocus();


        btn_quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // to confirm quit action from user
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
                if (response == 0) {
                    f.dispose();
                }
            }
        });

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // to convert entered password from CharArray to String for validating it by equals method
                String pass = new String(passwordField_password.getPassword());

                if (textField_username.getText().equals("RushitSaliya") && pass.equals("1234")) {
                    f.setContentPane(new MainWindow(f).JPanel_mainWindow);
                    f.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new LoginForm(frame).JPanel_login);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Icon login_icon = new ImageIcon("Logo/login.png");
        btn_login = new JButton(login_icon);
        Icon quit_icon = new ImageIcon("Logo/quit.png");
        btn_quit = new JButton(quit_icon);
    }
}
