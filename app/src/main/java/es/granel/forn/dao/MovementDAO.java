package es.granel.forn.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.List;

import es.granel.forn.db.MyDB;
import es.granel.forn.model.Movement;
import es.granel.forn.model.Product;

public class MovementDAO implements IPojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movement c = (Movement) obj;
        contentValues.put("tipo", c.getType());
        contentValues.put("fechaoperacion", c.getDate().getTime());
        contentValues.put("descripcion", c.getDescription());
        contentValues.put("importe", c.getAmount());
        contentValues.put("idProductrigen", c.getProductrigin().getId());
        contentValues.put("idProductdestino", c.getProductDestination().getId());

        return MyDB.getDB().insert("Movements", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Movement c = (Movement) obj;
        contentValues.put("tipo", c.getType());
        contentValues.put("fechaoperacion", c.getDate().getTime());
        contentValues.put("descripcion", c.getDescription());
        contentValues.put("importe", c.getAmount());
        contentValues.put("idProductrigen", c.getProductrigin().getId());
        contentValues.put("idProductdestino", c.getProductDestination().getId());

        String condicion = "id=" + String.valueOf(c.getId());

        return MyDB.getDB().update("Movements", contentValues, condicion, null);
    }

    @Override
    public void delete(Object obj) {
        Movement c = (Movement) obj;
        String condicion = "id=" + String.valueOf(c.getId());

        //Se borra el Product indicado en el campo de texto
        MyDB.getDB().delete("Movements", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Movement c = (Movement) obj;
        String condicion = "id=" + String.valueOf(c.getId());
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idProductrigen", "idProductdestino"
        };
        Cursor cursor = MyDB.getDB().query("Movements", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0));
            c.setType(cursor.getInt(1));
            c.setDate(new Date(cursor.getLong(2)));
            c.setDescription(cursor.getString(3));
            c.setAmount(cursor.getFloat(4));

            // Asignamos la Product de origen
            Product a = new Product();
            a.setId(cursor.getInt(5));
            c.setProductrigin((Product) MyDB.getInstance(null).getProductDAO().search(a));

            // Asignamos la Product de destino
            int aux = cursor.getInt(6);
            if (aux == -1) {
                a.setId(-1);
            } else {
                a.setId(aux);
                c.setProductrigin((Product) MyDB.getInstance(null).getProductDAO().search(a));
            }

            return c;
        } else {
            return null;
        }
    }

    @Override
    public List getAll() {
        List<Movement> listaMovements = new ArrayList<>();
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idProductrigen", "idProductdestino"
        };
        Cursor cursor = MyDB.getDB().query("Movements", columnas, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movement c = new Movement();
                c.setId(cursor.getInt(0));
                c.setType(cursor.getInt(1));
                c.setDate(new Date(cursor.getLong(2)));
                c.setDescription(cursor.getString(3));
                c.setAmount(cursor.getFloat(4));

                // Asignamos la Product de origen
                Product a = new Product();
                a.setId(cursor.getInt(5));
                c.setProductrigin((Product) MyDB.getInstance(null).getProductDAO().search(a));


                // Asignamos la Product de destino
                a = new Product();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductrigin(a);
                } else {
                    a.setId(aux);
                    c.setProductDestination((Product) MyDB.getInstance(null).getProductDAO().search(a));
                }


                listaMovements.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovements;
    }

    public List getMovements(Product Product) {
        List<Movement> listaMovements = new ArrayList<>();
        String condicion = "idProductrigen=" + String.valueOf(Product.getId());
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idProductrigen", "idProductdestino"
        };
        Cursor cursor = MyDB.getDB().query("Movements", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movement c = new Movement();
                c.setId(cursor.getInt(0));
                c.setType(cursor.getInt(1));
                c.setDate(new Date(cursor.getLong(2)));
                c.setDescription(cursor.getString(3));
                c.setAmount(cursor.getFloat(4));

                // Asignamos la Product de origen
                c.setProductrigin(Product);


                // Asignamos la Product de destino
                Product a = new Product();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductDestination(a);
                } else {
                    a.setId(aux);
                    a = (Product) MyDB.getInstance(null).getProductDAO().search(a);
                    c.setProductDestination(a);
                }

                listaMovements.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovements;
    }

    public List getMovementsTipo(Product Product, int tipo) {
        List<Movement> listaMovements = new ArrayList<>();
        String condicion = "idProductrigen=" + String.valueOf(Product.getId()) + " AND tipo = " + String.valueOf(tipo);
        String[] columnas = {
                "id", "tipo", "fechaoperacion", "descripcion", "importe", "idProductrigen", "idProductdestino"
        };
        Cursor cursor = MyDB.getDB().query("Movements", columnas, condicion, null, null, null, null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Movement c = new Movement();
                c.setId(cursor.getInt(0));
                c.setType(cursor.getInt(1));
                c.setDate(new Date(cursor.getLong(2)));
                c.setDescription(cursor.getString(3));
                c.setAmount(cursor.getFloat(4));

                // Asignamos la Product de origen
                c.setProductrigin(Product);


                // Asignamos la Product de destino
                Product a = new Product();
                int aux = cursor.getInt(6);
                if (aux == -1) {
                    a.setId(-1);
                    c.setProductDestination(a);
                } else {
                    a.setId(aux);
                    a = (Product) MyDB.getInstance(null).getProductDAO().search(a);
                    c.setProductDestination(a);
                }

                listaMovements.add(c);

            } while (cursor.moveToNext());
        }
        return listaMovements;
    }
}
