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

    public boolean isDisponibile(String spettacolo){
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
