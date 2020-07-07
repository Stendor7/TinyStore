package co.edu.unab.mgads.tiendapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unab.mgads.tiendapp.R;
import co.edu.unab.mgads.tiendapp.databinding.ProductItemBinding;
import co.edu.unab.mgads.tiendapp.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item,
                parent,
                false);
        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return this.productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ProductItemBinding productItemBinding;

        public ProductViewHolder(@NonNull ProductItemBinding itemView) {
            super(itemView.getRoot());
            productItemBinding = itemView;
        }

        public void bind(final Product product){
            productItemBinding.setProduct(product);
            if(onItemClickListener!=null){
                productItemBinding.getRoot().setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void  onClick(View v){
                        onItemClickListener.onItemClick(product, getAdapterPosition() );
                    }
                });
            }
        }


    }

    public interface OnItemClickListener{
        void onItemClick(Product product, int position);
    }
}
