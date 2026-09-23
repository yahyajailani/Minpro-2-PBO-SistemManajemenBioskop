### Nama : Yahya Jailani
### NIM : 2509116085
### Kelas : C

# Sistem Manajemen Bioskop

## Deskripsi Program

**Sistem Manajemen Bioskop** merupakan program berbasis Java yang digunakan untuk mengelola data film, studio, tiket, penonton, dan transaksi pembelian tiket.

Program memiliki dua jenis pengguna, yaitu **Admin** dan **Penonton**. Admin dapat mengelola data film, studio, tiket, dan penonton melalui fitur CRUD. Admin juga dapat melihat riwayat transaksi.

Penonton dapat melakukan registrasi dan login, melihat profil, melihat daftar film dan studio, membeli tiket, serta melihat riwayat transaksi.

Program juga dilengkapi dengan validasi input agar data yang dimasukkan sesuai dengan ketentuan sistem. Contohnya validasi nama, email, nomor HP, password, angka, pilihan menu, tipe studio, dan kapasitas studio.

Harga tiket ditentukan berdasarkan harga yang terdapat pada data film. Penonton tidak dapat memasukkan harga sendiri. Jika memilih studio bertipe **VIP**, sistem otomatis menambahkan biaya sebesar **Rp10.000**.

---

## Teknologi yang Digunakan

* Java
* Apache NetBeans
* Maven
* ArrayList
* Object-Oriented Programming (OOP)
* MVC (Model-View-Controller)

---

## Struktur Project

```text
src/main/java/com/mycompany/pertemuan1pbo/
│
├── Main.java
│
├── controller/
│   └── BioskopController.java
│
├── model/
│   ├── Pengguna.java
│   ├── Admin.java
│   ├── Penonton.java
│   ├── Film.java
│   ├── Studio.java
│   ├── Tiket.java
│   └── Transaksi.java
│
└── view/
    └── BioskopView.java
```

Struktur tersebut membagi program menjadi bagian **Model, View, dan Controller** sehingga setiap bagian memiliki fungsi masing-masing.

---

# Alur Program

## 1. Menu Utama

Saat program dijalankan, pengguna akan melihat menu:

```text
1. Login Admin
2. Login Penonton
3. Daftar Penonton
0. Keluar
```

Pengguna dapat memilih sesuai dengan kebutuhan.

---

## 2. Login Admin

Admin dapat login menggunakan akun bawaan:

```text
Email    : admin@cinematter.com
Password : admin123
```

Jika login berhasil, Admin masuk ke menu Admin.

```text
1. Data Film
2. Data Studio
3. Data Tiket
4. Data Penonton
5. Riwayat Transaksi
0. Logout
```

Admin dapat melakukan CRUD pada data yang tersedia.

---

## 3. Registrasi Penonton

Penonton yang belum mempunyai akun dapat melakukan registrasi dengan mengisi:

* Nama
* Email
* Password

Setiap data akan divalidasi sebelum disimpan.

Contohnya:

```text
Nama : Yahya123
```

akan ditolak karena nama hanya boleh berisi huruf dan spasi.

Email juga diperiksa agar mengikuti format yang sesuai.

---

## 4. Login Penonton

Setelah melakukan registrasi, penonton dapat login menggunakan email dan password.

Jika berhasil, penonton akan masuk ke menu:

```text
1. Lihat Film
2. Lihat Studio
3. Beli Tiket
4. Riwayat Transaksi
0. Logout
```

---

## 5. Pembelian Tiket

Pada proses pembelian tiket, penonton memilih:

1. Film
2. Studio
3. Jumlah tiket

Harga tiket tidak dimasukkan oleh penonton karena sudah berasal dari data film.

Contohnya:

```text
Harga Film = Rp50.000
```

Jika memilih:

```text
Studio Regular
```

maka harga tiket:

```text
Rp50.000
```

Sedangkan jika memilih:

```text
Studio VIP
```

maka sistem otomatis menghitung:

```text
Rp50.000 + Rp10.000 = Rp60.000
```

Jika jumlah tiket melebihi kapasitas studio, transaksi akan ditolak.

Setelah berhasil, sistem membuat ID transaksi dan menyimpan data transaksi ke dalam `ArrayList`.

---

# Penerapan Konsep OOP

## Encapsulation

Encapsulation diterapkan dengan menggunakan atribut `private` pada class.

Contohnya pada class `Film`:

```java
private String judul;
private String genre;
private int durasi;
private double harga;
```

Atribut tersebut tidak dapat diakses secara langsung dari class lain. Untuk mengakses atau mengubah data digunakan getter dan setter.

Contohnya:

```java
public String getJudul() {
    return judul;
}

public void setJudul(String judul) {
    this.judul = judul;
}
```

Konsep ini juga diterapkan pada class seperti `Pengguna`, `Admin`, `Penonton`, `Studio`, `Tiket`, dan `Transaksi`.

Encapsulation membuat data dalam objek lebih terkontrol dan tidak dapat diubah secara langsung tanpa melalui method yang disediakan.

---

## Inheritance

Inheritance diterapkan pada hubungan antara class `Pengguna`, `Admin`, dan `Penonton`.

`Pengguna` digunakan sebagai class induk:

```java
public abstract class Pengguna
```

Kemudian `Admin` dan `Penonton` mewarisi class tersebut:

```java
public class Admin extends Pengguna
```

```java
public class Penonton extends Pengguna
```

Atribut umum seperti:

```java
nama
email
```

diletakkan pada class `Pengguna`.

Dengan inheritance, class `Admin` dan `Penonton` dapat menggunakan atribut dan method yang berasal dari class `Pengguna` tanpa perlu membuatnya kembali.

---

# Nilai Tambah Program

## 1. MVC (Model View Controller)

Program menggunakan struktur **MVC** yang membagi program menjadi tiga bagian.

### Model

Model digunakan untuk menyimpan data dan objek program.

Contohnya:

* `Pengguna`
* `Admin`
* `Penonton`
* `Film`
* `Studio`
* `Tiket`
* `Transaksi`

### View

View terdapat pada:

```text
BioskopView.java
```

View bertugas menangani tampilan program dan input dari pengguna, seperti menu, input data, serta validasi input.

### Controller

Controller terdapat pada:

```text
BioskopController.java
```

Controller bertugas mengatur alur program dan menghubungkan View dengan Model.

Contohnya ketika penonton membeli tiket, Controller mengambil data film dan studio, memproses jumlah tiket, membuat objek `Tiket`, kemudian membuat objek `Transaksi`.

Dengan MVC, kode menjadi lebih terstruktur karena tampilan, data, dan proses program dipisahkan.

---

## 2. Polymorphism dan Method Overriding

Polymorphism diterapkan melalui **method overriding**.

Pada class `Pengguna` terdapat method:

```java
public abstract String getRole();
```

Method tersebut kemudian dioverride oleh class `Admin`:

```java
@Override
public String getRole() {
    return "Admin";
}
```

Sedangkan pada class `Penonton`:

```java
@Override
public String getRole() {
    return "Penonton";
}
```

Nama method yang digunakan sama, yaitu `getRole()`, tetapi hasilnya berbeda berdasarkan class yang mengimplementasikannya.

Method `toString()` juga dioverride pada beberapa class seperti `Admin`, `Penonton`, `Film`, `Studio`, `Tiket`, dan `Transaksi`.

---

## 3. Method Overloading

Pada program terdapat beberapa method yang menangani input berdasarkan jenis datanya, seperti:

```java
bacaString()
bacaInt()
bacaDouble()
```

Method tersebut digunakan untuk menangani kebutuhan input yang berbeda.

Namun, secara aturan Java, method-method tersebut **belum termasuk method overloading** karena nama method-nya berbeda. Method overloading harus memiliki nama method yang sama tetapi parameter yang berbeda.

Jadi, penerapan yang benar-benar terlihat pada program adalah **method overriding**, sedangkan method overloading belum menjadi bagian utama dari kode program.

---

# Fitur Program

Program memiliki beberapa fitur utama:

* Login Admin
* Login Penonton
* Registrasi Penonton
* CRUD Film
* CRUD Studio
* CRUD Tiket
* CRUD Penonton
* Lihat Profil Penonton
* Pembelian Tiket
* Perhitungan harga tiket otomatis
* Tambahan harga VIP Rp10.000
* Validasi nama
* Validasi email
* Validasi password
* Validasi angka
* Validasi pilihan menu
* Validasi tipe studio
* Validasi kapasitas studio
* Riwayat transaksi
* ArrayList untuk penyimpanan data

---

# Contoh Validasi

### Validasi Nama

```text
Nama : Yahya Jailani
```

Output:

```text
Nama hanya boleh berisi huruf dan spasi.
```

### Validasi Email

```text
Email : yahya@gmail
```

Output:

```text
Format email tidak valid.
```

### Validasi Harga

```text
Harga tiket film : Rpabc
```

Output:

```text
Harga harus berupa angka.
```

### Validasi Tipe Studio

```text
Tipe : Gold
```

Output:

```text
Tipe studio hanya Regular atau VIP.
```

Tipe studio yang diperbolehkan:

```text
Regular
VIP
```

### Validasi Kapasitas Tiket

Jika kapasitas studio adalah 100 orang:

```text
Jumlah tiket : 101
```

maka sistem akan menolak karena jumlah tiket melebihi kapasitas studio.

---

# Kesimpulan

Sistem Manajemen Bioskop merupakan program Java yang menerapkan konsep dasar Pemrograman Berorientasi Objek dalam sebuah sistem pengelolaan bioskop.

Konsep **Encapsulation** diterapkan melalui penggunaan atribut `private` dan getter/setter. **Inheritance** diterapkan melalui class `Pengguna` sebagai induk dari `Admin` dan `Penonton`. **Polymorphism** diterapkan melalui method overriding seperti `getRole()` dan `toString()`.

Selain konsep OOP, program juga menggunakan struktur **MVC**, CRUD, `ArrayList`, validasi input, login dan registrasi, pembelian tiket, perhitungan harga otomatis, serta riwayat transaksi.

Struktur tersebut membuat program lebih terorganisir dan setiap bagian memiliki fungsi yang jelas.

