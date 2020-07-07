package co.edu.unab.mgads.tiendapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import co.edu.unab.mgads.tiendapp.databinding.ActivityProductDetailBinding;
import co.edu.unab.mgads.tiendapp.model.Product;
import co.edu.unab.mgads.tiendapp.R;
import co.edu.unab.mgads.tiendapp.viewmodel.ProductDetailViewModel;
import co.edu.unab.mgads.tiendapp.viewmodel.ProductListViewModel;
//import co.edu.unab.mgads.tiendapp.databinding.ActivityProductDetailBindingBinding;

public class ProductDetailActivity extends AppCompatActivity  {

    private ActivityProductDetailBinding mainBinding;
    private ClickHandlers clickHandlers;
    private Product myProduct;
    private ProductDetailViewModel productDetailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_product_detail);
        mainBinding = DataBindingUtil.setContentView(ProductDetailActivity.this, R.layout.activity_product_detail);
        int keyMyProduct =  getIntent().getIntExtra("product", 0);
        productDetailViewModel = new ViewModelProvider(ProductDetailActivity.this).get(ProductDetailViewModel.class);

        productDetailViewModel.getProductData(keyMyProduct).observe(ProductDetailActivity.this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                if(product!=null){
                    myProduct = product;
                    mainBinding.setTitle(getString(R.string.detalle_del_producto, product.getName()));
                    mainBinding.setProduct(product);
                }
                }

        });


       //setTitle(getString(R.string.detalle_del_producto, myProduct.getName()));

      //  mainBinding.tvTitle.setText(getString(R.string.detalle_del_producto, myProduct.getName()));

        clickHandlers = new ClickHandlers(ProductDetailActivity.this);
        mainBinding.setClickHandlers(clickHandlers);
        mainBinding.setProduct(myProduct);



        Log.d("lifeCycle", "Method create");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifeCycle", "Method restart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifeCycle", "Method start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifeCycle", "Method resume");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifeCycle", "Method Pause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifeCycle", "Method Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifeCycle", "Method Destroy");
    }


    public class ClickHandlers extends ProductDetailActivity {

        Context context;

        public ClickHandlers(Context context){
            this.context = context;
        }

        public void editClickButton(View v){
            Toast.makeText(ProductDetailActivity.this, "OnClick Editar", Toast.LENGTH_SHORT).show();

        }

        public void deleteClickButton(View v){
            productDetailViewModel.deleteProduct(myProduct);
            ProductDetailActivity.this.finish();
        }

        public void backClickButton(View v){
            ProductDetailActivity.this.finish();

        }
    }

    public void getProduct(){
        myProduct = new Product("PC", 1500.00, "PC ASUS i9");
    }
}