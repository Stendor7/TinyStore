package co.edu.unab.mgads.tinystore.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CompraRepository {

    CompraDAO compraDAO;

    public CompraRepository (Context context){
        AppDataBase dataBase = AppDataBase.getInstance(context);
        compraDAO = dataBase.compraDAO();
    }

//    public LiveData<List<Product>> getAll(){
//        return compraDAO.getALL();
//    }

    public void insert(Compra compra){
        compraDAO.insert(compra);
    }

//    public void update(Compra compra){
//        compraDAO.update(compra);
//    }
//
//    public void delete(Compra compra){
//        compraDAO.delete(compra);
//    }



}
