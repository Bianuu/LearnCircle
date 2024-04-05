package model.test;

public enum Dificultate {
    EASY(3), // Dificultate usoara cu valoarea 3
    MEDIUM(5), // Dificultate medie cu valoarea 5
    HARD(7); // Dificultate dificila cu valoarea 7

    private final int value; // Valoarea asociata fiecarei dificultati

    // Constructor pentru initializarea valorii
    Dificultate(int value) {
        this.value = value;
    }

    // Metoda pentru a obtine valoarea dificultatii
    public int value() {
        return value;
    }
}
