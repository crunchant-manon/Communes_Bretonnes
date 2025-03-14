package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Departement;

public class DepartementDAO extends MyConnection<Departement> {

    @Override
    public ArrayList<Departement> findAll() {
        ArrayList<Departement> departements = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Departement");
            while (rs.next()) {
                String idDep = rs.getString("IDDEP");
                String invesCulturel2019 = rs.getString("investissementCulturel2019");
                String nomDep = rs.getString("NOMDEP");
            
                departements.add(new Departement(idDep,nomDep, invesCulturel2019));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departements;
    }

    @Override
    public int update(Departement departement, String login, String role) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE Departement SET investissementCulturel2019 = ? WHERE IDDEP = ?")) {
            st.setString(1, departement.getInvesCulturel2019());
            st.setString(2, departement.getIdDep());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Departement departement, String login) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM Departement WHERE IDDEP = ?")) {
            st.setString(1, departement.getIdDep());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Departement departement) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Departement (IDDEP, investissementCulturel2019) VALUES (?, ?)")) {
            st.setString(1, departement.getIdDep());
            st.setString(2, departement.getInvesCulturel2019());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Departement findByLoginPwd(String login, String pwd) {
        // Not applicable for Departement, returning null
        return null;
    }

    public Departement findById(String idDep) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Departement WHERE IDDEP = ?")) {
            st.setString(1, idDep);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                idDep = rs.getString("IDDEP");
                String invesCulturel2019 = rs.getString("investissementCulturel2019");
                String nomDep = rs.getString("NOMDEP");
            
                return new Departement(idDep,nomDep, invesCulturel2019);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
