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
public class Pengguna {

    private String id;
    private String nama;
    private String email;

    public Pengguna(String id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void tampilkanInfo() {
        System.out.println("ID     : " + id);
        System.out.println("Nama   : " + nama);
        System.out.println("Email  : " + email);
    }

    public static String inputTeks(Scanner input, String pesan) {
        String teks;

        do {
            System.out.print(pesan);
            teks = input.nextLine().trim();

            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong.");
            }

        } while (teks.isEmpty());

        return teks;
    }

    public static int inputAngka(
            Scanner input,
            String pesan,
            int min,
            int max) {

        int angka;

        while (true) {
            try {
                System.out.print(pesan);
                angka = Integer.parseInt(input.nextLine());

                if (angka >= min && angka <= max) {
                    return angka;
                }

                System.out.println(
                        "Masukkan angka dari "
                        + min + " sampai " + max + "."
                );

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    public static double inputDesimal(
            Scanner input,
            String pesan,
            double min,
            double max) {

        double angka;

        while (true) {
            try {
                System.out.print(pesan);
                angka = Double.parseDouble(input.nextLine());

                if (angka >= min && angka <= max) {
                    return angka;
                }

                System.out.println(
                        "Masukkan angka dari "
                        + min + " sampai " + max + "."
                );

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    public static String inputEmail(Scanner input) {

        String email;

        do {
            System.out.print("Email       : ");
            email = input.nextLine().trim();

            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                System.out.println("Format email tidak valid.");
            }

        } while (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        return email;
    }

    public static String inputNoHp(Scanner input) {

        String noHp;

        do {
            System.out.print("No HP       : ");
            noHp = input.nextLine().trim();

            if (!noHp.matches("[0-9]{10,13}")) {
                System.out.println(
                        "No HP harus berupa angka 10-13 digit."
                );
            }

        } while (!noHp.matches("[0-9]{10,13}"));

        return noHp;
    }

    public static void loading(String pesan) {

        System.out.print(pesan);

        for (int i = 0; i < 3; i++) {

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.print(".");
        }

        System.out.println();
    }

    public static void jalankan() {

        Scanner input = new Scanner(System.in);

        ArrayList<Film> daftarFilm = new ArrayList<>();
        ArrayList<Studio> daftarStudio = new ArrayList<>();
        ArrayList<Penonton> daftarPenonton = new ArrayList<>();
        ArrayList<Tiket> daftarTiket = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

        // Data awal film
        daftarFilm.add(
                new Film(
                        "F001",
                        "Avengers: Endgame",
                        "Action",
                        181,
                        50000
                )
        );

        daftarFilm.add(
                new Film(
                        "F002",
                        "Interstellar",
                        "Sci-Fi",
                        169,
                        45000
                )
        );

        // Data awal studio
        daftarStudio.add(
                new Studio(
                        "S001",
                        "Studio 1",
                        50,
                        "Regular"
                )
        );

        daftarStudio.add(
                new Studio(
                        "S002",
                        "Studio 2",
                        30,
                        "VIP"
                )
        );

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("       SISTEM MANAJEMEN BIOSKOP");
            System.out.println("==================================");
            System.out.println("1. Login sebagai Penonton");
            System.out.println("2. Login sebagai Admin");
            System.out.println("0. Keluar");
            System.out.println("==================================");

            pilihan = inputAngka(
                    input,
                    "Pilih menu : ",
                    0,
                    2
            );

            switch (pilihan) {

                case 1:
                    Penonton.login(
                            input,
                            daftarPenonton,
                            daftarFilm,
                            daftarStudio,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;

                case 2:
                    Admin.login(
                            input,
                            daftarPenonton,
                            daftarFilm,
                            daftarStudio,
                            daftarTiket,
                            daftarTransaksi
                    );
                    break;

                case 0:
                    loading("Keluar dari sistem");
                    System.out.println(
                            "Terima kasih telah menggunakan Cinematter."
                    );
                    break;
            }

        } while (pilihan != 0);

        input.close();
    }
}