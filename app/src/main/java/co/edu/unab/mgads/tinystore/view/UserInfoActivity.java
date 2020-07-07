package co.edu.unab.mgads.tinystore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.model.User;
import co.edu.unab.mgads.tinystore.viewmodel.UserInfoViewModel;
import co.edu.unab.mgads.tinystore.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends AppCompatActivity {

private ActivityUserInfoBinding userInfoBinding;
    private User user;
    private ClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userInfoBinding = DataBindingUtil.setContentView(UserInfoActivity.this, R.layout.activity_user_info);

        UserInfoViewModel viewModel = new ViewModelProvider(UserInfoActivity.this).get(UserInfoViewModel.class);

        clickHandlers = new ClickHandlers(UserInfoActivity.this);
        userInfoBinding.setClickHandlers(clickHandlers);

        setTitle("Informacion usuario");

viewModel.getUser().observe(UserInfoActivity.this, new Observer<User>() {
    @Override
    public void onChanged(User user) {
        userInfoBinding.setUser(user);
    }
});
        userInfoBinding.setViewModel(viewModel);
        userInfoBinding.setUser(user);


    }

    public void updateUser(View v){
        userInfoBinding.getViewModel().setUser();
    }

    public class ClickHandlers {

        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void backClickButton(View v){
            finish();
        }


    }

}