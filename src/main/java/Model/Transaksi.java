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

    private static ArrayList<Transaksi> daftarTransaksi
            = new ArrayList<>();

    public Transaksi(
            String idTransaksi,
            Penonton penonton,
            Tiket tiket) {

        this.idTransaksi = idTransaksi;
        this.penonton = penonton;
        this.tiket = tiket;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Penonton getPenonton() {
        return penonton;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public static ArrayList<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    public static void tambah(Transaksi transaksi) {
        daftarTransaksi.add(transaksi);
    }

    @Override
    public String toString() {

        return "ID Transaksi : " + idTransaksi
                + "\nPenonton     : " + penonton.getNama()
                + "\nFilm         : " + tiket.getFilm().getJudul()
                + "\nStudio       : " + tiket.getStudio().getNama()
                + "\nJumlah Tiket : " + tiket.getJumlah()
                + "\nTotal Harga  : Rp" + tiket.getTotalHarga();
    }
}