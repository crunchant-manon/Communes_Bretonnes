package application.model.data;

/**
 * Class that represents a year with its associated inflation rate.
 * @author M.CRUNCHANT, C.LEBRECH, G.ZENSEN, A-M.ESKHADJIEV
 */
public class Annee {

    /**
     * The year.
     */
    private String annee;

    /**
     * The inflation rate for the year.
     */
    private String tauxInflation;




    /**
     * The constructor method.
     * @param annee the year
     * @param tauxInflation the inflation rate for the year
     * @throws IllegalArgumentException if the year or the inflation rate are not positive values
     */
    public Annee(String annee, String tauxInflation) {
        if(annee != null && tauxInflation != null){
            this.annee = annee;
            this.tauxInflation = tauxInflation;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Gets the year.
     * @return the year
     */
    public String getAnnee() {
        return this.annee;
    }




    /**
     * Gets the inflation rate for the year.
     * @return the inflation rate
     */
    public String getTauxInflation() {
        return this.tauxInflation;
    }




    /**
     * Sets the year.
     * @param annee the new year
     * @throws IllegalArgumentException if the year is not a positive value
     */
    public void setAnnee(String annee) {
        if(annee != null){
            this.annee = annee;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the inflation rate for the year.
     * @param tauxInflation the new inflation rate
     * @throws IllegalArgumentException if the inflation rate is not a positive value
     */
    public void setTauxInflation(String tauxInflation) {
        if(tauxInflation != null){
            this.tauxInflation = tauxInflation;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }

    /**
     * Gets a string representation of the Annee object.
     * @return a string representation of the Annee object
     */
    public String toString() {
        return "Annee " + getAnnee() + ", avec un taux d'inflation de " + getTauxInflation();
    }

}



    
