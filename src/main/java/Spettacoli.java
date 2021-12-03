import java.util.Date;

public class Spettacoli {

    private String nome;
    private String nomeSala;
    private String genere;
    private Date orario;
    private Date giorno;
    private double prezzo;
    private int durata, id;

    public Spettacoli(String nome, String nomeSala, String genere, Date orario, Date giorno, double prezzo, int durata, int id) {
        this.nome = nome;
        this.nomeSala = nomeSala;
        this.genere = genere;
        this.orario = orario;
        this.giorno = giorno;
        this.prezzo = prezzo;
        this.durata = durata;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public String getGenere() {
        return genere;
    }

    public Date getOrario() {
        return orario;
    }

    public Date getGiorno() {
        return giorno;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getDurata() {
        return durata;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Spettacoli{" +
                "nome='" + nome + '\'' +
                ", nomeSala='" + nomeSala + '\'' +
                ", genere='" + genere + '\'' +
                ", orario=" + orario +
                ", giorno=" + giorno +
                ", prezzo=" + prezzo +
                ", durata=" + durata +
                ", id=" + id +
                '}';
    }
}
