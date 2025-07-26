import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        RubricaDbManager dbManager = new RubricaDbManager("credenziali_database.properties");

        SwingUtilities.invokeLater(() -> {
            JFrame _ = new LoginFrame(dbManager);
        });
    }
}