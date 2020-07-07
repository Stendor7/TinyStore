package co.edu.unab.mgads.tinystore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.model.ProductRepository;

public class ProductFormViewModel extends AndroidViewModel {
    private ProductRepository productRepository;

    public ProductFormViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public void insertProduct(Product product){
        productRepository.insert(product);
    }

}
