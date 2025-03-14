package application.model.data;
/**
 * Class that groups together information on a commune for a specific year.
 * @author M.CRUNCHANT, C.LEBRECH, G.ZENSEN, A-M.ESKHADJIEV
 */
public class InfoCommune {

    /**
     * The year of the information.
     */
    private Annee anneeInfo;

    /**
     * The commune for which the information is relevant.
     */
    private Commune laCommune;

    /**
     * Number of houses.
     */
    private String nbMaison;

    /**
     * Number of apartments.
     */
    private String nbAppart;

    /**
     * Average price.
     */
    private String prixMoyen;

    /**
     * Average price per square meter.
     */
    private String prixM2Moyen;

    /**
     * Average surface area.
     */
    private String surfaceMoy;

    /**
     * Total cultural expenditure.
     */
    private String depCulturelles;

    /**
     * Total budget.
     */
    private String budgetTotal;

    /**
     * Population.
     */
    private String population;




    /**
     * The constructor method.
     * @param anneeInfo the year of the information
     * @param laCommune the commune for which the information is relevant
     * @param nbMaison the number of houses
     * @param nbAppart the number of apartments
     * @param prixMoyen the average price of properties
     * @param prixM2Moyen the average price per square meter
     * @param surfaceMoy the average surface area of properties
     * @param depCulturelles the total cultural expenses
     * @param budgetTotal the total budget
     * @param population the population of the commune
     * @throws IllegalArgumentException if any of the parameters are invalid (null for objects or negative values)
     */
    public InfoCommune(Annee anneeInfo, Commune laCommune, String nbMaison, String nbAppart, String prixMoyen, String prixM2Moyen, String surfaceMoy, String depCulturelles, String budgetTotal, String population) {
        if (anneeInfo.getAnnee() != null && laCommune != null && nbMaison != null && nbAppart != null && prixMoyen != null && 
            prixM2Moyen != null && surfaceMoy != null && depCulturelles!= null && budgetTotal != null && population != null){
            this.anneeInfo = anneeInfo;
            this.laCommune = laCommune;
            this.nbMaison = nbMaison;
            this.nbAppart = nbAppart;
            this.prixMoyen = prixMoyen;
            this.prixM2Moyen = prixM2Moyen;
            this.surfaceMoy = surfaceMoy;
            this.depCulturelles = depCulturelles;
            this.budgetTotal = budgetTotal;
            this.population = population;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }


    

    /**
     * Gets the year of the information.
     * @return the year
     */
    public Annee getAnneeInfo() {
        return this.anneeInfo;
    }




    /**
     * Gets the commune for which the information is relevant.
     * @return the commune
     */
    public Commune getLaCommune() {
        return this.laCommune;
    }




    /**
     * Gets the number of houses.
     * @return the number of houses
     */
    public String getNbMaison() {
        return this.nbMaison;
    }




    /**
     * Gets the number of apartments.
     * @return the number of apartments
     */
    public String getNbAppart() {
        return this.nbAppart;
    }




    /**
     * Gets the average price of properties.
     * @return the average price
     */
    public String getPrixMoyen() {
        return this.prixMoyen;
    }




    /**
     * Gets the average price per square meter.
     * @return the average price per square meter
     */
    public String getPrixM2Moyen() {
        return this.prixM2Moyen;
    }




    /**
     * Gets the average surface area of properties.
     * @return the average surface area
     */
    public String getSurfaceMoy() {
        return this.surfaceMoy;
    }




    /**
     * Gets the total cultural expenses.
     * @return the total cultural expenses
     */
    public String getDepCulturelles() {
        return this.depCulturelles;
    }




    /**
     * Gets the total budget.
     * @return the total budget
     */
    public String getBudgetTotal() {
        return this.budgetTotal;
    }




    /**
     * Gets the population of the commune.
     * @return the population
     */
    public String getPopulation() {
        return this.population;
    }




    /**
     * Sets the year of the information.
     * @param anneeInfo the new year
     * @throws IllegalArgumentException if the year is negative
     */
    public void setAnneeInfo(Annee anneeInfo) {
        if (anneeInfo.getAnnee()  !=null){
            this.anneeInfo = anneeInfo;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the commune for which the information is relevant.
     * @param laCommune the new commune
     * @throws IllegalArgumentException if the commune is null
     */
    public void setLaCommune(Commune laCommune) {
        if (laCommune != null){
            this.laCommune = laCommune;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the number of houses.
     * @param nbMaison the new number of houses
     * @throws IllegalArgumentException if the number of houses is negative
     */
    public void setNbMaison(String nbMaison) {
        if (nbMaison !=null){
            this.nbMaison = nbMaison;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the number of apartments.
     * @param nbAppart the new number of apartments
     * @throws IllegalArgumentException if the number of apartments is negative
     */
    public void setNbAppart(String nbAppart) {
        if (nbAppart != null){
            this.nbAppart = nbAppart;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the average price of properties.
     * @param prixMoyen the new average price
     * @throws IllegalArgumentException if the average price is negative
     */
    public void setPrixMoyen(String prixMoyen) {
        if (prixMoyen != null){
            this.prixMoyen = prixMoyen;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the average price per square meter.
     * @param prixM2Moyen the new average price per square meter
     * @throws IllegalArgumentException if the average price per square meter is negative
     */
    public void setPrixM2Moyen(String prixM2Moyen) {
        if (prixM2Moyen != null){
            this.prixM2Moyen = prixM2Moyen;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the average surface area of properties.
     * @param surfaceMoy the new average surface area
     * @throws IllegalArgumentException if the average surface area is negative
     */
    public void setSurfaceMoy(String surfaceMoy) {
        if (surfaceMoy != null){
            this.surfaceMoy = surfaceMoy;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the total cultural expenses.
     * @param depCulturelles the new total cultural expenses
     * @throws IllegalArgumentException if the total cultural expenses are negative
     */
    public void setDepCulturelles(String depCulturelles) {
        if (depCulturelles != null){
            this.depCulturelles = depCulturelles;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the total budget.
     * @param budgetTotal the new total budget
     * @throws IllegalArgumentException if the total budget is negative
     */
    public void setBudgetTotal(String budgetTotal) {
        if (budgetTotal != null){
            this.budgetTotal = budgetTotal;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }




    /**
     * Sets the population of the commune.
     * @param population the new population
     * @throws IllegalArgumentException if the population is negative
     */
    public void setPopulation(String population) {
        if (population != null){
            this.population = population;

        } else {
            throw new IllegalArgumentException("Parameter value is not accepted.");
        }
    }





    /**
     * Gets a string representation of the InfoCommune object.ss
     * @return a string representation of the InfoCommune object
     */
    public String toString() {
        return "Information sur la commune " + this.getLaCommune() + " en " + this.getAnneeInfo().getAnnee() + " : \r\n" +
               "Nombre de maisons vendues     : " + this.getNbMaison() + "\r\n" +
               "Nombre d'appartements vendus  : " + this.getNbAppart() + "\r\n" +
               "Prix moyen des logements      : " + this.getPrixMoyen() + "\r\n" +
               "Prix moyen du mètre carré     : " + this.getPrixM2Moyen() + "\r\n" +
               "Surface moyenne des logements : " + this.getSurfaceMoy() + "\r\n" +
               "Dépenses culturelles          : " + this.getDepCulturelles() + "\r\n" +
               "Budget total                  : " + this.getBudgetTotal() + "\r\n" +
               "Nombre d'habitants            : " + this.getPopulation();
    }    

}
