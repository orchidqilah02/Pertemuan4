
package com.example.pertemuan4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pertemuan4.data.DosenRepository;
import com.example.pertemuan4.data.DosenRepositoryImpl;
import com.example.pertemuan4.data.MyKampusDatabase;
import com.example.pertemuan4.model.Dosen;

import java.util.List;

public class DosenViewModel extends AndroidViewModel {

    private final DosenRepository repository;
    private final LiveData<List<Dosen>> semuaDosen;
    private final MutableLiveData<String> pesan = new MutableLiveData<>();

    public DosenViewModel(@NonNull Application application) {
        super(application);

        repository = new DosenRepositoryImpl(
                MyKampusDatabase.getInstance(application).dosenDao());

        semuaDosen = repository.getAll();
    }

    public LiveData<List<Dosen>> getSemuaDosen() {
        return semuaDosen;
    }

    public LiveData<String> getPesan() {
        return pesan;
    }

    public void simpan(String nama, String email, String nidn,
                       String mataKuliah) {
        try {
            Dosen dosen = new Dosen(nama, email, nidn, mataKuliah);

            repository.insert(dosen,
                    () -> pesan.setValue("Data dosen berhasil disimpan."),
                    pesan::setValue);

        } catch (IllegalArgumentException e) {
            pesan.setValue(e.getMessage());
        }
    }

    public void edit(Dosen dosen, String nama, String email,
                     String nidn, String mataKuliah) {
        try {
            dosen.setNama(nama);
            dosen.setEmail(email);
            dosen.setNidn(nidn);
            dosen.setMataKuliahDiampu(mataKuliah);
            dosen.setUpdatedAt(System.currentTimeMillis());

            repository.update(dosen,
                    () -> pesan.setValue("Data dosen berhasil diperbarui."),
                    pesan::setValue);

        } catch (IllegalArgumentException e) {
            pesan.setValue(e.getMessage());
        }
    }

    public void hapus(Dosen dosen) {
        repository.delete(dosen,
                () -> pesan.setValue("Data dosen berhasil dihapus."),
                pesan::setValue);
    }
}
