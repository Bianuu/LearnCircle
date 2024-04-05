package presenter;

import model.geometrie.Cerc;
import model.geometrie.Poligon;
import model.geometrie.Punct;
import view.afisaj.Canvas;

import javax.swing.*;
import java.awt.*;

public class GeometrieP {

    private Canvas canvasView; // Referinta catre interfata Canvas

    // Constructor pentru initializarea presenter-ului GeometrieP
    public GeometrieP(Canvas cv) {
        canvasView = cv; // Setarea interfetei Canvas
    }

    // Metoda pentru schimbarea culorii desenului
    public void changeColor(JButton button) {
        canvasView.getCanvasPanel().setColor(button.getBackground());
    }

    // Metoda pentru schimbarea grosimii trasaturii
    public void changeStroke() {
        Stroke stroke = new BasicStroke(canvasView.getSlider(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        canvasView.getCanvasPanel().setStroke(stroke);
    }

    // Metoda pentru schimbarea tiparului trasaturii
    public void changePattern(float[] pattern) {
        Stroke stroke = new BasicStroke(canvasView.getSlider(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, pattern, 0.0f);
        canvasView.getCanvasPanel().setStroke(stroke);
    }

    // Metoda pentru afisarea informatiilor despre cerc
    public void displayCircleInfo() {
        Cerc c = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getCircle();
        if (c != null) {
            float area = c.computeArea();
            float perimeter = c.computePerimeter();
            String message = "Aria = " + area + "\nPerimetru = " + perimeter + "\nArieSector = " + area / (2 * Math.PI) + "teta";
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), message, "Informatii Cerc", JOptionPane.PLAIN_MESSAGE);
        } else
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), "Nici un cerc in canvas", "EROARE", JOptionPane.ERROR_MESSAGE);
    }

    // Metoda pentru desenarea cercului circumscris
    public void drawCircumscribed() {
        Poligon p = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getPolygon();
        if (p != null) {
            if (p.isCircumscribed()) {
                Punct cc = p.getCircumcenter();
                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                Punct rand = p.getVertices().get(0);
                float radius = (float) Math.sqrt((rand.getX() - cc.getX()) * (rand.getX() - cc.getX()) + (rand.getY() - cc.getY()) * (rand.getY() - cc.getY()));
                g2d.drawOval((int) (cc.getX() - radius), (int) (cc.getY() - radius), (int) (2 * radius), (int) (2 * radius));
            } else
                JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), "Poligonul nu poate fi circumscris", "EROARE", JOptionPane.ERROR_MESSAGE);
        } else
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), "Nici un poligon in canvas", "EROARE", JOptionPane.ERROR_MESSAGE);
    }

    // Metoda pentru desenarea cercului inscris
    public void drawInscribed() {
        Poligon p = canvasView.getCanvasPanel().getDrawingCanvasPresenter().getPolygon();
        if (p != null) {
            if (p.isInscribed()) {
                Punct ic = p.getIncenter();
                Graphics g = canvasView.getCanvasPanel().getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(canvasView.getCanvasPanel().getColor());
                g2d.setStroke(canvasView.getCanvasPanel().getStroke());
                Punct start = p.getVertices().get(0);
                Punct end = p.getVertices().get(1);
                float a = start.getY() - end.getY();
                float b = end.getX() - start.getX();
                float c = start.getX() * end.getY() - start.getY() * end.getX();
                float radius = (float) (Math.abs(a * ic.getX() + b * ic.getY() + c) / Math.sqrt(a * a + b * b));
                g2d.drawOval((int) (ic.getX() - radius), (int) (ic.getY() - radius), (int) (2 * radius), (int) (2 * radius));
            } else
                JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), "Poligonul nu poate fi inscris", "EROARE", JOptionPane.ERROR_MESSAGE);
        } else
            JOptionPane.showMessageDialog(canvasView.getCanvasPanel(), "Nici un poligon in canvas", "EROARE", JOptionPane.ERROR_MESSAGE);
    }

}
