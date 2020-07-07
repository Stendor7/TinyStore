package co.edu.unab.mgads.tiendapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import co.edu.unab.mgads.tiendapp.model.Product;
import co.edu.unab.mgads.tiendapp.model.ProductRepository;

public class ProductDetailViewModel extends AndroidViewModel {

//    private MutableLiveData<Product> productData;
    private ProductRepository productRepository;

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
//        productData = new MutableLiveData<>();
        productRepository = new ProductRepository(application);
    }


    public LiveData<Product> getProductData(int key) {
//        setProductData(key);
        return productRepository.getByKey(key);
    }

//    public void setProductData(int key) {
//       Product temp = productRepository.getByKey(key);
//       productData.setValue(temp);
//    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
}
