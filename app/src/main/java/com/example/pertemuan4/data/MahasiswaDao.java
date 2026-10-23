package com.example.pertemuan4.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pertemuan4.model.Mahasiswa;
import java.util.List;

@Dao
public interface MahasiswaDao {

    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    LiveData<List<Mahasiswa>> getAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Mahasiswa mahasiswa);

    @Update
    void update(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);
}