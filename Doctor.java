package PSI;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat; // Tambahkan import ini
import java.util.Date;

public class Doctor extends User {
    private String name;
    private String specialization;
    private String contactInfo;
    private ArrayList<Resep> resepList; // Koleksi untuk menyimpan resep yang dibuat
    private ArrayList<Patient> patients; // Koleksi untuk menyimpan pasien

    public Doctor(int userId, String password, String name, String specialization, String contactInfo, ArrayList<Patient> patients) {
        super(userId, password);
        this.name = name;
        this.specialization = specialization;
        this.contactInfo = contactInfo;
        this.resepList = new ArrayList<>(); // Inisialisasi koleksi resep
        this.patients = patients; // Menyimpan daftar pasien
    }

    @Override
    public void showRole() {
        System.out.println("Logged in as Doctor: " + name);
    }

    public void diagnosePatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Pasien untuk Diagnosis: ");
        int patientId = scanner.nextInt();

        // Mencari pasien berdasarkan ID dari daftar pasien
        Patient patient = null;
        for (Patient p : patients) {
            if (p.getUserId() == patientId) {
                patient = p;
                break;
            }
        }

        if (patient != null) {
            System.out.print("Masukkan Detail Diagnosis untuk " + patient.getName() + ": ");
            String diagnosis = scanner.next();
            System.out.println("Diagnosis untuk " + patient.getName() + " telah dicatat: " + diagnosis);

            // Menambahkan diagnosis ke riwayat medis pasien
            patient.addToMedicalHistory(diagnosis);
        } else {
            System.out.println("Pasien tidak ditemukan atau bukan pasien yang valid.");
        }
    }

    public void prescribeMedication(ArrayList<Patient> patients, ArrayList<Obat> obatList, ArrayList<Resep> resepList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Resep untuk pasien dengan ID siapa ?: ");
        int patientId = scanner.nextInt(); // Mengambil ID pasien dari input

        // Mencari pasien berdasarkan ID
        Patient selectedPatient = null;
        for (Patient patient : patients) {
            if (patient.getUserId() == patientId) {
                selectedPatient = patient;
                break;
            }
        }

        if (selectedPatient == null) {
            System.out.println("Pasien tidak ditemukan.");
            return;
        }

        // Menampilkan daftar obat
        System.out.println("Daftar obat:");
        for (int i = 0; i < obatList.size(); i++) {
            System.out.println((i + 1) + ". " + obatList.get(i).getNama());
        }

        System.out.print("Pilih salah satu: ");
        int obatChoice = scanner.nextInt() - 1; // Mengurangi 1 untuk indeks array
        if (obatChoice < 0 || obatChoice >= obatList.size()) {
            System.out.println("Pilihan obat tidak valid.");
            return;
        }

        System.out.print("Dosis: ");
        String dosis = scanner.next(); // Ambil dosis dari input

        // Membuat resep baru
        Resep newResep = new Resep(selectedPatient, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        newResep.tambahObat(obatList.get(obatChoice));
        newResep.setDosisObat(dosis); // Simpan dosis ke dalam resep
        resepList.add(newResep); // Menambahkan resep ke daftar resep

        System.out.println("Resep berhasil dibuat!");
    }



    public ArrayList<Resep> getResepList() {
        return resepList; // Menyediakan akses ke daftar resep
    }
}
