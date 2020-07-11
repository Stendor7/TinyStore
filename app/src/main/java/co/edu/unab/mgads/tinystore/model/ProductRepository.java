package co.edu.unab.mgads.tinystore.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository  {


    ProductDAO productDAO;

    public ProductRepository(Context context) {
       AppDataBase dataBase = AppDataBase.getInstance(context);
       productDAO = dataBase.productDAO();
    }

    public LiveData<List<Product>> getAll(){
        return productDAO.getALL();
    }

    public void insert(Product product){
        productDAO.insert(product);
    }

    public void update(Product product){
        productDAO.update(product);
    }

   public void delete(Product product){
        productDAO.delete(product);
   }

   public LiveData<Product> getByKey(int key){
        return productDAO.getByKey(key);
   }

    public Product getByScanned(String barcode){
        Log.d("test", "en repository code: " + barcode);
        return productDAO.getByScanned(barcode);
    }
}
