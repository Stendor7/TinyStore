package co.edu.unab.mgads.tinystore.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityProductFormBinding;
import co.edu.unab.mgads.tinystore.model.Product;
import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ProductFormActivity extends AppCompatActivity {

    FloatingActionButton btn_load, btn_take;
    String imagePath;
   // String currentPhotoPath;
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

        btn_load = findViewById(R.id.bt_gallery);
        btn_take = findViewById(R.id.bt_camera);

        //camera
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        //button to load image
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_A_PHOTO);
            }
        });
    }

    public void insertNewProduct(View v) {
        Product product = activityProductFormBinding.getProduct();
        product.setPrice(Double.parseDouble(activityProductFormBinding.etPrice.getText().toString()));
        product.setImage(imagePath);
        //Toast.makeText(this, "foto: " + product.getImage().toString(), Toast.LENGTH_SHORT).show();
        viewModel.insertProduct(product);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView iv_photo;
        iv_photo = findViewById(R.id.iv_image);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
           Glide.with(this).load(imagePath).into(iv_photo);
            imagePath = imagePath;
        }

        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK) {
            Uri selectPhoto = data.getData();

            Glide.with(this).load(selectPhoto).into(iv_photo);

            imagePath = selectPhoto.toString();

//            TextView tv_message = findViewById(R.id.et_description);
//            tv_message.setText(imagePath);

        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(this, "No se pudo guardar la foto.", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "co.edu.unab.mgads.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        imagePath = image.getAbsolutePath();
        return image;
    }
}