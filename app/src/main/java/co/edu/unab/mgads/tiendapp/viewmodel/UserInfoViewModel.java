package co.edu.unab.mgads.tiendapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import co.edu.unab.mgads.tiendapp.model.User;

public class UserInfoViewModel extends AndroidViewModel {

    private MutableLiveData<User> user;

    public UserInfoViewModel(@NonNull Application application) {
        super(application);
        user =  new MutableLiveData<>();
        setUser();
    }

    public  LiveData<User> getUser(){
        return user;
    }

    public void setUser() {
       User tempUser = new User("Steven Tellez" + Math.floor(Math.random()*10), "steve.tellez@gmail.com" , "https://www.pngitem.com/pimgs/m/78-786420_icono-usuario-joven-transparent-user-png-png-download.png", "Document.docx");
       user.setValue(tempUser);
    }

}
