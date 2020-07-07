package co.edu.unab.mgads.tiendapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import co.edu.unab.mgads.tiendapp.model.Product;
import co.edu.unab.mgads.tiendapp.model.ProductRepository;

public class ProductListViewModel extends AndroidViewModel {

//    private MutableLiveData<List<Product>> productList;
    private ProductRepository productRepository;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
//        productList = new MutableLiveData<>();
        productRepository = new ProductRepository(application);
//        setProductList();
    }

    public LiveData<List<Product>> getProductList() {
        return productRepository.getAll();
    }



    public void setFakeData(){
        for(int i =0; i<10; i++)
        {
            Product temp = new Product("Producto"+(i+1), 2500.00+(i+1), "PC Asus 17" + (i+1));
            productRepository.insert(temp);
        }
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
//        setProductList();
    }
}
