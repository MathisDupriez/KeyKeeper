package KeyKeeper.Modele;

public class Password {
    private String name;
    private String description;
    private String password;

    public Password(String name, String description, String password) {
        this.name = name;
        this.description = description;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
