package conectBDpersis.persistence;

import conectBDpersis.Persistence;
import model.user.Elev;
import model.user.StatusCont;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Elevi {

    private Persistence persistence;

    public Elevi() {
        this.persistence = new Persistence();
    }

    // Metoda pentru inserarea unui elev in baza de date
    public boolean insertStudent(Elev elev) {
        // Construieste interogarea SQL pentru inserare
        String query = "INSERT INTO STUDENTS (NAME, SURNAME, NICKNAME, PASSWORD, ACCOUNT_STATUS) VALUES ( '" +
                elev.getName() + "','" +
                elev.getSurname() + "','" +
                elev.getNickname() + "','" +
                elev.getPassword() + "','" +
                elev.getAccountStatus() + "')";
        // Executa interogarea si returneaza rezultatul
        return this.persistence.executeQuery(query);
    }

    // Metoda pentru stergerea unui elev din baza de date dupa ID
    public boolean deleteStudent(int id) {
        // Construieste interogarea SQL pentru stergere
        String query = "DELETE FROM STUDENTS WHERE SID = " + id;
        // Executa interogarea si returneaza rezultatul
        return this.persistence.executeQuery(query);
    }

    // Metoda pentru gasirea unui elev dupa porecla
    public Elev findStudentByNickname(String nickname) {
        Elev elev = null;
        // Construieste interogarea SQL pentru a selecta un elev dupa porecla
        String query = "SELECT * FROM STUDENTS WHERE NICKNAME = '" + nickname + "'";
        // Obtine setul de rezultate din interogare
        ResultSet resultSet = this.persistence.getDataTable(query);

        try {
            // Daca se gaseste un inregistrare, creeaza un nou obiect Elev cu datele recuperate
            if (resultSet.next()) {
                elev = new Elev(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        StatusCont.valueOf(resultSet.getString("ACCOUNT_STATUS")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elev;
    }

    // Metoda pentru a gasi toti elevii din baza de date
    public ArrayList<Elev> findAllStudents() {
        ArrayList<Elev> elevs = new ArrayList<>();
        // Construieste interogarea SQL pentru a selecta toti elevii
        String query = "SELECT * FROM STUDENTS";
        // Obtine setul de rezultate din interogare
        ResultSet resultSet = this.persistence.getDataTable(query);
        Elev elev;
        try {
            // Itareaza prin setul de rezultate si creeaza obiecte Elev pentru fiecare inregistrare
            while (resultSet.next()) {
                elev = new Elev(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        StatusCont.valueOf(resultSet.getString("ACCOUNT_STATUS")));
                elevs.add(elev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elevs;
    }

    // Metoda pentru a gasi elevii dupa starea contului lor
    public ArrayList<Elev> findStudentsByStatus(String status) {
        ArrayList<Elev> elevs = new ArrayList<>();
        // Construieste interogarea SQL pentru a selecta elevii dupa starea contului
        String query = "SELECT * FROM STUDENTS WHERE ACCOUNT_STATUS = '" + status + "'";
        // Obtine setul de rezultate din interogare
        ResultSet resultSet = this.persistence.getDataTable(query);
        Elev elev;
        try {
            // Itareaza prin setul de rezultate si creeaza obiecte Elev pentru fiecare inregistrare
            while (resultSet.next()) {
                elev = new Elev(resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("NICKNAME"),
                        resultSet.getString("PASSWORD"),
                        StatusCont.valueOf(resultSet.getString("ACCOUNT_STATUS")));
                elevs.add(elev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elevs;
    }

    // Metoda pentru a gasi ID-ul unui elev dupa porecla
    public int findIdByNickname(String nickname) {
        int id = -1;
        // Construieste interogarea SQL pentru a selecta ID-ul unui elev dupa porecla
        String query = "SELECT * FROM STUDENTS WHERE NICKNAME = '" + nickname + "'";

        ResultSet resultSet = this.persistence.getDataTable(query);

        try {
            // Daca se gaseste o inregistrare, preia ID-ul si-l converteste in intreg
            if (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("SID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    // Metoda pentru a actualiza starea unui elev dupa ID-ul sau
    public boolean updateStudentStatus(int id) {
        // Construieste interogarea SQL pentru a actualiza starea contului unui elev
        String query = "UPDATE STUDENTS SET ACCOUNT_STATUS = 'APPROVED' WHERE SID = " + id;
        // Executa interogarea si returneaza rezultatul
        return this.persistence.executeQuery(query);
    }
}
