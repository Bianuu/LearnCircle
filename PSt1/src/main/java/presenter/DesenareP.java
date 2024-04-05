package presenter;

import model.geometrie.*;
import view.resurse.Desenare;

import java.awt.*;
import java.util.ArrayList;

public class DesenareP {

    private ArrayList<ObiectGeometric> objects; // Lista de obiecte geometrice
    private Desenare desenare; // Referinta catre interfata Desenare

    // Constructor pentru initializarea presenter-ului DesenareP
    public DesenareP(Desenare dc) {
        this.desenare = dc; // Setarea interfetei Desenare
        objects = new ArrayList<>(); // Initializarea listei de obiecte geometrice
    }

    // Metoda pentru adaugarea unui punct
    public void addPoint() {
        Punct p = new Punct(desenare.returnx(), desenare.returny()); // Crearea unui obiect de tip Punct
        objects.add(p); // Adaugarea punctului in lista de obiecte geometrice
    }

    // Metoda pentru crearea unui poligon
    public boolean createPolygon() {
        ArrayList<Punct> puncts = new ArrayList<>();
        for (ObiectGeometric t : objects) {
            if (t instanceof Punct) {
                puncts.add((Punct) t);
            } else {
                objects.clear();
                return false;
            }
        }

        if (puncts.size() == 2) {
            Punct p1 = puncts.get(0);
            Punct p2 = puncts.get(1);
            addLine();
            objects.clear();
            return false;
        }

        Poligon pol = new Poligon(puncts);
        ArrayList<Linie> linies = pol.transformToLines();
        Graphics g = desenare.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(desenare.getColor());
        g2d.setStroke(desenare.getStroke());

        for (Linie linie : linies) {
            g2d.drawLine((int) linie.getStart().getX(), (int) linie.getStart().getY(), (int) linie.getEnd().getX(), (int) linie.getEnd().getY());
        }
        desenare.setContainsPolygon(true);
        desenare.repaint();
        objects.clear();
        objects.add(pol);
        return true;
    }

    // Metoda pentru adaugarea unei linii
    public void addLine() {
        Linie linie = new Linie(new Punct(desenare.startX(), desenare.startY()), new Punct(desenare.endX(), desenare.endY()));
        Graphics g = desenare.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine((int) linie.getStart().getX(), (int) linie.getStart().getY(), (int) linie.getEnd().getX(), (int) linie.getEnd().getY());
        objects.add(linie);
    }

    // Metoda pentru adaugarea unui cerc
    public void addCircle() {
        float radius = (float) Math.sqrt((desenare.endX() - desenare.startX()) * (desenare.endX() - desenare.startX()) + (desenare.endY() - desenare.startY()) * (desenare.endY() - desenare.startY()));
        Cerc cerc = new Cerc(new Punct(desenare.startX() - radius, desenare.startY() - radius), radius);
        objects.clear();
        objects.add(cerc);
        desenare.setContainsCircle(true);
    }

    // Metoda pentru stergerea tuturor obiectelor
    public void clear() {
        objects.clear();
        Graphics g = desenare.getGraphics();
        g.setColor(desenare.getBackground());
        g.fillRect(0, 0, desenare.getWidth(), desenare.getHeight());
    }

    // Metoda pentru a obtine cercul desenat
    public Cerc getCircle() {
        for (ObiectGeometric t : objects) {
            if (t instanceof Cerc)
                return (Cerc) t;
        }

        return null;
    }

    // Metoda pentru a obtine poligonul desenat
    public Poligon getPolygon() {
        for (ObiectGeometric t : objects) {
            if (t instanceof Poligon)
                return (Poligon) t;
        }
        return null;
    }
}
