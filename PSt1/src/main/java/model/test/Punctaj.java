package model.test;

import java.sql.Timestamp;

public class Punctaj {
    private int index; // Indexul punctajului
    private String name; // Numele persoanei
    private String surname; // Prenumele persoanei
    private int points; // Punctajul obtinut
    private Timestamp timestamp; // Marca temporala a punctajului

    // Constructor pentru initializarea punctajului
    public Punctaj(int index, String name, String surname, int points, Timestamp timestamp) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.timestamp = timestamp;
    }

    // Metoda pentru a obtine numele persoanei
    public String getName() {
        return name;
    }

    // Metoda pentru a obtine prenumele persoanei
    public String getSurname() {
        return surname;
    }

    // Metoda pentru a obtine punctajul
    public int getPoints() {
        return points;
    }

    // Metoda pentru a obtine marca temporala a punctajului
    public Timestamp getTimestamp() {
        return timestamp;
    }

    // Metoda pentru a obtine indexul punctajului
    public int getIndex() {
        return index;
    }
}
