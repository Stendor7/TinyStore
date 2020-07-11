package co.edu.unab.mgads.tinystore.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityScanProductBinding;
import co.edu.unab.mgads.tinystore.model.Compra;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.model.ProductDAO;
import co.edu.unab.mgads.tinystore.viewmodel.CompraViewModel;
import co.edu.unab.mgads.tinystore.viewmodel.ProductDetailViewModel;
import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ScanProductActivity extends AppCompatActivity {



    private Product myProduct;
    private Compra myCompra;
    private ProductDetailViewModel productDetailViewModel;
    private String new_purchase_date, identification ;

    CompraViewModel compraViewModel;

    String curentDate;

    ActivityScanProductBinding activityBinding;
    ProductFormViewModel viewModel;

     TextView tv_name, et_barcode, tv_price;
     EditText et_quantity;
     ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(ScanProductActivity.this, R.layout.activity_scan_product);

        et_barcode = findViewById(R.id.et_bar_code);
        tv_name = findViewById(R.id.et_name);
        tv_price = findViewById(R.id.tv_price);
        et_quantity = findViewById(R.id.et_quantity);
        iv_image = findViewById(R.id.iv_image);

        myCompra = new Compra();

        Bundle data = getIntent().getExtras();
        if(data!=null){
            new_purchase_date = data.getString("new_purchase_date");
            identification = data.getString("id");
        }



        //call from scanned
        productDetailViewModel = new ViewModelProvider(ScanProductActivity.this).get(ProductDetailViewModel.class);

        SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        curentDate =  dateTime.format(new Date());


    }

    public void scanBarcodeButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv_photo = findViewById(R.id.iv_image);


        //scan barcode
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                et_barcode.setText("Codigo no scaneado");
            } else {
                et_barcode.setText(intentResult.getContents().toString());

                String scannedMyProduct = et_barcode.getText().toString();

                myProduct = productDetailViewModel.getProductDataScanned(scannedMyProduct);

                activityBinding.setProduct(myProduct);
                viewModel = new ViewModelProvider(ScanProductActivity.this).get(ProductFormViewModel.class);

                Glide.with(ScanProductActivity.this)
                        .load(myProduct.getImage())
                        .placeholder(R.drawable.progress_circle)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(iv_image);

            }
        }

    }



    public void insertNewPurchase(View view) {


        int quantity = Integer.parseInt(et_quantity.getText().toString());
        Double total = quantity * (Double.parseDouble(tv_price.getText().toString()));

        myCompra.setProduct_key(myProduct.getKey());
        myCompra.setBuyer_identification(identification);
        myCompra.setDate_purchased(new_purchase_date);
        myCompra.setPrice(myProduct.getPrice());
        myCompra.setQuantity(quantity);
        myCompra.setTotal(total);

        compraViewModel = new ViewModelProvider(ScanProductActivity.this).get(CompraViewModel.class);
        compraViewModel.insertCompra(myCompra);
        Toast.makeText(this, "Producto agregado:  " + myProduct.getName() + "   cantidad: "+ myCompra.getQuantity(), Toast.LENGTH_SHORT).show();
//        Log.d("test", "comprakey: "+  myCompra.getProduct_key() + " total: " + myCompra.getTotal() + " quanitity: " + myCompra.getQuantity() +" iduser: " + myCompra.getBuyer_identification() +" date " + myCompra.getDate_purchased());
    }
}