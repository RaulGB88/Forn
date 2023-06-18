package es.granel.forn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import es.granel.forn.db.MyDBOperational;
import es.granel.forn.utility.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Clean
        TextView txtdatos = (TextView) findViewById(R.id.etUser);
        txtdatos.append("");

        // Charge BD.
        MyDBOperational mbo = MyDBOperational.getInstance(this);
    }

    public void goHome(View view) {

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

}