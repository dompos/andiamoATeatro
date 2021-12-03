import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public boolean isDisponibile(Connection conn, String spettacolo) throws SQLException {
        return true;
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
