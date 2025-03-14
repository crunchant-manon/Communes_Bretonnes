package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Commune;
import application.model.data.Departement;

public class CommuneDAO extends MyConnection<Commune> {

    @Override
    public ArrayList<Commune> findAll() {
        ArrayList<Commune> communes = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM Commune");
            while (rs.next()) {
                String idCommune = rs.getString("IDCOMMUNE");
                String nomCommune = rs.getString("NOMCOMMUNE");
                String leDepartement = rs.getString("leDepartement");
                communes.add(new Commune(idCommune, nomCommune, leDepartement));      
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return communes;
    }

    public ArrayList<Commune> findAllVoisine(String id) {
        ArrayList<Commune> communes = new ArrayList<>();
        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement("SELECT * FROM Voisinage WHERE communeVoisine = ?")) {
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String idCommune = rs.getString("commune");
                communes.add(findById(idCommune));      
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return communes;
    }

    @Override
    public int update(Commune commune, String login, String role) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE Commune SET NOMCOMMUNE = ?, leDepartement = ? WHERE IDCOMMUNE = ?")) {
            st.setString(1, commune.getNomCommune());
            st.setString(2, commune.getLeDepartement());
            st.setString(3, commune.getIdCommune());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Commune commune, String login) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM Commune WHERE IDCOMMUNE = ?")) {
            st.setString(1, commune.getIdCommune());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Commune commune) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Commune (IDCOMMUNE, NOMCOMMUNE, leDepartement) VALUES (?, ?, ?)")) {
            st.setString(1, commune.getIdCommune());
            st.setString(2, commune.getNomCommune());
            st.setString(3, commune.getLeDepartement());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Commune findByLoginPwd(String login, String pwd) {
        // Not applicable for Commune, returning null
        return null;
    }

    public boolean exists(int idCommune) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Commune WHERE IDCOMMUNE = ?")) {
            st.setInt(1, idCommune);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Commune findById(String idCommune) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Commune WHERE IDCOMMUNE = ?")) {
            st.setString(1, idCommune);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String nomCommune = rs.getString("NOMCOMMUNE");
                String leDepartement = rs.getString("leDepartement");
                return new Commune(idCommune, nomCommune, leDepartement);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Commune> findByNomCommune(String nomRecherche) {
        ArrayList<Commune> communesTrouvees = new ArrayList<>();
        String sql = "SELECT * FROM Commune WHERE NOMCOMMUNE LIKE ?";
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, "%" + nomRecherche + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String idCommune = rs.getString("IDCOMMUNE");
                String nomCommune = rs.getString("NOMCOMMUNE");
                String leDepartement = rs.getString("leDepartement");
                communesTrouvees.add(new Commune(idCommune, nomCommune, leDepartement));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return communesTrouvees;
    }

    public ArrayList<Commune> findWithQuerry(String sql) {
        ArrayList<Commune> communes = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String idCommune = rs.getString("IDCOMMUNE");
                String nomCommune = rs.getString("NOMCOMMUNE");
                String leDepartement = rs.getString("leDepartement");
                communes.add(new Commune(idCommune, nomCommune, leDepartement));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return communes;
    }
}
