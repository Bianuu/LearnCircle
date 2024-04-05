package view.resurse;

import view.afisaj.Canvas;

public interface IContNou {
    String getNameField(); // Metoda pentru obtinerea campului de nume

    String getSurnameField(); // Metoda pentru obtinerea campului de prenume

    String getNicknameField(); // Metoda pentru obtinerea campului de pseudonim

    char[] getPasswordField(); // Metoda pentru obtinerea campului de parola

    Canvas getCanvasView(); // Metoda pentru obtinerea vederii canvas-ului
}
