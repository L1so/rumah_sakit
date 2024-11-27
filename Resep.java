package PSI;

import java.util.ArrayList;

public class Resep {
    private Patient pasien;
    private ArrayList<Obat> obatList;
    private String tanggal;
    private String dosis; // Menyimpan dosis

    public Resep(Patient pasien, String tanggal) {
        this.pasien = pasien;
        this.tanggal = tanggal;
        this.obatList = new ArrayList<>();
    }

    public void tambahObat(Obat obat) {
        obatList.add(obat);
    }

    public ArrayList<Obat> getObatList() {
        return obatList; // Menyediakan akses ke daftar obat
    }

    public String getDosis() {
        return dosis; // Menyediakan akses ke dosis
    }

    public void setDosis(String dosis) {
        this.dosis = dosis; // Mengatur dosis
    }

    public Patient getPasien() {
        return pasien;
    }

    // Tambahkan metode untuk mengatur dosis saat menambahkan obat
    public void setDosisObat(String dosis) {
        this.dosis = dosis; // Menyimpan dosis
    }
}
