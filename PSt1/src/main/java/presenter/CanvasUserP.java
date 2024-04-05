package presenter;

import view.afisaj.Canvas;
import view.afisaj.ContNou;
import view.afisaj.Login;

public class CanvasUserP {

    private Canvas canvasView; // Referinta catre interfata Canvas

    // Constructor pentru initializarea presenter-ului CanvasUserP
    public CanvasUserP(Canvas cv) {
        canvasView = cv; // Setarea interfetei Canvas
    }

    // Metoda pentru deschiderea interfetei de autentificare
    public void openLogin() {
        canvasView.setVisible(false); // Ascunderea interfetei Canvas
        new Login(canvasView); // Deschiderea interfetei de autentificare
    }

    // Metoda pentru deschiderea interfetei de creare cont nou
    public void openNewAccount() {
        canvasView.setVisible(false); // Ascunderea interfetei Canvas
        new ContNou(canvasView); // Deschiderea interfetei de creare cont nou
    }
}
