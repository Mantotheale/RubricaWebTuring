import javax.swing.*;

public class LoginFrame extends JFrame {
    private final UserField userField;
    private final PasswordField passwordField;
    private final RubricaDbManager dbManager;

    public LoginFrame(RubricaDbManager rubricaDbManager) {
        this.dbManager = rubricaDbManager;

        setTitle("Login Rubrica");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        userField = new UserField();
        passwordField = new PasswordField();
        add(userField);
        add(passwordField);

        LoginButton button = new LoginButton(this);
        add(button);

        setVisible(true);
    }

    private void inviaForm() {
        String username = userField.getUser();
        String password = passwordField.getPassword();
        if (dbManager.verificaUtente(username, password)) {
            dispose();
            RubricaFrame rubricaFrame = new RubricaFrame(dbManager);
            rubricaFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login errato");
        }
    }

    private static class UserField extends JPanel {
        private final JTextField field = new JTextField(15);

        public UserField() {
            super();
            add(new JLabel("Username"));
            add(field);
        }

        public String getUser() {
            return field.getText();
        }
    }

    private static class PasswordField extends JPanel {
        private final JPasswordField field = new JPasswordField(15);

        public PasswordField() {
            super();
            add(new JLabel("Password"));
            add(field);
        }

        public String getPassword() {
            return String.valueOf(field.getPassword());
        }
    }

    private static class LoginButton extends JPanel {
        public LoginButton(LoginFrame frame) {
            super();

            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(_ -> frame.inviaForm());
            add(loginButton);
        }
    }
}