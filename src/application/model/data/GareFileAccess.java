package application.model.data;

import java.util.ArrayList;
import application.model.dao.GareDAO;

public class GareFileAccess {
    private ArrayList<Gare> gares;

    public GareFileAccess() {
        gares = new ArrayList<>();
        setList();
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }
    
    private void setList() {
        GareDAO dao = new GareDAO();
        this.gares = dao.findAll();
    }

    public Gare getGareByCode(String code) {
        for (Gare gare : this.gares) {
            if (gare.getCodeGare() == code) {
                return gare;
            }
        }
        return null;
    }

    
    public ArrayList<Gare> getGares(String idCommune) {
        ArrayList<Gare> garesCommune = new ArrayList<>();
        for (Gare gare : this.gares) {
            if (gare.getCodeGare() == idCommune) {
                garesCommune.add(gare);
            }
        }
        return garesCommune;
    }
    

}
