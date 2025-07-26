import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        RubricaDbManager dbManager = new RubricaDbManager("credenziali_database.properties");
        Rubrica rubrica = new Rubrica(dbManager);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rubrica");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTable tabella = new JTable(rubrica);

            JScrollPane scrollPane = new JScrollPane(tabella);
            frame.add(scrollPane, BorderLayout.CENTER);

            BottoniRubrica bottoni = new BottoniRubrica(frame, rubrica, tabella);
            frame.add(bottoni, BorderLayout.SOUTH);

            frame.setSize(600, 600);
            frame.setLocation(100, 100);
            frame.setVisible(true);
        });
    }
}