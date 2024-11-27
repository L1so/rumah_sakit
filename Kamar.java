package PSI;

public class Kamar {
    private int idKamar;
    private String status;

    public Kamar(int idKamar, String status) {
        this.idKamar = idKamar;
        this.status = status;
    }

    public int getIdKamar() {
        return idKamar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status; // Mengatur status kamar
    }
}
