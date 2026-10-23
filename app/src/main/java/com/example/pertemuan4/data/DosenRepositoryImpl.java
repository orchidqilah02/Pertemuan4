package com.example.pertemuan4.data;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.pertemuan4.model.Dosen;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DosenRepositoryImpl implements DosenRepository {

    private final DosenDao dao;
    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public DosenRepositoryImpl(DosenDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<Dosen>> getAll() {
        return dao.getAll();
    }

    @Override
    public void insert(Dosen dosen, Runnable success,
                       java.util.function.Consumer<String> error) {
        executor.execute(() -> {
            try {
                dao.insert(dosen);
                handler.post(success);
            } catch (Exception e) {
                handler.post(() ->
                        error.accept("Gagal menyimpan dosen."));
            }
        });
    }

    @Override
    public void update(Dosen dosen, Runnable success,
                       java.util.function.Consumer<String> error) {
        executor.execute(() -> {
            try {
                dao.update(dosen);
                handler.post(success);
            } catch (Exception e) {
                handler.post(() ->
                        error.accept("Gagal memperbarui dosen."));
            }
        });
    }

    @Override
    public void delete(Dosen dosen, Runnable success,
                       java.util.function.Consumer<String> error) {
        executor.execute(() -> {
            try {
                dao.delete(dosen);
                handler.post(success);
            } catch (Exception e) {
                handler.post(() ->
                        error.accept("Gagal menghapus dosen."));
            }
        });
    }
}