import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String numeroTelefonico;

    public Utente(String nome, String cognome, String indirizzo, String email, String numeroTelefonico) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
    }

    public static void RegistraUtente(){



    }

    boolean Prenotazione(Spettacoli spettacolo){            //bisogna ancora creare il costruttore

        return true;

    }

    public List<Spettacoli> RicercaSpettacoli(){
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefonico='" + numeroTelefonico + '\'' +
                '}';
    }
}
