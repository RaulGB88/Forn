package es.granel.forn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import es.granel.forn.dao.ProductDAO;
import es.granel.forn.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView et5;
    private Button btnBuy;
    private Button btnMain;

    ProductDAO productDAO = new ProductDAO();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Product product = (Product) getIntent().getSerializableExtra("item");
        if(product != null) {
            render(product);
        }

        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnMain = (Button) findViewById(R.id.btnMain);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                buy(product);
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goMain(product);
            }
        });
    }

    private void buy(Product item) {
        Toast.makeText(this, String.valueOf("Producto comprado...: " + item.getName() + " - " + item.getPrice()), Toast.LENGTH_SHORT).show();

        et5 = (TextView) findViewById(R.id.etNumber);
        int number = Integer.parseInt(et5.getText().toString());

        item.setStock(item.getStock()-number);
        productDAO.update(item);

        Intent intent = new Intent(ProductDetailActivity.this, ProductDetailActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
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

    public void goMain(Product item) {
        Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
        //intent.putExtra("item", item);
        startActivity(intent);
    }

}