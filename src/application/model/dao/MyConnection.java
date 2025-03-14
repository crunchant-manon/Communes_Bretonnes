package application.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import ressources.utils.Constants;

public abstract class MyConnection<T> {

    /** 
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///sae_201";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private Connection connection = null;
    */



    protected static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(Constants.DB_DRIVER);
            con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_LOGIN, Constants.DB_PASSWORD);
        return con;
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver not found: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Failed to establish connection: " + e.getMessage());
            e.printStackTrace();
            return null;
        
        }
       
        
    }

    

    public abstract ArrayList<T> findAll();
    public abstract int update(T element, String login, String role);
    public abstract int delete(T element, String login);
    public abstract int create(T element);
    public abstract T findByLoginPwd(String login, String pwd);
}
