
package com.example.pertemuan4.model;
import androidx.room.Ignore;
import androidx.room.Entity;
import androidx.room.ColumnInfo;

import androidx.room.Index;

@Entity(
        tableName = "mahasiswa",
        indices = {@Index(value = {"nim"}, unique = true)}
)
public class Mahasiswa extends Person implements EvaluasiAkademik {

    @ColumnInfo(name = "nim")
    private String nim;

    @ColumnInfo(name = "jurusan")
    private String jurusan;

    @ColumnInfo(name = "ipk")
    private double ipk;

    public Mahasiswa() {
        super();
    }

    @Ignore
    public Mahasiswa(String nama, String email,
                     String nim, String jurusan, double ipk) {
        super(nama, email);
        setNim(nim);
        setJurusan(jurusan);
        setIpk(ipk);
    }


    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        if (nim == null || !nim.matches("[0-9]{10}")) {
            throw new IllegalArgumentException(
                    "NIM harus terdiri dari 10 digit angka."
            );
        }
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan == null ? "" : jurusan.trim();
    }

    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        if (!Double.isFinite(ipk) || ipk < 0.0 || ipk > 4.0) {
            throw new IllegalArgumentException(
                    "IPK harus berada pada rentang 0.0 sampai 4.0."
            );
        }
        this.ipk = ipk;
    }

    @Override
    public String getRole() {
        return "Mahasiswa";
    }

    @Override
    public String getStatusAkademik() {
        if (ipk > 3.5) return "Cumlaude";
        if (ipk > 3.0) return "Memuaskan";
        if (ipk < 2.0) return "Peringatan";
        return "Cukup";
    }
}
