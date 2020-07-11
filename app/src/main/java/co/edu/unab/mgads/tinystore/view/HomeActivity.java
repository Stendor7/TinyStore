package co.edu.unab.mgads.tinystore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding activityHomeBinding;
    private ClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home);
        activityHomeBinding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);

        clickHandlers = new ClickHandlers(HomeActivity.this);
        activityHomeBinding.setClickHandlers(clickHandlers);

        setTitle("Inicio");

    }

    public class ClickHandlers  {
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void productsClickButton(View v){
            Intent intent = new Intent(context, ProductListActivity.class);
            startActivity(intent);
        }

        public void registerClickButton(View v){
            Intent intent = new Intent(context, RegisterUserActivity.class);
            startActivity(intent);
        }

        public void purchaseClickButton(View v){
            Intent intent = new Intent(context, ScanProductActivity.class);
            startActivity(intent);
        }
    }
}