package application.model.data;

import java.util.ArrayList;

/**
 * Class that represents a commune.
 * @author M.CRUNCHANT, C.LEBRECH, G.ZENSEN, A-M.ESKHADJIEV
 */
public class Commune {

    /**
     * The ID of the commune.
     */
    private String idCommune;

    /**
     * The name of the commune.
     */
    private String nomCommune;

    /**
     * The department to which the commune belongs.
     */
    private String leDepartement;

    /**
     * A list of neighboring municipalities.
     */
    private ArrayList<Commune> lstCommunesVoisines;

    /**
     * A list of railway stations in the commune.
     */
    private ArrayList<Gare> lstGare;

    private GareFileAccess gareFileAccess;

    /**
     * The constructor method.
     * @param idCommune the ID of the commune
     * @param nomCommune the name of the commune
     * @param leDepartement the department of the commune
     * @throws IllegalArgumentException if any parameter is invalid (null for objects or non-positive values)
     */
    public Commune(String idCommune, String nomCommune, String leDepartement) {
            this.idCommune = idCommune;
            this.nomCommune = nomCommune;
            this.leDepartement = leDepartement;
            this.lstCommunesVoisines = new ArrayList<Commune>();
            this.lstGare = new ArrayList<Gare>();
            this.gareFileAccess = new GareFileAccess();
            this.lstGare = this.gareFileAccess.getGares(this.idCommune);
            

    }

    /**
     * Gets the ID of the commune.
     * @return the ID of the commune
     */
    public String getIdCommune() {
        return this.idCommune;
    }

    /**
     * Gets the name of the commune.
     * @return the name of the commune
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Gets the department of the commune.
     * @return the department of the commune
     */
    public String getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Gets the list of neighboring communes.
     * @return the list of neighboring communes
     */
    public ArrayList<Commune> getLstCommunesVoisines() {
        return this.lstCommunesVoisines;
    }

    /**
     * Gets the list of stations.
     * @return the list of stations
     */
    public ArrayList<Gare> getLstGare() {
        return this.lstGare;
    }

    /**
     * Gets the number of railway stations in the commune.
     * @return the number of railway stations
     */
    public String getNbStation() {
        return String.valueOf(this.lstGare.size());
    }

    /**
     * Gets the number of neighboring communes.
     * @return the number of neighboring communes
     */
    public String getNbNeighboringCom() {
        return String.valueOf(this.lstCommunesVoisines.size());
    }

    /**
     * Gets the list of freight railway stations.
     * @return the list of freight railway stations
     */
    public ArrayList<Gare> getFreightStation() {
        ArrayList<Gare> f = new ArrayList<>();

        for (Gare gare : this.lstGare) {
            if (gare.getEstFret()) {
                f.add(gare);
            }
        }

        return f;
    }

    /**
     * Gets the list of passenger railway stations.
     * @return the list of passenger railway stations
     */
    public ArrayList<Gare> getPassengerStation() {
        ArrayList<Gare> v = new ArrayList<>();

        for (Gare gare : this.lstGare) {
            if (gare.getEstVoyageur()) {
                v.add(gare);
            }
        }

        return v;
    }

    /**
     * Sets the ID of the commune.
     * @param idCommune the new ID
     * @throws IllegalArgumentException if the ID is not positive
     */
    public void setIdCommune(String idCommune) {
        if (idCommune != null) {
            this.idCommune = idCommune;
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Sets the name of the commune.
     * @param nomCommune the new name 
     * @throws IllegalArgumentException if the name is null
     */
    public void setNomCommune(String nomCommune) {
        if (nomCommune != null) {
            this.nomCommune = nomCommune;
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Sets the department of the commune.
     * @param leDepartement the new department
     * @throws IllegalArgumentException if the department is null
     */
    public void setLeDepartement(String leDepartement) {
        if (leDepartement != null) {
            this.leDepartement = leDepartement;
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Sets the list of neighboring communes.
     * @param lstCommunesVoisines the new list of neighboring communes
     * @throws IllegalArgumentException if the list is null
     */
    public void setLstCommunesVoisines(ArrayList<Commune> lstCommunesVoisines) {
        if (lstCommunesVoisines != null) {
            this.lstCommunesVoisines = lstCommunesVoisines;
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Sets the list of stations.
     * @param lstGare the new list of stations
     * @throws IllegalArgumentException if the list is null
     */
    public void setLstGare(ArrayList<Gare> lstGare) {
        if (lstGare != null) {
            this.lstGare = lstGare;
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Adds a neighboring commune to the list of neighboring communes.
     * @param commune the neighboring commune to add
     * @throws IllegalArgumentException if the commune is null
     */
    public void addNeighboringCom(Commune commune) {
        if (commune != null) {
            this.lstCommunesVoisines.add(commune);
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Adds a station to the list of stations.
     * @param gare the station to add
     * @throws IllegalArgumentException if the station is null
     */
    public void addStation(Gare gare) {
        if (gare != null) {
            this.lstGare.add(gare);
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Removes a neighboring commune from the list of neighboring communes.
     * @param commune the neighboring commune to remove
     * @throws IllegalArgumentException if the commune is null
     */
    public void delNeighboringCom(Commune commune) {
        if (commune != null) {
            this.lstCommunesVoisines.remove(commune);
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Removes a station from the list of stations.
     * @param gare the station to remove
     * @throws IllegalArgumentException if the station is null
     */
    public void delStation(Gare gare) {
        if (gare != null) {
            this.lstGare.remove(gare);
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Checks whether this commune is equal to another commune.
     * @param c the other commune to compare with
     * @return true if this commune is equal, false otherwise
     * @throws IllegalArgumentException if the specified commune is null
     */
    public boolean equals(Commune c) {
        if (c != null) {
            return this.idCommune == c.getIdCommune() &&
                   this.nomCommune.equalsIgnoreCase(c.getNomCommune()) &&
                   this.leDepartement.equals(c.getLeDepartement()) &&
                   this.lstCommunesVoisines.equals(c.getLstCommunesVoisines()) &&
                   this.lstGare.equals(c.getLstGare());
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Finds a railway station by its name.
     * @param nom the name of the railway station
     * @return the railway station with the given name, or null if not found
     */
    public Gare findStation(String nom) {
        for (Gare gare : this.lstGare) {
            if (gare.getNomGare().equalsIgnoreCase(nom)) {
                return gare;
            }
        }
        return null;
    }

    /**
     * Finds a neighboring commune by its name.
     * @param nom the name of the neighboring commune
     * @return the neighboring commune with the given name, or null if not found
     */
    public Commune findNeighboringCom(String nom) {
        for (Commune commune : this.lstCommunesVoisines) {
            if (commune.getNomCommune().equalsIgnoreCase(nom)) {
                return commune;
            }
        }
        return null;
    }

   

    /**
     * Gets a string representation of the Commune object.
     * @return a string representation of the Commune object
     */
    public String toString() {
        return this.nomCommune + " (" + this.idCommune + ")";
    }

    public void setCommuneVoisines(ArrayList<Commune> communeVoisine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCommuneVoisines'");
    }
}
