package application.model.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import application.model.dao.*;

public class InfoCommuneFileAccess {
    public ArrayList<InfoCommune> infos;

    public InfoCommuneFileAccess() {
        infos = new ArrayList<>();
        setList();
    }

    public ArrayList<InfoCommune> getInfos() {
        return infos;
    }
    
    public void setList() {
        InfoCommuneDAO dao = new InfoCommuneDAO();
        this.infos = dao.findAll();
    }

    public InfoCommune getInfoCommuneById(String annee, String idCommune) {
        for (InfoCommune info : this.infos) {
            if (info.getAnneeInfo().getAnnee() == annee && info.getLaCommune().getIdCommune() == idCommune) {
                return info;
            }
        }
        return null;
    }
}
