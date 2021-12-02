import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String Nome;
    private String Cgnome;
    private String Indirizzo;
    private String email;
    private String NumeroTelefonico;

    public Utente(String nome, String cgnome, String indirizzo, String email, String numeroTelefonico) {
        Nome = nome;
        Cgnome = cgnome;
        Indirizzo = indirizzo;
        this.email = email;
        NumeroTelefonico = numeroTelefonico;
    }

    public static void RegistraUtente(){

    }

    boolean Prenotazione(Spettacoli spettacolo){            //bisogna ancora creare il costruttore

        return true;

    }

    public List<Spettacoli> RicercaSpettacoli(){
        return new ArrayList<>();
    }
}
