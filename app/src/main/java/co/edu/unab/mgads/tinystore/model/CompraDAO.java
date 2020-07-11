package co.edu.unab.mgads.tinystore.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompraDAO {

    @Insert
    void insert(Compra compra);

    @Query("select *from compras")
    LiveData<List<Compra>> getALLbyKey();

}
