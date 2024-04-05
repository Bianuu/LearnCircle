package conectBDpersis;

import java.sql.*;

public class Persistence {

    protected Connection connection;

    public Persistence() {
        try {
            Class.forName(conectBD.DRIVER.getValue()); // Incarca driverul pentru baza de date
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Afiseaza eroarea daca nu se poate incarca driverul
        }
    }

    // Executa o interogare in baza de date
    public boolean executeQuery(String query) {
        boolean result = false;

        Statement statement = null;

        try {
            this.connect(); // Conecteaza-te la baza de date
            statement = connection.createStatement();
            statement.executeUpdate(query); // Executa interogarea data
            result = true; // Seteaza rezultatul ca fiind adevarat daca interogarea a fost executata cu succes
        } catch (SQLException e) {
            e.printStackTrace(); // Afiseaza eroarea daca interogarea nu poate fi executata
        } finally {
            this.close(); // Inchide conexiunea cu baza de date
        }

        return result; // Returneaza rezultatul executarii interogarii
    }

    // Conecteaza-te la baza de date
    public void connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(conectBD.URL.getValue(),
                    conectBD.USER.getValue(), conectBD.PASSWORD.getValue());
        } catch (SQLException e) {
            e.printStackTrace(); // Afiseaza eroarea daca nu se poate realiza conexiunea
        }
    }

    // Inchide conexiunea cu baza de date
    public void close() {
        if (connection != null) {
            try {
                connection.close(); // Inchide conexiunea
            } catch (SQLException e) {
                e.printStackTrace(); // Afiseaza eroarea daca nu se poate inchide conexiunea
            }
        }
    }

    // Obtine rezultatul unei interogari sub forma de tabel de date
    public ResultSet getDataTable(String query) {
        Statement statement;
        ResultSet resultSet = null;

        try {
            this.connect(); // Conecteaza-te la baza de date
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query); // Executa interogarea pentru obtinerea datelor
        } catch (SQLException e) {
            e.printStackTrace(); // Afiseaza eroarea daca nu se poate obtine tabelul de date
        } finally {
            this.close(); // Inchide conexiunea cu baza de date
        }

        return resultSet; // Returneaza tabelul de date obtinut
    }
}
