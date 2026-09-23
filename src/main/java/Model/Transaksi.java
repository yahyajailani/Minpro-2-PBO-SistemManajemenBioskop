/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADVAN
 */
public class Transaksi {

    private String idTransaksi;
    private Penonton penonton;
    private Tiket tiket;
    private LocalDateTime tanggal;

    public Transaksi(
            String idTransaksi,
            Penonton penonton,
            Tiket tiket) {

        this.idTransaksi = idTransaksi;
        this.penonton = penonton;
        this.tiket = tiket;
        this.tanggal = LocalDateTime.now();
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Penonton getPenonton() {
        return penonton;
    }

    public void setPenonton(Penonton penonton) {
        this.penonton = penonton;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDateTime tanggal) {
        this.tanggal = tanggal;
    }

    public static void lihat(
            ArrayList<Transaksi> daftarTransaksi) {

        System.out.println("\n==================================");
        System.out.println("        RIWAYAT TRANSAKSI");
        System.out.println("==================================");

        if (daftarTransaksi.isEmpty()) {

            System.out.println(
                    "Belum ada transaksi."
            );

            return;
        }

        DateTimeFormatter format =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm"
                );

        for (int i = 0;
                i < daftarTransaksi.size();
                i++) {

            Transaksi transaksi =
                    daftarTransaksi.get(i);

            System.out.println(
                    "\nTransaksi ke-" + (i + 1)
            );

            System.out.println(
                    "ID Transaksi : "
                    + transaksi.getIdTransaksi()
            );

            System.out.println(
                    "Penonton     : "
                    + transaksi.getPenonton().getNama()
            );

            System.out.println(
                    "Film         : "
                    + transaksi.getTiket()
                            .getFilm()
                            .getJudul()
            );

            System.out.println(
                    "Studio       : "
                    + transaksi.getTiket()
                            .getStudio()
                            .getNama()
            );

            System.out.println(
                    "Jumlah       : "
                    + transaksi.getTiket()
                            .getJumlah()
            );

            System.out.println(
                    "Total Harga  : Rp"
                    + transaksi.getTiket()
                            .getTotalHarga()
            );

            System.out.println(
                    "Tanggal      : "
                    + transaksi.getTanggal()
                            .format(format)
            );
        }
    }

    public static void lihatTransaksiPenonton(
            ArrayList<Transaksi> daftarTransaksi,
            Penonton penonton) {

        System.out.println("\n==================================");
        System.out.println("      RIWAYAT TRANSAKSI SAYA");
        System.out.println("==================================");

        DateTimeFormatter format =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm"
                );

        boolean ada = false;

        for (Transaksi transaksi : daftarTransaksi) {

            if (transaksi.getPenonton() == penonton) {

                ada = true;

                System.out.println(
                        "\nID Transaksi : "
                        + transaksi.getIdTransaksi()
                );

                System.out.println(
                        "Film         : "
                        + transaksi.getTiket()
                                .getFilm()
                                .getJudul()
                );

                System.out.println(
                        "Studio       : "
                        + transaksi.getTiket()
                                .getStudio()
                                .getNama()
                );

                System.out.println(
                        "Jumlah       : "
                        + transaksi.getTiket()
                                .getJumlah()
                );

                System.out.println(
                        "Total Harga  : Rp"
                        + transaksi.getTiket()
                                .getTotalHarga()
                );

                System.out.println(
                        "Tanggal      : "
                        + transaksi.getTanggal()
                                .format(format)
                );
            }
        }

        if (!ada) {

            System.out.println(
                    "Belum memiliki riwayat transaksi."
            );
        }
    }

    public static void hapus(
            Scanner input,
            ArrayList<Transaksi> daftarTransaksi) {

        if (daftarTransaksi.isEmpty()) {

            System.out.println(
                    "Belum ada transaksi."
            );

            return;
        }

        lihat(daftarTransaksi);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih transaksi : ",
                1,
                daftarTransaksi.size()
        );

        daftarTransaksi.remove(
                pilih - 1
        );

        System.out.println(
                "Transaksi berhasil dihapus."
        );
    }

    public static void menuAdmin(
            Scanner input,
            ArrayList<Transaksi> daftarTransaksi) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("       KELOLA TRANSAKSI");
            System.out.println("==================================");
            System.out.println("1. Lihat Riwayat Transaksi");
            System.out.println("2. Hapus Transaksi");
            System.out.println("0. Kembali");
            System.out.println("==================================");

            pilihan = Pengguna.inputAngka(
                    input,
                    "Pilih menu : ",
                    0,
                    2
            );

            switch (pilihan) {

                case 1:
                    lihat(daftarTransaksi);
                    break;

                case 2:
                    hapus(
                            input,
                            daftarTransaksi
                    );
                    break;
            }

        } while (pilihan != 0);
    }
}