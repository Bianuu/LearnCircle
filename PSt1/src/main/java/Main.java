import conectBDpersis.creareBD;
import view.afisaj.Canvas;

public class Main {
    public static void main(String[] args) {
        creareBD db = new creareBD();
        db.createTestsTable();
        db.createTableStudent();
        db.fillTableQuestions("intrebariTeste.txt");
        new Canvas();
    }
}