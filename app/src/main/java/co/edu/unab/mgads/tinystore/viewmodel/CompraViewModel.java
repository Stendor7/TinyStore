package co.edu.unab.mgads.tinystore.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import co.edu.unab.mgads.tinystore.model.Compra;
import co.edu.unab.mgads.tinystore.model.CompraRepository;
import co.edu.unab.mgads.tinystore.model.ProductRepository;

public class CompraViewModel extends AndroidViewModel {

    private CompraRepository compraRepository;

    public CompraViewModel(@NonNull Application application) {
        super(application);
        compraRepository = new CompraRepository(application);
    }

    public void insertCompra(Compra compra){
        Log.d("test", "comprakey: "+  compra.getProduct_key() );

        compraRepository.insert(compra);
    }
}
