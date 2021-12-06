public class Sala {
    private int nPosti;
    private  int idSede;
    private String nome;

    public Sala(int nPosti, int idSede, String nome) {
        this.nPosti = nPosti;
        this.idSede = idSede;
        this.nome = nome;
    }

    public int getnPosti() {
        return nPosti;
    }

    public int getIdSede() {
        return idSede;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nPosti=" + nPosti +
                ", idSede=" + idSede +
                ", nome='" + nome + '\'' +
                '}';
    }
}
