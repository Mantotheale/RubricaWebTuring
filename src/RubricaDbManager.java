import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RubricaDbManager {
    private final String url;
    private final String user;
    private final String password;

    public RubricaDbManager(String credentialsPath) {
        try (InputStream input = new FileInputStream(credentialsPath)) {
            Properties properties = new Properties();
            properties.load(input);

            String ip = properties.getProperty("ip-server-mysql");
            String porta = properties.getProperty("porta");
            url = "jdbc:mysql://" + ip + ":" + porta + "/rubrica";
            user = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection connetti() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public List<Persona> getPersone() {
        List<Persona> lista = new ArrayList<>();
        try (Connection conn = connetti();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM persone")) {

            while (rs.next()) {
                lista.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cognome"),
                        rs.getString("indirizzo"),
                        rs.getString("telefono"),
                        rs.getInt("eta")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void aggiungiPersona(Persona p) {
        String sql = "INSERT INTO persone (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connetti();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.nome());
            ps.setString(2, p.cognome());
            ps.setString(3, p.indirizzo());
            ps.setString(4, p.telefono());
            ps.setInt(5, p.eta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminaPersona(Persona p) {
        String sql = "DELETE FROM persone WHERE id = ?";
        try (Connection conn = connetti();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modificaPersona(Persona personaVecchia, Persona personaNuova) {
        eliminaPersona(personaVecchia);
        aggiungiPersona(personaNuova);
    }

    public boolean verificaUtente(String username, String password) {
        String sql = "SELECT * FROM utenti WHERE username=? AND password=?";
        try (Connection conn = connetti();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
