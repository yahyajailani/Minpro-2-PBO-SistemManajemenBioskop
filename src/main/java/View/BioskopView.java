/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Film;
import Model.Penonton;
import Model.Studio;
import Model.Tiket;
import Model.Transaksi;
import java.util.Scanner;

/**
 *
 * @author ADVAN
 */
public class BioskopView {

    private Scanner input = new Scanner(System.in);

    public void header() {

        System.out.println("\n========================================");
        System.out.println("       SISTEM MANAJEMEN BIOSKOP");
        System.out.println("========================================");
    }

    public int menuLogin() {

        System.out.println("\n============== LOGIN ==============");
        System.out.println("1. Login Admin");
        System.out.println("2. Login Penonton");
        System.out.println("3. Daftar Penonton");
        System.out.println("0. Keluar");
        System.out.println("===================================");

        return bacaPilihan("Pilih menu: ", 0, 3);
    }

    public String bacaString(String pesan) {

        while (true) {

            System.out.print(pesan);

            String hasil = input.nextLine().trim();

            if (!hasil.isEmpty()) {
                return hasil;
            }

            System.out.println(
                    "Input tidak boleh kosong."
            );
        }
    }

    public String bacaNama(String pesan) {

        while (true) {

            String nama = bacaString(pesan);

            if (nama.matches("[a-zA-Z ]+")) {
                return nama;
            }

            System.out.println(
                    "Nama hanya boleh berisi huruf dan spasi."
            );
        }
    }

    public String bacaEmail(String pesan) {

        while (true) {

            String email = bacaString(pesan);

            if (email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                return email;
            }

            System.out.println(
                    "Format email tidak valid."
            );

            System.out.println(
                    "Contoh: nama@gmail.com"
            );
        }
    }

    public String bacaNoHP(String pesan) {

        while (true) {

            String noHP = bacaString(pesan);

            if (noHP.matches("[0-9]+")
                    && noHP.length() >= 10
                    && noHP.length() <= 13) {

                return noHP;
            }

            System.out.println(
                    "No HP harus berupa angka 10-13 digit."
            );
        }
    }

    public String bacaPassword(String pesan) {

        while (true) {

            String password = bacaString(pesan);

            if (password.length() >= 6) {
                return password;
            }

            System.out.println(
                    "Password minimal 6 karakter."
            );
        }
    }

    public int bacaInt(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                String teks =
                        input.nextLine().trim();

                if (teks.isEmpty()) {

                    System.out.println(
                            "Input tidak boleh kosong."
                    );

                    continue;
                }

                int hasil =
                        Integer.parseInt(teks);

                if (hasil >= 0) {
                    return hasil;
                }

                System.out.println(
                        "Angka tidak boleh negatif."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Input harus berupa angka."
                );
            }
        }
    }

    public int bacaIntPositif(String pesan) {

        while (true) {

            int hasil = bacaInt(pesan);

            if (hasil > 0) {
                return hasil;
            }

            System.out.println(
                    "Nilai harus lebih dari 0."
            );
        }
    }

    public double bacaDouble(String pesan) {

        while (true) {

            try {

                System.out.print(pesan);

                String teks =
                        input.nextLine().trim();

                if (teks.isEmpty()) {

                    System.out.println(
                            "Input tidak boleh kosong."
                    );

                    continue;
                }

                double hasil =
                        Double.parseDouble(teks);

                if (hasil > 0) {
                    return hasil;
                }

                System.out.println(
                        "Harga harus lebih dari 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Harga harus berupa angka."
                );
            }
        }
    }

    public int bacaPilihan(
            String pesan,
            int min,
            int max) {

        while (true) {

            int pilihan = bacaInt(pesan);

            if (pilihan >= min
                    && pilihan <= max) {

                return pilihan;
            }

            System.out.println(
                    "Pilihan hanya boleh "
                    + min
                    + " sampai "
                    + max
                    + "."
            );
        }
    }

    public String bacaTipeStudio(String pesan) {

        while (true) {

            String tipe =
                    bacaString(pesan);

            if (tipe.equalsIgnoreCase("Regular")) {
                return "Regular";
            }

            if (tipe.equalsIgnoreCase("VIP")) {
                return "VIP";
            }

            System.out.println(
                    "Tipe studio hanya Regular atau VIP."
            );
        }
    }

    public void pesan(String pesan) {

        System.out.println("\n" + pesan);
    }

    public void tampilAdmin(String nama) {

        System.out.println("\n=================================");
        System.out.println("           MENU ADMIN        ");
        System.out.println("      Login sebagai : " + nama);
        System.out.println("=================================");
    }

    public void tampilPenonton(String nama) {

        System.out.println("\n=================================");
        System.out.println("         MENU PENONTON      ");
        System.out.println("     Selamat datang, " + nama);
        System.out.println("=================================");
    }

    public int menuAdmin() {

        System.out.println("\n1. Data Film");
        System.out.println("2. Data Studio");
        System.out.println("3. Data Tiket");
        System.out.println("4. Data Penonton");
        System.out.println("5. Riwayat Transaksi");
        System.out.println("0. Logout");

        return bacaPilihan(
                "Pilih menu: ",
                0,
                5
        );
    }

    public int menuPenonton() {

        System.out.println("\n1. Lihat Profil");
        System.out.println("2. Lihat Film");
        System.out.println("3. Lihat Studio");
        System.out.println("4. Beli Tiket");
        System.out.println("5. Riwayat Transaksi");
        System.out.println("0. Logout");

        return bacaPilihan(
                "Pilih menu: ",
                0,
                5
        );
    }

    public int menuCRUD(String nama) {

        System.out.println(
                "\n========== DATA "
                + nama.toUpperCase()
                + " =========="
        );

        System.out.println("1. Tambah");
        System.out.println("2. Lihat");
        System.out.println("3. Update");
        System.out.println("4. Hapus");
        System.out.println("0. Kembali");

        return bacaPilihan(
                "Pilih menu: ",
                0,
                4
        );
    }

    public void tampilFilm(
            Film film,
            int index) {

        System.out.println("\n[" + index + "]");
        System.out.println(film);
    }

    public void tampilStudio(
            Studio studio,
            int index) {

        System.out.println("\n[" + index + "]");
        System.out.println(studio);
    }

    public void tampilTiket(
            Tiket tiket,
            int index) {

        System.out.println("\n[" + index + "]");
        System.out.println(tiket);
    }

    public void tampilPenontonData(
            Penonton penonton,
            int index) {

        System.out.println("\n[" + index + "]");
        System.out.println(penonton);
    }

    public void tampilTransaksi(
            Transaksi transaksi,
            int index) {

        System.out.println("\n[" + index + "]");
        System.out.println(transaksi);
    }

    public int pilihFilm() {

        System.out.println(
                "\n========== PILIH FILM =========="
        );

        for (int i = 0;
                i < Film.getDaftarFilm().size();
                i++) {

            Film film =
                    Film.getDaftarFilm().get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + film.getJudul()
                    + " - "
                    + film.getGenre()
                    + " - Rp"
                    + film.getHarga()
            );
        }

        while (true) {

            int pilihan =
                    bacaIntPositif("Pilih film: ");

            if (pilihan >= 1
                    && pilihan <= Film.getDaftarFilm().size()) {

                return pilihan - 1;
            }

            System.out.println(
                    "Film tidak tersedia."
            );
        }
    }

    public int pilihStudio() {

        System.out.println(
                "\n========== PILIH STUDIO =========="
        );

        for (int i = 0;
                i < Studio.getDaftarStudio().size();
                i++) {

            Studio studio =
                    Studio.getDaftarStudio().get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + studio.getNama()
                    + " - "
                    + studio.getTipe()
            );
        }

        while (true) {

            int pilihan =
                    bacaIntPositif("Pilih studio: ");

            if (pilihan >= 1
                    && pilihan <= Studio.getDaftarStudio().size()) {

                return pilihan - 1;
            }

            System.out.println(
                    "Studio tidak tersedia."
            );
        }
    }

    public boolean konfirmasi(String pesan) {

        while (true) {

            String jawaban =
                    bacaString(
                            pesan + " (y/n): "
                    );

            if (jawaban.equalsIgnoreCase("y")) {
                return true;
            }

            if (jawaban.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println(
                    "Masukkan hanya y atau n."
            );
        }
    }
}