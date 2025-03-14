package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Utilisateur;

public class UtilisateurDAO extends MyConnection<Utilisateur> {

    @Override
    public int create(Utilisateur utilisateur) {
        String query = "INSERT INTO UTILISATEUR(LOGIN, PWD, ROLE) VALUES ('" + utilisateur.getLogin() + "','" + utilisateur.getPwd() + "','" + utilisateur.getRole() + "')";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    @Override
    public int update(Utilisateur utilisateur, String login, String role) {
        String query = "UPDATE Utilisateur SET login ='" + utilisateur.getLogin() + "', pwd ='" + utilisateur.getPwd() + "', role ='" + utilisateur.getRole() + "' WHERE login ='" + login + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(Utilisateur utilisateur, String login) {
        String query = "DELETE FROM Utilisateur WHERE LOGIN='" + login + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Utilisateur> findAll() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM UTILISATEUR");
            while (rs.next()) {
                String id = rs.getString("ID");
                String nom = rs.getString("LOGIN");
                String pwd = rs.getString("PWD");
                String role = rs.getString("ROLE");
                utilisateurs.add(new Utilisateur(id, nom, pwd, role));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur findByLoginPwd(String login, String pwd) {
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement("SELECT * FROM UTILISATEUR WHERE LOGIN= ? AND PWD= ?")) {
            st.setString(1, login);
            st.setString(2, pwd);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String l = rs.getString("LOGIN");
                String p = rs.getString("PWD");
                String r = rs.getString("ROLE");
                return new Utilisateur(l, p, r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Utilisateur findByLogin(String login) {
        try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement("SELECT * FROM UTILISATEUR WHERE LOGIN= ?")) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String l = rs.getString("LOGIN");
                String p = rs.getString("PWD");
                String r = rs.getString("ROLE");
                return new Utilisateur(l, p, r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean exists(String email) {
        String query = "SELECT * FROM UTILISATEUR WHERE login = ?";
        try (Connection con = MyConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int updateLogin(String oldLogin, String newLogin) {
        String query = "UPDATE Utilisateur SET login ='" + newLogin + "' WHERE login ='" + oldLogin + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int updatePwd(String login, String pwd) {
        String query = "UPDATE Utilisateur SET pwd ='" + pwd + "' WHERE login ='" + login + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int updateRole(String login, String role) {
        String query = "UPDATE Utilisateur SET role ='" + role + "' WHERE login ='" + login + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
