package co.edu.unab.mgads.tiendapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.mgads.tiendapp.R;
import co.edu.unab.mgads.tiendapp.databinding.ActivityProductListBinding;
import co.edu.unab.mgads.tiendapp.model.Product;
import co.edu.unab.mgads.tiendapp.viewmodel.ProductListViewModel;

public class ProductListActivity extends AppCompatActivity {

    private ActivityProductListBinding productListBinding;
    private ClickHandlers clickHandlers;
    private ProductAdapter adapter;
    private ProductListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ViewModelProvider(ProductListActivity.this).get(ProductListViewModel.class);

        clickHandlers = new ClickHandlers(ProductListActivity.this);
        productListBinding = DataBindingUtil.setContentView(ProductListActivity.this, R.layout.activity_product_list);
        productListBinding.setClickHandlers(clickHandlers);
        setTitle("Listado de productos");


        adapter = new ProductAdapter(new ArrayList<Product>());

        RecyclerView recyclerView = productListBinding.rvProducts;
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product, int position) {
                Toast.makeText(ProductListActivity.this, "Seleccionado: "+ product.getName(), Toast.LENGTH_SHORT).show();
                //viewModel.deleteProduct(product);
                Intent intent  = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                intent.putExtra("product", product.getKey());
                startActivity(intent);
            }
        });

        viewModel.getProductList().observe(ProductListActivity.this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if(products.size()==0){
                    viewModel.setFakeData();
                }
                adapter.setProductList(products);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifeCycle", "Method resume");
//        viewModel.setProductList();

    }

    public class ClickHandlers  {

        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }



        public void addClick(View v){
            Intent intent = new Intent(context, ProductFormActivity.class);
            startActivity(intent);
        }

        public void backClickButton(View v){
            finish();
        }


    }
}