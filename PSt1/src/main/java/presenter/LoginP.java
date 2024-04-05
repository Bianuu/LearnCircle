package presenter;

import conectBDpersis.creareBD;
import conectBDpersis.persistence.Elevi;
import model.user.Elev;
import model.user.StatusCont;
import view.afisaj.Admin;
import view.afisaj.Cont;
import view.afisaj.Login;

import javax.swing.*;

public class LoginP {

    private Login loginView; // Referinta catre interfata Login
    private Elev elev; // Elevul curent

    // Constructor pentru initializarea presenter-ului LoginP
    public LoginP(Login lv) {
        creareBD db = new creareBD();
        db.createTableStudent();
        this.loginView = lv; // Setarea interfetei Login
    }

    // Metoda pentru autentificare
    public void login() {
        String name = loginView.getNicknameField();
        String password = loginView.getPasswordField();

        if (name == null || password == null || name.isEmpty() || password.isEmpty()) {
            loginView.getErrorLabel(); // Afisarea etichetei de eroare
        } else {
            Elevi sp = new Elevi();
            elev = sp.findStudentByNickname(name); // Cautarea elevului dupa nume de utilizator
            if (elev != null) {
                if (elev.getAccountStatus() == StatusCont.ADMIN) {
                    Admin av = new Admin(loginView.getCanvasView()); // Deschiderea interfetei de administrator
                    loginView.setVisible(false); // Ascunderea interfetei de autentificare
                    reset(); // Resetarea campurilor de autentificare
                    //loginView.getCanvasView().setBarLabel(elev.getNickname()); // Setarea etichetei barei de navigare
                } else if (elev.getAccountStatus() == StatusCont.APPROVED) {
                    Cont av = new Cont(elev.getName(), elev.getSurname(), elev.getNickname(), loginView.getCanvasView()); // Deschiderea interfetei de cont
                    loginView.setVisible(false); // Ascunderea interfetei de autentificare
                    //loginView.getCanvasView().setBarLabel(elev.getNickname()); // Setarea etichetei barei de navigare
                    reset(); // Resetarea campurilor de autentificare
                } else
                    JOptionPane.showMessageDialog(loginView, "Contul dumneavoastra nu a fost aprobat inca", "EROARE", JOptionPane.ERROR_MESSAGE);
            } else {
                loginView.getErrorLabel(); // Afisarea etichetei de eroare
            }
        }
    }

    // Metoda pentru resetarea campurilor de autentificare
    private void reset() {
        loginView.setNicknameTextField(null); // Stergerea continutului campului de nume de utilizator
        loginView.setPasswordTextField(null); // Stergerea continutului campului de parola
    }

    // Metoda pentru revenirea la ecranul principal
    public void back() {
        loginView.getCanvasView().setVisible(true); // Afisarea ecranului principal
        loginView.dispose(); // Inchiderea ferestrei de autentificare
    }

}
