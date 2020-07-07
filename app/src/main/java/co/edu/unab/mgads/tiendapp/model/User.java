package co.edu.unab.mgads.tiendapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class User {
    String name;
    String document;
    String email;
    String UrlPhoto;



    public User(String name, String email, String urlPhoto, String document) {
        this.name = name;
        this.document = document;
        this.email = email;
        UrlPhoto = urlPhoto;
    }

    @BindingAdapter({"urlPhoto"})
    public static void loadPhoto(ImageView imageview, String urlPhoto){
        Glide.with(imageview.getContext()).load(urlPhoto).into(imageview);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlPhoto() {
        return UrlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        UrlPhoto = urlPhoto;
    }
}
