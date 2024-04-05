package view.resurse;

import view.afisaj.Canvas;

public interface ILogin {
    String getPasswordField(); // Metoda pentru obtinerea continutului campului de parola

    void setNicknameTextField(String text); // Metoda pentru setarea textului campului pentru pseudonim

    void setPasswordTextField(String text); // Metoda pentru setarea textului campului pentru parola

    void getErrorLabel(); // Metoda pentru obtinerea etichetei de eroare

    Canvas getCanvasView(); // Metoda pentru obtinerea vederii canvas-ului
}
