package co.edu.unab.mgads.tiendapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "products")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int Key;
    String name;
    Double  price;
    String  priceString;
    @ColumnInfo(name = "description")
    String description;
    String image;

    public Product() {

    }

    public Product(String name, Double price, String image,String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }

    public String getPriceString(){
        return  "$" + this.price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
