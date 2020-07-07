package co.edu.unab.mgads.tinystore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityProductFormBinding;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ProductFormActivity extends AppCompatActivity {

    ActivityProductFormBinding activityProductFormBinding;
    ProductFormViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(ProductFormActivity.this).get(ProductFormViewModel.class);

        activityProductFormBinding = DataBindingUtil.setContentView(ProductFormActivity.this, R.layout.activity_product_form);

        activityProductFormBinding.setProduct(new Product());
    }

    public  void insertNewProduct(View v){
        Product product = activityProductFormBinding.getProduct();
        product.setPrice(Double.parseDouble(activityProductFormBinding.etPrice.getText().toString()));
        viewModel.insertProduct(product);
        finish();
    }



}