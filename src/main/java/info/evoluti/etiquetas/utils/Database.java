/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.evoluti.etiquetas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static info.evoluti.etiquetas.Etiquetas.EProp;

public class Database {

    private String url;
    private String userName;
    private String password;
    private Connection connection;

    public Database() throws SQLException, ClassNotFoundException {
        this.url = "jdbc:firebirdsql:local:"
//                + EProp.prop().getString("host.name", "localhost") + "/" + EProp.prop().getString("host.port", "3050")
//                + ":" 
                + EProp.prop().getString("host.database")
                + EProp.prop().getString("host.params", "");
        this.userName = EProp.prop().getString("host.user", "SYSDBA");
        this.password = EProp.prop().getString("host.password", "masterkey");
        setConnection();
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.firebirdsql.jdbc.FBDriver"); 
        Properties props = new Properties();

        props.setProperty("user", userName);
        props.setProperty("password", password);
        props.setProperty("encoding", "UTF8");
        connection = DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }

}
