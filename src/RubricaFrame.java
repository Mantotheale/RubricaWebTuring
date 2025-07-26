import javax.swing.*;
import java.awt.*;

public class RubricaFrame extends JFrame {
    public RubricaFrame(RubricaDbManager dbManager) {
        super("Rubrica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Rubrica rubrica = new Rubrica(dbManager);
        JTable tabella = new JTable(rubrica);

        JScrollPane scrollPane = new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

        BottoniRubrica bottoni = new BottoniRubrica(this, rubrica, tabella);
        add(bottoni, BorderLayout.NORTH);

        setSize(600, 600);
        setVisible(true);
    }
}
