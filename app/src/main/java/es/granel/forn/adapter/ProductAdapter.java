package es.granel.forn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.granel.forn.R;
import es.granel.forn.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private OnItemClickListener listener;

    List<Product> listData;
    LayoutInflater inflater;
    Context context;

    public ProductAdapter(List<Product> listData, Context context, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.listData = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Init Inflater.
        inflater = LayoutInflater.from(parent.getContext());
        // Inflate View items.
        View listItemView = inflater.inflate(R.layout.list_row_product, parent, false);
        // Init ViewHolder with View.
        return new ProductViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.render(listData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getAdapterPosition());
                //Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
