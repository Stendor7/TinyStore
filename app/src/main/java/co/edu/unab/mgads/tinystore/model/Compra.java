package co.edu.unab.mgads.tinystore.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "compras")
public class Compra implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int Key;
    int product_key;
    String buyer_identification;
    String date_purchased;
    Double  price;
    int  quantity;
    Double total;


    public Compra() {

    }

    public Compra( int product_key, String buyer_identification, String date_purchased, Double price, int quantity, Double total) {
        this.product_key = product_key;
        this.buyer_identification = buyer_identification;
        this.date_purchased = date_purchased;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }

    public int getProduct_key() {
        return product_key;
    }

    public void setProduct_key(int product_key) {
        this.product_key = product_key;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBuyer_identification() {
        return buyer_identification;
    }

    public void setBuyer_identification(String buyer_identification) {
        this.buyer_identification = buyer_identification;
    }

    public String getDate_purchased() {
        return date_purchased;
    }

    public void setDate_purchased(String date_purchased) {
        this.date_purchased = date_purchased;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
