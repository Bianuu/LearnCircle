package model.geometrie;

public class Cerc implements ObiectGeometric {
    private Punct origin; // Coordonatele originii cercului
    private float radius; // Raza cercului

    public Cerc(Punct origin, float radius) {
        this.origin = origin; // Seteaza originile cercului
        this.radius = radius; // Seteaza raza cercului
    }

    // Calculeaza aria cercului
    public float computeArea() {
        return (float) (Math.PI * radius * radius); // Formula pentru calculul ariei
    }

    // Calculeaza perimetrul cercului
    public float computePerimeter() {
        return (float) (2 * Math.PI * radius); // Formula pentru calculul perimetrului
    }
}
