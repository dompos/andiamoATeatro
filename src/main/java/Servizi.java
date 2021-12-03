import javax.management.Query;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Servizi {
    private static PreparedStatement insert;
    private static PreparedStatement select;
    private static PreparedStatement selectAll;
    public static final String URL = "jdbc:postgresql://localhost:5432/andiamoateatro";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD ="AlberobellO"; //inserite le vostre pssw


    public static boolean caricaUtente(Connection conn, Utente utente)  throws SQLException{
        if (scaricaUtente(conn, utente.getEmail()) != null)
            return false;
                insert = conn.prepareStatement("insert into public.utente " +
                        "(email, cognome, nome, residenza, telefono)" +
                        "values ('" + utente.getEmail() + "','" + utente.getCognome() + "','" + utente.getNome() + "','"
                        + utente.getIndirizzo() + "','" + utente.getTelefono() + "')");
                insert.executeUpdate();
                insert.close();
            return true;

    }

    public static Utente scaricaUtente(Connection conn, String emailUtente) throws SQLException{
        Utente utente = null;
        select = conn.prepareStatement("select * from public.utente where email = '" + emailUtente + "'" );
        ResultSet risultato = select.executeQuery();
            while (risultato.next())
            {
                utente = new Utente(risultato.getString("nome"),
                        risultato.getString("cognome"),
                        risultato.getString("residenza"),
                        risultato.getString("email"),
                        risultato.getString("telefono"));
            }
        select.close();
        if (utente == null) {
            System.out.println("Non esiste un utente con questa mail: " + emailUtente);
            return null;
        }
        else {
            System.out.println(utente.toString());
            return utente;
        }
    }

    public static Sede scaricaSede(Connection conn, int id) throws SQLException{
        Sede sede = null;
        select = conn.prepareStatement("select * from public.sede where id = " + id );
        ResultSet risultato = select.executeQuery();
        while (risultato.next()) {
               sede = new Sede(risultato.getString("nome"),
               risultato.getString("indirizzo"),
               risultato.getString("comune"),
               risultato.getInt("id"),
               risultato.getBoolean("coperto"));
            }
        select.close();
        if (sede == null) {
            System.out.println("Non esiste sede con questo id: " + id);
            return null;
        }else{
            System.out.println(sede.toString());
            return sede;
        }
    }

    public static Sala scaricaSala(Connection conn, String nome) throws SQLException{
        Sala sala = null;
        select = conn.prepareStatement("select * from public.sala where nome = '" + nome + "'");
        ResultSet risultato = select.executeQuery();
            while (risultato.next()) {
                sala = new Sala(risultato.getInt("n_posti"),
                        risultato.getInt("sede_id"),
                        risultato.getString("nome"));
            }
        select.close();
        if (sala == null) {
            System.out.println("Non esiste sala con questo nome: " + nome);
            return null;
        }else{
            System.out.println(sala.toString());
            return sala;
        }
    }

    public static Prenotazioni scaricaPrenotazione(Connection conn, int id) throws SQLException{
        Prenotazioni prenotazione = null;
            select = conn.prepareStatement("select * from public.prenotazioni where id = " + id );
            ResultSet risultato = select.executeQuery();
            while (risultato.next()) {
                prenotazione = new Prenotazioni(risultato.getInt("id"),
                        risultato.getInt("spettacoli_id"),
                        risultato.getInt("posti_id"),
                        risultato.getString("utente_email"));
            }
        select.close();
        if (prenotazione == null) {
            System.out.println("Non esiste prenotazione con questo id: " + id);
            return null;
        }else{
            System.out.println(prenotazione.toString());
            return prenotazione;
        }
    }

    public static Spettacoli scaricaSpettacolo(Connection conn, int id) throws SQLException{
        Spettacoli spettacolo = null;
        select = conn.prepareStatement("select * from public.spettacoli where id = " + id );
        ResultSet risultato = select.executeQuery();
        while (risultato.next()) {
            spettacolo = new Spettacoli(risultato.getString("nome"),
                    risultato.getString("sala_nome"),
                    risultato.getString("genere"),
                    risultato.getDate("orario"),
                    risultato.getDate("giorno"),
                    risultato.getDouble("prezzo"),
                    risultato.getInt("durata"),
                    risultato.getInt("id"));
        }
        select.close();
        if (spettacolo == null) {
            System.out.println("Non esiste prenotazione con questo id: " + id);
            return null;
        }else{
            System.out.println(spettacolo.toString());
            return spettacolo;
        }
    }

    public static Posti scaricaPosti(Connection conn, int id) throws SQLException{
        Posti posto = null;
            select = conn.prepareStatement("select * from public.posti where id = " + id );
            ResultSet risultato = select.executeQuery();
            while(risultato.next()) {
                posto = new Posti(risultato.getString("fila"),
                        risultato.getString("sala_nome"),
                        risultato.getInt("numero"),
                        risultato.getInt("id"));
            }
        select.close();
        if (posto == null) {
            System.out.println("Non esiste posto con questo id: " + id);
            return null;
        }else{
            System.out.println(posto.toString());
            return posto;
        }
    }

    public static List<Object> scaricaTutto(Connection conn, String oggetto) throws SQLException{

        selectAll = conn.prepareStatement("select * from public." + oggetto.toLowerCase());
        ResultSet risultato = selectAll.executeQuery();
        List<Object> lista;
        switch (oggetto.toLowerCase()){
            case "utenti":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaUtente(conn, risultato.getString("email")));
                }
                return lista;
            case "spettacoli":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaSpettacolo(conn, risultato.getInt("id")));
                }
                return lista;
            case "posti":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaPosti(conn, risultato.getInt("id")));
                }
                return lista;
            case "prenotazioni":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaPrenotazione(conn, risultato.getInt("id")));
                }
                return lista;
            case "sale":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaSala(conn, risultato.getString("nome")));
                }
                return lista;
            case "sedi":
                lista = new ArrayList<>();
                while (risultato.next()){
                    lista.add(scaricaSede(conn, risultato.getInt("id")));
                }
                return lista;
        }
        return null;
    }

    public List<Spettacoli> suggerimenti(String emailUtente){
        return new ArrayList<>();
    }
}
