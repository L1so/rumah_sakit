package PSI;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User {
    private String name;
    private String contactInfo;
    private ArrayList<Patient> patients; // Koleksi untuk menyimpan data pasien
    private ArrayList<Kamar> kamarList; // Koleksi untuk menyimpan data kamar
    private int lastPatientId; // Variabel instance untuk menyimpan ID terakhir pasien

    public Admin(int userId, String password, String name, String contactInfo) {
        super(userId, password);
        this.name = name;
        this.contactInfo = contactInfo;
        this.patients = new ArrayList<>(); // Inisialisasi koleksi pasien
        this.kamarList = new ArrayList<>(); // Inisialisasi koleksi kamar
        this.lastPatientId = 0; // Inisialisasi ID terakhir pasien

        // Menambahkan 10 kamar dengan status "Tersedia"
        for (int i = 1; i <= 10; i++) {
            kamarList.add(new Kamar(i, "Tersedia")); // ID kamar dari 1 hingga 10
        }
    }

    public void setLastPatientId(int id) {
        this.lastPatientId = id; // Mengatur ID terakhir
    }

    @Override
    public void showRole() {
        System.out.println("Logged in as Admin: " + name);
    }

    public void tampilkanKamar() {
        System.out.println("Daftar Kamar:");
        if (kamarList.isEmpty()) {
            System.out.println("Tidak ada kamar yang terdaftar.");
        } else {
            for (Kamar kamar : kamarList) {
                System.out.println("ID Kamar: " + kamar.getIdKamar() + ", Status: " + kamar.getStatus());
            }
        }
    }

    public void buatInvoice(ArrayList<Patient> patients, ArrayList<Invoice> invoices) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID pasien: ");
        int patientId = scanner.nextInt();

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

        // Menampilkan riwayat medis pasien
        System.out.println("Riwayat medis dari pasien " + selectedPatient.getName() + ":");
        selectedPatient.displayMedicalHistory();

        System.out.print("Nominal tagihan (Rp): ");
        double amount = scanner.nextDouble();

        // Mencari ID invoice baru yang belum digunakan
        int newInvoiceId = 1; // Mulai dari 1
        while (getInvoiceById(newInvoiceId, invoices) != null) {
            newInvoiceId++; // Increment ID hingga menemukan ID yang belum digunakan
        }

        // Membuat objek invoice baru dan menambahkannya ke koleksi
        Invoice newInvoice = new Invoice(newInvoiceId, amount, new Date());
        invoices.add(newInvoice); // Menambahkan invoice ke daftar invoice

        System.out.println("Berhasil membuat invoice untuk " + selectedPatient.getName() + " (ID invoice = " + newInvoiceId + ") !");
    }

    // Metode untuk mencari invoice berdasarkan ID
    private Invoice getInvoiceById(int id, ArrayList<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId() == id) {
                return invoice; // Jika ditemukan, kembalikan invoice
            }
        }
        return null; // Jika tidak ditemukan
    }

    public void cekStatusPembayaran(ArrayList<Invoice> invoices) {
        System.out.println("Daftar Invoice:");
        for (Invoice invoice : invoices) {
            System.out.println(invoice.getInvoiceId() + ". " + invoice.getAmount() + " - Ditagihkan kepada " + invoice.getDate() + " - Status: " + (invoice.isPaid() ? "LUNAS" : "BELUM LUNAS"));
        }
    }



    // Metode untuk mendaftarkan pasien ke kamar
    public void masukkanPasienKeKamar(ArrayList<Patient> patients) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID Pasien yang dimaksud: ");
        int patientId = scanner.nextInt();

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

        System.out.print("Masukkan ID Kamar: ");
        int kamarId = scanner.nextInt();

        // Mencari kamar berdasarkan ID
        Kamar selectedKamar = null;
        for (Kamar kamar : kamarList) {
            if (kamar.getIdKamar() == kamarId) {
                selectedKamar = kamar;
                break;
            }
        }

        if (selectedKamar == null) {
            System.out.println("Kamar tidak ditemukan.");
            return;
        }

        // Memeriksa status kamar
        if (selectedKamar.getStatus().equals("Tersedia")) {
            // Mengubah status kamar menjadi "Tidak Tersedia"
            selectedKamar.setStatus("Tidak Tersedia");
            System.out.println("Kamar " + kamarId + " telah didaftarkan untuk pasien " + selectedPatient.getName() + "!");
        } else {
            System.out.println("Kamar " + kamarId + " tidak tersedia.");
        }
    }


    // Metode untuk menampilkan ruang dokter
    public void tampilkanRuangDokter() {
        System.out.println("Daftar Ruang Dokter:");
        System.out.println("1. Ruang Dokter Umum");
        System.out.println("2. Ruang Dokter Spesialis Jantung");
        System.out.println("3. Ruang Dokter Spesialis Anak");
        System.out.println("4. Ruang Dokter Spesialis Kulit");
        System.out.println("5. Ruang Dokter Gigi");
    }

    // Metode untuk menampilkan jadwal praktek dokter
    public void tampilkanJadwalPraktek() {
        System.out.println("Jadwal Praktek Dokter:");
        System.out.println("1. Dr. Arli (Kardiologi) - Senin, Rabu, Jumat: 08:00 - 12:00");
        System.out.println("2. Dr. Budi (Anak) - Selasa, Kamis: 09:00 - 13:00");
        System.out.println("3. Dr. Siti (Kulit) - Senin, Kamis: 10:00 - 14:00");
        System.out.println("4. Dr. Joko (Gigi) - Rabu, Jumat: 11:00 - 15:00");
        System.out.println("5. Dr. Rina (Umum) - Setiap Hari: 08:00 - 16:00");
    }

    public void inputDataPasien(LoginSystem loginSystem, ArrayList<Patient> patients) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama pasien: ");
        String patientName = scanner.nextLine();
        System.out.print("Masukkan tanggal lahir pasien (yyyy-MM-dd): ");
        String dobString = scanner.nextLine();
        Date dateOfBirth = java.sql.Date.valueOf(dobString); // Mengonversi string ke Date
        System.out.print("Masukkan nomor rekam medis: ");
        String medicalRecordNumber = scanner.nextLine();

        // Mencari ID pasien baru yang belum digunakan
        int newPatientId = 1; // Mulai dari 1
        while (getPatientById(newPatientId, patients) != null) {
            newPatientId++; // Increment ID hingga menemukan ID yang belum digunakan
        }

        // Membuat objek pasien baru dan menambahkannya ke koleksi
        Patient newPatient = new Patient(newPatientId, "123", patientName, dateOfBirth, medicalRecordNumber);
        patients.add(newPatient); // Menambahkan pasien ke daftar pasien

        // Menambahkan pasien ke sistem login
        loginSystem.addUser(newPatient); // Tambahkan pasien ke sistem login

        System.out.println("Pasien bernama " + patientName + " dengan ID " + newPatientId + " telah terbuat !");
    }

    // Metode untuk mencari pasien berdasarkan ID
    private Patient getPatientById(int id, ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getUserId() == id) {
                return patient; // Jika ditemukan, kembalikan pasien
            }
        }
        return null; // Jika tidak ditemukan
    }


    public void mengelolaDataPasien(ArrayList<Patient> patients) {
        System.out.println("Daftar Pasien:");
        if (patients.isEmpty()) {
            System.out.println("Tidak ada pasien yang terdaftar.");
        } else {
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getUserId() + ", Nama: " + patient.getName());
            }
        }
    }

    public void mengelolaInvoice() {
        System.out.println("Fitur mengelola invoice belum diimplementasikan.");
    }

    public void cekStatusPembayaran() {
        System.out.println("Fitur cek status pembayaran belum diimplementasikan.");
    }
}
