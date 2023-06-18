package es.granel.forn.db;

import android.content.Context;

import java.util.List;

import es.granel.forn.model.Client;
import es.granel.forn.model.Movement;
import es.granel.forn.model.Product;

public class MyDBOperational {

    private MyDB myDB;
    private static MyDBOperational instance = null;

    protected MyDBOperational(Context context) {
        myDB = MyDB.getInstance(context);
    }

    //***************************************
    // Interfaz publica de la API del banco
    //***************************************

    // Constructor del banco. Obtiene una instancia del mismo para operar
    public static MyDBOperational getInstance(Context context) {
        if (instance == null) {
            instance = new MyDBOperational(context);
        }
        return instance;
    }

    // Operacion Login: Verifica que el Client existe y que su contraseña es correcta. Recibira un Client
    // que solo contendrá el nif y la password.
    public Client login(Client c) {
        Client aux = (Client) myDB.getClientDAO().search(c);
        if (aux == null) {
            return null;
        } else if (aux.getPassword().equals(c.getPassword())) {
            return aux;
        } else {
            return null;
        }
    }

    // Operacion changePassword: Cambia la password del Client. Recibirá el Client de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    public int changePassword(Client c) {
        int resultado = myDB.getClientDAO().update(c);
        if (resultado == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    // Operacion getProducts: Obtiene un List de las Products de un Client que recibe como parámetro
    public List<Product> getProducts(Client c) {
        return myDB.getProductDAO().getProducts(c);
    }

    // Operacion getMovements: Obtiene un List de los Movements de una Product que recibe como parámetro
    public List<Movement> getMovements(Product c) {
        return myDB.getMovementDAO().getMovements(c);
    }

    /* Operacion transferencia: Desde una Product hace transferencia a otra Product, siempre que en la Product origen haya dinero disponible.

       Restricciones:

         - La comprobacion de la existencia de la Product destino se realizará dentro del método. La Product de origen existe por defecto, ya que el alumno ha de poder seleccionarla.
         - En caso de no existir la Product destino se devolvera como entero un 1.
         - La fecha de la operación será la fecha del sistema. Recordar que es almacenada como un long.
         - No se permitirá realizar una transferencia si la Product se queda en negativo. En este caso se devolvera como entero un 2.
         - Solo se permiten Movement en las Products locales al banco, por lo que ambas Products deben existir.
         - La operación se ha de ver reflejada en las dos Products: el descuento en una y el ingreso en otra.
         - El campo tipo en la tabla de Movements indica como es el Movement. 0 indica que es un descuento, 1 indica que es un ingreso y 2 indica que es un reintegro por un cajero.
         - El Movement que viene como parametro en el metodo, que viene en la variable MovementTransferencia ha de ser de tipo 0.
         - Si la operacion es correcta se devuelve un 0
    */
    public int transferencia(Movement MovementTransferencia) {
        return 0;
    }

    // Operacion getMovementsTipo: Obtiene un List de los Movements de un tipo específico de una Product que recibe como parámetro
    public List<Movement> getMovementsTipo(Product c, int tipo) {
        return myDB.getMovementDAO().getMovementsTipo(c, tipo);
    }
}
