package conectBDpersis;

import conectBDpersis.persistence.Teste;
import model.test.Punctaj;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestPersistenceTeste {

    @org.junit.jupiter.api.Test
    void insertTest() {
        Teste tp = new Teste();
        boolean value = tp.insertTest(5000, 50, new Timestamp(System.currentTimeMillis()));
        assertTrue(value);
    }

    @org.junit.jupiter.api.Test
    void deleteAllTestsOfStudent() {
        Teste tp = new Teste();
        boolean value = tp.deleteAllTestsOfStudent(5000);
        assertTrue(value);
    }

    @org.junit.jupiter.api.Test
    void findTestsByStudent() {
        Teste tp = new Teste();
        ArrayList<Punctaj> tte = tp.findTestsByStudent(1);
        assertEquals(0, tte.size());
    }
}