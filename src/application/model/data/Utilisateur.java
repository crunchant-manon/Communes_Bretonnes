package application.model.data;
public class Utilisateur {
    private String login;
    private String pwd;
    private String role;
    private String id;

    public Utilisateur(){
        this.login = "";
        this.pwd = "";
        this.role = "";
    }

    public Utilisateur(String login , String pwd) {
        this.login = login;
        this.pwd = pwd;
        this.role = "utilisateur";
    }

    public Utilisateur(String login , String pwd, String role) {
        this.login = login;
        this.pwd = pwd;
        this.role = role;
    }

    public Utilisateur(String id, String login , String pwd, String role) {
        this.id = id;
        this.login = login;
        this.pwd = pwd;
        this.role = role;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public Utilisateur getUser() {
        return this;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "User [login=" + login + ", pwd=" + pwd + ", role=" + role + "]";
    } 
}