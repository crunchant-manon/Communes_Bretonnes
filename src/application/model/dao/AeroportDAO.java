package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Aeroport;

public class AeroportDAO extends MyConnection<Aeroport> {

    @Override
    public ArrayList<Aeroport> findAll() {
        ArrayList<Aeroport> aeroports = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Aeroport");
            while (rs.next()) {
                String nom = rs.getString("NOM");
                String adresse = rs.getString("ADRESSE");
                String dep = rs.getString("LeDepartement");
                aeroports.add(new Aeroport(nom, adresse,dep));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return aeroports;
    }

    @Override
    public int update(Aeroport aeroport, String login, String role) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE Aeroport SET ADRESSE = ? WHERE NOM = ?")) {
            st.setString(1, aeroport.getAdresse());
            st.setString(2, aeroport.getNom());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Aeroport aeroport, String login) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM Aeroport WHERE NOM = ?")) {
            st.setString(1, aeroport.getNom());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Aeroport aeroport) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Aeroport (NOM, ADRESSE) VALUES (?, ?)")) {
            st.setString(1, aeroport.getNom());
            st.setString(2, aeroport.getAdresse());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Aeroport findByLoginPwd(String login, String pwd) {
        // Not applicable for Aeroport, returning null
        return null;
    }

    public Aeroport findByNom(String nom) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Aeroport WHERE NOM = ?")) {
            st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String adresse = rs.getString("ADRESSE");
                nom = rs.getString("NOM");
                String dep = rs.getString("LeDepartement");
                return new Aeroport(nom, adresse, dep);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
