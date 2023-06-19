package es.granel.forn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.granel.forn.adapter.OnItemClickListener;
import es.granel.forn.adapter.ProductAdapter;
import es.granel.forn.dao.ProductDAO;
import es.granel.forn.db.MyDBOperational;
import es.granel.forn.model.Client;
import es.granel.forn.model.Product;
import es.granel.forn.utility.Constants;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    ProductDAO productDAO = new ProductDAO();

    List<Product> listData;

    ProductAdapter adapter;

    RecyclerView rvView;

    Client clientLoged;

    TextView txtdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Client loged (in session).
        clientLoged = (Client) getIntent().getSerializableExtra("user");

        // Clean
        txtdatos = (TextView) findViewById(R.id.etUser);
        if(clientLoged == null) {
            txtdatos.append("");
        } else {
            txtdatos.setText(clientLoged.getName());
        }

        // Charge BD.
        MyDBOperational mbo = MyDBOperational.getInstance(this);

        // 1. Get list from Dao (Dao List).
        listData = productDAO.getAll();
        // 2. Instance Adapter and put list into it (adapter(list)).
        initRecyclerView(listData);
    }

    public void initRecyclerView(List<Product> listData) {
        // Adapter
        adapter = new ProductAdapter(listData, this, this);
        // RecyclerView
        rvView = (RecyclerView) findViewById(R.id.rvList);
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(adapter);
    }

    public void goHome(View view) {

    }

    public void buy(View view){
        Intent intent;
        if(clientLoged == null) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            // Stock -1 in DAO, and reload MainActiviy.
            intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if(item.getItemId() == Constants.MENU_MAIN) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
/*                i.putExtra(Constantes.C_MODO, Constantes.C_CREAR);
                startActivityForResult(i, Constantes.C_CREAR);*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        Product item = listData.get(position);

        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}