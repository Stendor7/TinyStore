package co.edu.unab.mgads.tinystore.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityScanProductBinding;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.model.ProductDAO;
import co.edu.unab.mgads.tinystore.viewmodel.ProductDetailViewModel;
import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ScanProductActivity extends AppCompatActivity {


    private Product myProduct;
    private ProductDetailViewModel productDetailViewModel;
    private String imageUrl;


    ActivityScanProductBinding activityBinding;
    ProductFormViewModel viewModel;

     TextView tv_name, et_barcode, tv_price;
     ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(ScanProductActivity.this, R.layout.activity_scan_product);

        et_barcode = findViewById(R.id.et_bar_code);
        tv_name = findViewById(R.id.et_name);
        tv_price = findViewById(R.id.tv_price);
        iv_image = findViewById(R.id.iv_image);

        //call from scanned
        productDetailViewModel = new ViewModelProvider(ScanProductActivity.this).get(ProductDetailViewModel.class);




    }

    public void scanBarcodeButton(View view){
        Log.d("lifeCycle", "ingreso al boton"  );
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("lifeCycle", "ingreso result"  );
        ImageView iv_photo = findViewById(R.id.iv_image);


        //scan barcode
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                et_barcode.setText("Codigo no scaneado");
            } else {
                et_barcode.setText(intentResult.getContents().toString());

                String scannedMyProduct = et_barcode.getText().toString();

                Log.d("test", "Scanned: "+ scannedMyProduct);

              Product myProduct = new Product();
              myProduct =  productDetailViewModel.getProductDataScanned(scannedMyProduct);

Log.d("test", "nombre" +myProduct.getName());

                activityBinding.setProduct(myProduct);
                viewModel = new ViewModelProvider(ScanProductActivity.this).get(ProductFormViewModel.class);

                if(myProduct!= null) {
                    Log.d("test", "entro y no es null el producto");
                   // tv_name.setText(myProduct.getName().toString());
//                    tv_price.setText(String.valueOf(myProduct.getPrice()));
//                    Glide.with(ScanProductActivity.this)
//                          .load(imageUrl)
//                            .placeholder(R.drawable.progress_circle)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .into(iv_image);
                }
                else{
                    Toast.makeText(this, "No exite codigo", Toast.LENGTH_SHORT).show();
                }

//                productDetailViewModel.getProductDataScanned(ScannedMyProduct).observe(ScanProductActivity.this, new Observer<Product>() {
//                    @Override
//                    public void onChanged(Product product) {
//                        Log.d("test", "entro onchange: " );
//                        if (product != null) {
//                            Log.d("test", "product not null: ");
//                            myProduct = product;
//
//                            activityBinding.setProduct(product);
//                            imageUrl = product.getImage();
//
//                            ImageView imageView = findViewById(R.id.iv_image);
//                            Glide.with(ScanProductActivity.this)
//                                    .load(imageUrl)
////                            .placeholder(R.drawable.progress_circle)
////                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                    .into(imageView);
//
//
//                            Log.d("test", "name: "+ myProduct.getName().toString());
//
//                            tv_name.setText(myProduct.getName());
//                        }
//                    }
//                });

            }
        }

    }
}