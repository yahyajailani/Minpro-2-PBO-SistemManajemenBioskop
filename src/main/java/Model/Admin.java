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
public class Admin extends Pengguna {

    private String jabatan;

    public Admin(
            String id,
            String nama,
            String email,
            String jabatan) {

        super(id, nama, email);
        this.jabatan = jabatan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public void tampilkanInfo() {

        System.out.println("ID       : " + getId());
        System.out.println("Nama     : " + getNama());
        System.out.println("Email    : " + getEmail());
        System.out.println("Jabatan  : " + jabatan);
    }

    public static void login(
            Scanner input,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        System.out.println("\n==================================");
        System.out.println("            LOGIN ADMIN");
        System.out.println("==================================");

        String username = Pengguna.inputTeks(
                input,
                "Username : "
        );

        String password = Pengguna.inputTeks(
                input,
                "Password : "
        );

        if (username.equals("admin")
                && password.equals("admin123")) {

            Pengguna.loading("Memproses login");

            Admin admin = new Admin(
                    "A001",
                    "Administrator",
                    "admin@gmail.com",
                    "Administrator"
            );

            menu(
                    input,
                    admin,
                    daftarPenonton,
                    daftarFilm,
                    daftarStudio,
                    daftarTiket,
                    daftarTransaksi
            );

        } else {

            System.out.println(
                    "Username atau password salah."
            );
        }
    }

    public static void menu(
            Scanner input,
            Admin admin,
            ArrayList<Penonton> daftarPenonton,
            ArrayList<Film> daftarFilm,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket,
            ArrayList<Transaksi> daftarTransaksi) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("            MENU ADMIN");
            System.out.println("==================================");
            System.out.println(
                    "Selamat datang, " + admin.getNama()
            );
            System.out.println("----------------------------------");
            System.out.println("1. Kelola Film");
            System.out.println("2. Kelola Studio");
            System.out.println("3. Kelola Penonton");
            System.out.println("4. Kelola Tiket");
            System.out.println("5. Riwayat Transaksi");
            System.out.println("6. Profil Admin");
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
                    Film.menu(
                            input,
                            daftarFilm,
                            daftarTiket
                    );
                    break;

                case 2:
                    Studio.menu(
                            input,
                            daftarStudio,
                            daftarTiket
                    );
                    break;

                case 3:
                    Penonton.menuAdmin(
                            input,
                            daftarPenonton,
                            daftarTiket
                    );
                    break;

                case 4:
                    Tiket.menuAdmin(
                            input,
                            daftarPenonton,
                            daftarFilm,
                            daftarStudio,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;

                case 5:
                    Transaksi.menuAdmin(
                            input,
                            daftarTransaksi
                    );
                    break;

                case 6:
                    System.out.println("\n==================================");
                    System.out.println("           PROFIL ADMIN");
                    System.out.println("==================================");

                    admin.tampilkanInfo();
                    break;

                case 0:
                    Pengguna.loading("Logout");
                    break;
            }

        } while (pilihan != 0);
    }
}