package co.edu.unab.mgads.tinystore.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Compra.class}, version=1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ProductDAO productDAO();
    public abstract CompraDAO compraDAO();
    private static  AppDataBase instance;

    public static AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context,
                    AppDataBase.class,
                    "tinyStore.db"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

}
