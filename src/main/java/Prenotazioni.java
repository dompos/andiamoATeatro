public class Prenotazioni {
    private int id;
    private int idSpettacolo;
    private int idPosto;
    private String emailUtente;
    private float prezzoFinale;

    public Prenotazioni(int id, int idSpettacolo, int idPosto, String emailUtente) {
        this.id = id;
        this.idSpettacolo = idSpettacolo;
        this.idPosto = idPosto;
        this.emailUtente = emailUtente;
    }

    @Override
    public String toString() {
        return "Prenotazioni{" +
                "id=" + id +
                ", idSpettacolo=" + idSpettacolo +
                ", idPosto=" + idPosto +
                ", emailUtente='" + emailUtente + '\'' +
                ", prezzoFinale=" + prezzoFinale +
                '}';
    }
}
