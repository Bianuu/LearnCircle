package model.geometrie;

public class Punct implements ObiectGeometric {
    private float x; // Coordonata x a punctului
    private float y; // Coordonata y a punctului

    // Constructor care initializeaza coordonatele punctului
    public Punct(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Metoda pentru a obtine coordonata x a punctului
    public float getX() {
        return x;
    }

    // Metoda pentru a obtine coordonata y a punctului
    public float getY() {
        return y;
    }

    // Metoda pentru calcularea distantei intre doua puncte
    public double computeDistance(Punct a) {
        return Math.sqrt(Math.pow((this.x - a.x), 2) + Math.pow((this.y - a.y), 2));
    }

    // Suprascrierea metodei toString pentru a afisa coordonatele punctului
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
