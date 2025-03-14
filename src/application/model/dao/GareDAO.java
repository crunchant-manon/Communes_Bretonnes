package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Gare;

public class GareDAO extends MyConnection<Gare> {

    @Override
    public ArrayList<Gare> findAll() {
        ArrayList<Gare> gares = new ArrayList<>();
        String query = "SELECT * FROM Gare";
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String codeGare = rs.getString("CODEGARE");
                String nomGare = rs.getString("NOMGARE");
                boolean estFret = rs.getBoolean("ESTFRET");
                boolean estVoyageur = rs.getBoolean("ESTVOYAGEUR");
                gares.add(new Gare(codeGare, nomGare, estFret, estVoyageur));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return gares;
    }

    @Override
    public int update(Gare gare, String login, String role) {
        String query = "UPDATE Gare SET NOMGARE = ?, ESTFRET = ?, ESTVOYAGEUR = ? WHERE CODEGARE = ?";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, gare.getNomGare());
            st.setBoolean(2, gare.getEstFret());
            st.setBoolean(3, gare.getEstVoyageur());
            st.setString(4, gare.getCodeGare());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Gare gare, String login) {
        String query = "DELETE FROM Gare WHERE CODEGARE = ?";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, gare.getCodeGare());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Gare gare) {
        String query = "INSERT INTO Gare (CODEGARE, NOMGARE, ESTFRET, ESTVOYAGEUR) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, gare.getCodeGare());
            st.setString(2, gare.getNomGare());
            st.setBoolean(3, gare.getEstFret());
            st.setBoolean(4, gare.getEstVoyageur());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Gare findByLoginPwd(String login, String pwd) {
        // Not applicable for Gare, returning null
        return null;
    }

    public Gare findByCode(String codeGare) {
        String query = "SELECT * FROM Gare WHERE CODEGARE = ?";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, codeGare);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String nomGare = rs.getString("NOMGARE");
                boolean estFret = rs.getBoolean("ESTFRET");
                boolean estVoyageur = rs.getBoolean("ESTVOYAGEUR");
                return new Gare(codeGare, nomGare, estFret, estVoyageur);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Gare> findByCommune(String idCommune) {
        ArrayList<Gare> gares = new ArrayList<>();
        String query = "SELECT * FROM Gare WHERE IDCOMMUNE = ?";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, idCommune);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String codeGare = rs.getString("CODEGARE");
                String nomGare = rs.getString("NOMGARE");
                boolean estFret = rs.getBoolean("ESTFRET");
                boolean estVoyageur = rs.getBoolean("ESTVOYAGEUR");
                gares.add(new Gare(codeGare, nomGare, estFret, estVoyageur));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return gares;
    }

    public boolean exists(int codeGare) {
        String query = "SELECT * FROM Gare WHERE CODEGARE = ?";
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, codeGare);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void updateNom(int codeGare, String newValue) {
        if (exists(codeGare)) {
            String query = "UPDATE Gare SET NOMGARE = ? WHERE CODEGARE = ?";
            try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, newValue);
                st.setInt(2, codeGare);
                st.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateEstFret(int codeGare, boolean newValue) {
        if (exists(codeGare)) {
            String query = "UPDATE Gare SET ESTFRET = ? WHERE CODEGARE = ?";
            try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
                st.setBoolean(1, newValue);
                st.setInt(2, codeGare);
                st.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateEstVoyageur(int codeGare, boolean newValue) {
        if (exists(codeGare)) {
            String query = "UPDATE Gare SET ESTVOYAGEUR = ? WHERE CODEGARE = ?";
            try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(query)) {
                st.setBoolean(1, newValue);
                st.setInt(2, codeGare);
                st.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
