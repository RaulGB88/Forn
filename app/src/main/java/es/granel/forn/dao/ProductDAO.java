package es.granel.forn.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;

import es.granel.forn.R;
import es.granel.forn.db.ImageDB;
import es.granel.forn.db.MyDB;
import es.granel.forn.model.Client;
import es.granel.forn.model.Product;
import es.granel.forn.utility.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.List;

public class ProductDAO implements IPojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Product c = (Product) obj;

        contentValues.put("nombre", c.getName());
        contentValues.put("precio", c.getPrice());
        contentValues.put("stock", c.getStock());
        return MyDB.getDB().insert("Products", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Product c = (Product) obj;
        contentValues.put("nombre", c.getName());
        contentValues.put("precio", c.getPrice());
        contentValues.put("stock", c.getStock());
        contentValues.put("idClient", c.getClient().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MyDB.getDB().update("Products", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Product c = (Product) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el Product indicado en el campo de texto
        MyDB.getDB().delete("Products", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Product c = (Product) obj;
        String condicion = "";
        if (TextUtils.isEmpty(c.getName())) {
            condicion = "id=" + String.valueOf(c.getId());
        }

        String[] columnas = {
                "id", "nombre", "precio", "stock", "idClient"
        };
        Cursor cursor = MyDB.getDB().query("Products", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setPrice(cursor.getFloat(2));
            c.setStock(cursor.getInt(3));

            // Obtenemos el Client y lo asignamos
            Client a = new Client();
            a.setId(cursor.getInt(6));
            a = (Client) MyDB.getInstance(null).getClientDAO().search(a);
            c.setClient(a);

            // Obtenemos la lista de Movements y los asignamos
            //c.setListaMovements(MyDB.getInstance(null).getMovementDAO().getMovements(c));

            return c;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> listaProducts = new ArrayList<>();
        String[] columnas = {
                "id", "nombre", "precio", "stock"
        };
        Cursor cursor = MyDB.getDB().query("Products", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Product c = new Product();
                c.setId(cursor.getInt(0));
                c.setName(cursor.getString(1));
                c.setPrice(cursor.getFloat(2));
                c.setStock(cursor.getInt(3));
                c.setImage(checkImage(c).getImage());

                // Obtenemos el Client y lo asignamos
                /*
                Client a = new Client();
                a.setId(cursor.getInt(6));
                a = (Client) MyDB.getInstance(null).getClientDAO().search(a);
                c.setClient(a);

                // Obtenemos la lista de Movements y los asignamos
                //c.setListaMovements(MyDB.getInstance(null).getMovementDAO().getMovements(c));
                */
                listaProducts.add(c);

            } while (cursor.moveToNext());
        }
        return listaProducts;
    }

    public List<Product> getProducts(Client Client) {
        List<Product> listaProducts = new ArrayList<>();
        String condicion = "idClient=" + String.valueOf(Client.getId());
        String[] columnas = {
                "id", "nombre", "precio", "dc", "numeroProduct", "saldoactual", "idClient"
        };
        Cursor cursor = MyDB.getDB().query("Products", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Product c = new Product();
                c.setId(cursor.getInt(0));
                c.setName(cursor.getString(1));
                c.setPrice(cursor.getFloat(2));
                c.setStock(cursor.getInt(3));
                c.setImage(checkImage(c).getImage());

                c.setClient(Client);

                // Obtenemos la lista de Movements y los asignamos
                //c.setListaMovements(MyDB.getInstance(null).getMovementDAO().getMovements(c));

                listaProducts.add(c);

            } while (cursor.moveToNext());
        }
        return listaProducts;
    }

    public Product checkImage(Product itemEntry) {
        List<Integer> listDB = ImageDB.initImages();
        Product itemExit = new Product();
        for(int i : listDB) {
            if(i == R.drawable.cake && itemEntry.getName().equalsIgnoreCase(Constants.CAKE)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.croissant && itemEntry.getName().equalsIgnoreCase(Constants.CROISSANT)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.pan && itemEntry.getName().equalsIgnoreCase(Constants.PAN)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.muffin && itemEntry.getName().equalsIgnoreCase(Constants.MUFFIN)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.pepito_pan && itemEntry.getName().equalsIgnoreCase(Constants.PEPITO)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.pepito_pan && itemEntry.getName().equalsIgnoreCase(Constants.PAN_PUEBLO)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.pepito_pan && itemEntry.getName().equalsIgnoreCase(Constants.PAN_CLASICO)) {
                itemExit.setImage(i);
            }
            if(i == R.drawable.pepito_pan && itemEntry.getName().equalsIgnoreCase(Constants.PAN_INTEGRAL)) {
                itemExit.setImage(i);
            }

        }
        return itemExit;
    }

}
