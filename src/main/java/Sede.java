import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Sede {
    private String nome;
    private String indirizzo;
    private String comune;
    private int id;
    private boolean coperto;

    public Sede(String nome, String indirizzo, String comune, int id, boolean coperto) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.comune = comune;
        this.id = id;
        this.coperto = coperto;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getComune() {
        return comune;
    }

    public int getId() {
        return id;
    }

    public boolean isCoperto() {
        return coperto;
    }

    public boolean isDisponibile(Connection conn, String spettacolo) throws SQLException {
        Set<Spettacoli> spettacoliSet = new HashSet();
        List<Sala> sale = Servizi.scaricaSale(
                conn,
                new ArrayList<>(Collections.singleton("sede_id = "+ id)));
        for (Sala s : sale){
            List<Spettacoli> spettacoli = Servizi.scaricaSpettacoli(
                    conn,
                    new ArrayList<>(Collections.singleton("sala_nome = '" + s.getNome() + "'")));
            spettacoliSet.addAll(spettacoli);
        }
        for (Spettacoli setSpettacolo : spettacoliSet){
            if (setSpettacolo.getNome().equalsIgnoreCase(spettacolo) &&
                Servizi.scaricaSala(conn, setSpettacolo.getNomeSala()).getnPosti() > 1)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", comune='" + comune + '\'' +
                ", id=" + id +
                ", coperto=" + coperto +
                '}';
    }
}
