package application.model.data;

import java.util.ArrayList;
import application.model.dao.AeroportDAO;

public class AeroportFileAccess {
    private ArrayList<Aeroport> aeroports;

    public AeroportFileAccess() {
        aeroports = new ArrayList<>();
        setList();
    }

    public ArrayList<Aeroport> getAeroportsDep(String idDep) {
        ArrayList<Aeroport> aeroportsDep = new ArrayList<>();
        for (Aeroport aeroport : this.aeroports) {
            if (aeroport.getNom().equals(idDep)) {
                aeroportsDep.add(aeroport);
            }
        }
        return aeroports;
    }
    
    private void setList() {
        AeroportDAO dao = new AeroportDAO();
        this.aeroports = dao.findAll();
    }

    public Aeroport getAeroportByNom(String nom) {
        for (Aeroport aeroport : this.aeroports) {
            if (aeroport.getNom().equals(nom)) {
                return aeroport;
            }
        }
        return null;
    }
}

