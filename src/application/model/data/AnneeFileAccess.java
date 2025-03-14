package application.model.data;

import java.util.ArrayList;
import application.model.dao.AnneeDAO;

public class AnneeFileAccess {
    private ArrayList<Annee> annees;

    public AnneeFileAccess() {
        annees = new ArrayList<>();
        setList();
    }

    public ArrayList<Annee> getAnnees() {
        return annees;
    }
    
    private void setList() {
        AnneeDAO dao = new AnneeDAO();
        this.annees = dao.findAll();
    }

    public Annee getAnneeById(String id) {
        for (Annee annee : this.annees) {
            if (annee.getAnnee() == id) {
                return annee;
            }
        }
        return null;
    }
}
