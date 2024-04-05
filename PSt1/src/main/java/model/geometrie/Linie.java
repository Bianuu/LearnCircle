package model.geometrie;

public class Linie implements ObiectGeometric {
    private Punct start; // Punctul de start al liniei
    private Punct end; // Punctul de sfarsit al liniei

    public Linie(Punct start, Punct end) {
        this.start = start; // Seteaza punctul de start al liniei
        this.end = end; // Seteaza punctul de sfarsit al liniei
    }

    public Punct getStart() {
        return start; // Returneaza punctul de start al liniei
    }

    public Punct getEnd() {
        return end; // Returneaza punctul de sfarsit al liniei
    }

    // Calculeaza lungimea liniei
    public double computeLength() {
        return start.computeDistance(end); // Calculeaza distanta intre punctul de start si punctul de sfarsit
    }

    // Calculeaza unghiul dintre doua linii
    public double computeAngle(Linie linie) {
        double slope1 = this.computeSlope(); // Panta primei linii
        double slope2 = linie.computeSlope(); // Panta celei de-a doua linii

        if (Double.isInfinite(slope1) && Double.isInfinite(slope2)) {
            return 0.0; // Daca ambele linii sunt verticale, returneaza 0 grade
        } else if (Double.isInfinite(slope1)) {
            double angle = Math.atan(slope2); // Calculeaza unghiul pentru a doua linie
            return Math.toDegrees(slope2 > 0 ? angle : (angle + Math.PI / 2)); // Returneaza unghiul in grade
        } else if (Double.isInfinite(slope2)) {
            double angle = Math.atan(slope1); // Calculeaza unghiul pentru prima linie
            return Math.toDegrees(slope1 > 0 ? angle : (angle + Math.PI / 2)); // Returneaza unghiul in grade
        } else {
            return Math.abs(Math.toDegrees(Math.atan((slope2 - slope1) / (1 + slope1 * slope2))));
            // Calculeaza unghiul dintre cele doua linii
        }
    }

    // Calculeaza panta liniei
    public double computeSlope() {
        if (end.getX() - start.getX() == 0) {
            return Double.POSITIVE_INFINITY; // Daca linia este verticala, returneaza infinit
        } else return (end.getY() - start.getY()) / (end.getX() - start.getX()); // Calculeaza panta
    }

    // Calculeaza punctul de mijloc al liniei
    public Punct computeMiddlePoint() {
        return new Punct((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
        // Returneaza un nou punct care reprezinta mijlocul liniei
    }

    @Override
    public String toString() {
        return this.start.toString() + "->" + this.end.toString(); // Returneaza reprezentarea sub forma de sir de caractere a liniei
    }
}
