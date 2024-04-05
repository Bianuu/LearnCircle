package model.test;

import java.util.ArrayList;
import java.util.Collections;

public class Intrebare {

    private String text; // Textul intrebarii
    private Dificultate dificultate; // Dificultatea intrebarii
    private ArrayList<String> options; // Optiunile intrebarii
    private String imageFile; // Fisierul imagine asociat intrebarii

    // Constructor pentru initializarea intrebarii
    public Intrebare(String text, Dificultate dificultate, ArrayList<String> options, String imageFile) {
        this.text = text;
        this.dificultate = dificultate;
        this.options = options;
        this.imageFile = imageFile;
    }

    // Metoda pentru a obtine textul intrebarii
    public String getText() {
        return text;
    }

    // Metoda pentru a obtine dificultatea intrebarii
    public Dificultate getDifficulty() {
        return dificultate;
    }

    // Metoda pentru a obtine optiunile intrebarii
    public ArrayList<String> getOptions() {
        return options;
    }

    // Metoda pentru a obtine fisierul imagine asociat intrebarii
    public String getImageFile() {
        return imageFile;
    }

    // Metoda pentru a amesteca optiunile intrebarii si returna indicele optiunii corecte
    public int randomizeOptions() {
        String correct_opt = options.get(0);
        Collections.shuffle(options);
        return options.indexOf(correct_opt);
    }

    // Suprascrierea metodei toString pentru afisarea intrebarii
    @Override
    public String toString() {
        return this.text + "\n"
                + "A." + this.options.get(0) + "\n"
                + "B." + this.options.get(1) + "\n"
                + "C." + this.options.get(2) + "\n"
                + "D." + this.options.get(3) + "\n";
    }
}
