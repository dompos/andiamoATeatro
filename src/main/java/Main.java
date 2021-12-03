import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(Servizi.URL, Servizi.USERNAME, Servizi.PASSWORD)){
            Servizi.scaricaUtente(conn,"domposi@gmail.com");
            Servizi.scaricaSede(conn,1);
            Servizi.scaricaSala(conn,"Sala Debora");
            Servizi.scaricaPrenotazione(conn,1);
            Servizi.scaricaPosti(conn,1);
            Utente utente = new Utente("Davide", "Golino", "Roma", "dasuaphs@blizzard.com", "3694221564");
            System.out.println(Servizi.caricaUtente(conn,utente));
            Servizi.scaricaUtente(conn,"dasuaphs@blizzard.com");
            Servizi.scaricaUtente(conn,"dompos@hotmail.it");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
