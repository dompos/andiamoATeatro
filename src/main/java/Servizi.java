import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Servizi {
    private static PreparedStatement insert;
    private static PreparedStatement select;
    public static final String URL = "jdbc:postgresql://localhost:5432/andiamoateatro";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD ="AlberobellO"; //inserite le vostre pssw


    public static boolean caricaUtente(Connection conn, Utente utente)  throws SQLException{
        if (scaricaUtente(conn, utente.getEmail()) != null)
            return false;
        insert = getQuery(
                conn,
                "insert",
                "public.utente",
                new ArrayList<>(Arrays.asList("email", "cognome", "nome", "residenza", "telefono")),
                new ArrayList<>(Arrays.asList(
                        "'" + utente.getEmail() + "'",
                        "'" + utente.getCognome() + "'",
                        "'" + utente.getNome() + "'",
                        "'" + utente.getIndirizzo() + "'",
                        "'" + utente.getTelefono() + "'"))
        );
        insert.executeUpdate();
        insert.close();
        return true;

    }

    public static boolean caricaPrenotazione(Connection conn, Prenotazioni prenotazione)  throws SQLException{
        if (scaricaPrenotazione(conn, prenotazione.getId()) != null)
            return false;
        insert = getQuery(
                conn,
                "insert",
                "public.prenotazioni",
                new ArrayList<>(Arrays.asList("id", "posti_id", "spettacoli_id", "utente_email")),
                new ArrayList<>(Arrays.asList(
                        ""+ prenotazione.getId() + "",
                        "" + prenotazione.getIdPosto() + "",
                        "" + prenotazione.getIdSpettacolo() + "",
                        "'" + prenotazione.getEmailUtente() + "'"))
        );
        insert.executeUpdate();
        insert.close();
        return true;

    }

    public static Utente scaricaUtente(Connection conn, String emailUtente) throws SQLException{
        Utente utente = null;
        select = getQuery(
                conn,
                "select",
                "public.utente",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("email = '" + emailUtente + "'"))
        );
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
        select = getQuery(
                conn,
                "select",
                "public.sede",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("id = " + id))
        );
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
        select = getQuery(
                conn,
                "select",
                "public.sala",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("nome = '" + nome + "'"))
        );
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

    public static List<Sala> scaricaSale(Connection conn, List<String> restrictions) throws SQLException{
        Sala sala = null;
        List<Sala> sale = new ArrayList<>();
        select = getQuery(
                conn,
                "select",
                "public.sala",
                new ArrayList<>(),
                restrictions
        );
        ResultSet risultato = select.executeQuery();
        while (risultato.next()) {
            sala = new Sala(risultato.getInt("n_posti"),
                    risultato.getInt("sede_id"),
                    risultato.getString("nome"));
            sale.add(sala);
        }
        select.close();
        if (sale.size() == 0) {
            System.out.println("Non esistono sale");
            return null;
        }else{
            return sale;
        }
    }

    public static Prenotazioni scaricaPrenotazione(Connection conn, int id) throws SQLException{
        Prenotazioni prenotazione = null;
        select = getQuery(
                conn,
                "select",
                "public.prenotazioni",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("id = " + id))
        );
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
        select = getQuery(
                conn,
                "select",
                "public.spettacoli",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("id = " + id))
        );
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

    public static List<Spettacoli> scaricaSpettacoli(Connection conn, List<String> restrictions) throws SQLException{
        Spettacoli spettacolo = null;
        List<Spettacoli> spettacoli = new ArrayList<>();
        select = getQuery(
                conn,
                "select",
                "public.spettacoli",
                new ArrayList<>(),
                restrictions
        );
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
            spettacoli.add(spettacolo);
        }
        select.close();
        if (spettacoli.size() == 0) {
            System.out.println("Non esistono spettacoli");
            return null;
        }else{
            return spettacoli;
        }
    }

    public static Posti scaricaPosti(Connection conn, int id) throws SQLException{
        Posti posto = null;
        select = getQuery(
                conn,
                "select",
                "public.posti",
                new ArrayList<>(),
                new ArrayList<>(Collections.singletonList("id = " + id))
        );
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

    protected static PreparedStatement getQuery(Connection conn, String queryType, String table, List<String> fields, List<String> restrictions)
    throws SQLException{
        //"id_sede = :id"
        //if fields è vuoto ==> *
        String field = "";
        String restriction = "where ";
        switch (queryType.toLowerCase()){
            case "select":
                if (fields.size() == 0)
                    field = "*";
                else{
                    for (String s : fields)
                        if (field.equalsIgnoreCase(""))
                            field += s;
                        else
                            field += "," + s;
                }
                //if restrictions è vuoto ==> n non metti il where
                if (restrictions.size() == 0)
                    restriction = "";
                else {
                    for (String s : restrictions)
                        if (restriction.equalsIgnoreCase("where "))
                            restriction += s;
                        else
                            restriction += "," + s;
                }
                return conn.prepareStatement("select " + field +" from " + table + " " + restriction);
            case "insert":
                if (fields.size() == 0)
                    field = "*";
                else{
                    for (String s : fields)
                        if (field.equalsIgnoreCase(""))
                            field += s;
                        else
                            field += "," + s;
                }
                //if restrictions è vuoto ==> n non metti il where
                if (restrictions.size() == 0)
                    restriction = "";
                else {
                    for (String s : restrictions)
                        if (restriction.equalsIgnoreCase("where "))
                            restriction = s;
                        else
                            restriction += "," + s;
                }
                return conn.prepareStatement("insert into " + table + "(" + field + ") values(" + restriction +")");
        }
        return null;
    }


    //----------------------------------------------------------------

    public static void suggerimenti(Connection connection, String emailUtente, List<String> generiFilmPrenotati) throws SQLException {


        for (int i = 0; i < generiFilmPrenotati.size(); i++) {
            String query = "SELECT * FROM public.spettacoli WHERE LOWER (spettacoli.genere) = ('" + generiFilmPrenotati.get(i) + "')";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String genere = resultSet.getString("genere");
                String prezzo = resultSet.getString("prezzo");
                String durata = resultSet.getString("durata");
                String giorno = resultSet.getString("giorno");
                String sala = resultSet.getString("sala_nome");
                String orario = resultSet.getString("orario");
                String film = resultSet.getString("nome");

                System.out.println("Id: " + id + " - Genere : " + genere + " - Prezzo: " + prezzo + " - Durata: " + durata +
                        " - Giorno: " + giorno + " - Sala: " + sala + " - Orario : " + orario + " - Film: " + film);
            }

        }
    }

    //----------------------------------------------------------------
}
