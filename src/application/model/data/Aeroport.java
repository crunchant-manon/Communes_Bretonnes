package application.model.data;

/**
 * Class that represents an airport.
 * @author M.CRUNCHANT, C.LEBRECH, G.ZENSEN, A-M.ESKHADJIEV
 */
public class Aeroport {

    /**
     * The name of the airport.
     */
    private String nom;

    /**
     * The address of the airport.
     */
    private String adresse;

    private String idDep;


    /**
     * The constructor method.
     * @param nom the name of the airport
     * @param adresse the address of the airport
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Aeroport(String nom, String adresse, String idDep) {
        if (nom != null && adresse != null){
            this.nom = nom;
            this.adresse = adresse;
            this.idDep = idDep;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Gets the name of the airport.
     * @return the name of the airport
     */
    public String getNom() {
        return this.nom;
    }




    /**
     * Gets the address of the airport.
     * @return the address of the airport
     */
    public String getAdresse() {
        return this.adresse;
    }

    public String getIdDep() {
        return this.idDep;
    }



    /**
     * Sets the name of the airport.
     * @param nom the new name
     * @throws IllegalArgumentException if the name is null
     */
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the address of the airport.
     * @param adresse the new address
     * @throws IllegalArgumentException if the address is null
     */
    public void setAdresse(String adresse) {
        if (adresse != null) {
            this.adresse = adresse;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    public void setIdDep(String idDep) {
        if (idDep != null) {
            this.idDep = idDep;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Checks whether this airport is equal to another airport.
     * @param a the other airport to compare with
     * @return true if this airport is equal, false otherwise
     * @throws IllegalArgumentException if the specified airport is null
     */
    public boolean equals(Aeroport a) {
        boolean ret = false;

        if (a != null){
            ret = this.nom.equalsIgnoreCase(a.getNom()) &&
                  this.adresse.equalsIgnoreCase(a.getAdresse());
        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
       
        return ret;
    }




    /**
     * Gets a string representation of the Aeroport object.
     * @return a string representation of the Aeroport object
     */
    public String toString() {
        return "Aeroport " + getNom() + " situé à " + getAdresse() + "dans le département de" +getIdDep();
    }

}
