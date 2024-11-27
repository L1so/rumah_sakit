package PSI;

public class Obat {
    private String nama;
    private String efekSamping;

    public Obat(String nama, String efekSamping) {
        this.nama = nama;
        this.efekSamping = efekSamping;
    }

    public String getNama() {
        return nama;
    }

    public String getEfekSamping() {
        return efekSamping;
    }

    @Override
    public String toString() {
        return "Obat: " + nama + ", Efek Samping: " + efekSamping; // Hapus dosis dari sini
    }
}
