package co.edu.unab.mgads.tiendapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import co.edu.unab.mgads.tiendapp.R;
import co.edu.unab.mgads.tiendapp.databinding.ActivityProductFormBinding;
import co.edu.unab.mgads.tiendapp.model.Product;
import co.edu.unab.mgads.tiendapp.viewmodel.ProductFormViewModel;

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