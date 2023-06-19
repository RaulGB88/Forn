package es.granel.forn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.granel.forn.dao.ClientDAO;
import es.granel.forn.model.Client;

public class LoginActivity extends AppCompatActivity {


    ClientDAO clientDAO = new ClientDAO();

    List<Client> listClient;

    TextView txtDNI;
    TextView txtPass;

    Intent pageReturned;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @SuppressLint("ResourceAsColor")
    public void checkLogin (View view) {

        txtDNI = (TextView) findViewById(R.id.etDNI);
        String dni = (String) txtDNI.getText().toString();

        txtPass = (TextView) findViewById(R.id.etPass);
        String pass = (String) txtPass.getText().toString();

        Client cliente = new Client();
        cliente.setNif(dni);
        cliente.setPassword(pass);

        Intent pageReturned = new Intent(LoginActivity.this, MainActivity.class);

        // Get all clients.
        ClientDAO clienteDAO = new ClientDAO();
        List<Client> listClient = clienteDAO.getAll();

        boolean clientChecked = false;

        // Check Client.
        for (Client c : listClient) {
            if (cliente.getNif().equalsIgnoreCase(c.getNif()) && cliente.getPassword().equalsIgnoreCase(c.getPassword())) {
                pageReturned = new Intent(LoginActivity.this, MainActivity.class);
                clientChecked = true;

                // Pasamos el usuario logeqado al siguiente Activity (LogedActivity)
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", c);
                startActivity(intent);

            }
        }

        if (!clientChecked) {
            TextView txtLoginWrong = (TextView) findViewById(R.id.loginIncorrecto);
            txtLoginWrong.setText(R.string.loginError);
            txtLoginWrong.setTextColor(R.color.red);
        }
    }


    public Client checkClient(Client client) {

        Client clientReturned = null;

        // Check Client.
        for (Client c : listClient) {
            if (client.getNif().equalsIgnoreCase(c.getNif()) && client.getPassword().equalsIgnoreCase(c.getPassword())) {
                pageReturned = new Intent(LoginActivity.this, MainActivity.class);
                clientReturned = (Client) clientDAO.search(client);

                // Pasamos el usuario logeqado al siguiente Activity (LogedActivity)
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", c);
                startActivity(intent);
            }
        }

        return clientReturned;
    }

}