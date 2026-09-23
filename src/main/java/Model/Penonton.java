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
public class Penonton extends Pengguna {

    private String noHp;

    public Penonton(
            String id,
            String nama,
            String email,
            String noHp) {

        super(id, nama, email);
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    @Override
    public void tampilkanInfo() {

        System.out.println("ID       : " + getId());
        System.out.println("Nama     : " + getNama());
        System.out.println("Email    : " + getEmail());
        System.out.println("No HP    : " + noHp);
    }

    public static void login(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        System.out.println("\n==================================");
        System.out.println("          DATA PENONTON");
        System.out.println("==================================");

        String id;

        while (true) {

            id = Pengguna.inputTeks(
                    input,
                    "ID Penonton : "
            );

            boolean sudahAda = false;

            for (Penonton p : daftarPenonton) {

                if (p.getId().equalsIgnoreCase(id)) {
                    sudahAda = true;
                    break;
                }
            }

            if (!sudahAda) {
                break;
            }

            System.out.println(
                    "ID penonton sudah terdaftar."
            );
            System.out.println(
                    "Silakan gunakan ID lain."
            );
        }

        String nama = Pengguna.inputTeks(
                input,
                "Nama        : "
        );

        String email = Pengguna.inputEmail(input);

        String noHp = Pengguna.inputNoHp(input);

        Penonton penonton = new Penonton(
                id,
                nama,
                email,
                noHp
        );

        daftarPenonton.add(penonton);

        Pengguna.loading("Memproses login");

        menu(
                input,
                penonton,
                daftarFilm,
                daftarStudio,
                daftarTiket,
                daftarTransaksi
        );
    }

    public static void menu(
            Scanner input,
            Penonton penonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("          MENU PENONTON");
            System.out.println("==================================");
            System.out.println(
                    "Selamat datang, " + penonton.getNama()
            );
            System.out.println("----------------------------------");
            System.out.println("1. Lihat Daftar Film");
            System.out.println("2. Lihat Daftar Studio");
            System.out.println("3. Beli Tiket");
            System.out.println("4. Lihat Tiket Saya");
            System.out.println("5. Riwayat Transaksi Saya");
            System.out.println("6. Profil Saya");
            System.out.println("0. Logout");
            System.out.println("==================================");

            pilihan = Pengguna.inputAngka(
                    input,
                    "Pilih menu : ",
                    0,
                    6
            );

            switch (pilihan) {

                case 1:
                    Film.lihat(daftarFilm);
                    break;

                case 2:
                    Studio.lihat(daftarStudio);
                    break;

                case 3:
                    Tiket.beliUntukPenonton(
                            input,
                            penonton,
                            daftarFilm,
                            daftarStudio,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;

                case 4:
                    Tiket.lihatTiketPenonton(
                            daftarTiket,
                            penonton
                    );
                    break;

                case 5:
                    Transaksi.lihatTransaksiPenonton(
                            daftarTransaksi,
                            penonton
                    );
                    break;

                case 6:
                    System.out.println("\n==================================");
                    System.out.println("           PROFIL SAYA");
                    System.out.println("==================================");

                    penonton.tampilkanInfo();
                    break;

                case 0:
                    Pengguna.loading("Logout");
                    break;
            }

        } while (pilihan != 0);
    }

    public static void tambah(
            Scanner input,
            ArrayList<Penonton> daftarPenonton) {

        System.out.println("\n=== TAMBAH PENONTON ===");

        String id = Pengguna.inputTeks(
                input,
                "ID Penonton : "
        );

        for (Penonton p : daftarPenonton) {

            if (p.getId().equalsIgnoreCase(id)) {

                System.out.println(
                        "ID penonton sudah digunakan."
                );

                return;
            }
        }

        String nama = Pengguna.inputTeks(
                input,
                "Nama        : "
        );

        String email = Pengguna.inputEmail(input);
        String noHp = Pengguna.inputNoHp(input);

        daftarPenonton.add(
                new Penonton(
                        id,
                        nama,
                        email,
                        noHp
                )
        );

        System.out.println(
                "Penonton berhasil ditambahkan."
        );
    }

    public static void lihat(
            ArrayList<Penonton> daftarPenonton) {

        System.out.println("\n==================================");
        System.out.println("          DAFTAR PENONTON");
        System.out.println("==================================");

        if (daftarPenonton.isEmpty()) {

            System.out.println(
                    "Belum ada data penonton."
            );

            return;
        }

        for (int i = 0; i < daftarPenonton.size(); i++) {

            Penonton p = daftarPenonton.get(i);

            System.out.println(
                    "\nPenonton ke-" + (i + 1)
            );

            p.tampilkanInfo();
        }
    }

    public static void ubah(
            Scanner input,
            ArrayList<Penonton> daftarPenonton) {

        if (daftarPenonton.isEmpty()) {

            System.out.println(
                    "Belum ada data penonton."
            );

            return;
        }

        lihat(daftarPenonton);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih penonton : ",
                1,
                daftarPenonton.size()
        );

        Penonton p = daftarPenonton.get(
                pilih - 1
        );

        String nama = Pengguna.inputTeks(
                input,
                "Nama baru  : "
        );

        String email = Pengguna.inputEmail(input);
        String noHp = Pengguna.inputNoHp(input);

        p.setNama(nama);
        p.setEmail(email);
        p.setNoHp(noHp);

        System.out.println(
                "Data penonton berhasil diubah."
        );
    }

    public static void hapus(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Tiket> daftarTiket) {

        if (daftarPenonton.isEmpty()) {

            System.out.println(
                    "Belum ada data penonton."
            );

            return;
        }

        lihat(daftarPenonton);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih penonton : ",
                1,
                daftarPenonton.size()
        );

        Penonton p = daftarPenonton.get(
                pilih - 1
        );

        for (Tiket tiket : daftarTiket) {

            if (tiket.getPenonton() == p) {

                System.out.println(
                        "Penonton tidak dapat dihapus "
                        + "karena memiliki tiket."
                );

                return;
            }
        }

        daftarPenonton.remove(p);

        System.out.println(
                "Penonton berhasil dihapus."
        );
    }

    public static void menuAdmin(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Tiket> daftarTiket) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("        KELOLA DATA PENONTON");
            System.out.println("==================================");
            System.out.println("1. Tambah Penonton");
            System.out.println("2. Lihat Penonton");
            System.out.println("3. Ubah Penonton");
            System.out.println("4. Hapus Penonton");
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
                    tambah(input, daftarPenonton);
                    break;

                case 2:
                    lihat(daftarPenonton);
                    break;

                case 3:
                    ubah(input, daftarPenonton);
                    break;

                case 4:
                    hapus(
                            input,
                            daftarPenonton,
                            daftarTiket
                    );
                    break;
            }

        } while (pilihan != 0);
    }
}