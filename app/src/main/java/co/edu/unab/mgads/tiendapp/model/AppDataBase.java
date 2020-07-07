package co.edu.unab.mgads.tiendapp.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version=1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ProductDAO productDAO();
    private static  AppDataBase instance;

    public static AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context,
                    AppDataBase.class,
                    "tiendapp.db"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

}