package PSI;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Patient> patients;

    public Hospital() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientById(int userId) {
        for (Patient patient : patients) {
            if (patient.getUserId() == userId) {
                return patient;
            }
        }
        return null; // Jika pasien tidak ditemukan
    }

    public ArrayList<Patient> getAllPatients() {
        return patients; // Mengembalikan semua pasien
    }
}
