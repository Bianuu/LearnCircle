package model.geometrie;

import java.util.ArrayList;

public class Poligon implements ObiectGeometric {
    private ArrayList<Punct> vertices; // Varfurile poligonului

    public Poligon(ArrayList<Punct> vertices) {
        this.vertices = vertices; // Seteaza varfurile poligonului
    }

    public ArrayList<Punct> getVertices() {
        return vertices; // Returneaza varfurile poligonului
    }

    public boolean isInscribed() {
        if (this.vertices.size() == 3)
            return true; // Verifica daca poligonul este un triunghi inscris
        else {
            ArrayList<Linie> linies = this.transformToLines(); // Transforma poligonul in linii
            float angle = (float) linies.get(0).computeAngle(linies.get(1)); // Calculeaza unghiul dintre primele doua linii
            float length = (float) linies.get(0).computeLength(); // Calculeaza lungimea primei linii
            for (int i = 0; i < linies.size(); i++) {
                if ((int) linies.get(0).computeLength() != (int) length)
                    return false; // Verifica daca toate liniile au aceeasi lungime
                if (i == linies.size() - 1) {
                    if ((int) linies.get(i).computeAngle(linies.get(0)) != (int) angle)
                        return false; // Verifica daca toate unghiurile sunt egale
                } else {
                    if ((int) linies.get(i).computeAngle(linies.get(i + 1)) != (int) angle)
                        return false; // Verifica daca toate unghiurile sunt egale
                }
            }
            return true; // Poligonul este inscris
        }
    }

    public ArrayList<Linie> transformToLines() {
        ArrayList<Linie> linies = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Linie linie;
            if (i == vertices.size() - 1) {
                linie = new Linie(this.vertices.get(i), this.vertices.get(0)); // Creeaza o linie intre ultimul varf si primul varf
            } else
                linie = new Linie(this.vertices.get(i), this.vertices.get(i + 1)); // Creeaza o linie intre varfurile consecutive
            linies.add(linie);
        }
        return linies; // Returneaza lista de linii
    }

    public boolean isCircumscribed() {
        Punct center = this.getCircumcenter(); // Calculeaza centrul circumscrisului
        float distance = (float) center.computeDistance(vertices.get(0)); // Calculeaza distanta de la centrul circumscrisului la primul varf
        for (Punct vertex : vertices.subList(1, vertices.size())) {
            if ((int) center.computeDistance(vertex) != (int) distance)
                return false; // Verifica daca toate distantele de la centrul circumscrisului la varfuri sunt egale
        }
        return true; // Poligonul este circumscris
    }

    public Punct getCircumcenter() {
        Punct punct;

        ArrayList<Linie> linies = this.transformToLines(); // Transforma poligonul in linii
        Linie linie1;
        double slope1;
        int index = 0;
        while (linies.get(index).computeSlope() == 0) {
            index++;
        }
        linie1 = linies.get(index);
        slope1 = linie1.computeSlope();
        index++;

        Linie linie2;
        double slope2;
        while (linies.get(index).computeSlope() == 0) {
            index++;
        }
        linie2 = linies.get(index);
        slope2 = linie2.computeSlope();

        Punct middle1 = linie1.computeMiddlePoint(); // Calculeaza mijlocul primei linii
        Punct middle2 = linie2.computeMiddlePoint(); // Calculeaza mijlocul celei de-a doua linii

        float x = (float) ((middle2.getX() / slope2 - middle1.getX() / slope1 + middle2.getY() - middle1.getY()) / (1 / slope2 - 1 / slope1));
        float y = (float) (-1 / slope1 * (x - middle1.getX()) + middle1.getY());

        punct = new Punct(x, y);

        return punct; // Returneaza centrul circumscrisului
    }

    public Punct getIncenter() {
        Punct incenter;
        if (this.vertices.size() == 3) {
            ArrayList<Linie> linies = this.transformToLines(); // Transforma poligonul in linii
            float x = (float) ((this.vertices.get(0).getX() * linies.get(2).computeLength() +
                    this.vertices.get(1).getX() * linies.get(0).computeLength() +
                    this.vertices.get(2).getX() * linies.get(1).computeLength()) /
                    (linies.get(0).computeLength() + linies.get(1).computeLength() + linies.get(2).computeLength())); // Calculeaza coordonata x a incenter-ului
            float y = (float) ((this.vertices.get(0).getY() * linies.get(2).computeLength() +
                    this.vertices.get(1).getY() * linies.get(0).computeLength() +
                    this.vertices.get(2).getY() * linies.get(1).computeLength()) /
                    (linies.get(0).computeLength() + linies.get(1).computeLength() + linies.get(2).computeLength())); // Calculeaza coordonata y a incenter-ului
            incenter = new Punct(x, y); // Creeaza punctul incenter
        } else {
            incenter = this.getCircumcenter(); // Daca poligonul nu este un triunghi, incenter-ul coincide cu centrul circumscrisului
        }
        return incenter; // Returneaza incenter-ul
    }
}
