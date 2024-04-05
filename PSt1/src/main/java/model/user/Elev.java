package model.user;

public class Elev {

    private String name; // Numele elevului
    private String surname; // Prenumele elevului
    private String nickname; // Pseudonimul elevului
    private String password; // Parola elevului
    private StatusCont statusCont; // Starea contului elevului

    // Constructor pentru initializarea elevului cu starea contului
    public Elev(String name, String surname, String nickname, String password, StatusCont statusCont) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.password = password;
        this.statusCont = statusCont;
    }

    // Constructor pentru initializarea elevului cu starea contului setata ca REQUESTED
    public Elev(String name, String surname, String nickname, String password) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.password = password;
        this.statusCont = StatusCont.REQUESTED;
    }

    // Metoda pentru a obtine numele elevului
    public String getName() {
        return name;
    }

    // Metoda pentru a seta numele elevului
    public void setName(String name) {
        this.name = name;
    }

    // Metoda pentru a obtine prenumele elevului
    public String getSurname() {
        return surname;
    }

    // Metoda pentru a obtine pseudonimul elevului
    public String getNickname() {
        return nickname;
    }

    // Metoda pentru a obtine parola elevului
    public String getPassword() {
        return password;
    }

    // Metoda pentru a obtine starea contului elevului
    public StatusCont getAccountStatus() {
        return statusCont;
    }

}
