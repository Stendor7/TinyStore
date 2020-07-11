package co.edu.unab.mgads.tinystore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityNewPurchaseBinding;
import co.edu.unab.mgads.tinystore.model.Compra;

public class NewPurchaseActivity extends AppCompatActivity {

    ActivityNewPurchaseBinding activityBinding;

    TextView et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(NewPurchaseActivity.this, R.layout.activity_new_purchase);

        et_id = findViewById(R.id.et_identification);
    }



    public void newPurchase(View view) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String date = simpleDateFormat.format(new Date());

        Intent intent  = new Intent(NewPurchaseActivity.this, ScanProductActivity.class);
        intent.putExtra("new_purchase_date", date);
        intent.putExtra("id", et_id.getText().toString());

        startActivity(intent);
    }
}