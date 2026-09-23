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
public class Film {

    private String idFilm;
    private String judul;
    private String genre;
    private int durasi;
    private double harga;

    public Film(
            String idFilm,
            String judul,
            String genre,
            int durasi,
            double harga) {

        this.idFilm = idFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.harga = harga;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public static void tambah(
            Scanner input,
            ArrayList<Film> daftarFilm) {

        System.out.println("\n=== TAMBAH FILM ===");

        String id = Pengguna.inputTeks(
                input,
                "ID Film   : "
        );

        for (Film f : daftarFilm) {

            if (f.getIdFilm().equalsIgnoreCase(id)) {

                System.out.println(
                        "ID film sudah digunakan."
                );

                return;
            }
        }

        String judul = Pengguna.inputTeks(
                input,
                "Judul     : "
        );

        String genre = Pengguna.inputTeks(
                input,
                "Genre     : "
        );

        int durasi = Pengguna.inputAngka(
                input,
                "Durasi (menit) : ",
                1,
                500
        );

        double harga = Pengguna.inputDesimal(
                input,
                "Harga     : Rp",
                1,
                1000000
        );

        daftarFilm.add(
                new Film(
                        id,
                        judul,
                        genre,
                        durasi,
                        harga
                )
        );

        System.out.println(
                "Film berhasil ditambahkan."
        );
    }

    public static void lihat(
            ArrayList<Film> daftarFilm) {

        System.out.println("\n==================================");
        System.out.println("            DAFTAR FILM");
        System.out.println("==================================");

        if (daftarFilm.isEmpty()) {

            System.out.println(
                    "Belum ada data film."
            );

            return;
        }

        for (int i = 0; i < daftarFilm.size(); i++) {

            Film f = daftarFilm.get(i);

            System.out.println(
                    "\nFilm ke-" + (i + 1)
            );

            System.out.println(
                    "ID       : " + f.getIdFilm()
            );

            System.out.println(
                    "Judul    : " + f.getJudul()
            );

            System.out.println(
                    "Genre    : " + f.getGenre()
            );

            System.out.println(
                    "Durasi   : " + f.getDurasi()
                    + " menit"
            );

            System.out.println(
                    "Harga    : Rp" + f.getHarga()
            );
        }
    }

    public static void ubah(
            Scanner input,
            ArrayList<Film> daftarFilm) {

        if (daftarFilm.isEmpty()) {

            System.out.println(
                    "Belum ada data film."
            );

            return;
        }

        lihat(daftarFilm);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih film : ",
                1,
                daftarFilm.size()
        );

        Film f = daftarFilm.get(pilih - 1);

        String judul = Pengguna.inputTeks(
                input,
                "Judul baru : "
        );

        String genre = Pengguna.inputTeks(
                input,
                "Genre baru : "
        );

        int durasi = Pengguna.inputAngka(
                input,
                "Durasi baru : ",
                1,
                500
        );

        double harga = Pengguna.inputDesimal(
                input,
                "Harga baru : Rp",
                1,
                1000000
        );

        f.setJudul(judul);
        f.setGenre(genre);
        f.setDurasi(durasi);
        f.setHarga(harga);

        System.out.println(
                "Film berhasil diubah."
        );
    }

    public static void hapus(
            Scanner input,
            ArrayList<Film> daftarFilm,
            ArrayList<Tiket> daftarTiket) {

        if (daftarFilm.isEmpty()) {

            System.out.println(
                    "Belum ada data film."
            );

            return;
        }

        lihat(daftarFilm);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih film : ",
                1,
                daftarFilm.size()
        );

        Film film = daftarFilm.get(pilih - 1);

        for (Tiket tiket : daftarTiket) {

            if (tiket.getFilm() == film) {

                System.out.println(
                        "Film tidak dapat dihapus "
                        + "karena sudah digunakan pada tiket."
                );

                return;
            }
        }

        daftarFilm.remove(film);

        System.out.println(
                "Film berhasil dihapus."
        );
    }

    public static void menu(
            Scanner input,
            ArrayList<Film> daftarFilm,
            ArrayList<Tiket> daftarTiket) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("           KELOLA FILM");
            System.out.println("==================================");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Film");
            System.out.println("3. Ubah Film");
            System.out.println("4. Hapus Film");
            System.out.println("0. Kembali");
            System.out.println("==================================");

            pilihan = Pengguna.inputAngka(
                    input,
                    "Pilih menu : ",
                    0,
                    4
            );

            switch (pilihan) {

                case 1:
                    tambah(input, daftarFilm);
                    break;

                case 2:
                    lihat(daftarFilm);
                    break;

                case 3:
                    ubah(input, daftarFilm);
                    break;

                case 4:
                    hapus(
                            input,
                            daftarFilm,
                            daftarTiket
                    );
                    break;
            }

        } while (pilihan != 0);
    }
}