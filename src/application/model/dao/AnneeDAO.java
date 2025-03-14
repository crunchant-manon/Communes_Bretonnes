package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Annee;

public class AnneeDAO extends MyConnection<Annee> {

    @Override
    public ArrayList<Annee> findAll() {
        ArrayList<Annee> annees = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Annee");
            while (rs.next()) {
                String annee = rs.getString("ANNEE");
                String tauxInflation = rs.getString("tauxInflation");
                annees.add(new Annee(annee, tauxInflation));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annees;
    }

    @Override
    public int update(Annee annee, String login, String role) {
        // Implementation here
        return 0;
    }

    @Override
    public int delete(Annee annee, String login) {
        // Implementation here
        return 0;
    }

    @Override
    public int create(Annee annee) {
        // Implementation here
        return 0;
    }

    @Override
    public Annee findByLoginPwd(String login, String pwd) {
        // Implementation here
        return null;
    }

    public Annee findByAnnee(String annee) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Annee WHERE ANNEE = ?")) {
            st.setString(1, annee);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String tauxInflation = rs.getString("tauxInflation");
                return new Annee(annee, tauxInflation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
