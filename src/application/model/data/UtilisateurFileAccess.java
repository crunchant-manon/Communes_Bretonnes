package application.model.data;

import application.model.dao.UtilisateurDAO;
import java.util.ArrayList;

public class UtilisateurFileAccess {
    public ArrayList<Utilisateur> utilisateurs;

    public UtilisateurFileAccess() {
        utilisateurs = new ArrayList<Utilisateur>();
        setList();
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    // Ajoute un utilisateur à la liste
    public void addUtilisateur(Utilisateur utilisateur) {
        UtilisateurDAO userDAO = new UtilisateurDAO();
        this.utilisateurs.add(utilisateur);
        userDAO.create(utilisateur);
    }

    // Supprime un utilisateur de la liste
    public void deleteUtilisateur(Utilisateur utilisateur) {
        UtilisateurDAO userDAO = new UtilisateurDAO();
        this.utilisateurs.remove(utilisateur);
        userDAO.delete(utilisateur, utilisateur.getLogin());
    }

    // Modifie un utilisateur de la liste
    public void updateUtilisateur(Utilisateur utilisateur, String login, String role) {
        UtilisateurDAO userDAO = new UtilisateurDAO();
        userDAO.update(utilisateur, login, role);
    }

    public void loadUtilisateurs() {
        UtilisateurDAO userDAO = new UtilisateurDAO();
        this.utilisateurs = userDAO.findAll();
    }

    public Utilisateur getUtilisateur(String login) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getLogin().equals(login)) {
                return utilisateur;
            }
        }
        return null;
    }

    public void setList() {
        UtilisateurDAO userDAO = new UtilisateurDAO();
        this.utilisateurs = userDAO.findAll();
    }
}
