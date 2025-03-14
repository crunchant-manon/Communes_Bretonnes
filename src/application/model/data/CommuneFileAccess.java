package application.model.data;

import java.util.ArrayList;
import application.model.dao.CommuneDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CommuneFileAccess {
    private ArrayList<Commune> communes;
    private ArrayList<Commune> communesV;
    private CommuneDAO dao = new CommuneDAO();

    public CommuneFileAccess() {
        communes = new ArrayList<>();
        setList();
    }

    public ArrayList<Commune> getCommunes() {
        return communes;
    }

    public ArrayList<Commune> getCommuneVoisine(String idCommune) {
        this.communesV = dao.findAllVoisine(idCommune);
        return communesV;
    }
    
    private void setList() {
        this.communes = dao.findAll();
    }

    private void setList(String idCommune) {
        this.communesV = dao.findAllVoisine(idCommune);
    }

    public Commune getCommuneById(String idCommune) {
        for (Commune commune : this.communes) {
            if (commune.getIdCommune() == idCommune) {
                return commune;
            }
        }
        return null;
    }

    public void writeDonneeToCSVFile(String fileName) {
        CommuneDAO communeDao = new CommuneDAO();

        StringBuilder stringBuilder = new StringBuilder();

        // Header row
        stringBuilder.append("Nom Commune;Code Postal;Département\n");

        // Commune information
        for (Commune commune : communeDao.findAll()) {
            String communeName = commune.getNomCommune();
            String communeCode = String.valueOf(commune.getIdCommune());
            String departement = commune.getLeDepartement(); 

            // Append commune data
            stringBuilder.append(communeName).append(";").append(communeCode).append(";").append(departement).append("\n");
        }

        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
