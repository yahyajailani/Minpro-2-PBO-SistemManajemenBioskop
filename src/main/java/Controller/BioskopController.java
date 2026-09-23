/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin;
import Model.Film;
import Model.Penonton;
import Model.Studio;
import Model.Tiket;
import Model.Transaksi;
import View.BioskopView;
import java.util.ArrayList;

/**
 *
 * @author ADVAN
 */
public class BioskopController {

    private BioskopView view;
    private Admin admin;

    private ArrayList<Penonton> daftarPenonton
            = new ArrayList<>();

    public BioskopController() {

        view = new BioskopView();

        admin = new Admin(
                "Admin",
                "Cinematter@gmail.com",
                "081229596016",
                "Cinematter33"
        );

        isiDataAwal();
    }

    private void isiDataAwal() {

        if (Film.getDaftarFilm().isEmpty()) {

            Film.tambah(
                    new Film(
                            "Avengers: Endgame",
                            "Action",
                            181,
                            50000
                    )
            );

            Film.tambah(
                    new Film(
                            "Interstellar",
                            "Sci-Fi",
                            169,
                            45000
                    )
            );
        }

        if (Studio.getDaftarStudio().isEmpty()) {

            Studio.tambah(
                    new Studio(
                            "Studio 1",
                            100,
                            "Regular"
                    )
            );

            Studio.tambah(
                    new Studio(
                            "Studio 2",
                            80,
                            "VIP"
                    )
            );
        }
    }

    public void jalankan() {

        view.header();

        boolean jalan = true;

        while (jalan) {

            int pilihan =
                    view.menuLogin();

            switch (pilihan) {

                case 1:
                    loginAdmin();
                    break;

                case 2:
                    loginPenonton();
                    break;

                case 3:
                    daftarPenonton();
                    break;

                case 0:
                    jalan = false;

                    view.pesan(
                            "Terima kasih telah menggunakan sistem."
                    );

                    break;
            }
        }
    }

    private void loginAdmin() {

        System.out.println(
                "\n========== LOGIN ADMIN =========="
        );

        String email =
                view.bacaEmail("Email : ");

        String password =
                view.bacaString("Password : ");

        if (admin.login(email, password)) {

            view.pesan(
                    "Login admin berhasil."
            );

            menuAdmin();

        } else {

            view.pesan(
                    "Email atau password admin salah."
            );
        }
    }

    private void loginPenonton() {

        System.out.println(
                "\n========== LOGIN PENONTON =========="
        );

        if (daftarPenonton.isEmpty()) {

            view.pesan(
                    "Belum ada akun penonton. Silakan daftar terlebih dahulu."
            );

            return;
        }

        String email =
                view.bacaEmail("Email : ");

        String password =
                view.bacaString("Password : ");

        for (Penonton penonton : daftarPenonton) {

            if (penonton.login(
                    email,
                    password)) {

                view.pesan(
                        "Login penonton berhasil."
                );

                menuPenonton(penonton);

                return;
            }
        }

        view.pesan(
                "Email atau password penonton salah."
        );
    }

    private void daftarPenonton() {

        System.out.println(
                "\n========== DAFTAR PENONTON =========="
        );

        String nama =
                view.bacaNama("Nama : ");

        String email =
                view.bacaEmail("Email : ");

        String noHP =
                view.bacaNoHP("No HP : ");

        String password =
                view.bacaPassword("Password : ");

        for (Penonton penonton : daftarPenonton) {

            if (penonton.getEmail()
                    .equalsIgnoreCase(email)) {

                view.pesan(
                        "Email sudah terdaftar."
                );

                return;
            }
        }

        Penonton penonton =
                new Penonton(
                        nama,
                        email,
                        noHP,
                        password
                );

        daftarPenonton.add(penonton);

        view.pesan(
                "Pendaftaran berhasil. Silakan login."
        );
    }

    private void menuAdmin() {

        boolean kembali = false;

        while (!kembali) {

            view.tampilAdmin(
                    admin.getNama()
            );

            int pilihan =
                    view.menuAdmin();

            switch (pilihan) {

                case 1:
                    menuFilm();
                    break;

                case 2:
                    menuStudio();
                    break;

                case 3:
                    menuTiket();
                    break;

                case 4:
                    menuPenonton();
                    break;

                case 5:
                    lihatTransaksi();
                    break;

                case 0:
                    kembali = true;

                    view.pesan(
                            "Logout admin berhasil."
                    );

                    break;
            }
        }
    }

    private void menuPenonton(
            Penonton penonton) {

        boolean kembali = false;

        while (!kembali) {

            view.tampilPenonton(
                    penonton.getNama()
            );

            int pilihan =
                    view.menuPenonton();

            switch (pilihan) {

                case 1:
                    lihatProfil(penonton);
                    break;

                case 2:
                    lihatFilm();
                    break;

                case 3:
                    lihatStudio();
                    break;

                case 4:
                    beliTiket(penonton);
                    break;

                case 5:
                    lihatTransaksiPenonton(
                            penonton
                    );
                    break;

                case 0:
                    kembali = true;

                    view.pesan(
                            "Logout penonton berhasil."
                    );

                    break;
            }
        }
    }

    private void menuFilm() {

        boolean kembali = false;

        while (!kembali) {

            int pilihan =
                    view.menuCRUD("Film");

            switch (pilihan) {

                case 1:
                    tambahFilm();
                    break;

                case 2:
                    lihatFilm();
                    break;

                case 3:
                    updateFilm();
                    break;

                case 4:
                    hapusFilm();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambahFilm() {

        System.out.println(
                "\n========== TAMBAH FILM =========="
        );

        String judul =
                view.bacaString(
                        "Judul film : "
                );

        String genre =
                view.bacaString(
                        "Genre : "
                );

        int durasi =
                view.bacaIntPositif(
                        "Durasi : "
                );

        double harga =
                view.bacaDouble(
                        "Harga tiket film : Rp"
                );

        Film film =
                new Film(
                        judul,
                        genre,
                        durasi,
                        harga
                );

        Film.tambah(film);

        view.pesan(
                "Film berhasil ditambahkan."
        );
    }

    private void lihatFilm() {

        System.out.println(
                "\n========== DAFTAR FILM =========="
        );

        if (Film.getDaftarFilm().isEmpty()) {

            view.pesan(
                    "Belum ada data film."
            );

            return;
        }

        for (int i = 0;
                i < Film.getDaftarFilm().size();
                i++) {

            view.tampilFilm(
                    Film.getDaftarFilm().get(i),
                    i + 1
            );
        }
    }

    private void updateFilm() {

        if (Film.getDaftarFilm().isEmpty()) {

            view.pesan(
                    "Data film belum tersedia."
            );

            return;
        }

        lihatFilm();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor film yang ingin diupdate: "
                ) - 1;

        if (index < 0
                || index >= Film.getDaftarFilm().size()) {

            view.pesan(
                    "Nomor film tidak valid."
            );

            return;
        }

        Film filmLama =
                Film.getDaftarFilm().get(index);

        System.out.println(
                "\n========== UPDATE FILM =========="
        );

        System.out.println("Data lama:");
        System.out.println(filmLama);

        String judul =
                view.bacaString(
                        "Judul film baru : "
                );

        String genre =
                view.bacaString(
                        "Genre baru : "
                );

        int durasi =
                view.bacaIntPositif(
                        "Durasi baru : "
                );

        double harga =
                view.bacaDouble(
                        "Harga tiket baru : Rp"
                );

        Film filmBaru =
                new Film(
                        judul,
                        genre,
                        durasi,
                        harga
                );

        Film.update(
                index,
                filmBaru
        );

        view.pesan(
                "Data film berhasil diupdate."
        );
    }

    private void hapusFilm() {

        if (Film.getDaftarFilm().isEmpty()) {

            view.pesan(
                    "Data film belum tersedia."
            );

            return;
        }

        lihatFilm();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor film yang ingin dihapus: "
                ) - 1;

        if (index < 0
                || index >= Film.getDaftarFilm().size()) {

            view.pesan(
                    "Nomor film tidak valid."
            );

            return;
        }

        if (view.konfirmasi(
                "Yakin ingin menghapus film ini?"
        )) {

            Film.hapus(index);

            view.pesan(
                    "Film berhasil dihapus."
            );
        }
    }

    private void menuStudio() {

        boolean kembali = false;

        while (!kembali) {

            int pilihan =
                    view.menuCRUD("Studio");

            switch (pilihan) {

                case 1:
                    tambahStudio();
                    break;

                case 2:
                    lihatStudio();
                    break;

                case 3:
                    updateStudio();
                    break;

                case 4:
                    hapusStudio();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambahStudio() {

        System.out.println(
                "\n========== TAMBAH STUDIO =========="
        );

        String nama =
                view.bacaString(
                        "Nama studio : "
                );

        int kapasitas =
                view.bacaIntPositif(
                        "Kapasitas : "
                );

        String tipe =
                view.bacaTipeStudio(
                        "Tipe (Regular/VIP) : "
                );

        Studio studio =
                new Studio(
                        nama,
                        kapasitas,
                        tipe
                );

        Studio.tambah(studio);

        view.pesan(
                "Studio berhasil ditambahkan."
        );
    }

    private void lihatStudio() {

        System.out.println(
                "\n========== DAFTAR STUDIO =========="
        );

        if (Studio.getDaftarStudio().isEmpty()) {

            view.pesan(
                    "Belum ada data studio."
            );

            return;
        }

        for (int i = 0;
                i < Studio.getDaftarStudio().size();
                i++) {

            view.tampilStudio(
                    Studio.getDaftarStudio().get(i),
                    i + 1
            );
        }
    }

    private void updateStudio() {

        if (Studio.getDaftarStudio().isEmpty()) {

            view.pesan(
                    "Data studio belum tersedia."
            );

            return;
        }

        lihatStudio();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor studio yang ingin diupdate: "
                ) - 1;

        if (index < 0
                || index >= Studio.getDaftarStudio().size()) {

            view.pesan(
                    "Nomor studio tidak valid."
            );

            return;
        }

        String nama =
                view.bacaString(
                        "Nama studio baru : "
                );

        int kapasitas =
                view.bacaIntPositif(
                        "Kapasitas baru : "
                );

        String tipe =
                view.bacaTipeStudio(
                        "Tipe baru (Regular/VIP) : "
                );

        Studio studioBaru =
                new Studio(
                        nama,
                        kapasitas,
                        tipe
                );

        Studio.update(
                index,
                studioBaru
        );

        view.pesan(
                "Studio berhasil diupdate."
        );
    }

    private void hapusStudio() {

        if (Studio.getDaftarStudio().isEmpty()) {

            view.pesan(
                    "Data studio belum tersedia."
            );

            return;
        }

        lihatStudio();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor studio yang ingin dihapus: "
                ) - 1;

        if (index < 0
                || index >= Studio.getDaftarStudio().size()) {

            view.pesan(
                    "Nomor studio tidak valid."
            );

            return;
        }

        if (view.konfirmasi(
                "Yakin ingin menghapus studio ini?"
        )) {

            Studio.hapus(index);

            view.pesan(
                    "Studio berhasil dihapus."
            );
        }
    }

    private void menuTiket() {

        boolean kembali = false;

        while (!kembali) {

            int pilihan =
                    view.menuCRUD("Tiket");

            switch (pilihan) {

                case 1:
                    tambahTiketAdmin();
                    break;

                case 2:
                    lihatTiket();
                    break;

                case 3:
                    updateTiket();
                    break;

                case 4:
                    hapusTiket();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambahTiketAdmin() {

        if (Film.getDaftarFilm().isEmpty()
                || Studio.getDaftarStudio().isEmpty()) {

            view.pesan(
                    "Film atau studio belum tersedia."
            );

            return;
        }

        String pembeli =
                view.bacaNama(
                        "Nama pembeli : "
                );

        int filmIndex =
                view.pilihFilm();

        Film film =
                Film.getDaftarFilm().get(filmIndex);

        int studioIndex =
                view.pilihStudio();

        Studio studio =
                Studio.getDaftarStudio().get(studioIndex);

        int jumlah =
                view.bacaIntPositif(
                        "Jumlah tiket : "
                );

        if (jumlah > studio.getKapasitas()) {

            view.pesan(
                    "Jumlah tiket melebihi kapasitas studio."
            );

            return;
        }

        Tiket tiket =
                new Tiket(
                        pembeli,
                        film,
                        studio,
                        jumlah
                );

        Tiket.tambah(tiket);

        System.out.println(
                "\n========== DETAIL TIKET =========="
        );

        System.out.println(
                "Film        : "
                + film.getJudul()
        );

        System.out.println(
                "Studio      : "
                + studio.getNama()
        );

        System.out.println(
                "Tipe Studio : "
                + studio.getTipe()
        );

        System.out.println(
                "Harga Tiket : Rp"
                + tiket.getHarga()
        );

        System.out.println(
                "Jumlah      : "
                + jumlah
        );

        System.out.println(
                "Total       : Rp"
                + tiket.getTotalHarga()
        );

        view.pesan(
                "Tiket berhasil ditambahkan."
        );
    }

    private void lihatTiket() {

        System.out.println(
                "\n========== DAFTAR TIKET =========="
        );

        if (Tiket.getDaftarTiket().isEmpty()) {

            view.pesan(
                    "Belum ada data tiket."
            );

            return;
        }

        for (int i = 0;
                i < Tiket.getDaftarTiket().size();
                i++) {

            view.tampilTiket(
                    Tiket.getDaftarTiket().get(i),
                    i + 1
            );
        }
    }

    private void updateTiket() {

        if (Tiket.getDaftarTiket().isEmpty()) {

            view.pesan(
                    "Data tiket belum tersedia."
            );

            return;
        }

        lihatTiket();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor tiket yang ingin diupdate: "
                ) - 1;

        if (index < 0
                || index >= Tiket.getDaftarTiket().size()) {

            view.pesan(
                    "Nomor tiket tidak valid."
            );

            return;
        }

        String pembeli =
                view.bacaNama(
                        "Nama pembeli baru : "
                );

        int filmIndex =
                view.pilihFilm();

        Film film =
                Film.getDaftarFilm().get(filmIndex);

        int studioIndex =
                view.pilihStudio();

        Studio studio =
                Studio.getDaftarStudio().get(studioIndex);

        int jumlah =
                view.bacaIntPositif(
                        "Jumlah tiket baru : "
                );

        if (jumlah > studio.getKapasitas()) {

            view.pesan(
                    "Jumlah tiket melebihi kapasitas studio."
            );

            return;
        }

        Tiket tiketBaru =
                new Tiket(
                        pembeli,
                        film,
                        studio,
                        jumlah
                );

        Tiket.update(
                index,
                tiketBaru
        );

        view.pesan(
                "Tiket berhasil diupdate."
        );
    }

    private void hapusTiket() {

        if (Tiket.getDaftarTiket().isEmpty()) {

            view.pesan(
                    "Data tiket belum tersedia."
            );

            return;
        }

        lihatTiket();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor tiket yang ingin dihapus: "
                ) - 1;

        if (index < 0
                || index >= Tiket.getDaftarTiket().size()) {

            view.pesan(
                    "Nomor tiket tidak valid."
            );

            return;
        }

        if (view.konfirmasi(
                "Yakin ingin menghapus tiket ini?"
        )) {

            Tiket.hapus(index);

            view.pesan(
                    "Tiket berhasil dihapus."
            );
        }
    }

    private void menuPenonton() {

        boolean kembali = false;

        while (!kembali) {

            int pilihan =
                    view.menuCRUD("Penonton");

            switch (pilihan) {

                case 1:
                    tambahPenontonAdmin();
                    break;

                case 2:
                    lihatPenonton();
                    break;

                case 3:
                    updatePenonton();
                    break;

                case 4:
                    hapusPenonton();
                    break;

                case 0:
                    kembali = true;
                    break;
            }
        }
    }

    private void tambahPenontonAdmin() {

        String nama =
                view.bacaNama("Nama : ");

        String email =
                view.bacaEmail("Email : ");

        String noHP =
                view.bacaNoHP("No HP : ");

        String password =
                view.bacaPassword("Password : ");

        for (Penonton penonton : daftarPenonton) {

            if (penonton.getEmail()
                    .equalsIgnoreCase(email)) {

                view.pesan(
                        "Email sudah terdaftar."
                );

                return;
            }
        }

        Penonton penonton =
                new Penonton(
                        nama,
                        email,
                        noHP,
                        password
                );

        daftarPenonton.add(penonton);

        view.pesan(
                "Penonton berhasil ditambahkan."
        );
    }

    private void lihatPenonton() {

        System.out.println(
                "\n========== DAFTAR PENONTON =========="
        );

        if (daftarPenonton.isEmpty()) {

            view.pesan(
                    "Belum ada data penonton."
            );

            return;
        }

        for (int i = 0;
                i < daftarPenonton.size();
                i++) {

            view.tampilPenontonData(
                    daftarPenonton.get(i),
                    i + 1
            );
        }
    }

    private void updatePenonton() {

        if (daftarPenonton.isEmpty()) {

            view.pesan(
                    "Data penonton belum tersedia."
            );

            return;
        }

        lihatPenonton();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor penonton yang ingin diupdate: "
                ) - 1;

        if (index < 0
                || index >= daftarPenonton.size()) {

            view.pesan(
                    "Nomor penonton tidak valid."
            );

            return;
        }

        Penonton penonton =
                daftarPenonton.get(index);

        String nama =
                view.bacaNama(
                        "Nama baru : "
                );

        String email =
                view.bacaEmail(
                        "Email baru : "
                );

        String noHP =
                view.bacaNoHP(
                        "No HP baru : "
                );

        String password =
                view.bacaPassword(
                        "Password baru : "
                );

        for (int i = 0;
                i < daftarPenonton.size();
                i++) {

            if (i != index
                    && daftarPenonton.get(i)
                    .getEmail()
                    .equalsIgnoreCase(email)) {

                view.pesan(
                        "Email sudah digunakan oleh penonton lain."
                );

                return;
            }
        }

        penonton.setNama(nama);
        penonton.setEmail(email);
        penonton.setNoHP(noHP);
        penonton.setPassword(password);

        view.pesan(
                "Data penonton berhasil diupdate."
        );
    }

    private void hapusPenonton() {

        if (daftarPenonton.isEmpty()) {

            view.pesan(
                    "Data penonton belum tersedia."
            );

            return;
        }

        lihatPenonton();

        int index =
                view.bacaIntPositif(
                        "Pilih nomor penonton yang ingin dihapus: "
                ) - 1;

        if (index < 0
                || index >= daftarPenonton.size()) {

            view.pesan(
                    "Nomor penonton tidak valid."
            );

            return;
        }

        if (view.konfirmasi(
                "Yakin ingin menghapus penonton ini?"
        )) {

            daftarPenonton.remove(index);

            view.pesan(
                    "Penonton berhasil dihapus."
            );
        }
    }

    private void lihatProfil(
            Penonton penonton) {

        System.out.println(
                "\n========== PROFIL PENONTON =========="
        );

        System.out.println(penonton);
    }

    private void beliTiket(
            Penonton penonton) {

        if (Film.getDaftarFilm().isEmpty()
                || Studio.getDaftarStudio().isEmpty()) {

            view.pesan(
                    "Film atau studio belum tersedia."
            );

            return;
        }

        System.out.println(
                "\n========== PEMBELIAN TIKET =========="
        );

        int filmIndex =
                view.pilihFilm();

        Film film =
                Film.getDaftarFilm().get(filmIndex);

        int studioIndex =
                view.pilihStudio();

        Studio studio =
                Studio.getDaftarStudio().get(studioIndex);

        int jumlah =
                view.bacaIntPositif(
                        "Jumlah tiket : "
                );

        if (jumlah > studio.getKapasitas()) {

            view.pesan(
                    "Jumlah tiket melebihi kapasitas studio."
            );

            return;
        }

        Tiket tiket =
                new Tiket(
                        penonton.getNama(),
                        film,
                        studio,
                        jumlah
                );

        Tiket.tambah(tiket);

        String id =
                "TRX-"
                + (Transaksi.getDaftarTransaksi().size() + 1);

        Transaksi transaksi =
                new Transaksi(
                        id,
                        penonton,
                        tiket
                );

        Transaksi.tambah(transaksi);

        System.out.println(
                "\n========== DETAIL PEMBELIAN =========="
        );

        System.out.println(
                "Film         : "
                + film.getJudul()
        );

        System.out.println(
                "Studio       : "
                + studio.getNama()
        );

        System.out.println(
                "Tipe Studio  : "
                + studio.getTipe()
        );

        System.out.println(
                "Harga Film   : Rp"
                + film.getHarga()
        );

        if (studio.getTipe()
                .equalsIgnoreCase("VIP")) {

            System.out.println(
                    "Tambahan VIP : Rp10000"
            );

        } else {

            System.out.println(
                    "Tambahan VIP : Rp0"
            );
        }

        System.out.println(
                "Harga Tiket  : Rp"
                + tiket.getHarga()
        );

        System.out.println(
                "Jumlah       : "
                + jumlah
        );

        System.out.println(
                "Total        : Rp"
                + tiket.getTotalHarga()
        );

        System.out.println(
                "ID Transaksi : "
                + id
        );

        view.pesan(
                "Pembelian berhasil."
        );
    }

    private void lihatTransaksi() {

        System.out.println(
                "\n========== RIWAYAT TRANSAKSI =========="
        );

        if (Transaksi.getDaftarTransaksi().isEmpty()) {

            view.pesan(
                    "Belum ada transaksi."
            );

            return;
        }

        for (int i = 0;
                i < Transaksi.getDaftarTransaksi().size();
                i++) {

            view.tampilTransaksi(
                    Transaksi.getDaftarTransaksi().get(i),
                    i + 1
            );
        }
    }

    private void lihatTransaksiPenonton(
            Penonton penonton) {

        System.out.println(
                "\n========== RIWAYAT TRANSAKSI =========="
        );

        boolean adaTransaksi = false;

        for (int i = 0;
                i < Transaksi.getDaftarTransaksi().size();
                i++) {

            Transaksi transaksi =
                    Transaksi.getDaftarTransaksi().get(i);

            if (transaksi.getPenonton() == penonton) {

                view.tampilTransaksi(
                        transaksi,
                        i + 1
                );

                adaTransaksi = true;
            }
        }

        if (!adaTransaksi) {

            view.pesan(
                    "Belum ada transaksi."
            );
        }
    }
}