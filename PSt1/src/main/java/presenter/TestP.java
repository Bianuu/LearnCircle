package presenter;

import conectBDpersis.creareBD;
import conectBDpersis.persistence.Teste;
import model.test.Intrebare;
import view.afisaj.Test;

import javax.swing.*;
import java.sql.Timestamp;

public class TestP {

    private Test testView; // View-ul pentru test
    private model.test.Test test; // Modelul testului
    private int index; // Indexul intrebarii curente
    private int points; // Punctele acumulate

    // Constructorul clasei
    public TestP(Test tv) {
        creareBD db = new creareBD(); // Conectare la baza de date
        db.createTestsTable(); // Creare tabel pentru teste
        db.fillTableQuestions("intrebariTeste.txt"); // Incarcare intrebari din fisier
        testView = tv; // Initializare view
    }

    // Metoda pentru inceperea testului
    public void start() {
        index = 0; // Resetarea indexului
        points = 0; // Resetarea punctelor
        test = new model.test.Test(); // Initializare test
        next(); // Afisarea primei intrebari
    }

    // Metoda pentru afisarea urmatoarei intrebari
    public void next() {
        if (index != 0) { // Verificare daca nu este prima intrebare
            int selection = checkQuestion(); // Verificarea raspunsului selectat
            if (selection == -1) { // Daca nu s-a selectat niciun raspuns
                JOptionPane.showMessageDialog(testView, "Va rugam selectati o optiune pentru a continua!",
                        "EROARE", JOptionPane.ERROR_MESSAGE); // Afisare mesaj de eroare
                index--; // Decrementare index pentru a ramane la intrebarea curenta
            } else { // Daca s-a selectat un raspuns
                if (selection == test.getCorrectAnswers().get(index - 1)) // Verificare corectitudine raspunsului
                    points += test.getQuestions().get(index - 1).getDifficulty().value(); // Incrementare puncte
            }
        }
        Intrebare q = test.getQuestions().get(index); // Obtine intrebarea curenta
        if (q.getImageFile() == null) { // Verificare daca intrebarea are imagine
            testView.fillPanel(q.getText(), q.getOptions()); // Afisare intrebare fara imagine
            if (index != 9) { // Verificare daca nu este ultima intrebare
                testView.getEndTestF(); // Ascundere buton de finalizare test
                testView.getNextButton(); // Afisare buton pentru intrebarea urmatoare
            } else { // Daca este ultima intrebare
                testView.getEndTest(); // Afisare buton de finalizare test
                testView.getNextButtonF(); // Ascundere buton pentru intrebarea urmatoare
            }
        } else { // Daca intrebarea are imagine
            ImageIcon img = new ImageIcon(q.getImageFile()); // Incarcare imagine
            testView.fillPanel(q.getText(), q.getOptions(), img); // Afisare intrebare cu imagine
            if (index != 9) { // Verificare daca nu este ultima intrebare
                testView.getEndTestF(); // Ascundere buton de finalizare test
                testView.getNextButton(); // Afisare buton pentru intrebarea urmatoare
            } else { // Daca este ultima intrebare
                testView.getEndTest(); // Afisare buton de finalizare test
                testView.getNextButtonF(); // Ascundere buton pentru intrebarea urmatoare
            }
        }
        index++; // Incrementare index pentru a trece la urmatoarea intrebare
    }

    // Metoda pentru verificarea raspunsului selectat
    private int checkQuestion() {
        int selection = -1; // Initializare selectie cu valoare default
        if (testView.getOption1()) // Verificare selectie pentru optiunea 1
            selection = 0; // Setare selectie la 0
        else if (testView.getOption2()) // Verificare selectie pentru optiunea 2
            selection = 1; // Setare selectie la 1
        else if (testView.getOption3()) // Verificare selectie pentru optiunea 3
            selection = 2; // Setare selectie la 2
        else if (testView.getOption4()) // Verificare selectie pentru optiunea 4
            selection = 3; // Setare selectie la 3

        return selection; // Returnare selectie
    }

    // Metoda pentru incheierea testului
    public void end() {
        int selection = checkQuestion(); // Verificare ultimul raspuns selectat
        if (selection == -1) { // Daca nu s-a selectat niciun raspuns
            JOptionPane.showMessageDialog(testView, "Va rugam selectati o optiune pentru a continua!",
                    "EROARE", JOptionPane.ERROR_MESSAGE); // Afisare mesaj de eroare
            index--; // Decrementare index pentru a ramane la intrebarea curenta
        } else { // Daca s-a selectat un raspuns
            if (selection == test.getCorrectAnswers().get(index - 1)) // Verificare corectitudine raspunsului
                points += test.getQuestions().get(index - 1).getDifficulty().value(); // Incrementare puncte
            JOptionPane.showMessageDialog(testView, "Rezultatul final este " + points + " din " + test.getMAXIMUM_POINTS() + ".",
                    "Rezultate", JOptionPane.INFORMATION_MESSAGE); // Afisare rezultate
            Teste tp = new Teste(); // Creare obiect pentru gestionarea testelor in baza de date
            tp.insertTest(testView.getSid(), points, new Timestamp(System.currentTimeMillis())); // Inregistrare rezultat test in baza de date
            testView.dispose(); // Inchidere fereastra de test
        }
    }
}
