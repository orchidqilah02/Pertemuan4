# PERTANYAAN REFLEKSI

## 1. Apa yang terjadi jika constructor kosong di Entity Room dihapus?

Jika constructor kosong di Entity Room dihapus, Room dapat mengalami kesulitan membuat objek Entity ketika membaca data dari database, terutama jika constructor yang tersisa tidak sesuai dengan kebutuhan Room. Akibatnya, proses kompilasi atau pengambilan data dapat gagal. Constructor kosong membantu Room membuat objek sebelum mengisi nilai dari database.

## 2. Mengapa ViewModel tidak boleh meng-import `android.widget.TextView`? Hubungkan dengan prinsip Abstraction.

ViewModel sebaiknya tidak meng-import `android.widget.TextView` karena ViewModel bertugas mengelola data dan logika tampilan, bukan mengatur komponen antarmuka secara langsung. Hal ini sesuai dengan prinsip Abstraction, yaitu memisahkan pengelolaan data dan logika aplikasi dari detail implementasi antarmuka. Dengan begitu, ViewModel tidak bergantung pada Activity atau komponen UI tertentu dan lebih mudah diuji serta digunakan kembali.

## 3. Berikan satu contoh nyata di studi kasus ini ketika Polymorphism menghemat baris kode.

Pada aplikasi MyKampus, objek Mahasiswa dan Dosen sama-sama dapat diperlakukan sebagai turunan dari kelas `Person`. Adapter dapat menggunakan referensi `Person` untuk menampilkan data keduanya, kemudian memanggil metode seperti `getRole()` dan `getStatusAkademik()` sesuai objek yang digunakan. Dengan Polymorphism, kode penampilan data dapat digunakan kembali tanpa perlu membuat seluruh proses tampilan secara terpisah untuk Mahasiswa dan Dosen.