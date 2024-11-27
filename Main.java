package PSI;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();

        // Inisialisasi pengguna
        Admin admin = new Admin(1, "123", "Raina", "raina@mail.com");
        ArrayList<Patient> patients = new ArrayList<>(); // Koleksi untuk menyimpan pasien
        ArrayList<Invoice> invoices = new ArrayList<>(); // Koleksi untuk menyimpan invoice
        Doctor doctor = new Doctor(2, "123", "Dr. Arli", "Kardiologi", "082-314-214", patients);
        Apoteker apoteker = new Apoteker(3, "123", "Apoteker Ahmad", "021-4124-1212");

        // Menambahkan pengguna ke sistem login
        loginSystem.addUser(admin);
        loginSystem.addUser(doctor);
        loginSystem.addUser(apoteker);

        // Menambahkan pasien existing untuk pengujian
        Patient existingPatient = new Patient(1, "123", "Ipul", java.sql.Date.valueOf("1995-10-11"), "1");
        patients.add(existingPatient); // Menambahkan pasien ke daftar pasien
        loginSystem.addUser(existingPatient); // Menambahkan pasien ke sistem login

        // Daftar obat yang sudah terdefinisi (50 nama obat umum)
        ArrayList<Obat> obatList = new ArrayList<>();
        obatList.add(new Obat("Aspirin", "Mual"));
        obatList.add(new Obat("Ibuprofen", "Sakit perut"));
        obatList.add(new Obat("Paracetamol", "Reaksi alergi"));
        obatList.add(new Obat("Amoxicillin", "Ruam kulit"));
        obatList.add(new Obat("Ibrutinib", "Mual"));
        obatList.add(new Obat("Acalabrutinib", "Mual"));
        obatList.add(new Obat("Zanubrutinib", "Mual"));
        obatList.add(new Obat("Bruton", "Mual"));
        obatList.add(new Obat("Idelalisib", "Mual"));
        obatList.add(new Obat("Duvelisib", "Mual"));
        obatList.add(new Obat("Copanlisib", "Mual"));
        obatList.add(new Obat("Umbralisib", "Mual"));

        // Daftar resep
        ArrayList<Resep> resepList = new ArrayList<>();

        System.out.println("Selamat datang di Sistem Informasi Rumah Sakit");

        boolean continueMainLoop = true;

        while (continueMainLoop) {
            System.out.println("\nSilakan pilih tindakan:");
            System.out.println("1. Admin");
            System.out.println("2. Dokter");
            System.out.println("3. Pasien");
            System.out.println("4. Apoteker");
            System.out.println("5. Keluar");

            System.out.print("Masukkan pilihan Anda (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                System.out.println("Keluar dari sistem. Selamat tinggal!");
                break;
            }

            switch (choice) {
                case 1:
                    handleAdminActions(admin, scanner, loginSystem, patients, invoices); // Pastikan untuk menambahkan invoices
                    break;
                case 2:
                    handleDoctorActions(doctor, scanner, patients, obatList, resepList);
                    break;
                case 3:
                    handlePatientLogin(scanner, patients, invoices); // Mengubah untuk login pasien dengan menambahkan invoices
                    break;
                case 4:
                    handleApotekerActions(apoteker, scanner, resepList, obatList); // Kosongkan resep list
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }


    private static void handlePatientLogin(Scanner scanner, ArrayList<Patient> patients, ArrayList<Invoice> invoices) {
        System.out.print("Masukkan ID Pasien: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        // Mencari pasien berdasarkan ID
        Patient patient = null;
        for (Patient p : patients) {
            if (p.getUserId() == patientId && p.getPassword().equals(password)) {
                patient = p;
                break;
            }
        }

        if (patient != null) {
            handlePatientActions(patient, scanner, invoices); // Akses dashboard pasien dengan menambahkan invoices
        } else {
            System.out.println("Login gagal. ID atau password salah.");
        }
    }


    private static void handleAdminActions(Admin admin, Scanner scanner, LoginSystem loginSystem, ArrayList<Patient> patients, ArrayList<Invoice> invoices) {
        System.out.println("Selamat datang, Admin!");
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("\nPilih tindakan:");
            System.out.println("1. Input Data Pasien");
            System.out.println("2. Mengelola Data Pasien");
            System.out.println("3. Mengelola Invoice");
            System.out.println("4. Tampilkan Kamar");
            System.out.println("5. Cek Status Pembayaran"); // Opsi ini memerlukan argumen
            System.out.println("6. Tampilkan Ruang Dokter");
            System.out.println("7. Tampilkan Jadwal Praktek Dokter");
            System.out.println("8. Masukkan Pasien ke Kamar");
            System.out.println("9. Buat Invoice");
            System.out.println("10. Logout");

            System.out.print("Masukkan pilihan Anda: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.inputDataPasien(loginSystem, patients); // Pass patients
                    break;
                case 2:
                    admin.mengelolaDataPasien(patients); // Pass patients to the method
                    break;
                case 3:
                    admin.mengelolaInvoice();
                    break;
                case 4:
                    admin.tampilkanKamar();
                    break;
                case 5:
                    admin.cekStatusPembayaran(invoices); // Cek status pembayaran dengan argumen invoices
                    break;
                case 6:
                    admin.tampilkanRuangDokter(); // Menampilkan ruang dokter
                    break;
                case 7:
                    admin.tampilkanJadwalPraktek(); // Menampilkan jadwal praktek dokter
                    break;
                case 8:
                    admin.masukkanPasienKeKamar(patients); // Memanggil metode untuk mendaftarkan pasien ke kamar
                    break;
                case 9:
                    admin.buatInvoice(patients, invoices); // Memanggil metode untuk membuat invoice
                    break;
                case 10:
                    continueLoop = false;
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }







    private static void handleDoctorActions(Doctor doctor, Scanner scanner, ArrayList<Patient> patients, ArrayList<Obat> obatList, ArrayList<Resep> resepList) {
        System.out.println("Selamat datang, Dokter!");
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("\nPilih tindakan:");
            System.out.println("1. Diagnosis Pasien");
            System.out.println("2. Meresepkan Obat");
            System.out.println("3. Logout");

            System.out.print("Masukkan pilihan Anda: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    doctor.diagnosePatient();
                    break;

                case 2:
                    doctor.prescribeMedication(patients, obatList, resepList); // Pass daftar pasien, obat, dan resep
                    break;
                case 3:
                    continueLoop = false;
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void handlePatientActions(Patient patient, Scanner scanner, ArrayList<Invoice> invoices) {
        System.out.println("Selamat datang, Pasien: " + patient.getName() + "!");
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("\nPilih tindakan:");
            System.out.println("1. Melihat Riwayat Medis");
            System.out.println("2. Mencatat Keluhan");
            System.out.println("3. Cek Tagihan"); // Opsi baru
            System.out.println("4. Logout");

            System.out.print("Masukkan pilihan Anda: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    patient.viewMedicalHistory();
                    break;
                case 2:
                    patient.recordComplaint();
                    break;
                case 3:
                    patient.cekTagihan(invoices); // Memanggil metode untuk cek tagihan
                    break;
                case 4:
                    continueLoop = false;
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }


    private static void handleApotekerActions(Apoteker apoteker, Scanner scanner, ArrayList<Resep> resepList, ArrayList<Obat> obatList) {
        System.out.println("Selamat datang, Apoteker!");
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("\nPilih tindakan:");
            System.out.println("1. Mengelola Resep");
            System.out.println("2. Melihat Daftar Obat");
            System.out.println("3. Logout");

            System.out.print("Masukkan pilihan Anda: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    apoteker.mengelolaResep(resepList); // Mengelola resep
                    break;
                case 2:
                    apoteker.melihatObat(obatList); // Menampilkan daftar obat
                    break;
                case 3:
                    continueLoop = false;
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
