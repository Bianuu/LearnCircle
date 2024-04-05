package presenter;

import conectBDpersis.persistence.Elevi;
import conectBDpersis.persistence.Teste;
import model.test.Punctaj;
import view.afisaj.Cont;
import view.afisaj.Test;

import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ContP {

    private Cont accountView; // Referinta catre interfata Cont

    // Constructor pentru initializarea presenter-ului ContP
    public ContP(Cont av) {
        accountView = av; // Setarea interfetei Cont
    }

    // Metoda pentru a prelua testele elevului
    public DefaultTableModel retrieveStudentTests() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Punctaj");
        model.addColumn("Data_Ora");

        Elevi elevi = new Elevi();
        int id = elevi.findIdByNickname(accountView.getNicknameSLabel());

        Teste teste = new Teste();
        ArrayList<Punctaj> entries = teste.findTestsByStudent(id);

        for (Punctaj e : entries) {
            model.addRow(new Object[]{e.getIndex(), e.getPoints(), e.getTimestamp()});
        }

        return model;
    }

    // Metoda pentru a incepe un test nou
    public void takeTest() {
        Elevi elevi = new Elevi();
        int id = elevi.findIdByNickname(accountView.getNicknameSLabel());
        accountView.setVisible(false);
        Test tv = new Test(id);
        tv.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                accountView.setVisible(true);
            }
        });
    }

    // Metoda pentru a reveni la interfata principala
    public void goBack() {
        accountView.getCanvasView().setVisible(true);
        accountView.dispose();
    }
}
