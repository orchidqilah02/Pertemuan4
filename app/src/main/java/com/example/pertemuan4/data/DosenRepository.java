package com.example.pertemuan4.data;

import androidx.lifecycle.LiveData;
import com.example.pertemuan4.model.Dosen;
import java.util.List;
import java.util.function.Consumer;

public interface DosenRepository {
    LiveData<List<Dosen>> getAll();

    void insert(Dosen dosen, Runnable onSuccess,
                Consumer<String> onError);

    void update(Dosen dosen, Runnable onSuccess,
                Consumer<String> onError);

    void delete(Dosen dosen, Runnable onSuccess,
                Consumer<String> onError);
}