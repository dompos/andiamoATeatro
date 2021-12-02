public class Posti {
    private String fila;
    private String nomeSala;
    private int numero;
    private int id;

    public Posti(String fila, String nomeSala, int numero, int id) {
        this.fila = fila;
        this.nomeSala = nomeSala;
        this.numero = numero;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Posti{" +
                "fila='" + fila + '\'' +
                ", nomeSala='" + nomeSala + '\'' +
                ", numero=" + numero +
                ", id=" + id +
                '}';
    }
}
