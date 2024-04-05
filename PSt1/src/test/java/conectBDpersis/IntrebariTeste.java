package conectBDpersis;

import conectBDpersis.persistence.Intrebari;
import model.test.Dificultate;
import model.test.Intrebare;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IntrebariTeste {

    @Test
    void insertQuestion() {
        Intrebari qp = new Intrebari();
        String text = "QUESTION?";
        String opt1 = "1";
        String opt2 = "2";
        String opt3 = "3";
        String opt4 = "4";
        ArrayList<String> opts = new ArrayList<>();
        opts.add(opt1);
        opts.add(opt2);
        opts.add(opt3);
        opts.add(opt4);
        Intrebare q = new Intrebare(text, Dificultate.EASY, opts, "");
        boolean value = qp.insertQuestion(q);
        assertTrue(value);
    }

    @Test
    void findQuestionById() {
        int id = 10;
        Intrebari qp = new Intrebari();
        Intrebare q = qp.findQuestionById(id);
        assertEquals(q.getDifficulty(), Dificultate.MEDIUM);
        assertNull(q.getImageFile());
    }
}