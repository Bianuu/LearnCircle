package conectBDpersis.persistence;

import conectBDpersis.Persistence;
import model.test.Punctaj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Teste {
    private Persistence persistence;

    public Teste() {
        this.persistence = new Persistence();
    }

    // Metoda pentru inserarea unui test în baza de date
    public boolean insertTest(int sid, int points, Timestamp timestamp) {
        String query = "INSERT INTO TESTS (SID, POINTS, TEST_TIME) VALUES (" + sid + "," + points + ",'" + timestamp + "')";
        return this.persistence.executeQuery(query);
    }

    // Metoda pentru ștergerea tuturor testelor unui student din baza de date
    public boolean deleteAllTestsOfStudent(int sid) {
        String query = "DELETE FROM TESTS WHERE SID = " + sid;
        return this.persistence.executeQuery(query);
    }

    // Metoda pentru găsirea tuturor testelor unui student din baza de date
    public ArrayList<Punctaj> findTestsByStudent(int sid) {
        ArrayList<Punctaj> testTableEntries = new ArrayList<>();

        String query = "SELECT STUDENTS.NAME, STUDENTS.SURNAME, TESTS.POINTS, TESTS.TEST_TIME FROM " +
                "STUDENTS INNER JOIN TESTS ON (STUDENTS.SID = TESTS.SID AND STUDENTS.SID = " + sid + ")";

        ResultSet resultSet = this.persistence.getDataTable(query);

        Punctaj tte;
        int index = 1;

        if (resultSet == null)
            return testTableEntries;

        try {
            while (resultSet.next()) {
                tte = new Punctaj(index, resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        Integer.parseInt(resultSet.getString("POINTS")), resultSet.getTimestamp("TEST_TIME"));
                testTableEntries.add(tte);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testTableEntries;
    }
}
