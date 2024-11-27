package PSI;

import java.util.ArrayList;

public class Apoteker extends User {
    private String name;
    private String contactInfo;
    private ArrayList<Obat> obatList; // Koleksi untuk menyimpan data obat

    public Apoteker(int userId, String password, String name, String contactInfo) {
        super(userId, password);
        this.name = name;
        this.contactInfo = contactInfo;
        this.obatList = new ArrayList<>(); // Inisialisasi koleksi obat
    }

    @Override
    public void showRole() {
        System.out.println("Logged in as Apoteker: " + name);
    }

    public void mengelolaResep(ArrayList<Resep> resepList) {
        System.out.println("Daftar Resep:");
        if (resepList.isEmpty()) {
            System.out.println("Tidak ada resep yang tersedia.");
        } else {
            for (Resep resep : resepList) {
                System.out.println(resep.getPasien().getName() + " - " + resep.getObatList().get(0).getNama() + " - " + resep.getDosis());
            }
        }
    }

    // Modifikasi metode melihatObat untuk menerima daftar obat
    public void melihatObat(ArrayList<Obat> obatList) {
        System.out.println("Daftar obat yang tersedia:");
        if (obatList.isEmpty()) {
            System.out.println("Tidak ada obat yang terdaftar.");
        } else {
            for (Obat obat : obatList) {
                System.out.println(obat);
            }
        }
    }

    public void tambahObat(Obat obat) {
        obatList.add(obat);
        System.out.println("Obat berhasil ditambahkan.");
    }
}
