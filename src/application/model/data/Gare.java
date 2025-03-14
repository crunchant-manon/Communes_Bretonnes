package application.model.data;
/**
 * Class that represents a train station.
 * @author M.CRUNCHANT, C.LEBRECH, G.ZENSEN, A-M.ESKHADJIEV
 */
public class Gare {

    /**
     * The code of the station.
     */
    private String codeGare;

    /**
     * The name of the station.
     */
    private String nomGare;

    /**
     * Whether the station is for freight.
     */
    private boolean estFret;

    /**
     * Whether the station is for passengers.
     */
    private boolean estVoyageur;





    /**
     * The constructor method.
     * @param codeGare the code of the station
     * @param nomGare the name of the station
     * @param estFret whether the station is for freight
     * @param estVoyageur whether the station is for passengers
     * @throws IllegalArgumentException if any parameter is invalid (null for objects or non-positive values)
     */
    public Gare(String codeGare, String nomGare, boolean estFret, boolean estVoyageur) {
        if(codeGare != null && nomGare != null ){
            this.codeGare = codeGare;
            this.nomGare = nomGare;
            this.estFret = estFret;
            this.estVoyageur = estVoyageur;
        } else {
            throw new IllegalArgumentException("parameter value is not accepted.");
        }
    }




    /**
     * Gets the code of the station.
     * @return the code of the station
     */
    public String getCodeGare() {
        return this.codeGare;
    }




    /**
     * Gets the name of the station.
     * @return the name of the station
     */
    public String getNomGare() {
        return this.nomGare;
    }




    /**
     * Gets whether the station is for freight.
     * @return true if the station is for freight, false otherwise
     */
    public boolean getEstFret() {
        return this.estFret;
    }




    /**
     * Gets whether the station is for passengers.
     * @return true if the station is for passengers, false otherwise
     */
    public boolean getEstVoyageur() {
        return this.estVoyageur;
    }




    /**
     * Sets the code of the station.
     * @param codeGare the new code of the station
     * @throws IllegalArgumentException if the code is not positive
     */
    public void setCodeGare(String codeGare) {
        if(codeGare != null){
            this.codeGare = codeGare;

        } else {
            throw new IllegalArgumentException("parameter value is not accepted.");
        } 
    }




    /**
     * Sets the name of the station.
     * @param nomGare the new name
     * @throws IllegalArgumentException if the name is null
     */
    public void setNomGare(String nomGare) {
        if(nomGare != null){
            this.nomGare = nomGare;

        } else {
            throw new IllegalArgumentException("parameter value is not accepted.");
        } 
    }




    /**
     * Sets whether the station is for freight.
     * @param estFret true if the station is for freight, false otherwise
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }




    /**
     * Sets whether the station is for passengers.
     * @param estVoyageur true if the station is for passengers, false otherwise
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }




    /**
     * Checks whether this station is equal to another station.
     * @param g the other station to compare with
     * @return true if this station is equal, false otherwise
     * @throws IllegalArgumentException if the specified station is null
     */
    public boolean equals(Gare g) {
        boolean ret = false;

        if (g != null){
            ret = this.codeGare == g.getCodeGare() &&
                  this.nomGare.equals(g.getNomGare()) &&
                  this.estFret == g.getEstFret() &&
                  this.estVoyageur == g.getEstVoyageur();

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
       
        return ret;
    }




    /**
     * Returns a string representation of the Gare object.
     * @return a string representation of the Gare object
     */
    public String toString() {
        return "Gare " +  getNomGare() + " (" + getCodeGare() + ") " + " : est fret = " + getEstFret() + ", est voyageur = " + getEstVoyageur();
    }

}
