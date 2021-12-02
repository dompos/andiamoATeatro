import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Servizi {
    private static PreparedStatement insert;
    private static PreparedStatement select;
    private static final String URL = "jdbc:postgresql://localhost:5432/andiamoateatro";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD ="AlberobellO"; //inserite le vostre pssw


    public boolean caricaDb(){
        return true;
    }

    public static Utente scaricaUtente(String emailUtente) {
        ResultSet risultato = null;
        Utente utente=null;

        try{
            Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            select = con.prepareStatement("select * from public.utente where email = '" + emailUtente + "'" );
            risultato = select.executeQuery();
            risultato.next();
            utente=new Utente(risultato.getString("nome"),
                    risultato.getString("cognome"),
                    risultato.getString("residenza"),
                    risultato.getString("email"),
                    risultato.getString("telefono"));
            System.out.println(utente.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    public static Sede scaricaSede(int id){
        ResultSet risultato = null;
        Sede sede = null;

        try{
            Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            select = con.prepareStatement("select * from public.sede where id = " + id );
            risultato = select.executeQuery();
            risultato.next();
            sede = new Sede(risultato.getString("nome"),
                    risultato.getString("indirizzo"),
                    risultato.getString("comune"),
                    risultato.getInt("id"),
                    risultato.getBoolean("coperto"));
            System.out.println(sede.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sede;
    }

    public List<Spettacoli> suggerimenti(String emailUtente){
        return new ArrayList<>();
    }
}
