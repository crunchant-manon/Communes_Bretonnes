package application.model.dao;

import java.sql.*;
import java.util.ArrayList;
import application.model.data.Annee;
import application.model.data.Commune;
import application.model.data.InfoCommune;

public class InfoCommuneDAO extends MyConnection<InfoCommune> {

    @Override
    public ArrayList<InfoCommune> findAll() {
        ArrayList<InfoCommune> infoCommunes = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM donneesannuelles");
            while (rs.next()) {
                String idAnnee = rs.getString("lAnnee");
                String idCommune = rs.getString("laCommune");
                String nbMaison = rs.getString("nbMaison");
                String nbAppart = rs.getString("nbAppart");
                String prixMoyen = rs.getString("prixMoyen");
                String prixM2Moyen = rs.getString("prixM2Moyen");
                String surfaceMoy = rs.getString("SurfaceMoy");
                String depCulturellesTotal = rs.getString("depensesCulturellesTotales");
                String budgetTotal = rs.getString("budgetTotal");
                String population = rs.getString("population");

                Annee annee = new AnneeDAO().findByAnnee(idAnnee);
                Commune commune = new CommuneDAO().findById(idCommune);
                infoCommunes.add(new InfoCommune(annee, commune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy, depCulturellesTotal, budgetTotal, population));      
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infoCommunes;
    }

    @Override
    public int update(InfoCommune infoCommune, String login, String role) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE donneesannuelles SET nbMaison = ?, nbAppart = ?, prixMoyen = ?, prixM2Moyen = ?, SurfaceMoy = ?, depensesCulturellesTotales = ?, budgetTotal = ?, population = ? WHERE lAnnee = ? AND laCommune = ?")) {
            st.setString(1, infoCommune.getNbMaison());
            st.setString(2, infoCommune.getNbAppart());
            st.setString(3, infoCommune.getPrixMoyen());
            st.setString(4, infoCommune.getPrixM2Moyen());
            st.setString(5, infoCommune.getSurfaceMoy());
            st.setString(6, infoCommune.getDepCulturelles());
            st.setString(7, infoCommune.getBudgetTotal());
            st.setString(8, infoCommune.getPopulation());
            st.setString(9, infoCommune.getAnneeInfo().getAnnee());
            st.setString(10, infoCommune.getLaCommune().getIdCommune());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(InfoCommune infoCommune, String login) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM donneesannuelles WHERE lAnnee = ? AND laCommune = ?")) {
            st.setString(1, infoCommune.getAnneeInfo().getAnnee());
            st.setString(2, infoCommune.getLaCommune().getIdCommune());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(InfoCommune infoCommune) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO donneesannuelles (lAnnee, laCommune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            st.setString(1, infoCommune.getAnneeInfo().getAnnee());
            st.setString(2, infoCommune.getLaCommune().getIdCommune());
            st.setString(3, infoCommune.getNbMaison());
            st.setString(4, infoCommune.getNbAppart());
            st.setString(5, infoCommune.getPrixMoyen());
            st.setString(6, infoCommune.getPrixM2Moyen());
            st.setString(7, infoCommune.getSurfaceMoy());
            st.setString(8, infoCommune.getDepCulturelles());
            st.setString(9, infoCommune.getBudgetTotal());
            st.setString(10, infoCommune.getPopulation());
            return st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public InfoCommune findAnneeCommune(String string, String idCommune2) {
        InfoCommune infoCommune = null;
        try (Connection con = getConnection(); 
             PreparedStatement st = con.prepareStatement("SELECT * FROM donneesannuelles WHERE lAnnee = ? AND laCommune = ?")) {
            st.setString(1, string);
            st.setString(2, idCommune2);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String idAnnee = rs.getString("lAnnee");
                String idCommune = rs.getString("laCommune");
                String nbMaison = rs.getString("nbMaison");
                String nbAppart = rs.getString("nbAppart");
                String prixMoyen = rs.getString("prixMoyen");
                String prixM2Moyen = rs.getString("prixM2Moyen");
                String surfaceMoy = rs.getString("SurfaceMoy");
                String depCulturellesTotal = rs.getString("depensesCulturellesTotales");
                String budgetTotal = rs.getString("budgetTotal");
                String population = rs.getString("population");

                Annee a = new AnneeDAO().findByAnnee
                
                (idAnnee);
                Commune c = new CommuneDAO().findById(idCommune);
                infoCommune = new InfoCommune(a, c, nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy, depCulturellesTotal, budgetTotal, population);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return infoCommune;
    }

    @Override
    public InfoCommune findByLoginPwd(String login, String pwd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLoginPwd'");
    }
}
