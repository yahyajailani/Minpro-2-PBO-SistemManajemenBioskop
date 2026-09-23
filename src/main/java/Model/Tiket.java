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

    private String idTiket;
    private Penonton penonton;
    private Film film;
    private Studio studio;
    private int jumlah;
    private double harga;
    private double totalHarga;

    public Tiket(
            String idTiket,
            Penonton penonton,
            Film film,
            Studio studio,
            int jumlah) {

        this.idTiket = idTiket;
        this.penonton = penonton;
        this.film = film;
        this.studio = studio;
        this.jumlah = jumlah;
        this.harga = film.getHarga();

        hitungTotal();
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
    }

    public Penonton getPenonton() {
        return penonton;
    }

    public void setPenonton(Penonton penonton) {
        this.penonton = penonton;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {

        this.film = film;
        this.harga = film.getHarga();

        hitungTotal();
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

        hitungTotal();
    }

    public double getHarga() {
        return harga;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    private void hitungTotal() {
        totalHarga = harga * jumlah;
    }

    private static String generateId(
            ArrayList<Tiket> daftarTiket) {

        int nomor = daftarTiket.size() + 1;
        String id;

        do {

            id = String.format(
                    "TKT%03d",
                    nomor
            );

            nomor++;

        } while (adaId(daftarTiket, id));

        return id;
    }

    private static boolean adaId(
            ArrayList<Tiket> daftarTiket,
            String id) {

        for (Tiket tiket : daftarTiket) {

            if (tiket.getIdTiket()
                    .equalsIgnoreCase(id)) {

                return true;
            }
        }

        return false;
    }

    public static void beliUntukPenonton(
            Scanner input,
            Penonton penonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        if (daftarFilm.isEmpty()) {

            System.out.println(
                    "Belum ada film."
            );

            return;
        }

        if (daftarStudio.isEmpty()) {

            System.out.println(
                    "Belum ada studio."
            );

            return;
        }

        System.out.println("\n==================================");
        System.out.println("            BELI TIKET");
        System.out.println("==================================");

        System.out.println(
                "Penonton : " + penonton.getNama()
        );

        Film.lihat(daftarFilm);

        int pilihFilm = Pengguna.inputAngka(
                input,
                "\nPilih film : ",
                1,
                daftarFilm.size()
        );

        Film film = daftarFilm.get(
                pilihFilm - 1
        );

        Studio.lihat(daftarStudio);

        int pilihStudio = Pengguna.inputAngka(
                input,
                "\nPilih studio : ",
                1,
                daftarStudio.size()
        );

        Studio studio = daftarStudio.get(
                pilihStudio - 1
        );

        int jumlah = Pengguna.inputAngka(
                input,
                "Jumlah tiket : ",
                1,
                studio.getKapasitas()
        );

        String idTiket = generateId(
                daftarTiket
        );

        Tiket tiket = new Tiket(
                idTiket,
                penonton,
                film,
                studio,
                jumlah
        );

        daftarTiket.add(tiket);

        String idTransaksi = String.format(
                "TRX%03d",
                daftarTransaksi.size() + 1
        );

        Transaksi transaksi = new Transaksi(
                idTransaksi,
                penonton,
                tiket
        );

        daftarTransaksi.add(transaksi);

        Pengguna.loading(
                "Memproses pembelian"
        );

        System.out.println("\nTiket berhasil dibeli.");
        System.out.println(
                "ID Tiket    : " + tiket.getIdTiket()
        );
        System.out.println(
                "Film        : "
                + tiket.getFilm().getJudul()
        );
        System.out.println(
                "Studio      : "
                + tiket.getStudio().getNama()
        );
        System.out.println(
                "Jumlah      : " + tiket.getJumlah()
        );
        System.out.println(
                "Total Harga : Rp"
                + tiket.getTotalHarga()
        );
    }

    public static void tambah(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        if (daftarPenonton.isEmpty()
                || daftarFilm.isEmpty()
                || daftarStudio.isEmpty()) {

            System.out.println(
                    "Data penonton, film, atau studio "
                    + "belum tersedia."
            );

            return;
        }

        System.out.println("\n=== TAMBAH TIKET ===");

        System.out.println("\nDAFTAR PENONTON");
        Penonton.lihat(daftarPenonton);

        int pilihPenonton = Pengguna.inputAngka(
                input,
                "\nPilih penonton : ",
                1,
                daftarPenonton.size()
        );

        Penonton penonton =
                daftarPenonton.get(
                        pilihPenonton - 1
                );

        System.out.println("\nDAFTAR FILM");
        Film.lihat(daftarFilm);

        int pilihFilm = Pengguna.inputAngka(
                input,
                "\nPilih film : ",
                1,
                daftarFilm.size()
        );

        Film film =
                daftarFilm.get(
                        pilihFilm - 1
                );

        System.out.println("\nDAFTAR STUDIO");
        Studio.lihat(daftarStudio);

        int pilihStudio = Pengguna.inputAngka(
                input,
                "\nPilih studio : ",
                1,
                daftarStudio.size()
        );

        Studio studio =
                daftarStudio.get(
                        pilihStudio - 1
                );

        int jumlah = Pengguna.inputAngka(
                input,
                "Jumlah tiket : ",
                1,
                studio.getKapasitas()
        );

        String idTiket =
                generateId(daftarTiket);

        Tiket tiket = new Tiket(
                idTiket,
                penonton,
                film,
                studio,
                jumlah
        );

        daftarTiket.add(tiket);

        String idTransaksi = String.format(
                "TRX%03d",
                daftarTransaksi.size() + 1
        );

        daftarTransaksi.add(
                new Transaksi(
                        idTransaksi,
                        penonton,
                        tiket
                )
        );

        System.out.println(
                "Tiket berhasil ditambahkan."
        );
    }

    public static void lihat(
            ArrayList<Tiket> daftarTiket) {

        System.out.println("\n==================================");
        System.out.println("            DAFTAR TIKET");
        System.out.println("==================================");

        if (daftarTiket.isEmpty()) {

            System.out.println(
                    "Belum ada tiket."
            );

            return;
        }

        for (int i = 0; i < daftarTiket.size(); i++) {

            Tiket tiket = daftarTiket.get(i);

            System.out.println(
                    "\nTiket ke-" + (i + 1)
            );

            System.out.println(
                    "ID Tiket    : "
                    + tiket.getIdTiket()
            );

            System.out.println(
                    "Penonton    : "
                    + tiket.getPenonton().getNama()
            );

            System.out.println(
                    "Film        : "
                    + tiket.getFilm().getJudul()
            );

            System.out.println(
                    "Studio      : "
                    + tiket.getStudio().getNama()
            );

            System.out.println(
                    "Jumlah      : "
                    + tiket.getJumlah()
            );

            System.out.println(
                    "Harga       : Rp"
                    + tiket.getHarga()
            );

            System.out.println(
                    "Total Harga : Rp"
                    + tiket.getTotalHarga()
            );
        }
    }

    public static void lihatTiketPenonton(
            ArrayList<Tiket> daftarTiket,
            Penonton penonton) {

        System.out.println("\n==================================");
        System.out.println("           TIKET SAYA");
        System.out.println("==================================");

        boolean ada = false;

        for (Tiket tiket : daftarTiket) {

            if (tiket.getPenonton() == penonton) {

                ada = true;

                System.out.println(
                        "\nID Tiket    : "
                        + tiket.getIdTiket()
                );

                System.out.println(
                        "Film        : "
                        + tiket.getFilm().getJudul()
                );

                System.out.println(
                        "Studio      : "
                        + tiket.getStudio().getNama()
                );

                System.out.println(
                        "Jumlah      : "
                        + tiket.getJumlah()
                );

                System.out.println(
                        "Total Harga : Rp"
                        + tiket.getTotalHarga()
                );
            }
        }

        if (!ada) {

            System.out.println(
                    "Belum memiliki tiket."
            );
        }
    }

    public static void ubah(
            Scanner input,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio) {

        if (daftarTiket.isEmpty()) {

            System.out.println(
                    "Belum ada tiket."
            );

            return;
        }

        lihat(daftarTiket);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih tiket : ",
                1,
                daftarTiket.size()
        );

        Tiket tiket = daftarTiket.get(
                pilih - 1
        );

        Film.lihat(daftarFilm);

        int pilihFilm = Pengguna.inputAngka(
                input,
                "\nPilih film baru : ",
                1,
                daftarFilm.size()
        );

        Film film = daftarFilm.get(
                pilihFilm - 1
        );

        Studio.lihat(daftarStudio);

        int pilihStudio = Pengguna.inputAngka(
                input,
                "\nPilih studio baru : ",
                1,
                daftarStudio.size()
        );

        Studio studio = daftarStudio.get(
                pilihStudio - 1
        );

        int jumlah = Pengguna.inputAngka(
                input,
                "Jumlah tiket baru : ",
                1,
                studio.getKapasitas()
        );

        tiket.setFilm(film);
        tiket.setStudio(studio);
        tiket.setJumlah(jumlah);

        System.out.println(
                "Tiket berhasil diubah."
        );
    }

    public static void hapus(
            Scanner input,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        if (daftarTiket.isEmpty()) {

            System.out.println(
                    "Belum ada tiket."
            );

            return;
        }

        lihat(daftarTiket);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih tiket : ",
                1,
                daftarTiket.size()
        );

        Tiket tiket = daftarTiket.get(
                pilih - 1
        );

        daftarTransaksi.removeIf(
                transaksi ->
                        transaksi.getTiket() == tiket
        );

        daftarTiket.remove(tiket);

        System.out.println(
                "Tiket berhasil dihapus."
        );
    }

    public static void menuAdmin(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("           KELOLA TIKET");
            System.out.println("==================================");
            System.out.println("1. Tambah Tiket");
            System.out.println("2. Lihat Tiket");
            System.out.println("3. Ubah Tiket");
            System.out.println("4. Hapus Tiket");
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
                    tambah(
                            input,
                            daftarPenonton,
                            daftarFilm,
                            daftarStudio,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;

                case 2:
                    lihat(daftarTiket);
                    break;

                case 3:
                    ubah(
                            input,
                            daftarTiket,
                            daftarFilm,
                            daftarStudio
                    );
                    break;

                case 4:
                    hapus(
                            input,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;
            }

        } while (pilihan != 0);
    }
}