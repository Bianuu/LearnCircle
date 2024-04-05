package view.resurse;

import javax.swing.*;
import java.util.ArrayList;

public interface ITest {
    void fillPanel(String question, ArrayList<String> options); // Metoda pentru umplerea panoului cu intrebare si optiuni

    void fillPanel(String question, ArrayList<String> options, ImageIcon img); // Metoda pentru umplerea panoului cu intrebare, optiuni si o imagine

    void getEndTest(); // Metoda pentru obtinerea butonului de incheiere a testului

    void getNextButton(); // Metoda pentru obtinerea butonului de urmatoare

    void getEndTestF(); // Metoda pentru obtinerea butonului de incheiere a testului

    void getNextButtonF(); // Metoda pentru obtinerea butonului de urmatoare

    Boolean getOption1(); // Metoda pentru obtinerea primului buton de optiune

    Boolean getOption2(); // Metoda pentru obtinerea celui de-al doilea buton de optiune

    Boolean getOption3(); // Metoda pentru obtinerea celui de-al treilea buton de optiune

    Boolean getOption4(); // Metoda pentru obtinerea celui de-al patrulea buton de optiune

    int getSid(); // Metoda pentru obtinerea identificatorului de sesiune
}
