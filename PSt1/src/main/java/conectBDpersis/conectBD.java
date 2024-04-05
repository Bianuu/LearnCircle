package conectBDpersis;

public enum conectBD {
    DRIVER("org.postgresql.Driver"),
    URL("jdbc:postgresql://localhost/proiectPS"),
    USER("postgres"),
    PASSWORD("fabian11");

    private final String value;

    conectBD(String value) {
        this.value = value;
    }

    // Metoda pentru obtinerea valorii
    public String getValue() {
        return value;
    }

}
