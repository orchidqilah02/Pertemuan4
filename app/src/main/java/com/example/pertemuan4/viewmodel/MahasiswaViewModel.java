package com.example.pertemuan4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pertemuan4.data.AkademikRepository;
import com.example.pertemuan4.data.MahasiswaRepository;
import com.example.pertemuan4.data.MyKampusDatabase;
import com.example.pertemuan4.model.Mahasiswa;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {

    private final AkademikRepository repository;
    private final LiveData<List<Mahasiswa>> semuaMahasiswa;
    private final MutableLiveData<String> pesanError = new MutableLiveData<>();

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);

        MyKampusDatabase db =
                MyKampusDatabase.getInstance(application);

        repository = new MahasiswaRepository(db.mahasiswaDao());
        semuaMahasiswa = repository.getAll();
    }

    public LiveData<List<Mahasiswa>> getSemuaMahasiswa() {
        return semuaMahasiswa;
    }

    public LiveData<String> getPesanError() {
        return pesanError;
    }

    public void simpan(String nama, String email, String nim,
                       String jurusan, String ipkText) {
        try {
            double ipk = Double.parseDouble(ipkText);

            Mahasiswa mahasiswa = new Mahasiswa(
                    nama, email, nim, jurusan, ipk
            );

            repository.insert(mahasiswa,
                    () -> pesanError.setValue("Data mahasiswa berhasil disimpan."),
                    pesanError::setValue
            );

        } catch (NumberFormatException e) {
            pesanError.setValue("IPK harus berupa angka.");
        } catch (IllegalArgumentException e) {
            pesanError.setValue(e.getMessage());
        }
    }

    public void hapus(Mahasiswa mahasiswa) {
        repository.delete(mahasiswa,
                () -> pesanError.setValue("Data berhasil dihapus."),
                pesanError::setValue
        );
    }

    public void edit(Mahasiswa mahasiswa, String nama, String email,
                     String nim, String jurusan, String ipkText) {
        try {
            mahasiswa.setNama(nama);
            mahasiswa.setEmail(email);
            mahasiswa.setNim(nim);
            mahasiswa.setJurusan(jurusan);
            mahasiswa.setIpk(Double.parseDouble(ipkText));
            mahasiswa.setUpdatedAt(System.currentTimeMillis());

            repository.update(mahasiswa,
                    () -> pesanError.setValue("Data berhasil diperbarui."),
                    pesanError::setValue
            );

        } catch (NumberFormatException e) {
            pesanError.setValue("IPK harus berupa angka.");
        } catch (IllegalArgumentException e) {
            pesanError.setValue(e.getMessage());
        }
    }
}