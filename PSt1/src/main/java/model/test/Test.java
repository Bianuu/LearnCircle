package model.test;

import conectBDpersis.persistence.Intrebari;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    private final int MAXIMUM_POINTS; // Punctajul maxim al testului
    private final ArrayList<Integer> correctAnswers; // Lista de raspunsuri corecte
    private ArrayList<Intrebare> intrebarises; // Lista de intrebari

    // Constructor pentru initializarea testului
    public Test() {
        intrebarises = new ArrayList<>(); // Initializarea listei de intrebari
        correctAnswers = new ArrayList<>(); // Initializarea listei de raspunsuri corecte
        Intrebari persistence = new Intrebari(); // Obiect pentru a accesa intrebarile din baza de date
        ArrayList<Integer> qids = new ArrayList<>(); // Lista de id-uri ale intrebarilor
        for (int i = 1; i <= 50; i++)
            qids.add(i); // Adaugarea id-urilor in lista de id-uri
        Collections.shuffle(qids); // Amestecarea id-urilor intrebarilor
        int points = 0; // Initializarea punctajului cu 0
        int SIZE = 10; // Numarul de intrebari din test
        for (int i = 1; i <= SIZE; i++) {
            Intrebare q = persistence.findQuestionById(qids.get(i)); // Gasirea intrebarii in baza de date
            points += q.getDifficulty().value(); // Adaugarea dificultatii intrebarii la punctaj
            correctAnswers.add(q.randomizeOptions()); // Adaugarea raspunsului corect amestecat in lista de raspunsuri corecte
            intrebarises.add(q); // Adaugarea intrebarii in lista de intrebari
        }
        MAXIMUM_POINTS = points; // Initializarea punctajului maxim al testului
    }

    // Metoda pentru a obtine lista de intrebari
    public ArrayList<Intrebare> getQuestions() {
        return intrebarises;
    }

    // Metoda pentru a obtine punctajul maxim al testului
    public int getMAXIMUM_POINTS() {
        return MAXIMUM_POINTS;
    }

    // Metoda pentru a obtine lista de raspunsuri corecte
    public ArrayList<Integer> getCorrectAnswers() {
        return correctAnswers;
    }
}
