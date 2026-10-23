package com.example.pertemuan4.data;

import androidx.lifecycle.LiveData;
import com.example.pertemuan4.model.Mahasiswa;
import java.util.List;

public interface AkademikRepository {

    LiveData<List<Mahasiswa>> getAll();

    void insert(Mahasiswa mahasiswa, Runnable onSuccess,
                java.util.function.Consumer<String> onError);

    void update(Mahasiswa mahasiswa, Runnable onSuccess,
                java.util.function.Consumer<String> onError);

    void delete(Mahasiswa mahasiswa, Runnable onSuccess,
                java.util.function.Consumer<String> onError);
}