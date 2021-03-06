package co.edu.unab.mgads.tinystore.view;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.FileProvider;
        import androidx.databinding.DataBindingUtil;
        import androidx.lifecycle.ViewModelProvider;

        import android.Manifest;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.zxing.integration.android.IntentIntegrator;
        import com.google.zxing.integration.android.IntentResult;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.nio.channels.FileChannel;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        import co.edu.unab.mgads.tinystore.R;
        import co.edu.unab.mgads.tinystore.databinding.ActivityProductFormBinding;
        import co.edu.unab.mgads.tinystore.model.Product;
        import co.edu.unab.mgads.tinystore.viewmodel.ProductFormViewModel;

public class ProductFormActivity extends AppCompatActivity {

    FloatingActionButton btn_load, btn_take;
    EditText et_barCode, et_name, et_price, et_description;
    int product_key;
    String imagePath ="", p_name="",  p_description="", p_barcode="";
    Double p_price =0.0;
    Boolean selectedGallery = true, productUpdate = false;

    Product myProduct;

    // String currentPhotoPath;
    ActivityProductFormBinding activityProductFormBinding;
    ProductFormViewModel viewModel;

    OutputStream outputStream;

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SELECT_A_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductFormBinding = DataBindingUtil.setContentView(ProductFormActivity.this, R.layout.activity_product_form);
        //button set
        btn_load = findViewById(R.id.bt_gallery);
        btn_take = findViewById(R.id.bt_camera);

        //edittext set

        et_barCode = findViewById(R.id.et_bar_code);
        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_description = findViewById(R.id.et_description);
        ImageView iv_photo = findViewById(R.id.iv_image);
        //  get values
        Bundle data = getIntent().getExtras();


            //new product
            viewModel = new ViewModelProvider(ProductFormActivity.this).get(ProductFormViewModel.class);
            activityProductFormBinding.setProduct(new Product());

        //camera
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA} , PackageManager.PERMISSION_GRANTED);
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                selectedGallery = false;
            }
        });

        //button to load image
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_A_PHOTO);

                selectedGallery =true;

            }
        });
    }

    public void insertNewProduct(View v) {
        Log.d("test", "ingreso a insert new");

        Log.d("test", "ingreso a new");
        if (selectedGallery = true) {
            createImageFromGallery();
        }
        Product product = activityProductFormBinding.getProduct();
        product.setPrice(Double.parseDouble(activityProductFormBinding.etPrice.getText().toString()));
        product.setImage(imagePath);
        product.setBarcode(et_barCode.getText().toString());
        //Toast.makeText(this, "foto: " + product.getImage().toString(), Toast.LENGTH_SHORT).show();
        Log.d("prueba", "nombre: " + product.getName());
        viewModel.insertProduct(product);


        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("lifeCycle", "ingreso result"  );
        ImageView iv_photo = findViewById(R.id.iv_image);

        //takes photo
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Glide.with(this).load(imagePath).into(iv_photo);
            imagePath = imagePath;
        }

        //from gallery
        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK) {
            Uri selectPhoto = data.getData();
            Glide.with(this).load(selectPhoto).into(iv_photo);
        }

        //scan barcode
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                et_barCode.setText("Codigo no scaneado");
            } else {
                et_barCode.setText(intentResult.getContents().toString());
            }
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

    private void createImageFromGallery(){
        //test image save to directory
        ImageView iv_photo = findViewById(R.id.iv_image);

        BitmapDrawable drawable = (BitmapDrawable) iv_photo.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        dir.mkdir();
        File file = new File(dir, System.currentTimeMillis()+".jpg");

        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        imagePath = file.toString();
    }

    public void scanBarcodeButton(View view){
        Log.d("lifeCycle", "ingreso al boton"  );
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();

    }
}




