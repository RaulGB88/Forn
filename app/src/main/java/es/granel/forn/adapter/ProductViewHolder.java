package es.granel.forn.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.granel.forn.R;
import es.granel.forn.model.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;

    ImageView iv1;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    // Constructor
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        // 4. Get TextView rows / atributtes (Cast to TextView).
        iv1 = (ImageView) itemView.findViewById(R.id.ivImage);
        tv1 = (TextView) itemView.findViewById(R.id.tvName);
        tv2 = (TextView) itemView.findViewById(R.id.tvDescription);
        tv3 = (TextView) itemView.findViewById(R.id.tvPrice);
        tv4 = (TextView) itemView.findViewById(R.id.tvStock);
    }

    public void render(Product product) {

        // 6. Set the object atributtes to TextView (Set toi TextView).
        iv1.setImageResource(product.getImage());
        tv1.setText(String.valueOf(product.getId()));
        tv2.setText(String.valueOf(product.getName()));
        tv3.setText(String.valueOf(product.getStock()));
        tv4.setText(String.valueOf(product.getPrice()));
    }

}
