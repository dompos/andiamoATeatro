import javax.management.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Servizi {
    private static PreparedStatement insert;
    private static PreparedStatement select;
    private static final String url= "jdbc:postgresql://localhost:5432/andiamoateatro";
    private static final String password="alekira2014"; //inserite le vostre pssw
    private static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url,"postgres",password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public boolean caricaDb(){
        return true;
    }

    public static Utente scaricaUtente() {
        ResultSet risultato = null;
        Utente utente=null;

        try {
            select = con.prepareStatement("select * from public.utente where email=" + "domposi@gmail.com");
            risultato = select.executeQuery();
            risultato.next();
            utente=new Utente(risultato.getString("nome"),
                    risultato.getString("cognome"),
                    risultato.getString("residenza"),
                    risultato.getString("email"),
                    risultato.getString("telefono"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            System.out.println(utente.toString());
        }

        return utente;
    }

    public List<Spettacoli> suggerimenti(String emailUtente){
        return new ArrayList<>();
    }
}
