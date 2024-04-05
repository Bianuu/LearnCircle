package conectBDpersis.persistence;

import conectBDpersis.Persistence;
import model.test.Dificultate;
import model.test.Intrebare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Intrebari {

    private Persistence persistence;

    // Constructor pentru clasa Intrebari
    public Intrebari() {
        this.persistence = new Persistence();
    }

    // Metoda pentru inserarea unei intrebari in baza de date
    public boolean insertQuestion(Intrebare intrebare) {
        String query = "INSERT INTO QUESTIONS (QUESTION,DIFFICULTY,ANSWER_A,ANSWER_B,ANSWER_C,ANSWER_D,IMAGE_FILE) VALUES ('" +
                intrebare.getText() + "','" +
                intrebare.getDifficulty().toString() + "','" +
                intrebare.getOptions().get(0) + "','" +
                intrebare.getOptions().get(1) + "','" +
                intrebare.getOptions().get(2) + "','" +
                intrebare.getOptions().get(3) + "',";
        if (!intrebare.getImageFile().isEmpty()) {
            query += "'" + intrebare.getImageFile() + "'";
        } else query += "null";
        query += ")";
        return this.persistence.executeQuery(query);
    }

    // Metoda pentru gasirea unei intrebari dupa ID
    public Intrebare findQuestionById(int id) {
        Intrebare intrebare = null;
        String query = "SELECT * FROM QUESTIONS WHERE QID = " + id;

        ResultSet resultSet = this.persistence.getDataTable(query);

        try {
            if (resultSet.next()) {
                Dificultate dificultate = Dificultate.valueOf(resultSet.getString("DIFFICULTY"));
                ArrayList<String> options = new ArrayList<>();
                options.add(resultSet.getString("ANSWER_A"));
                options.add(resultSet.getString("ANSWER_B"));
                options.add(resultSet.getString("ANSWER_C"));
                options.add(resultSet.getString("ANSWER_D"));
                intrebare = new Intrebare(resultSet.getString("QUESTION"), dificultate, options, resultSet.getString("IMAGE_FILE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return intrebare;
    }
}