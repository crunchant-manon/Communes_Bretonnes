package application.model.data;

import java.util.ArrayList;
import application.model.dao.DepartementDAO;

public class DepartementFileAccess {
    private ArrayList<Departement> departements;

    public DepartementFileAccess() {
        departements = new ArrayList<>();
        setList();
    }

    public ArrayList<Departement> getDepartements() {
        return departements;
    }
    
    private void setList() {
        DepartementDAO dao = new DepartementDAO();
        this.departements = dao.findAll();
    }

    public Departement getDepartementById(String id) {
        for (Departement departement : this.departements) {
            if (departement.getIdDep() == id) {
                return departement;
            }
        }
        return null;
    }
}
