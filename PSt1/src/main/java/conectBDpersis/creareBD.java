package conectBDpersis;

import conectBDpersis.persistence.Intrebari;
import model.test.Dificultate;
import model.test.Intrebare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class creareBD {

    public creareBD() {

    }

    // Verifica daca tabela "students" exista deja in baza de date
    public boolean createTableStudent() {
        Persistence persistence = new Persistence();
        DatabaseMetaData md;
        boolean ok = true;
        try {
            persistence.connect();
            md = persistence.connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                if (rs.getString(3).equals("students"))
                    ok = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }

        // Daca tabela nu exista, o creeaza si insereaza un administrator
        if (ok) {
            String query = "CREATE TABLE STUDENTS (" +
                    "SID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(30) NOT NULL," +
                    "SURNAME VARCHAR(30) NOT NULL," +
                    "NICKNAME VARCHAR(30) UNIQUE NOT NULL," +
                    "PASSWORD VARCHAR(40) NOT NULL," +
                    "ACCOUNT_STATUS VARCHAR(10) NOT NULL)";

            return persistence.executeQuery(query) && insertAdmin();
        } else return false;
    }

    // Insereaza un administrator in tabela "students"
    public boolean insertAdmin() {
        Persistence persistence = new Persistence();
        String query = "INSERT INTO STUDENTS (NAME, SURNAME, NICKNAME, PASSWORD, ACCOUNT_STATUS) VALUES " +
                "('ADMIN','ADMIN','ADMIN','ADMIN','ADMIN')";
        return persistence.executeQuery(query);
    }

    // Populeaza tabela "questions" cu intrebari dintr-un fisier dat
    public boolean fillTableQuestions(String filename) {
        Intrebari intrebari = new Intrebari();
        boolean result = createTableQuestions();
        if (!result)
            return false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String question = reader.readLine();
            while (question != null) {
                String difficulty = reader.readLine();
                String option1 = reader.readLine();
                String option2 = reader.readLine();
                String option3 = reader.readLine();
                String option4 = reader.readLine();
                String imageFile = reader.readLine();

                Dificultate diff = Dificultate.valueOf(difficulty);
                ArrayList<String> options = new ArrayList<>();
                options.add(option1);
                options.add(option2);
                options.add(option3);
                options.add(option4);

                Intrebare ques = new Intrebare(question, diff, options, imageFile);

                result = result && intrebari.insertQuestion(ques);

                question = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Creeaza tabela "questions" in baza de date
    private boolean createTableQuestions() {
        Persistence persistence = new Persistence();
        DatabaseMetaData md;
        boolean ok = true;
        try {
            persistence.connect();
            md = persistence.connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                if (rs.getString(3).equals("questions"))
                    ok = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            persistence.close();
        }

        // Daca tabela nu exista, o creeaza
        if (ok) {
            String query = "CREATE TABLE QUESTIONS (" +
                    "QID SERIAL PRIMARY KEY," +
                    "QUESTION VARCHAR(300) NOT NULL," +
                    "DIFFICULTY VARCHAR(8) NOT NULL," +
                    "ANSWER_A VARCHAR(70) NOT NULL," +
                    "ANSWER_B VARCHAR(70) NOT NULL," +
                    "ANSWER_C VARCHAR(70) NOT NULL," +
                    "ANSWER_D VARCHAR(70) NOT NULL," +
                    "IMAGE_FILE VARCHAR(30))";
            return persistence.executeQuery(query);
        } else return false;

    }

    // Creeaza tabela "tests" in baza de date
    public boolean createTestsTable() {
        Persistence persistence = new Persistence();
        DatabaseMetaData md;
        boolean ok = true;
        try {
            persistence.connect();
            md = persistence.connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                if (rs.getString(3).equals("tests"))
                    ok = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Daca tabela nu exista, o creeaza
        if (ok) {
            String query = "CREATE TABLE TESTS (" +
                    "TID SERIAL PRIMARY KEY," +
                    "SID NUMERIC NOT NULL," +
                    "POINTS NUMERIC NOT NULL," +
                    "TEST_TIME TIMESTAMP NOT NULL)";
            return persistence.executeQuery(query);
        } else return false;
    }
}
