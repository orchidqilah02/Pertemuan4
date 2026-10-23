
package com.example.pertemuan4.model;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;

@Entity(tableName = "dosen")
public class Dosen extends Person implements EvaluasiAkademik {

    @ColumnInfo(name = "nidn")
    private String nidn;

    @ColumnInfo(name = "mata_kuliah_diampu")
    private String mataKuliahDiampu;

    public Dosen() {
        super();
    }

    @Ignore
    public Dosen(String nama, String email, String nidn,
                 String mataKuliahDiampu) {
        super(nama, email);
        setNidn(nidn);
        setMataKuliahDiampu(mataKuliahDiampu);
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn == null ? "" : nidn.trim();
    }

    public String getMataKuliahDiampu() {
        return mataKuliahDiampu;
    }

    public void setMataKuliahDiampu(String mataKuliahDiampu) {
        this.mataKuliahDiampu =
                mataKuliahDiampu == null ? "" : mataKuliahDiampu.trim();
    }

    @Override
    public String getRole() {
        return "Dosen";
    }

    @Override
    public String getStatusAkademik() {
        if (mataKuliahDiampu == null ||
                mataKuliahDiampu.trim().isEmpty()) {
            return "Normal";
        }

        int jumlah = 0;
        for (String mataKuliah : mataKuliahDiampu.split(",")) {
            if (!mataKuliah.trim().isEmpty()) {
                jumlah++;
            }
        }

        return jumlah > 3 ? "Beban Lebih" : "Normal";
    }
}
