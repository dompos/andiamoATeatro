//------------------------------------------------
/**
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 */       import java.sql.*;        // basta scrivere così
//-----------------------------------------------


import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String telefono;

    public Utente(String nome, String cognome, String indirizzo, String email, String numeroTelefonico) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.telefono = numeroTelefonico;
    }



    //------------------------------------------------------------------

    //getter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() { return indirizzo; }

    public String getEmail() { return email; }

    public String getTelefono() {
        return telefono;
    }

    //metodo

    public static void registraUtente(Connection connection, Utente utente) throws SQLException {
        String query = "SELECT email FROM public.utente WHERE utente.email = '" + utente.getEmail() + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        //utente non presente
        if (resultSet.toString() != null) {
            //insert to nel database
            Servizi.caricaUtente(connection, utente);

            //altrimenti messaggio di avviso
        } else {
            System.out.println("Registrazione fallita! L'utente risulta già registato!");
        }

    }


    //----------------------------------------------------------



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
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
