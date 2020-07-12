package co.edu.unab.mgads.tinystore.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unab.mgads.tinystore.R;
import co.edu.unab.mgads.tinystore.databinding.CompraItemBinding;
import co.edu.unab.mgads.tinystore.model.Compra;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraViewHolder> {

    private List<Compra> compraList;
    private OnItemClickListener onItemClickListener;

    public CompraAdapter(List<Compra> compraList) {
        this.compraList = compraList;
        notifyDataSetChanged();
    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CompraItemBinding compraItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.compra_item,
                parent,
                false);
        return new CompraAdapter.CompraViewHolder(compraItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CompraViewHolder holder, int position) {
        Compra compra = compraList.get(position);
        holder.bind(compra);
    }



    @Override
    public int getItemCount() {
        return this.compraList.size();
    }

    public class CompraViewHolder extends RecyclerView.ViewHolder{

        private CompraItemBinding compraItemBinding;

        public CompraViewHolder(@NonNull CompraItemBinding itemView) {
            super(itemView.getRoot());
            compraItemBinding = itemView;
        }

        public void bind(final Compra compra) {
            compraItemBinding.setCompra(compra);
            if (onItemClickListener != null) {
                compraItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(compra, getAdapterPosition());
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Compra compra, int position);
    }
}