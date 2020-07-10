package co.edu.unab.mgads.tinystore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.model.ProductRepository;

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
        for(int i =0; i<3; i++)
        {
            Product temp = new Product("Producto-"+(i+1), 2500.00+(i+1), "https://i.imgur.com/qpr5LR2.jpg", "12345" + (i+1) , "PC Asus 17" + (i+1) );
            productRepository.insert(temp);
        }
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
//        setProductList();
    }
}
