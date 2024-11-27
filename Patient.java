package PSI;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Patient extends User {
    private String name;
    private Date dateOfBirth;
    private ArrayList<String> medicalHistory = new ArrayList<>();
    private ArrayList<String> complaints = new ArrayList<>(); // Menyimpan keluhan pasien

    public Patient(int userId, String password, String name, Date dateOfBirth) {
        super(userId, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        // Contoh riwayat medis
        medicalHistory.add("2024-01-15: Diagnosis - Flu Burung");
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
        ArrayList<Invoice> outstandingInvoices = new ArrayList<>(); // Daftar tagihan yang belum dibayar

        for (Invoice invoice : invoices) {
            // Menampilkan hanya tagihan yang belum dibayar dan terkait dengan pasien ini
            if (!invoice.isPaid() && invoice.getPatientId() == this.getUserId()) {
                outstandingInvoices.add(invoice);
                System.out.println(outstandingInvoices.size() + ". " + new SimpleDateFormat("yyyy-MM-dd").format(invoice.getDate()) + " - Rp" + invoice.getAmount());
            }
        }

        if (outstandingInvoices.isEmpty()) {
            System.out.println("Tidak ada tagihan outstanding !");
            return;
        }

        System.out.print("Pilih nomor urut tagihan yang ingin dibayarkan: ");
        int invoiceChoice = new Scanner(System.in).nextInt();

        if (invoiceChoice < 1 || invoiceChoice > outstandingInvoices.size()) {
            System.out.println("Pilihan tagihan tidak valid.");
            return;
        }

        Invoice selectedInvoice = outstandingInvoices.get(invoiceChoice - 1); // Mengambil invoice berdasarkan pilihan

        System.out.print("Bayar tagihan ini (ya/tidak): ");
        String payChoice = new Scanner(System.in).nextLine();

        if (payChoice.equalsIgnoreCase("ya")) {
            selectedInvoice.payInvoice(); // Menandai tagihan sebagai dibayar
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
