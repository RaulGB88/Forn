package es.granel.forn.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import es.granel.forn.db.MyDB;
import es.granel.forn.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class ClientDAO implements IPojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Client c = (Client) obj;
        contentValues.put("nif", c.getNif());
        contentValues.put("nombre", c.getName());
        contentValues.put("apellidos", c.getSurname());
        contentValues.put("claveSeguridad", c.getPassword());
        contentValues.put("email", c.getEmail());
        return MyDB.getDB().insert("Clients", null, contentValues);
    }

    @Override
    public int update(Object obj) {

        ContentValues contentValues = new ContentValues();
        Client c = (Client) obj;
        contentValues.put("nif", c.getNif());
        contentValues.put("nombre", c.getName());
        contentValues.put("apellidos", c.getSurname());
        contentValues.put("claveSeguridad", c.getPassword());
        contentValues.put("email", c.getEmail());

        String condicion = "id=" + String.valueOf(c.getId());

        int resultado = MyDB.getDB().update("Clients", contentValues, condicion, null);

        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Client c = (Client) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el Client indicado en el campo de texto
        MyDB.getDB().delete("Clients", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Client c = (Client) obj;
        String condicion = "";
        if (TextUtils.isEmpty(c.getNif())) {
            condicion = "id=" + String.valueOf(c.getId());
        } else {
            condicion = "nif=" + "'" + c.getNif() + "'";
        }

        String[] columnas = {
                "id", "nif", "nombre", "apellidos", "claveseguridad", "email"
        };
        Cursor cursor = MyDB.getDB().query("Clients", columnas, condicion, null, null, null, null);
        Client nuevoClient = null;
        if (cursor.moveToFirst()) {
            nuevoClient = new Client();
            nuevoClient.setId(cursor.getInt(0));
            nuevoClient.setNif(cursor.getString(1));
            nuevoClient.setName(cursor.getString(2));
            nuevoClient.setSurname(cursor.getString(3));
            nuevoClient.setPassword(cursor.getString(4));
            nuevoClient.setEmail(cursor.getString(5));

            // Obtenemos la lista de Products que tiene el Client
            //c.setListaProducts(MyDB.getInstance(null).getProductDAO().getProducts(c));


        }
        return nuevoClient;
    }

    @Override
    public List<Client> getAll() {
        List<Client> listaClients = new ArrayList<>();
        String[] columnas = {
                "id", "nif", "nombre", "apellidos", "claveseguridad", "email"
        };
        Cursor cursor = MyDB.getDB().query("Clients", columnas, null, null, null, null, null);
        ProductDAO ProductDAO = new ProductDAO();
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Client c = new Client();
                c.setId(cursor.getInt(0));
                c.setNif(cursor.getString(1));
                c.setName(cursor.getString(2));
                c.setSurname(cursor.getString(3));
                c.setPassword(cursor.getString(4));
                c.setEmail(cursor.getString(5));
                //c.setListaProducts(MyDB.getInstance(null).getProductDAO().getProducts(c));
                listaClients.add(c);

            } while (cursor.moveToNext());
        }
        return listaClients;
    }

}
