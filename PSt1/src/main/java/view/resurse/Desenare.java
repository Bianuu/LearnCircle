package view.resurse;

import presenter.DesenareP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Desenare extends Canvas {
    private boolean point; // Indicator pentru desenarea unui punct
    private boolean circle; // Indicator pentru desenarea unui cerc
    private boolean containsPolygon; // Verifica daca contine un poligon
    private boolean containsCircle; // Verifica daca contine un cerc
    private int x, y; // Coordonatele punctului
    private int startX, startY, endX, endY; // Coordonatele pentru desenarea cercului

    private DesenareP desenareP; // Prezentatorul pentru desenare

    private Color color; // Culoarea pentru desenare
    private Stroke stroke; // Grosimea liniei pentru desenare

    public Desenare(Color color, Stroke stroke) {
        this.color = color;
        this.stroke = stroke;

        point = true;
        circle = false;
        containsPolygon = false;
        containsCircle = false;
        x = -1;
        y = -1;

        desenareP = new DesenareP(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!circle) {
                    point = true;
                    x = e.getX();
                    y = e.getY();
                    if (containsPolygon) {
                        desenareP.clear();
                        containsPolygon = false;
                    }
                    if (containsCircle) {
                        desenareP.clear();
                        containsCircle = false;
                    }
                    if (x != -1 && y != -1)
                        repaint(getGraphics());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (circle) {
                    startX = e.getX();
                    startY = e.getY();
                    desenareP.clear();
                    containsPolygon = containsCircle = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (circle) {
                    endX = e.getX();
                    endY = e.getY();
                    repaint(getGraphics());
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if (key == 'g') {
                    if (!desenareP.createPolygon()) {
                        displayError("Poligonul nu poate fi creat!");
                    }
                    ;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isShiftDown()) {
                    point = false;
                    circle = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!e.isShiftDown()) {
                    circle = false;
                    point = true;
                }
            }
        });
    }

    public void repaint(Graphics g) {
        paint(g);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "EROARE", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(stroke);

        if (point && x != -1 && y != -1) {
            g2d.drawOval(x, y, 6, 6);
            desenareP.addPoint();
        }
        if (circle) {
            float radius = (float) Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));
            g2d.drawOval((int) (startX - radius), (int) (startY - radius), (int) (2 * radius), (int) (2 * radius));
            desenareP.addCircle();
        }
    }

    @Override
    public void update(Graphics g) {

    }

    public int returnx() {
        return x;
    }

    public int returny() {
        return y;
    }

    public int endX() {
        return endX;
    }

    public int endY() {
        return endY;
    }

    public int startX() {
        return startX;
    }

    public int startY() {
        return startY;
    }

    public void setContainsPolygon(boolean containsPolygon) {
        this.containsPolygon = containsPolygon;
    }

    public void setContainsCircle(boolean containsCircle) {
        this.containsCircle = containsCircle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public DesenareP getDrawingCanvasPresenter() {
        return desenareP;
    }
}
