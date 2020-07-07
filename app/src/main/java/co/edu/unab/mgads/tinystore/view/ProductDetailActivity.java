package co.edu.unab.mgads.tinystore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import co.edu.unab.mgads.tinystore.databinding.ActivityProductDetailBinding;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.viewmodel.ProductDetailViewModel;
//import co.edu.unab.mgads.tinystore.databinding.ActivityProductDetailBindingBinding;

public class ProductDetailActivity extends AppCompatActivity  {

    private ActivityProductDetailBinding mainBinding;
    private ClickHandlers clickHandlers;
    private Product myProduct;
    private ProductDetailViewModel productDetailViewModel;
    private String imageUrl;


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
                    imageUrl = product.getImage();

                    ImageView imageView = findViewById(R.id.iv_image);
                    Glide.with(ProductDetailActivity.this)
                            .load(imageUrl)
                            .placeholder(R.drawable.progress_circle)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(imageView);

                    Log.d("imagen", "url: "+ imageUrl);
                }
                }

        });


       //setTitle(getString(R.string.detalle_del_producto, myProduct.getName()));

      //  mainBinding.tvTitle.setText(getString(R.string.detalle_del_producto, myProduct.getName()));

        clickHandlers = new ClickHandlers(ProductDetailActivity.this);
        mainBinding.setClickHandlers(clickHandlers);
        mainBinding.setProduct(myProduct);




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


    public class ClickHandlers {

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
        myProduct = new Product("PC", 1500.00, "https://www.publicdomainpictures.net/pictures/90000/nahled/camera-icon.jpg" , "product Description");
    }
}