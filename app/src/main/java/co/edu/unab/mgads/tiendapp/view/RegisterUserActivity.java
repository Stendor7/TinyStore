package co.edu.unab.mgads.tiendapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.edu.unab.mgads.tiendapp.R;
import co.edu.unab.mgads.tiendapp.databinding.ActivityRegisterUserBinding;

public class RegisterUserActivity extends AppCompatActivity {

    private ActivityRegisterUserBinding registerUserBinding;
    private ClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerUserBinding = DataBindingUtil.setContentView(RegisterUserActivity.this, R.layout.activity_register_user);

        clickHandlers = new ClickHandlers(RegisterUserActivity.this);
        registerUserBinding.setClickHandlers(clickHandlers);
        setTitle("Registro de usuario");
    }

    public class ClickHandlers  {
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public  void registerClick(View v){
            Toast.makeText(RegisterUserActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, UserInfoActivity.class);
            startActivity(intent);
        }

        public  void backClickButton(View v){
            finish();
        }



    }
}