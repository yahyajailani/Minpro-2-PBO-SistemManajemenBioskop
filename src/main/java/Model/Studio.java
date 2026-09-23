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
public class Studio {

    private String idStudio;
    private String nama;
    private int kapasitas;
    private String tipe;

    public Studio(
            String idStudio,
            String nama,
            int kapasitas,
            String tipe) {

        this.idStudio = idStudio;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.tipe = tipe;
    }

    public String getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(String idStudio) {
        this.idStudio = idStudio;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public static void tambah(
            Scanner input,
            ArrayList<Studio> daftarStudio) {

        System.out.println("\n=== TAMBAH STUDIO ===");

        String id = Pengguna.inputTeks(
                input,
                "ID Studio : "
        );

        for (Studio s : daftarStudio) {

            if (s.getIdStudio().equalsIgnoreCase(id)) {

                System.out.println(
                        "ID studio sudah digunakan."
                );

                return;
            }
        }

        String nama = Pengguna.inputTeks(
                input,
                "Nama      : "
        );

        int kapasitas = Pengguna.inputAngka(
                input,
                "Kapasitas : ",
                1,
                500
        );

        String tipe = Pengguna.inputTeks(
                input,
                "Tipe      : "
        );

        daftarStudio.add(
                new Studio(
                        id,
                        nama,
                        kapasitas,
                        tipe
                )
        );

        System.out.println(
                "Studio berhasil ditambahkan."
        );
    }

    public static void lihat(
            ArrayList<Studio> daftarStudio) {

        System.out.println("\n==================================");
        System.out.println("           DAFTAR STUDIO");
        System.out.println("==================================");

        if (daftarStudio.isEmpty()) {

            System.out.println(
                    "Belum ada data studio."
            );

            return;
        }

        for (int i = 0; i < daftarStudio.size(); i++) {

            Studio s = daftarStudio.get(i);

            System.out.println(
                    "\nStudio ke-" + (i + 1)
            );

            System.out.println(
                    "ID        : " + s.getIdStudio()
            );

            System.out.println(
                    "Nama      : " + s.getNama()
            );

            System.out.println(
                    "Kapasitas : " + s.getKapasitas()
            );

            System.out.println(
                    "Tipe      : " + s.getTipe()
            );
        }
    }

    public static void ubah(
            Scanner input,
            ArrayList<Studio> daftarStudio) {

        if (daftarStudio.isEmpty()) {

            System.out.println(
                    "Belum ada data studio."
            );

            return;
        }

        lihat(daftarStudio);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih studio : ",
                1,
                daftarStudio.size()
        );

        Studio s = daftarStudio.get(
                pilih - 1
        );

        String nama = Pengguna.inputTeks(
                input,
                "Nama baru      : "
        );

        int kapasitas = Pengguna.inputAngka(
                input,
                "Kapasitas baru : ",
                1,
                500
        );

        String tipe = Pengguna.inputTeks(
                input,
                "Tipe baru      : "
        );

        s.setNama(nama);
        s.setKapasitas(kapasitas);
        s.setTipe(tipe);

        System.out.println(
                "Studio berhasil diubah."
        );
    }

    public static void hapus(
            Scanner input,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket) {

        if (daftarStudio.isEmpty()) {

            System.out.println(
                    "Belum ada data studio."
            );

            return;
        }

        lihat(daftarStudio);

        int pilih = Pengguna.inputAngka(
                input,
                "\nPilih studio : ",
                1,
                daftarStudio.size()
        );

        Studio studio = daftarStudio.get(
                pilih - 1
        );

        for (Tiket tiket : daftarTiket) {

            if (tiket.getStudio() == studio) {

                System.out.println(
                        "Studio tidak dapat dihapus "
                        + "karena sudah digunakan pada tiket."
                );

                return;
            }
        }

        daftarStudio.remove(studio);

        System.out.println(
                "Studio berhasil dihapus."
        );
    }

    public static void menu(
            Scanner input,
            ArrayList<Studio> daftarStudio,
            ArrayList<Tiket> daftarTiket) {

        int pilihan;

        do {

            System.out.println("\n==================================");
            System.out.println("          KELOLA STUDIO");
            System.out.println("==================================");
            System.out.println("1. Tambah Studio");
            System.out.println("2. Lihat Studio");
            System.out.println("3. Ubah Studio");
            System.out.println("4. Hapus Studio");
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
                    tambah(input, daftarStudio);
                    break;

                case 2:
                    lihat(daftarStudio);
                    break;

                case 3:
                    ubah(input, daftarStudio);
                    break;

                case 4:
                    hapus(
                            input,
                            daftarStudio,
                            daftarTiket
                    );
                    break;
            }

        } while (pilihan != 0);
    }
}