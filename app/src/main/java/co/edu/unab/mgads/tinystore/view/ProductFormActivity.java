package co.edu.unab.mgads.tinystore.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityProductFormBinding;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ProductFormActivity extends AppCompatActivity {

    FloatingActionButton btn_load, btn_take;
    String imagePath;
    ActivityProductFormBinding activityProductFormBinding;
    ProductFormViewModel viewModel;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SELECT_A_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        viewModel = new ViewModelProvider(ProductFormActivity.this).get(ProductFormViewModel.class);

        activityProductFormBinding = DataBindingUtil.setContentView(ProductFormActivity.this, R.layout.activity_product_form);

        activityProductFormBinding.setProduct(new Product());

        btn_load =  findViewById(R.id.bt_gallery);

        //button to load image
        btn_load.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_A_PHOTO);
            }
        });
    }

    public  void insertNewProduct(View v){
        Product product = activityProductFormBinding.getProduct();
        product.setPrice(Double.parseDouble(activityProductFormBinding.etPrice.getText().toString()));
       product.setImage(imagePath);
        viewModel.insertProduct(product);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv_photo;
        iv_photo = findViewById(R.id.iv_image);

        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK){
            Uri selectPhoto = data.getData();

            Glide.with(this).load(selectPhoto).into(iv_photo);

            imagePath = selectPhoto.toString();

//            TextView tv_message = findViewById(R.id.et_description);
//            tv_message.setText(imagePath);

        }
    }
}