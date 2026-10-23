package com.example.pertemuan4.data;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.pertemuan4.model.Mahasiswa;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MahasiswaRepository implements AkademikRepository {

    private final MahasiswaDao dao;
    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();
    private final Handler mainHandler =
            new Handler(Looper.getMainLooper());

    public MahasiswaRepository(MahasiswaDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<Mahasiswa>> getAll() {
        return dao.getAll();
    }

    @Override
    public void insert(Mahasiswa mahasiswa, Runnable onSuccess,
                       java.util.function.Consumer<String> onError) {
        executor.execute(() -> {
            try {
                dao.insert(mahasiswa);
                mainHandler.post(onSuccess);
            } catch (Exception e) {
                mainHandler.post(() ->
                        onError.accept("Gagal menyimpan: NIM mungkin duplikat.")
                );
            }
        });
    }

    @Override
    public void update(Mahasiswa mahasiswa, Runnable onSuccess,
                       java.util.function.Consumer<String> onError) {
        executor.execute(() -> {
            try {
                dao.update(mahasiswa);
                mainHandler.post(onSuccess);
            } catch (Exception e) {
                mainHandler.post(() ->
                        onError.accept("Gagal memperbarui data mahasiswa.")
                );
            }
        });
    }

    @Override
    public void delete(Mahasiswa mahasiswa, Runnable onSuccess,
                       java.util.function.Consumer<String> onError) {
        executor.execute(() -> {
            try {
                dao.delete(mahasiswa);
                mainHandler.post(onSuccess);
            } catch (Exception e) {
                mainHandler.post(() ->
                        onError.accept("Gagal menghapus data mahasiswa.")
                );
            }
        });
    }
}