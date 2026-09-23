/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADVAN
 */
public class Tiket {

    private String pembeli;
    private Film film;
    private Studio studio;
    private int jumlah;

    private static ArrayList<Tiket> daftarTiket = new ArrayList<>();

    public Tiket(
            String pembeli,
            Film film,
            Studio studio,
            int jumlah) {

        this.pembeli = pembeli;
        this.film = film;
        this.studio = studio;
        this.jumlah = jumlah;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {

        double harga = film.getHarga();

        if (studio.getTipe().equalsIgnoreCase("VIP")) {
            harga += 10000;
        }

        return harga;
    }

    public double getTotalHarga() {

        return jumlah * getHarga();
    }

    public static ArrayList<Tiket> getDaftarTiket() {
        return daftarTiket;
    }

    public static void tambah(Tiket tiket) {
        daftarTiket.add(tiket);
    }

    public static void update(int index, Tiket tiket) {
        daftarTiket.set(index, tiket);
    }

    public static void hapus(int index) {
        daftarTiket.remove(index);
    }

    @Override
    public String toString() {

        return "Pembeli      : " + pembeli
                + "\nFilm         : " + film.getJudul()
                + "\nStudio       : " + studio.getNama()
                + "\nTipe Studio  : " + studio.getTipe()
                + "\nHarga Tiket  : Rp" + getHarga()
                + "\nJumlah Tiket : " + jumlah
                + "\nTotal Harga  : Rp" + getTotalHarga();
    }
}