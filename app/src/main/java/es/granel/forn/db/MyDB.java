package es.granel.forn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB extends SQLiteOpenHelper {


    private static SQLiteDatabase db;
    //nombre de la base de datos
    private static final String database = "MiBanco";
    //versión de la base de datos
    private static final int version = 15;
    //Instrucción SQL para crear la tabla de Clients
    private String sqlCreacionClients = "CREATE TABLE Clients ( id INTEGER PRIMARY KEY AUTOINCREMENT, nif STRING, nombre STRING, " +
            "apellidos STRING, claveSeguridad STRING, email STRING);";
    //Instruccion SQL para crear la tabla de Products
    private String sqlCreacionProducts = "CREATE TABLE Products ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, precio FLOAT, " +
            "stock INTEGER);";
    //Instruccion SQL para crear la tabla de Movements
    private String sqlCreacionMovements = "CREATE TABLE Movements ( id INTEGER PRIMARY KEY AUTOINCREMENT, tipo INTEGER, fechaoperacion LONG," +
            " descripcion STRING, importe FLOAT, idProductrigen INTEGER, idProductdestino INTEGER);";


    public static MyDB instance = null;

    private static es.granel.forn.dao.ClientDAO ClientDAO;
    private static es.granel.forn.dao.ProductDAO ProductDAO;

    public es.granel.forn.dao.ClientDAO getClientDAO() {
        return ClientDAO;
    }

    public es.granel.forn.dao.ProductDAO getProductDAO() {
        return ProductDAO;
    }

    public es.granel.forn.dao.MovementDAO getMovementDAO() {
        return MovementDAO;
    }

    private static es.granel.forn.dao.MovementDAO MovementDAO;

    public static MyDB getInstance(Context context) {
        if (instance == null) {
            instance = new MyDB(context);
            db = instance.getWritableDatabase();
            ClientDAO = new es.granel.forn.dao.ClientDAO();
            ProductDAO = new es.granel.forn.dao.ProductDAO();
            MovementDAO = new es.granel.forn.dao.MovementDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB() {
        return db;
    }

    public static void closeDB() {
        db.close();
    }

    ;

    /**
     * Constructor de clase
     */
    public MyDB(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionClients);
        db.execSQL(sqlCreacionProducts);
        db.execSQL(sqlCreacionMovements);

        insercionDatos(db);
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion);
        if (newVersion > oldVersion) {
            //elimina tabla
            db.execSQL("DROP TABLE IF EXISTS Clients");
            db.execSQL("DROP TABLE IF EXISTS Products");
            db.execSQL("DROP TABLE IF EXISTS Movements");
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionClients);
            db.execSQL(sqlCreacionProducts);
            db.execSQL(sqlCreacionMovements);

            insercionDatos(db);
            Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion);
        }
    }

    private void insercionDatos(SQLiteDatabase db) {
        // Insertamos los Clients
        db.execSQL("INSERT INTO Clients(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (0, '29211661M', 'Raul', 'Granel', '1234', 'raul.granel@blasco.es');");
        db.execSQL("INSERT INTO Clients(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (1, '11111111A', 'Filemón', 'Pí', '1234', 'filemon.pi@tia.es');");
        db.execSQL("INSERT INTO Clients(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (2, '22222222B', 'Mortadelo', 'Ibáñez', '1234', 'mortadelo.ibanez@tia.es');");
        db.execSQL("INSERT INTO Clients(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (3, '33333333C', 'Vicente', 'Mondragón', '1234', 'vicente.mondragon@tia.es');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, '44444444D', 'Ayrton', 'Senna', '1234', 'ayrton.senna@f1.es');");
        db.execSQL("INSERT INTO Clients(rowid, id, nif, nombre, apellidos, claveSeguridad, email)VALUES(null, null, 'B1111111A', 'Ibertrola', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B2222222B', 'Gas Natural', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B3333333C', 'Telefónica', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B4444444D', 'Aguas de Valencia', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B5555555E', 'Audi', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B6666666F', 'BMW', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B7777777G', 'PayPal', '-', '1234', '-');");
        db.execSQL("INSERT INTO Clients (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B8888888H', 'Ayuntamiento de Valencia', '-', '1234', '-');");

        // Insertamos las Products
        db.execSQL("INSERT INTO Products (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan de pueblo', 0.9, 20);");
        db.execSQL("INSERT INTO Products (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan clasico', 0.8, 40);");
        db.execSQL("INSERT INTO Products (rowid, id, nombre, precio, stock) VALUES (null, null, 'pan integral', 0.85, 12);");
        db.execSQL("INSERT INTO Products (rowid, id, nombre, precio, stock) VALUES (null, null, 'croisant', 1.2, 15);");
        db.execSQL("INSERT INTO Products (rowid, id, nombre, precio, stock) VALUES (null, null, 'magdalena', 1.1, 18);");

        // Insertamos los Movements
        //db.execSQL("INSERT INTO Movements (rowid, id, tipo, fechaoperacion, descripcion, importe, idProductrigen, idProductdestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -73.87, 1, 5);");
    }
}
