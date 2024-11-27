package PSI;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private int invoiceId;
    private double amount;
    private Date date;
    private boolean isPaid; // Menyimpan status pembayaran

    public Invoice(int invoiceId, double amount, Date date) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.date = date;
        this.isPaid = false; // Default status belum dibayar
    }

    public void printInvoice() {
        System.out.println("Invoice ID: " + invoiceId + ", Amount: " + amount + ", Date: " + date + ", Status: " + (isPaid ? "LUNAS" : "BELUM LUNAS"));
    }

    public void payInvoice() {
        this.isPaid = true; // Mengubah status menjadi dibayar
    }

    public boolean isPaid() {
        return isPaid; // Mengembalikan status pembayaran
    }


    public int getInvoiceId() {
        return invoiceId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
