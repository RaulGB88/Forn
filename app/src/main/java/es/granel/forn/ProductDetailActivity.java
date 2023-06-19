package es.granel.forn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import es.granel.forn.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Product product = (Product) getIntent().getSerializableExtra("item");
        if(product != null) {
            render(product);
        }
    }

    public void render(Product item) {
        // 1. Get TextView rows / atributtes (Cast to TextView).
        iv1 = (ImageView) findViewById(R.id.ivImage);
        tv1 = (TextView) findViewById(R.id.tvName);
        tv2 = (TextView) findViewById(R.id.tvDescription);
        tv3 = (TextView) findViewById(R.id.tvPrice);
        tv4 = (TextView) findViewById(R.id.tvStock);

        // 2. Set the object atributtes to TextView (Set toi TextView).
        iv1.setImageResource(item.getImage());
        tv1.setText(String.valueOf(item.getId()));
        tv2.setText(String.valueOf(item.getName()));
        tv3.setText(String.valueOf(item.getStock()));
        tv4.setText(String.valueOf(item.getPrice()));
    }

}