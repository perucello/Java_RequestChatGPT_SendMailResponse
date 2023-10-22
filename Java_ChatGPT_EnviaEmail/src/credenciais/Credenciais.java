package credenciais;

/**
 * *********************************************
 * EducaCiência FastCode 22/10/2023 Credenciais
 * *********************************************
 */
public class Credenciais {

    // Informações da conta de e-mail
    private String emailRemetente = "";
    private String username = "";
    private String password = "";

    public Credenciais() {
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
