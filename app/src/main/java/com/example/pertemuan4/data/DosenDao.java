package com.example.pertemuan4.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.pertemuan4.model.Dosen;
import java.util.List;

@Dao
public interface DosenDao {

    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    LiveData<List<Dosen>> getAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Dosen dosen);

    @Update
    void update(Dosen dosen);

    @Delete
    void delete(Dosen dosen);
}