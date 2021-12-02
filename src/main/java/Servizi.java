import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class Servizi {
    private String Query;

    private void CreaQuery(){

    }

    public boolean caricaDb(){
        return true;
    }

    public boolean scaricaDb(){
        return true;
    }

    public List<Spettacoli> suggerimenti(String emailUtente){
        return new ArrayList<>();
    }
}
