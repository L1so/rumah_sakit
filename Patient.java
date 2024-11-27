package PSI;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Patient extends User {
    private String name;
    private Date dateOfBirth;
    private String medicalRecordNumber;
    private ArrayList<String> medicalHistory = new ArrayList<>();
    private ArrayList<String> complaints = new ArrayList<>(); // Menyimpan keluhan pasien

    public Patient(int userId, String password, String name, Date dateOfBirth, String medicalRecordNumber) {
        super(userId, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.medicalRecordNumber = medicalRecordNumber;
        // Contoh riwayat medis
        medicalHistory.add("2024-01-15: Check-up, all clear.");
        medicalHistory.add("2024-06-20: Flu diagnosis, prescribed medication.");
    }

    @Override
    public void showRole() {
        System.out.println("Logged in as Patient: " + name);
    }

    public void viewMedicalHistory() {
        System.out.println("Riwayat Medis untuk " + name + ":");
        displayMedicalHistory();
    }

    public void displayMedicalHistory() {
        if (medicalHistory.isEmpty()) {
            System.out.println("Tidak ada riwayat medis yang tersedia.");
        } else {
            for (String record : medicalHistory) {
                System.out.println(record);
            }
        }
    }

    public void recordComplaint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan keluhan Anda: ");
        String complaint = scanner.nextLine();
        String record = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": Keluhan - " + complaint;
        complaints.add(record); // Menyimpan keluhan ke daftar keluhan
        System.out.println("Keluhan berhasil dicatat.");
    }

    public void cekTagihan(ArrayList<Invoice> invoices) {
        System.out.println("Daftar Tagihan untuk " + name + ":");
        int count = 1;
        for (Invoice invoice : invoices) {
            System.out.println(count + ". " + invoice.getDate() + " - " + invoice.getAmount());
            count++;
        }

        if (count == 1) {
            System.out.println("Tidak ada tagihan yang tersedia.");
            return;
        }

        System.out.print("Pilih nomor urut tagihan yang ingin dibayarkan: ");
        int invoiceChoice = new Scanner(System.in).nextInt();

        if (invoiceChoice < 1 || invoiceChoice >= count) {
            System.out.println("Pilihan tagihan tidak valid.");
            return;
        }

        Invoice selectedInvoice = invoices.get(invoiceChoice - 1); // Mengambil invoice berdasarkan pilihan

        System.out.print("Bayar tagihan ini (ya/tidak): ");
        String payChoice = new Scanner(System.in).nextLine();

        if (payChoice.equalsIgnoreCase("ya")) {
            selectedInvoice.payInvoice(); // Membayar tagihan
            System.out.println("Tagihan berhasil dibayar!");
        } else {
            System.out.println("Pembayaran dibatalkan.");
        }
    }


    public void addToMedicalHistory(String diagnosis) {
        String record = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ": Diagnosis - " + diagnosis;
        medicalHistory.add(record); // Menyimpan diagnosis ke riwayat medis
    }

    public ArrayList<String> getComplaints() {
        return complaints; // Menyediakan akses keluhan
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return super.getUserId(); // Mengakses ID pengguna dari kelas User
    }

    public ArrayList<String> getMedicalHistory() {
        return medicalHistory;
    }
}
