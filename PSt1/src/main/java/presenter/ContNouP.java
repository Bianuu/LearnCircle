package presenter;

import conectBDpersis.persistence.Elevi;
import model.user.Elev;
import view.afisaj.ContNou;

import javax.swing.*;

public class ContNouP {

    private ContNou contNouView; // Referinta catre interfata ContNou

    // Constructor pentru initializarea presenter-ului ContNouP
    public ContNouP(ContNou contNouView) {
        this.contNouView = contNouView; // Setarea interfetei ContNou
        this.contNouView.getCanvasView().setVisible(false); // Ascunderea interfetei Canvas
    }

    // Metoda pentru a trimite cererea de creare cont nou
    public void request() {
        String name = contNouView.getNameField();
        String surname = contNouView.getSurnameField();
        String nickname = contNouView.getNicknameField();
        String password = String.valueOf(contNouView.getPasswordField());

        int id = -1;
        Elevi sp = new Elevi();

        if (nickname != null)
            id = sp.findIdByNickname(nickname);

        if (id == -1) {
            if (name != null && surname != null && !password.isEmpty()) {
                sp.insertStudent(new Elev(name, surname, nickname, password));
                JOptionPane.showMessageDialog(contNouView, "Cererea dumneavoastra a fost trimisa.", "CERERE", JOptionPane.PLAIN_MESSAGE);
                contNouView.getCanvasView().setVisible(true); // Afisarea interfetei Canvas
                contNouView.dispose(); // Inchiderea interfetei ContNou
            } else
                JOptionPane.showMessageDialog(contNouView, "Te rog,nu lasa campuri necompletate.", "EROARE", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(contNouView, "Acest nume este deja folosit.", "EROARE", JOptionPane.ERROR_MESSAGE);
        }
        reset(); // Reseteaza campurile de introducere
    }

    // Metoda pentru a reseta campurile de introducere
    private void reset() {
        contNouView.getNameField();
        contNouView.getSurnameField();
        contNouView.getNicknameField();
        contNouView.getPasswordField();
    }

    // Metoda pentru revenirea la ecranul principal
    public void back() {
        contNouView.getCanvasView().setVisible(true); // Afisarea ecranului principal
        contNouView.dispose(); // Inchiderea ferestrei de autentificare
    }
}
