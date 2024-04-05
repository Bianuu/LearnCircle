package conectBDpersis;

import conectBDpersis.persistence.Elevi;
import model.user.Elev;
import model.user.StatusCont;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EleviTeste {

    @Test
    void findStudentByNickname() {
        Elevi sp = new Elevi();
        Elev s1 = sp.findStudentByNickname("cori");
        Elev s2 = sp.findStudentByNickname("anamaria");
        assertNull(s2);
        assertNotNull(s1);
        assertEquals("Corina", s1.getName());
        assertEquals("Cioban", s1.getSurname());
    }


    @Test
    void findAllStudents() {
        Elevi sp = new Elevi();
        ArrayList<Elev> elevs = sp.findAllStudents();
        assertTrue(elevs.size() > 0);
    }

    @Test
    void findStudentsByStatus() {
        Elevi sp = new Elevi();
        ArrayList<Elev> admins = sp.findStudentsByStatus(String.valueOf(StatusCont.ADMIN));
        assertTrue(admins.size() > 0);
    }

    @Test
    void findIdByNickname() {
        Elevi sp = new Elevi();
        int id1 = sp.findIdByNickname("cori");
        int id2 = sp.findIdByNickname("a");
        assertEquals(2, id1);
        assertEquals(12, id2);
    }

    @Test
    void updateStudentStatus() {
        Elevi sp = new Elevi();
        boolean value = sp.updateStudentStatus(8);
        Elev s = sp.findStudentByNickname("cori");
        assertTrue(value);
        assertEquals(StatusCont.APPROVED, s.getAccountStatus());
    }
}