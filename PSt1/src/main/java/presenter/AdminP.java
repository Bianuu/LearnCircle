package presenter;

import conectBDpersis.persistence.Elevi;
import conectBDpersis.persistence.Teste;
import model.user.Elev;
import model.user.StatusCont;
import view.afisaj.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AdminP {

    private Admin adminView; // Referinta catre interfata de administrare
    private Elevi elevi = new Elevi(); // Obiect pentru a accesa elevii in baza de date

    // Constructor pentru initializarea presenter-ului de administrare
    public AdminP(Admin av) {
        adminView = av; // Setarea interfetei de administrare
    }

    // Metoda pentru a aproba cererea de cont
    public void approve() {
        String nickname = adminView.getNickname();
        if (nickname != null) {
            Elevi sp = new Elevi();
            if (sp.updateStudentStatus(sp.findIdByNickname(nickname)))
                JOptionPane.showMessageDialog(adminView, "Elevul a fost adaugat cu succes!", "TERMINAT", JOptionPane.PLAIN_MESSAGE);
            else JOptionPane.showMessageDialog(adminView, "Ceva nu a mers bine!", "EROARE", JOptionPane.ERROR_MESSAGE);
        } else JOptionPane.showMessageDialog(adminView, "Nici un elev selectat!", "EROARE", JOptionPane.ERROR_MESSAGE);
    }

    // Metoda pentru a afisa toti elevii
    public DefaultTableModel seeAll() {
        ArrayList<Elev> elevs = elevi.findAllStudents();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Prenume");
        dtm.addColumn("Nume");
        dtm.addColumn("Porecla");

        for (Elev elev : elevs)
            if (elev.getAccountStatus() != StatusCont.ADMIN)
                dtm.addRow(new Object[]{elev.getName(), elev.getSurname(), elev.getNickname()});

        return dtm;
    }

    // Metoda pentru a afisa cererile de conturi
    public DefaultTableModel seeRequests() {
        ArrayList<Elev> elevs = elevi.findStudentsByStatus(String.valueOf(StatusCont.REQUESTED));
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Prenume");
        dtm.addColumn("Nume");
        dtm.addColumn("Porecla");

        for (Elev elev : elevs)
            dtm.addRow(new Object[]{elev.getName(), elev.getSurname(), elev.getNickname()});

        return dtm;
    }

    // Metoda pentru a sterge un elev
    public void delete() {
        String nickname = adminView.getNickname();
        if (nickname != null) {
            Elevi sp = new Elevi();
            if (sp.deleteStudent(sp.findIdByNickname(nickname))) {
                Teste tp = new Teste();
                tp.deleteAllTestsOfStudent(sp.findIdByNickname(nickname));
                JOptionPane.showMessageDialog(adminView, "Elevul a fost sters cu succes!", "TERMINAT", JOptionPane.PLAIN_MESSAGE);
            } else
                JOptionPane.showMessageDialog(adminView, "Ceva nu a mers bine!", "EROARE", JOptionPane.ERROR_MESSAGE);
        } else JOptionPane.showMessageDialog(adminView, "Nici un elev selectat!", "EROARE", JOptionPane.ERROR_MESSAGE);
    }

    // Metoda pentru a reveni la interfata principala
    public void back() {
        adminView.getCanvasView().setVisible(true);
        adminView.dispose();
    }
}
