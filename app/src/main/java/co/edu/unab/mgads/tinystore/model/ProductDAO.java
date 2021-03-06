package co.edu.unab.mgads.tinystore.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Query("select *from products")
    LiveData<List<Product>> getALL();

    @Query("SELECT * FROM products WHERE `Key`=:key")
    LiveData<Product> getByKey(int key);

    @Query(("SELECT * FROM products WHERE barcode =:barcode"))
    Product getByScanned(String barcode);


    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);
}
